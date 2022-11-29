package multiCalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class Standard_Cal {

	public JFrame frame;
	private JButton Backbtn;
	private JButton Nxtbtn;
	private JTextField Input_Field;
	private JTextField Ans_Field;
	
	private double result=0;
	
	private String eq="";
	private String ans="";
	private String on_off="off";
	
	Stack<Double> num;
	Stack<Double> operation;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private static String[] page_arr=new String[100];
	private int index=0;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Standard_Cal window = new Standard_Cal(false, page_arr, -1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		else
			return 0;
	}
	
	// Equation solver function using stack
	private Double EqSolver(String exp) 
	{
		String[] elements=exp.trim().split(" ",0);
		
		LinkedList<String> list=new LinkedList<String>();
		Stack<Double> num=new Stack<Double>();
		Stack<String> operation = new Stack<String>();
		
		// Storing String array elements to linked list
		for(int i=0; i<elements.length; i++)
			list.add(elements[i]);
		
		for(int i=0; i<list.size(); i++) 
		{
			// Ignoring null value or whitespace
			if(list.get(i).equals("") || list.get(i).equals(" "))
				continue;
			
			if(list.get(i).equals("%")) 
			{
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
			else if(list.get(i).equals(")")) 
			{
				// Continue till the inside brackets are solved
				while(!(operation.peek().equals("("))) {
					num.push(operation2(operation.pop(),num.pop(),num.pop()));
				}
				operation.pop();
			}
			else {	// if element is an operator
				while(!operation.empty() && (precedence(list.get(i))<=precedence(operation.peek()))) 
				{
					num.push(operation2(operation.pop(),num.pop(),num.pop()));
				}
				operation.push(list.get(i));
			}
		}
		while(!operation.empty()) 
		{
			num.push(operation2(operation.pop(),num.pop(),num.pop()));
		}
		return num.pop();
	}
	
	// Create the application.
	public Standard_Cal(Boolean prev_nxt_clicked, String[] elements, int indx) {
		initialize();
		
		prev_next obj=new prev_next(elements);
		page_arr=obj.add(prev_nxt_clicked, "standrd", indx);
		
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
	
	// Result function
	private void result() {
		if(!Input_Field.getText().equals("")) {
			if(eq.charAt(0)=='-')
				result=EqSolver("0 "+Input_Field.getText());
			else
				result=EqSolver(Input_Field.getText());
			
			ans=String.format("%.3f", result);
			Ans_Field.setText(ans);
		}
		else
			Ans_Field.setText(null);
	}	

	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 640, 581);
		frame.setTitle("Standard Calculator");
		frame.setLocationRelativeTo(null); // For setting frame center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 100, 0, 100, 100};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_panel);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(new Color(0, 0, 51));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 16;
		gbc_panel_2.gridwidth = 5;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		mainPanel.add(subPanel, gbc_panel_2);
		
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
		Input_Field.setForeground(Color.WHITE);
		Input_Field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Input_Field.setEditable(false);
		Input_Field.setColumns(10);
		Input_Field.setBorder(null);
		Input_Field.setBackground(new Color(0, 0, 80));
		
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
		Ans_Field.setForeground(Color.WHITE);
		Ans_Field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Ans_Field.setEditable(false);
		Ans_Field.setColumns(10);
		Ans_Field.setBorder(null);
		Ans_Field.setBackground(new Color(0, 0, 80));
		
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
				
				result();
			}
		});
		btn7.setForeground(new Color(0, 204, 255));
		btn7.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn7.setFocusable(false);
		btn7.setEnabled(false);
		btn7.setBorder(null);
		btn7.setBackground(new Color(0, 0, 102));
		btn7.setAlignmentX(0.5f);
		
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
					eq=eq.substring(0,eq.trim().length()-1);
					Input_Field.setText(eq);
					
					result();
				}
			}
		});
		btnBack.setForeground(new Color(0, 204, 255));
		btnBack.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btnBack.setFocusable(false);
		btnBack.setEnabled(false);
		btnBack.setBorder(null);
		btnBack.setBackground(Color.BLUE);
		btnBack.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn8.setForeground(new Color(0, 204, 255));
		btn8.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn8.setFocusable(false);
		btn8.setEnabled(false);
		btn8.setBorder(null);
		btn8.setBackground(new Color(0, 0, 102));
		btn8.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn4.setForeground(new Color(0, 204, 255));
		btn4.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn4.setFocusable(false);
		btn4.setEnabled(false);
		btn4.setBorder(null);
		btn4.setBackground(new Color(0, 0, 102));
		btn4.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn1.setForeground(new Color(0, 204, 255));
		btn1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn1.setFocusable(false);
		btn1.setEnabled(false);
		btn1.setBorder(null);
		btn1.setBackground(new Color(0, 0, 102));
		btn1.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn_dot.setForeground(new Color(0, 204, 255));
		btn_dot.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_dot.setFocusable(false);
		btn_dot.setEnabled(false);
		btn_dot.setBorder(null);
		btn_dot.setBackground(new Color(0, 0, 102));
		btn_dot.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn5.setForeground(new Color(0, 204, 255));
		btn5.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn5.setFocusable(false);
		btn5.setEnabled(false);
		btn5.setBorder(null);
		btn5.setBackground(new Color(0, 0, 102));
		btn5.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn2.setForeground(new Color(0, 204, 255));
		btn2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn2.setFocusable(false);
		btn2.setEnabled(false);
		btn2.setBorder(null);
		btn2.setBackground(new Color(0, 0, 102));
		btn2.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn0.setForeground(new Color(0, 204, 255));
		btn0.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn0.setFocusable(false);
		btn0.setEnabled(false);
		btn0.setBorder(null);
		btn0.setBackground(new Color(0, 0, 102));
		btn0.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn9.setForeground(new Color(0, 204, 255));
		btn9.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn9.setFocusable(false);
		btn9.setEnabled(false);
		btn9.setBorder(null);
		btn9.setBackground(new Color(0, 0, 102));
		btn9.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn6.setForeground(new Color(0, 204, 255));
		btn6.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn6.setFocusable(false);
		btn6.setEnabled(false);
		btn6.setBorder(null);
		btn6.setBackground(new Color(0, 0, 102));
		btn6.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn3.setForeground(new Color(0, 204, 255));
		btn3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn3.setFocusable(false);
		btn3.setEnabled(false);
		btn3.setBorder(null);
		btn3.setBackground(new Color(0, 0, 102));
		btn3.setAlignmentX(0.5f);
		
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
				
				Input_Field.setText(Ans_Field.getText());
				eq=Input_Field.getText();
				Ans_Field.setText(null);
			}
		});
		btn_equal.setForeground(new Color(0, 204, 255));
		btn_equal.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_equal.setFocusable(false);
		btn_equal.setEnabled(false);
		btn_equal.setBorder(null);
		btn_equal.setBackground(new Color(0, 0, 204));
		btn_equal.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn_divide.setForeground(new Color(0, 204, 255));
		btn_divide.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_divide.setFocusable(false);
		btn_divide.setEnabled(false);
		btn_divide.setBorder(null);
		btn_divide.setBackground(new Color(0, 0, 204));
		btn_divide.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn_multiply.setForeground(new Color(0, 204, 255));
		btn_multiply.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_multiply.setFocusable(false);
		btn_multiply.setEnabled(false);
		btn_multiply.setBorder(null);
		btn_multiply.setBackground(new Color(0, 0, 204));
		btn_multiply.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn_minus.setForeground(new Color(0, 204, 255));
		btn_minus.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_minus.setFocusable(false);
		btn_minus.setEnabled(false);
		btn_minus.setBorder(null);
		btn_minus.setBackground(new Color(0, 0, 204));
		btn_minus.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn_add.setForeground(new Color(0, 204, 255));
		btn_add.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_add.setFocusable(false);
		btn_add.setEnabled(false);
		btn_add.setBorder(null);
		btn_add.setBackground(new Color(0, 0, 204));
		btn_add.setAlignmentX(0.5f);
		
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
				
				result();
			}
		});
		btn_percent.setForeground(new Color(0, 204, 255));
		btn_percent.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btn_percent.setFocusable(false);
		btn_percent.setEnabled(false);
		btn_percent.setBorder(null);
		btn_percent.setBackground(new Color(0, 0, 204));
		btn_percent.setAlignmentX(0.5f);
		
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
								
				Input_Field.setText(eq);
				Ans_Field.setText(ans);
			}
		});
		btnAC.setForeground(new Color(0, 204, 255));
		btnAC.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		btnAC.setFocusable(false);
		btnAC.setEnabled(false);
		btnAC.setBorder(null);
		btnAC.setBackground(new Color(0, 0, 204));
		btnAC.setAlignmentX(0.5f);
		
		JRadioButton ONbtn = new JRadioButton("ON");
		JRadioButton OFFbtn = new JRadioButton("OFF");
		
		ONbtn.setHorizontalAlignment(SwingConstants.CENTER);
		ONbtn.setForeground(new Color(0, 102, 255));
		ONbtn.setFont(new Font("Rockwell", Font.PLAIN, 14));
		ONbtn.setFocusable(false);
		ONbtn.setBackground(new Color(0, 0, 51));
		ONbtn.setAlignmentX(0.5f);
		ONbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				on_off="on";
				
				Input_Field.setText("");
				Ans_Field.setText("");
				
				ONbtn.setForeground(new Color(0, 204, 255));
				OFFbtn.setForeground(new Color(0, 102, 255));
				Input_Field.setBackground(new Color(0, 0, 102));
				Ans_Field.setBackground(new Color(0, 0, 102));
				
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
			}
		});
		buttonGroup.add(ONbtn);
		
		OFFbtn.setSelected(true);
		OFFbtn.setForeground(new Color(0, 204, 255));
		OFFbtn.setFont(new Font("Rockwell", Font.PLAIN, 14));
		OFFbtn.setFocusable(false);
		OFFbtn.setBackground(new Color(0, 0, 51));
		OFFbtn.setAlignmentX(0.5f);
		
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
			}
		});
		buttonGroup.add(OFFbtn);
		
		GroupLayout gl_panel_2 = new GroupLayout(subPanel);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(121)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(Input_Field, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(Ans_Field, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(btn_dot, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn0, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_equal, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_add, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_minus, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_multiply, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(ONbtn, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(OFFbtn, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAC, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(btn7, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btn8, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btn9, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_divide, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_percent, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))))
					.addGap(114))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(Input_Field, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Ans_Field, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(ONbtn)
						.addComponent(OFFbtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btn7, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn8, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn9, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_divide, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_multiply, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_minus, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_add, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btn_equal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btn0, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addComponent(btn_dot, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
							.addComponent(btn_percent, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAC, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		subPanel.setLayout(gl_panel_2);
		
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
		CalMenu.setOpaque(true);
		CalMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator Cal=new Calculator(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
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
		StdMenu.setEnabled(false);
		StdMenu.setForeground(new Color(255, 255, 255));
		StdMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		StdMenu.setBackground(new Color(0, 0, 90));
		FileMenu.add(StdMenu);
		
		JMenuItem SciMenu = new JMenuItem("Scientific");
		SciMenu.setOpaque(true);
		SciMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scientific_Cal Cal=new Scientific_Cal(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
		StdMenu.setEnabled(false);
		SciMenu.setForeground(new Color(255, 255, 255));
		SciMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		SciMenu.setBackground(new Color(0, 0, 64));
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
		CnvrtrMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Converter Con=new Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		CnvrtrMenu.setOpaque(true);
		CnvrtrMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		CnvrtrMenu.setHorizontalAlignment(SwingConstants.CENTER);
		CnvrtrMenu.setForeground(new Color(255, 255, 255));
		CnvrtrMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		CnvrtrMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(CnvrtrMenu);
		
		JSeparator separator_3 = new JSeparator();
		FileMenu.add(separator_3);
		
		JMenuItem WeightMenu = new JMenuItem("Weight and Mass");
		WeightMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Weight_Converter Con=new Weight_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		WeightMenu.setOpaque(true);
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
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
	}
}
