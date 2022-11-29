package multiCalculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Scientific_Cal {

	public JFrame frame;
	private JTextField Input_Field;
	private JTextField Ans_Field;
	private JButton Backbtn;
	private JButton Nxtbtn;
	private JButton btn_RAD;
	private JButton btn_DEG;
	
	private String eq="";
	private String ans="";
	private String on_off="off";
	private String Rad_Deg="Deg";
	private String bracket="close";
	
	private Boolean find=true;
	
	private int count=0;
	private double result=0;
	
	Stack<Double> num;
	Stack<Double> operation;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private static String[] page_arr=new String[100];
	private int index=0;
	
	private static final DecimalFormat df=new DecimalFormat("0.000");
	
	private boolean isNumeric(String str) {
		if(str==null)
			return false;
 		try {
			Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	private Double operation1(String opr, Double num) {
		switch(opr) {
			case "sin":{
				if(Rad_Deg=="Deg")
					return Double.parseDouble(df.format(Math.sin(Math.toRadians(num))));
				else
					return Double.parseDouble(df.format(Math.sin(num)));
			}
			case "sin^(-1)":{
				if(Rad_Deg=="Deg")
					return Double.parseDouble(df.format(Math.asin(Math.toRadians(num))));
				else
					return Double.parseDouble(df.format(Math.asin(num)));
			}
			case "cos":{
				if(Rad_Deg=="Deg")
					if(num==90)
						return (double) 0;
					else
						return Double.parseDouble(df.format(Math.cos(Math.toRadians(num))));
				else
					return Double.parseDouble(df.format(Math.cos(num)));
			}
			case "cos^(-1)":{
				if(Rad_Deg=="Deg")
					return Double.parseDouble(df.format(Math.acos(Math.toRadians(num))));
				else
					return Double.parseDouble(df.format(Math.acos(num)));
			}
			case "tan":{
				if(Rad_Deg=="Deg")
					if(num==90)
						return num/0;
					else
						return Double.parseDouble(df.format(Math.tan(Math.toRadians(num))));
				else
					return Double.parseDouble(df.format(Math.tan(num)));
			}
			case "tan^(-1)":{
				if(Rad_Deg=="Deg")
					return Double.parseDouble(df.format(Math.atan(Math.toRadians(num))));
				else
					return Double.parseDouble(df.format(Math.atan(num)));
			}
			case "ln":
				return Math.log(num);
			case "exp":
				return Math.exp(num);
			case "log":
				return Math.log(num)/Math.log(10);
			case "!":{
				double fact=1;
				for(int i=1; i<=num; i++)
					fact*=i;
				return fact;
			}
			case "√":
				return Math.sqrt(num);
			default:
				return 0.0;
		}
	}
	
	private Double operation2(String opr, Double num2, Double num1) {
		switch(opr) {
			case "/":
				return num1/num2;
			case "*":
				return num1*num2;
			case "-":
				return num1-num2;
			case "+":
				return num1+num2;
			case "^":
				return Math.pow(num1,num2);
			case "%":
				return (num1/100)*num2;
			default:
				return 0.0;
		}
	}
	
	// Precedence function of operator
	private int precedence(String opr) {
		if(opr.equals("+") || opr.equals("-"))
			return 1;
		else if(opr.equals("*") || opr.equals("/") || opr.equals("%"))
			return 2;
		else if(opr.equals("sin") || opr.equals("cos") || opr.equals("tan") || opr.equals("log") || opr.equals("ln"))
			return 3;
		else if(opr.equals("^"))
			return 4;
		else if(opr.equals("√") || opr.equals("!"))
			return 5;
		else
			return 0;
	}
	
	// Equation solver function using stack
	private Double EqSolver(String exp) {
		String[] elements=exp.trim().split(" ",0);
		
		LinkedList<String> list=new LinkedList<String>();
		Stack<Double> num=new Stack<Double>();
		Stack<String> operation = new Stack<String>();
		
		for(int i=0; i<elements.length; i++) {
			if(elements[i].equals("√")) {
				if(elements[i+2].equals("-")) {
					JOptionPane.showMessageDialog(null, "Square root of negative value is not working!!!");
					find=false;
					eq=eq.substring(0,eq.length()-2);
					Input_Field.setText(eq);
					break;
				}
			}
		}
		
		if(find) {
			// Storing String array elements to linked list
			for(int i=0; i<elements.length; i++)
				list.add(elements[i]);
			
			for(int i=0; i<list.size(); i++) {
				// Ignoring null value or whitespace
				if(list.get(i).equals("") || list.get(i).equals(" "))
					continue;
				
				if(list.get(i).equals("%")) {
					if(i+1==list.size())
						list.add("1");
					if(!isNumeric(list.get(i+1)))
						list.add(i+1, "1");
				}
				
				// Checking is it number
				if(isNumeric(list.get(i)))
					num.push(Double.parseDouble(list.get(i)));
				else if(list.get(i).equals("("))
					operation.push(list.get(i));
				else if(list.get(i).equals(")")) {
					// Continue till the inside brackets are solved
					while(!(operation.peek().equals("("))) {
						if(operation.peek().equals("/") || operation.peek().equals("*") || operation.peek().equals("-") || operation.peek().equals("+") || operation.peek().equals("%") || operation.peek().equals("^"))
							num.push(operation2(operation.pop(),num.pop(),num.pop()));
						else
							num.push(operation1(operation.pop(),num.pop()));
					}
					operation.pop();
				}
				else {	// if element is an operator
					while(!operation.empty() && (precedence(list.get(i))<=precedence(operation.peek()))) {
						if(operation.peek().equals("/") || operation.peek().equals("*") || operation.peek().equals("-") || operation.peek().equals("+") || operation.peek().equals("%") || operation.peek().equals("^"))
							num.push(operation2(operation.pop(),num.pop(),num.pop()));
						else
							num.push(operation1(operation.pop(),num.pop()));
					}
					operation.push(list.get(i));
				}
			}
			while(!operation.empty()) {
				if(operation.peek().equals("/") || operation.peek().equals("*") || operation.peek().equals("-") || operation.peek().equals("+") || operation.peek().equals("%") || operation.peek().equals("^"))
					num.push(operation2(operation.pop(),num.pop(),num.pop()));
				else
					num.push(operation1(operation.pop(),num.pop()));
			}
			return num.pop();
		}
		return null;
	}

	// Result function
	private void result() {
		if(!Input_Field.getText().equals("")) {
			if(bracket.equals("open"))
				result=EqSolver(Input_Field.getText()+" ) ");
			else {
				if(eq.charAt(0)=='-')
					result=EqSolver("0 "+Input_Field.getText());
				else
					result=EqSolver(Input_Field.getText());
			}
			
			ans=String.valueOf(result);
			Ans_Field.setText(ans);
		}
		else
			Ans_Field.setText(null);
	}
	
	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scientific_Cal window = new Scientific_Cal(false, page_arr, -1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public Scientific_Cal(Boolean prev_nxt_clicked, String[] elements, int indx) {
		initialize();
		prev_next obj=new prev_next(elements);
		page_arr=obj.add(prev_nxt_clicked, "scientific", indx);
		if(prev_nxt_clicked)
			index=indx;
		else
			index=obj.curr_index();
		
		Boolean prev_exist=obj.prevExist(index);
		if(prev_exist)
			Backbtn.setEnabled(true);
		else
			Backbtn.setEnabled(false);
		
		Boolean next_exist=obj.nextExist(index);
		if(next_exist)
			Nxtbtn.setEnabled(true);
		else
			Nxtbtn.setEnabled(false);
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(0, 0, 51));
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 600, 381);
		frame.setTitle("Scientific Calculator");
		frame.setLocationRelativeTo(null); // For setting frame center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Input_Field = new JTextField();
		Input_Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					Input_Field.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					Input_Field.setBackground(new Color(0, 0, 102));
			}
		});
		Input_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Input_Field.setBorder(null);
		Input_Field.setForeground(new Color(255, 255, 255));
		Input_Field.setBackground(new Color(0, 0, 80));
		Input_Field.setEditable(false);
		Input_Field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Input_Field.setColumns(10);
		
		Ans_Field = new JTextField();
		Ans_Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					Ans_Field.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					Ans_Field.setBackground(new Color(0, 0, 102));
			}
		});
		Ans_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Ans_Field.setBorder(null);
		Ans_Field.setForeground(new Color(255, 255, 255));
		Ans_Field.setBackground(new Color(0, 0, 80));
		Ans_Field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Ans_Field.setEditable(false);
		Ans_Field.setColumns(10);
		
		JButton btn7 = new JButton("7");
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn7.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn7.setBackground(new Color(0, 0, 102));
			}
		});
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn7.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn7.setFocusable(false);
		btn7.setBorder(null);
		btn7.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn7.setBackground(new Color(0, 0, 102));
		btn7.setForeground(new Color(0, 204, 255));
		btn7.setEnabled(false);
		btn7.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn8 = new JButton("8");
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn8.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn8.setBackground(new Color(0, 0, 102));
			}
		});
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn8.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn8.setFocusable(false);
		btn8.setBorder(null);
		btn8.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn8.setBackground(new Color(0, 0, 102));
		btn8.setForeground(new Color(0, 204, 255));
		btn8.setEnabled(false);
		btn8.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn9 = new JButton("9");
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn9.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn9.setBackground(new Color(0, 0, 102));
			}
		});
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn9.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn9.setFocusable(false);
		btn9.setBorder(null);
		btn9.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn9.setBackground(new Color(0, 0, 102));
		btn9.setForeground(new Color(0, 204, 255));
		btn9.setEnabled(false);
		btn9.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn4 = new JButton("4");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn4.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn4.setBackground(new Color(0, 0, 102));
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn4.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn4.setFocusable(false);
		btn4.setBorder(null);
		btn4.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn4.setBackground(new Color(0, 0, 102));
		btn4.setForeground(new Color(0, 204, 255));
		btn4.setEnabled(false);
		btn4.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn5 = new JButton("5");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn5.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn5.setBackground(new Color(0, 0, 102));
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn5.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn5.setFocusable(false);
		btn5.setBorder(null);
		btn5.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn5.setBackground(new Color(0, 0, 102));
		btn5.setForeground(new Color(0, 204, 255));
		btn5.setEnabled(false);
		btn5.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn6 = new JButton("6");
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn6.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn6.setBackground(new Color(0, 0, 102));
			}
		});
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn6.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn6.setFocusable(false);
		btn6.setBorder(null);
		btn6.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn6.setBackground(new Color(0, 0, 102));
		btn6.setForeground(new Color(0, 204, 255));
		btn6.setEnabled(false);
		btn6.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn1 = new JButton("1");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn1.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn1.setBackground(new Color(0, 0, 102));
			}
		});
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn1.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn1.setFocusable(false);
		btn1.setBorder(null);
		btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn1.setBackground(new Color(0, 0, 102));
		btn1.setForeground(new Color(0, 204, 255));
		btn1.setEnabled(false);
		btn1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn2 = new JButton("2");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn2.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn2.setBackground(new Color(0, 0, 102));
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn2.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn2.setFocusable(false);
		btn2.setBorder(null);
		btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn2.setBackground(new Color(0, 0, 102));
		btn2.setForeground(new Color(0, 204, 255));
		btn2.setEnabled(false);
		btn2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn3 = new JButton("3");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn3.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn3.setBackground(new Color(0, 0, 102));
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn3.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn3.setFocusable(false);
		btn3.setBorder(null);
		btn3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn3.setBackground(new Color(0, 0, 102));
		btn3.setForeground(new Color(0, 204, 255));
		btn3.setEnabled(false);
		btn3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_dot = new JButton(".");
		btn_dot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_dot.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_dot.setBackground(new Color(0, 0, 102));
			}
		});
		btn_dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn_dot.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_dot.setFocusable(false);
		btn_dot.setBorder(null);
		btn_dot.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_dot.setBackground(new Color(0, 0, 102));
		btn_dot.setForeground(new Color(0, 204, 255));
		btn_dot.setEnabled(false);
		btn_dot.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn0 = new JButton("0");
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn0.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn0.setBackground(new Color(0, 0, 102));
			}
		});
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn0.getText();
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn0.setFocusable(false);
		btn0.setBorder(null);
		btn0.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn0.setBackground(new Color(0, 0, 102));
		btn0.setForeground(new Color(0, 204, 255));
		btn0.setEnabled(false);
		btn0.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_equal = new JButton("=");
		btn_equal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_equal.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_equal.setBackground(new Color(0, 0, 204));
			}
		});
		btn_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result();
				find=true;
				eq=Ans_Field.getText();
				Input_Field.setText(eq);
				
				Ans_Field.setText(null);
			}
		});
		btn_equal.setFocusable(false);
		btn_equal.setBorder(null);
		btn_equal.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_equal.setBackground(new Color(0, 0, 204));
		btn_equal.setForeground(new Color(0, 204, 255));
		btn_equal.setEnabled(false);
		btn_equal.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btnBack.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btnBack.setBackground(new Color(0, 0, 255));
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eq.isEmpty()) {
					if(eq.charAt(eq.trim().length()-1)=='(')
						bracket="close";
					else if(eq.charAt(eq.trim().length()-1)==')')
						bracket="open";
					eq=eq.substring(0,eq.trim().length()-1);
					Input_Field.setText(eq);
					find=true;
					result();
					if(eq.isEmpty()) {
						btn_RAD.setForeground(new Color(0, 204, 255));
						btn_DEG.setForeground(new Color(0, 204, 255));
					}
				}
			}
		});
		btnBack.setFocusable(false);
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBack.setBorder(null);
		btnBack.setBackground(new Color(0, 0, 255));
		btnBack.setForeground(new Color(0, 204, 255));
		btnBack.setEnabled(false);
		btnBack.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btnAC = new JButton("AC");
		btnAC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btnAC.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btnAC.setBackground(new Color(0, 0, 255));
			}
		});
		btnAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq=ans="";
				result=0;
				find=true;
				bracket="close";
				
				Input_Field.setText(eq);
				Ans_Field.setText(ans);
				
				btn_RAD.setForeground(new Color(0, 204, 255));
				btn_DEG.setForeground(new Color(0, 204, 255));
			}
		});
		btnAC.setFocusable(false);
		btnAC.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAC.setBorder(null);
		btnAC.setBackground(new Color(0, 0, 255));
		btnAC.setForeground(new Color(0, 204, 255));
		btnAC.setEnabled(false);
		btnAC.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_divide = new JButton("/");
		btn_divide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_divide.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_divide.setBackground(new Color(0, 0, 204));
			}
		});
		btn_divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+="/ ";
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_divide.setFocusable(false);
		btn_divide.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_divide.setBorder(null);
		btn_divide.setBackground(new Color(0, 0, 204));
		btn_divide.setForeground(new Color(0, 204, 255));
		btn_divide.setEnabled(false);
		btn_divide.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_multiply = new JButton("*");
		btn_multiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_multiply.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_multiply.setBackground(new Color(0, 0, 204));
			}
		});
		btn_multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+="* ";
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_multiply.setFocusable(false);
		btn_multiply.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_multiply.setBorder(null);
		btn_multiply.setBackground(new Color(0, 0, 204));
		btn_multiply.setForeground(new Color(0, 204, 255));
		btn_multiply.setEnabled(false);
		btn_multiply.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_minus = new JButton("-");
		btn_minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_minus.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_minus.setBackground(new Color(0, 0, 204));
			}
		});
		btn_minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eq.equals("") && !(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				
				eq+="- ";
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_minus.setFocusable(false);
		btn_minus.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_minus.setBorder(null);
		btn_minus.setBackground(new Color(0, 0, 204));
		btn_minus.setForeground(new Color(0, 204, 255));
		btn_minus.setEnabled(false);
		btn_minus.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_add = new JButton("+");
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_add.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_add.setBackground(new Color(0, 0, 204));
			}
		});
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+="+ ";
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_add.setFocusable(false);
		btn_add.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_add.setBorder(null);
		btn_add.setBackground(new Color(0, 0, 204));
		btn_add.setForeground(new Color(0, 204, 255));
		btn_add.setEnabled(false);
		btn_add.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_percent = new JButton("%");
		btn_percent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_percent.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_percent.setBackground(new Color(0, 0, 204));
			}
		});
		btn_percent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+="% ";
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_percent.setFocusable(false);
		btn_percent.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_percent.setBorder(null);
		btn_percent.setBackground(new Color(0, 0, 204));
		btn_percent.setForeground(new Color(0, 204, 255));
		btn_percent.setEnabled(false);
		btn_percent.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_INV = new JButton("INV");
		btn_INV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_INV.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_INV.setBackground(new Color(0, 0, 153));
			}
		});
		btn_INV.setFocusable(false);
		btn_INV.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_INV.setBorder(null);
		btn_INV.setBackground(new Color(0, 0, 153));
		btn_INV.setForeground(new Color(0, 204, 255));
		btn_INV.setEnabled(false);
		btn_INV.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		btn_RAD = new JButton("RAD");
		btn_RAD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_RAD.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_RAD.setBackground(new Color(0, 0, 153));
			}
		});
		btn_RAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rad_Deg="Rad";
				find=true;
				result();
				
				btn_RAD.setForeground(new Color(255, 255, 255));
				btn_DEG.setForeground(new Color(0, 204, 255));
			}
		});
		btn_RAD.setFocusable(false);
		btn_RAD.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_RAD.setBorder(null);
		btn_RAD.setBackground(new Color(0, 0, 153));
		btn_RAD.setForeground(new Color(0, 204, 255));
		btn_RAD.setEnabled(false);
		btn_RAD.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		btn_DEG = new JButton("DEG");
		btn_DEG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_DEG.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_DEG.setBackground(new Color(0, 0, 153));
			}
		});
		btn_DEG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rad_Deg="Deg";
				find=true;
				result();
				
				btn_DEG.setForeground(new Color(255, 255, 255));
				btn_RAD.setForeground(new Color(0, 204, 255));
			}
		});
		btn_DEG.setFocusable(false);
		btn_DEG.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_DEG.setBorder(null);
		btn_DEG.setBackground(new Color(0, 0, 153));
		btn_DEG.setForeground(new Color(0, 204, 255));
		btn_DEG.setEnabled(false);
		btn_DEG.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_sin = new JButton("sin");
		btn_sin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_sin.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_sin.setBackground(new Color(0, 0, 153));
			}
		});
		btn_sin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn_sin.getText()+" ( ";
				Input_Field.setText(eq);
				find=true;
				Rad_Deg="Deg";
				bracket="open";
				
				btn_RAD.setForeground(new Color(0, 204, 255));
				btn_DEG.setForeground(new Color(255, 255, 255));
			}
		});
		btn_sin.setFocusable(false);
		btn_sin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_sin.setBorder(null);
		btn_sin.setBackground(new Color(0, 0, 153));
		btn_sin.setForeground(new Color(0, 204, 255));
		btn_sin.setEnabled(false);
		btn_sin.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_cos = new JButton("cos");
		btn_cos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_cos.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_cos.setBackground(new Color(0, 0, 153));
			}
		});
		btn_cos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn_cos.getText()+" ( ";
				Input_Field.setText(eq);
				find=true;
				Rad_Deg="Deg";
				bracket="open";
				
				btn_RAD.setForeground(new Color(0, 204, 255));
				btn_DEG.setForeground(new Color(255, 255, 255));
			}
		});
		btn_cos.setFocusable(false);
		btn_cos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_cos.setBorder(null);
		btn_cos.setBackground(new Color(0, 0, 153));
		btn_cos.setForeground(new Color(0, 204, 255));
		btn_cos.setEnabled(false);
		btn_cos.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_tan = new JButton("tan");
		btn_tan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_tan.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_tan.setBackground(new Color(0, 0, 153));
			}
		});
		btn_tan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=btn_tan.getText()+" ( ";
				Input_Field.setText(eq);
				find=true;
				Rad_Deg="Deg";
				bracket="open";
				
				btn_RAD.setForeground(new Color(0, 204, 255));
				btn_DEG.setForeground(new Color(255, 255, 255));
			}
		});
		btn_tan.setFocusable(false);
		btn_tan.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_tan.setBorder(null);
		btn_tan.setBackground(new Color(0, 0, 153));
		btn_tan.setForeground(new Color(0, 204, 255));
		btn_tan.setEnabled(false);
		btn_tan.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_ln = new JButton("ln");
		btn_ln.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_ln.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_ln.setBackground(new Color(0, 0, 153));
			}
		});
		btn_ln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn_ln.getText().equals("e^x")) {
					if(!eq.isEmpty())
						if(!(eq.charAt(eq.length()-1)==(' ')))
							eq+=" ";
					eq+="exp ( ";
				}
				else
					eq+=btn_ln.getText()+" ( ";
				
				bracket="open";
				find=true;
				Input_Field.setText(eq);
			}
		});
		btn_ln.setFocusable(false);
		btn_ln.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_ln.setBorder(null);
		btn_ln.setBackground(new Color(0, 0, 153));
		btn_ln.setForeground(new Color(0, 204, 255));
		btn_ln.setEnabled(false);
		btn_ln.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_log = new JButton("log");
		btn_log.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_log.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_log.setBackground(new Color(0, 0, 153));
			}
		});
		btn_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn_log.getText().equals("10^x")) {
					if(!eq.isEmpty())
						if((isNumeric(Character.toString(eq.charAt(eq.length()-1)))))
							eq+=" * ";
					eq+="10 ^ ";
				}
				else {
					eq+=btn_log.getText()+" ( ";
					bracket="open";
				}
					
				Input_Field.setText(eq);
				find=true;
			}
		});
		btn_log.setFocusable(false);
		btn_log.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_log.setBorder(null);
		btn_log.setBackground(new Color(0, 0, 153));
		btn_log.setForeground(new Color(0, 204, 255));
		btn_log.setEnabled(false);
		btn_log.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_fact = new JButton("!");
		btn_fact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_fact.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_fact.setBackground(new Color(0, 0, 153));
			}
		});
		btn_fact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+="! ";
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_fact.setFocusable(false);
		btn_fact.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_fact.setBorder(null);
		btn_fact.setBackground(new Color(0, 0, 153));
		btn_fact.setForeground(new Color(0, 204, 255));
		btn_fact.setEnabled(false);
		btn_fact.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_pi = new JButton("π");
		btn_pi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_pi.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_pi.setBackground(new Color(0, 0, 153));
			}
		});
		btn_pi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=String.valueOf(Math.PI);
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_pi.setFocusable(false);
		btn_pi.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_pi.setBorder(null);
		btn_pi.setBackground(new Color(0, 0, 153));
		btn_pi.setForeground(new Color(0, 204, 255));
		btn_pi.setEnabled(false);
		btn_pi.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_Eulers = new JButton("e");
		btn_Eulers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_Eulers.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_Eulers.setBackground(new Color(0, 0, 153));
			}
		});
		btn_Eulers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+=String.valueOf(Math.E);
				Input_Field.setText(eq);
				find=true;
				result();
			}
		});
		btn_Eulers.setFocusable(false);
		btn_Eulers.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_Eulers.setBorder(null);
		btn_Eulers.setBackground(new Color(0, 0, 153));
		btn_Eulers.setForeground(new Color(0, 204, 255));
		btn_Eulers.setEnabled(false);
		btn_Eulers.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_exponential = new JButton("^");
		btn_exponential.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_exponential.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_exponential.setBackground(new Color(0, 0, 153));
			}
		});
		btn_exponential.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+="^ ";
				Input_Field.setText(eq);
				find=true;
			}
		});
		btn_exponential.setFocusable(false);
		btn_exponential.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_exponential.setBorder(null);
		btn_exponential.setBackground(new Color(0, 0, 153));
		btn_exponential.setForeground(new Color(0, 204, 255));
		btn_exponential.setEnabled(false);
		btn_exponential.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_open_bracket = new JButton("(");
		btn_open_bracket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_open_bracket.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_open_bracket.setBackground(new Color(0, 0, 153));
			}
		});
		btn_open_bracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq+="( ";
				Input_Field.setText(eq);
				find=true;
				bracket="open";
			}
		});
		btn_open_bracket.setFocusable(false);
		btn_open_bracket.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_open_bracket.setBorder(null);
		btn_open_bracket.setBackground(new Color(0, 0, 153));
		btn_open_bracket.setForeground(new Color(0, 204, 255));
		btn_open_bracket.setEnabled(false);
		btn_open_bracket.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_close_bracket = new JButton(")");
		btn_close_bracket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_close_bracket.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_close_bracket.setBackground(new Color(0, 0, 153));
			}
		});
		btn_close_bracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(eq.charAt(eq.length()-1)==(' ')))
					eq+=" ";
				eq+=") ";
				Input_Field.setText(eq);
				bracket="close";
				find=true;
				
				result();
			}
		});
		btn_close_bracket.setFocusable(false);
		btn_close_bracket.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_close_bracket.setBorder(null);
		btn_close_bracket.setBackground(new Color(0, 0, 153));
		btn_close_bracket.setForeground(new Color(0, 204, 255));
		btn_close_bracket.setEnabled(false);
		btn_close_bracket.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JButton btn_sqr_root = new JButton("√");
		btn_sqr_root.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(on_off=="on")
					btn_sqr_root.setBackground(new Color(0, 0, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(on_off=="on")
					btn_sqr_root.setBackground(new Color(0, 0, 153));
			}
		});
		btn_sqr_root.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn_sqr_root.getText().equals("x^2")) {
					if(!eq.isEmpty())
						if(!(eq.charAt(eq.length()-1)==(' ')))
							eq+=" ";
					eq+="^ 2 ";
				}
				else {
					eq+="√ ( ";
					bracket="open";
				}	
				Input_Field.setText(eq);
				find=true;
			}
		});
		btn_sqr_root.setFocusable(false);
		btn_sqr_root.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_sqr_root.setBorder(null);
		btn_sqr_root.setBackground(new Color(0, 0, 153));
		btn_sqr_root.setForeground(new Color(0, 204, 255));
		btn_sqr_root.setEnabled(false);
		btn_sqr_root.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		btn_INV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count==0) {
					btn_sin.setText("sin^(-1)");
					btn_cos.setText("cos^(-1)");
					btn_tan.setText("tan^(-1)");
					btn_ln.setText("e^x");
					btn_log.setText("10^x");
					btn_sqr_root.setText("x^2");
					count++;
				}
				else {
					btn_sin.setText("sin");
					btn_cos.setText("cos");
					btn_tan.setText("tan");
					btn_ln.setText("ln");
					btn_log.setText("log");
					btn_sqr_root.setText("√");
					count--;
				}
			}
		});
		
		JRadioButton ONbtn = new JRadioButton("ON");
		JRadioButton OFFbtn = new JRadioButton("OFF");
		
		ONbtn.setFocusable(false);
		ONbtn.setHorizontalAlignment(SwingConstants.CENTER);
		ONbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		ONbtn.setForeground(new Color(0, 102, 255));
		ONbtn.setBackground(new Color(0, 0, 51));
		ONbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				on_off="on";
				
				Input_Field.setText("");
				Ans_Field.setText("");
				
				ONbtn.setForeground(new Color(0, 204, 255));
				OFFbtn.setForeground(new Color(0, 102, 255));
				Input_Field.setBackground(new Color(0, 0, 102));
				Ans_Field.setBackground(new Color(0, 0, 102));
				btn_RAD.setForeground(new Color(0, 204, 255));
				btn_DEG.setForeground(new Color(0, 204, 255));
				
				btn7.setEnabled(true);
				btn8.setEnabled(true);
				btn9.setEnabled(true);
				btn4.setEnabled(true);
				btn5.setEnabled(true);
				btn6.setEnabled(true);
				btn1.setEnabled(true);
				btn2.setEnabled(true);
				btn3.setEnabled(true);
				btn0.setEnabled(true);
				btn_dot.setEnabled(true);
				btn_equal.setEnabled(true);
				btnBack.setEnabled(true);
				btnAC.setEnabled(true);
				btn_divide.setEnabled(true);
				btn_multiply.setEnabled(true);
				btn_minus.setEnabled(true);
				btn_add.setEnabled(true);
				btn_percent.setEnabled(true);
				btn_INV.setEnabled(true);
				btn_RAD.setEnabled(true);
				btn_DEG.setEnabled(true);
				btn_sin.setEnabled(true);
				btn_cos.setEnabled(true);
				btn_tan.setEnabled(true);
				btn_ln.setEnabled(true);
				btn_log.setEnabled(true);
				btn_fact.setEnabled(true);
				btn_pi.setEnabled(true);
				btn_Eulers.setEnabled(true);
				btn_exponential.setEnabled(true);
				btn_open_bracket.setEnabled(true);
				btn_close_bracket.setEnabled(true);
				btn_sqr_root.setEnabled(true);
			}
		});
		buttonGroup.add(ONbtn);
		ONbtn.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		OFFbtn.setFocusable(false);
		OFFbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		OFFbtn.setForeground(new Color(0, 204, 255));
		OFFbtn.setBackground(new Color(0, 0, 51));
		OFFbtn.setSelected(true);
		OFFbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				on_off="off";
				
				Input_Field.setText("");
				Ans_Field.setText("");
				
				ONbtn.setForeground(new Color(0, 102, 255));
				OFFbtn.setForeground(new Color(0, 204, 255));
				Input_Field.setBackground(new Color(0, 0, 80));
				Ans_Field.setBackground(new Color(0, 0, 80));
				
				btn7.setEnabled(false);
				btn8.setEnabled(false);
				btn9.setEnabled(false);
				btn4.setEnabled(false);
				btn5.setEnabled(false);
				btn6.setEnabled(false);
				btn1.setEnabled(false);
				btn2.setEnabled(false);
				btn3.setEnabled(false);
				btn0.setEnabled(false);
				btn_dot.setEnabled(false);
				btn_equal.setEnabled(false);
				btnBack.setEnabled(false);
				btnAC.setEnabled(false);
				btn_divide.setEnabled(false);
				btn_multiply.setEnabled(false);
				btn_minus.setEnabled(false);
				btn_add.setEnabled(false);
				btn_percent.setEnabled(false);
				btn_INV.setEnabled(false);
				btn_RAD.setEnabled(false);
				btn_DEG.setEnabled(false);
				btn_sin.setEnabled(false);
				btn_cos.setEnabled(false);
				btn_tan.setEnabled(false);
				btn_ln.setEnabled(false);
				btn_log.setEnabled(false);
				btn_fact.setEnabled(false);
				btn_pi.setEnabled(false);
				btn_Eulers.setEnabled(false);
				btn_exponential.setEnabled(false);
				btn_open_bracket.setEnabled(false);
				btn_close_bracket.setEnabled(false);
				btn_sqr_root.setEnabled(false);
			}
		});
		buttonGroup.add(OFFbtn);
		OFFbtn.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ONbtn, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(OFFbtn, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
							.addGap(463))
						.addComponent(Ans_Field, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
						.addComponent(Input_Field, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btn4, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn5, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn6, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btn7, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn8, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn9, GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
									.addGap(5)
									.addComponent(btnAC, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
										.addComponent(btn_dot, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btn0, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
										.addComponent(btn2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btn_equal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addComponent(btn3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE))))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btn_divide, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
								.addComponent(btn_multiply, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
								.addComponent(btn_minus, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
								.addComponent(btn_add, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
								.addComponent(btn_percent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_pi, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(btn_ln, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(btn_sin, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(btn_INV, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(btn_open_bracket, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btn_log, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btn_fact, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btn_cos, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btn_tan, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btn_RAD, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btn_DEG, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btn_close_bracket, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
										.addComponent(btn_Eulers, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btn_sqr_root, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
										.addComponent(btn_exponential, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))))))
					.addGap(11))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(Input_Field, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Ans_Field, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(ONbtn)
						.addComponent(OFFbtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_divide, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn8, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn9, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn7, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_INV, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_RAD, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_DEG, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_multiply, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn4, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn5, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn6, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_sin, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_cos, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_tan, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_minus, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn3, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_ln, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_log, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_fact, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn0, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_equal, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_exponential, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_Eulers, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_pi, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_add, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_dot, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_sqr_root, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_close_bracket, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_open_bracket, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btn_percent, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btnAC, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addGap(20))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFocusable(false);
		menuBar.setBackground(new Color(0, 0, 64));
		frame.setJMenuBar(menuBar);
		
		Backbtn = new JButton("  🢀 ");
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev_next obj=new prev_next(page_arr);
				obj.prevbtn(index);
				frame.dispose();
			}
		});
		Backbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Backbtn.setForeground(new Color(0, 204, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Backbtn.setForeground(new Color(255, 255, 255));;
			}
		});
		Backbtn.setOpaque(true);
		Backbtn.setFocusable(false);
		Backbtn.setBorder(null);
		Backbtn.setForeground(new Color(255, 255, 255));
		Backbtn.setBackground(new Color(0, 0, 64));
		menuBar.add(Backbtn);
		
		Nxtbtn = new JButton(" 🢂 ");
		Nxtbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev_next obj=new prev_next(page_arr);
            	obj.nextbtn(index);
				frame.dispose();
			}
		});
		Nxtbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Nxtbtn.setForeground(new Color(0, 204, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Nxtbtn.setForeground(new Color(255, 255, 255));;
			}
		});
		Nxtbtn.setOpaque(true);
		Nxtbtn.setFocusable(false);
		Nxtbtn.setBorder(null);
		Nxtbtn.setForeground(new Color(255, 255, 255));
		Nxtbtn.setBackground(new Color(0, 0, 64));
		menuBar.add(Nxtbtn);
		
		JButton HomeBtn = new JButton(" 🏠  ");
		HomeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Multi_Calculator Cal=new Multi_Calculator(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		HomeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				HomeBtn.setForeground(new Color(0, 204, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				HomeBtn.setForeground(new Color(255, 255, 255));;
			}
		});
		HomeBtn.setOpaque(true);
		HomeBtn.setFocusable(false);
		HomeBtn.setBorder(null);
		HomeBtn.setForeground(new Color(255, 255, 255));
		HomeBtn.setBackground(new Color(0, 0, 64));
		menuBar.add(HomeBtn);
		
		JMenu FileMenu = new JMenu("File");
		FileMenu.setOpaque(true);
		FileMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				FileMenu.setForeground(new Color(0, 204, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				FileMenu.setForeground(new Color(255, 255, 255));;
			}
		});
		FileMenu.setFont(new Font("Rockwell", Font.PLAIN, 14));
		FileMenu.setFocusable(false);
		FileMenu.setBorder(null);
		FileMenu.setForeground(new Color(255, 255, 255));
		FileMenu.setBackground(new Color(0, 0, 64));
		menuBar.add(FileMenu);
		
		JSeparator separator = new JSeparator();
		FileMenu.add(separator);
		
		JMenuItem CalMenu = new JMenuItem("Calculator");
		CalMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator Cal=new Calculator(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		CalMenu.setOpaque(true);
		CalMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		CalMenu.setHorizontalAlignment(SwingConstants.CENTER);
		CalMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		CalMenu.setForeground(new Color(255, 255, 255));
		CalMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(CalMenu);
		
		JSeparator separator_1 = new JSeparator();
		FileMenu.add(separator_1);
		
		JMenuItem StdMenu = new JMenuItem("Standard");
		StdMenu.setOpaque(true);
		StdMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Standard_Cal Cal=new Standard_Cal(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		StdMenu.setForeground(new Color(255, 255, 255));
		StdMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		StdMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(StdMenu);
		
		JMenuItem SciMenu = new JMenuItem("Scientific");
		SciMenu.setOpaque(true);
		SciMenu.setEnabled(false);
		SciMenu.setForeground(new Color(255, 255, 255));
		SciMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		SciMenu.setBackground(new Color(0, 0, 90));
		FileMenu.add(SciMenu);
		
		JMenuItem NetwrkMenu = new JMenuItem("Network");
		NetwrkMenu.setOpaque(true);
		NetwrkMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Network_Calculator Cal=new Network_Calculator(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		NetwrkMenu.setForeground(new Color(255, 255, 255));
		NetwrkMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		NetwrkMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(NetwrkMenu);
		
		JMenuItem BMIMenu = new JMenuItem("BMI");
		BMIMenu.setOpaque(true);
		BMIMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BMI Cal=new BMI(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		BMIMenu.setForeground(new Color(255, 255, 255));
		BMIMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		BMIMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(BMIMenu);
		
		JMenuItem DSTMenu = new JMenuItem("Distance Speed Time");
		DSTMenu.setOpaque(true);
		DSTMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Distance_Speed_Time_Cal Cal=new Distance_Speed_Time_Cal(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		DSTMenu.setForeground(new Color(255, 255, 255));
		DSTMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		DSTMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(DSTMenu);
		
		JSeparator separator_2 = new JSeparator();
		FileMenu.add(separator_2);
		
		JMenuItem CnvrtrMenu = new JMenuItem("Converter");
		CnvrtrMenu.setOpaque(true);
		CnvrtrMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Converter Con=new Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		CnvrtrMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		CnvrtrMenu.setHorizontalAlignment(SwingConstants.CENTER);
		CnvrtrMenu.setForeground(new Color(255, 255, 255));
		CnvrtrMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		CnvrtrMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(CnvrtrMenu);
		
		JSeparator separator_3 = new JSeparator();
		FileMenu.add(separator_3);
		
		JMenuItem WeightMenu = new JMenuItem("Weight and Mass");
		WeightMenu.setOpaque(true);
		WeightMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Weight_Converter Con=new Weight_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		WeightMenu.setForeground(new Color(255, 255, 255));
		WeightMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		WeightMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(WeightMenu);
		
		JMenuItem LengthMenu = new JMenuItem("Length");
		LengthMenu.setOpaque(true);
		LengthMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Length_Converter Con=new Length_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		LengthMenu.setForeground(new Color(255, 255, 255));
		LengthMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		LengthMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(LengthMenu);
		
		JMenuItem TempMenu = new JMenuItem("Temperature");
		TempMenu.setOpaque(true);
		TempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Temp_Converter Con=new Temp_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		TempMenu.setForeground(new Color(255, 255, 255));
		TempMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		TempMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(TempMenu);
		
		JMenuItem TimeMenu = new JMenuItem("Time");
		TimeMenu.setOpaque(true);
		TimeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Time_Converter Cal=new Time_Converter(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		TimeMenu.setForeground(new Color(255, 255, 255));
		TimeMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		TimeMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(TimeMenu);
		
		JMenuItem AngleMenu = new JMenuItem("Angle");
		AngleMenu.setOpaque(true);
		AngleMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Angle_Converter Con=new Angle_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		AngleMenu.setForeground(new Color(255, 255, 255));
		AngleMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		AngleMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(AngleMenu);
		
		JMenuItem DataMenu = new JMenuItem("Data");
		DataMenu.setOpaque(true);
		DataMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data_Converter Con=new Data_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		DataMenu.setForeground(new Color(255, 255, 255));
		DataMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		DataMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(DataMenu);
		
		JSeparator separator_4 = new JSeparator();
		FileMenu.add(separator_4);
		
		JMenuItem ExitMenu = new JMenuItem("Exit");
		ExitMenu.setOpaque(true);
		ExitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		ExitMenu.setForeground(new Color(255, 255, 255));
		ExitMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		ExitMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(ExitMenu);
	}
}