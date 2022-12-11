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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class Temp_Converter {

	public JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JButton Backbtn;
	private JButton Nxtbtn;
	private JComboBox unit1;
	private JComboBox unit2;
	
	
	private static String[] page_arr=new String[100];
	private int index=0;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temp_Converter window = new Temp_Converter(false, page_arr, -1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public Temp_Converter(Boolean prev_nxt_clicked, String[] elements, int indx) {
		initialize();
		prev_next obj=new prev_next(elements);
		page_arr=obj.add(prev_nxt_clicked, "Temp", indx);
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
		if(!t1.getText().equals("") && !unit1.getSelectedItem().equals("--From Unit--") && !unit2.getSelectedItem().equals("--To Unit--")){
			if(unit1.getSelectedItem().equals("Celcius") && unit2.getSelectedItem().equals("Fahrenheit"))
	        {
	            double c = Double.parseDouble(t1.getText());
	            
	            double f = (double)(c * 1.8 + 32);
	            
	            t2.setText(String.format("%.3f",f));
	        }
	        else if(unit1.getSelectedItem().equals("Celcius") && unit2.getSelectedItem().equals("Kelvin"))
	        {
	            double c = Double.parseDouble(t1.getText());
	            
	            double k = (double)(c + 273.15);
	            
	            t2.setText(String.format("%.3f",k));
	        }
	        else if(unit1.getSelectedItem().equals("Celcius") && unit2.getSelectedItem().equals("Celcius"))
	        {
	            double c = Double.parseDouble(t1.getText());
	            
	            t2.setText(String.format("%.3f",c));
	        }
	        
	        //---------------------------------------------------------------------
	        else if(unit1.getSelectedItem().equals("Fahrenheit") && unit2.getSelectedItem().equals("Celcius"))
	        {
	            double f = Double.parseDouble(t1.getText());
	            
	            double c = (double)((f-32)*5/9);
	            
	            t2.setText(String.format("%.3f",c));
	        }
	        else if(unit1.getSelectedItem().equals("Fahrenheit") && unit2.getSelectedItem().equals("Kelvin"))
	        {
	            double f = Double.parseDouble(t1.getText());
	            
	            double k = (double)((f-32)*5/9 + 273.15);
	            
	            t2.setText(String.format("%.3f",k));
	        }
	        else if(unit1.getSelectedItem().equals("Fahrenheit") && unit2.getSelectedItem().equals("Fahrenheit"))
	        {
	            double f = Double.parseDouble(t1.getText());
	          
	            t2.setText(String.format("%.3f",f));
	        }
	        
	        //---------------------------------------------------------------------
	        else if(unit1.getSelectedItem().equals("Kelvin") && unit2.getSelectedItem().equals("Celcius"))
	        {
	            double k = Double.parseDouble(t1.getText());
	            
	            double c = (k - 273.15);
	            
	            t2.setText(String.format("%.3f",c));
	        }
	        else if(unit1.getSelectedItem().equals("Kelvin") && unit2.getSelectedItem().equals("Fahrenheit"))
	        {
	            double k = Double.parseDouble(t1.getText());
	            
	            double f = (double)(k - 273.15)*9/5+32;
	            
	            t2.setText(String.format("%.3f",f));
	        }
	        else if(unit1.getSelectedItem().equals("Kelvin") && unit2.getSelectedItem().equals("Kelvin"))
	        {
	            double k = Double.parseDouble(t1.getText());
	          
	            t2.setText(String.format("%.3f",k));
	        }
		}
		else if(t1.getText().equals("")) {
			t2.setText(null);
		}
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 445, 459);
		frame.setTitle("Temperature Converter");
		frame.setLocationRelativeTo(null); // For setting frame center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 100, 0, 100, 100};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_panel);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(new Color(0, 0, 51));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 14;
		gbc_panel_2.gridwidth = 5;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		mainPanel.add(subPanel, gbc_panel_2);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(null);
		btnPanel.setBackground(new Color(0, 0, 102));
		
		unit1 = new JComboBox();
		unit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result();
			}
		});
		unit1.setForeground(new Color(0, 204, 255));
		unit1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 19));
		unit1.setModel(new DefaultComboBoxModel(new String[] {"--From Unit--", "Celcius", "Fahrenheit", "Kelvin"}));
		unit1.setBackground(new Color(0, 0, 104));
		unit1.setBorder(null);
		
		t1 = new JTextField();
		t1.addKeyListener(new KeyAdapter() {
			// Accepting only numeric values
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
			// Calling result function when key is released
			@Override
			public void keyReleased(KeyEvent e) {
				result();
			}
		});
		t1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				t1.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				t1.setBackground(new Color(0, 0, 102));
			}
		});
		t1.setForeground(Color.WHITE);
		t1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 17));
		t1.setColumns(10);
		t1.setBorder(null);
		t1.setBackground(new Color(0, 0, 102));
		
		unit2 = new JComboBox();
		unit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result();
			}
		});
		unit2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 19));
		unit2.setModel(new DefaultComboBoxModel(new String[] {"--To Unit--", "Celcius", "Fahrenheit", "Kelvin"}));
		unit2.setBorder(null);
		unit2.setForeground(new Color(0, 204, 255));
		unit2.setBackground(new Color(0, 0, 104));
		
		t2 = new JTextField();
		t2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				t2.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				t2.setBackground(new Color(0, 0, 102));
			}
		});
		t2.setEditable(false);
		t2.setForeground(Color.WHITE);
		t2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 17));
		t2.setColumns(10);
		t2.setBorder(null);
		t2.setBackground(new Color(0, 0, 102));
		
		JLabel U1 = new JLabel("Unit");
		U1.setForeground(Color.WHITE);
		unit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SI1="";
				switch(unit1.getSelectedItem().toString())
		        {
		            case "Celcius": SI1 = "°C";
		            break;
		            case "Fahrenheit": SI1 = "F";
		            break;
		            case "Kelvin": SI1 = "K";
		            break;
		            
		        }
				U1.setText(SI1);
				result();
			}
		});
				
		U1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JLabel U2 = new JLabel("Unit");
		U2.setForeground(Color.WHITE);
		
		unit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SI2="";
				switch(unit2.getSelectedItem().toString())
		        {
		            case "Celcius": SI2 = "°C";
		            break;
		            case "Fahrenheit": SI2 = "F";
		            break;
		            case "Kelvin": SI2 = "K";
		            break;
		            
		        }
				U2.setText(SI2);
				result();
			}
		});
		U2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		GroupLayout gl_panel_2 = new GroupLayout(subPanel);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(104)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(unit2, 0, 185, Short.MAX_VALUE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(t1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(U1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addComponent(unit1, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(t2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(U2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addComponent(unit1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(t1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(U1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(unit2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(t2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(U2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		JButton btn7 = new JButton("7");
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn7.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn7.setForeground(new Color(0, 204, 255));
			}
		});
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"7");
				result();
			}
		});
		btn7.setForeground(new Color(0, 204, 255));
		btn7.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn7.setFocusable(false);
		btn7.setBorder(null);
		btn7.setBackground(new Color(0, 0, 102));
		btn7.setAlignmentX(0.5f);
		
		JButton btn8 = new JButton("8");
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn8.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn8.setForeground(new Color(0, 204, 255));
			}
		});
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"8");
				result();
			}
		});
		btn8.setForeground(new Color(0, 204, 255));
		btn8.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn8.setFocusable(false);
		btn8.setBorder(null);
		btn8.setBackground(new Color(0, 0, 102));
		btn8.setAlignmentX(0.5f);
		
		JButton btn9 = new JButton("9");
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn9.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn9.setForeground(new Color(0, 204, 255));
			}
		});
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"9");
				result();
			}
		});
		btn9.setForeground(new Color(0, 204, 255));
		btn9.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn9.setFocusable(false);
		btn9.setBorder(null);
		btn9.setBackground(new Color(0, 0, 102));
		btn9.setAlignmentX(0.5f);
		
		JButton btn4 = new JButton("4");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn4.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn4.setForeground(new Color(0, 204, 255));
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"4");
				result();
			}
		});
		btn4.setForeground(new Color(0, 204, 255));
		btn4.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn4.setFocusable(false);
		btn4.setBorder(null);
		btn4.setBackground(new Color(0, 0, 102));
		btn4.setAlignmentX(0.5f);
		
		JButton btn5 = new JButton("5");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn5.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn5.setForeground(new Color(0, 204, 255));
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"5");
				result();
			}
		});
		btn5.setForeground(new Color(0, 204, 255));
		btn5.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn5.setFocusable(false);
		btn5.setBorder(null);
		btn5.setBackground(new Color(0, 0, 102));
		btn5.setAlignmentX(0.5f);
		
		JButton btn6 = new JButton("6");
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn6.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn6.setForeground(new Color(0, 204, 255));
			}
		});
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"6");
				result();
			}
		});
		btn6.setForeground(new Color(0, 204, 255));
		btn6.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn6.setFocusable(false);
		btn6.setBorder(null);
		btn6.setBackground(new Color(0, 0, 102));
		btn6.setAlignmentX(0.5f);
		
		JButton btn3 = new JButton("3");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn3.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn3.setForeground(new Color(0, 204, 255));
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"3");
				result();
			}
		});
		btn3.setForeground(new Color(0, 204, 255));
		btn3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn3.setFocusable(false);
		btn3.setBorder(null);
		btn3.setBackground(new Color(0, 0, 102));
		btn3.setAlignmentX(0.5f);
		
		JButton btn2 = new JButton("2");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn2.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn2.setForeground(new Color(0, 204, 255));
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"2");
				result();
			}
		});
		btn2.setForeground(new Color(0, 204, 255));
		btn2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn2.setFocusable(false);
		btn2.setBorder(null);
		btn2.setBackground(new Color(0, 0, 102));
		btn2.setAlignmentX(0.5f);
		
		JButton btn1 = new JButton("1");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn1.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn1.setForeground(new Color(0, 204, 255));
			}
		});
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"1");
				result();
			}
		});
		btn1.setForeground(new Color(0, 204, 255));
		btn1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn1.setFocusable(false);
		btn1.setBorder(null);
		btn1.setBackground(new Color(0, 0, 102));
		btn1.setAlignmentX(0.5f);
		
		JButton btn_dot = new JButton(".");
		btn_dot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_dot.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_dot.setForeground(new Color(0, 204, 255));
			}
		});
		btn_dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+".");
				result();
			}
		});
		btn_dot.setForeground(new Color(0, 204, 255));
		btn_dot.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn_dot.setFocusable(false);
		btn_dot.setBorder(null);
		btn_dot.setBackground(new Color(0, 0, 102));
		btn_dot.setAlignmentX(0.5f);
		
		JButton btn0 = new JButton("0");
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn0.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn0.setForeground(new Color(0, 204, 255));
			}
		});
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(t1.getText()+"0");
				result();
			}
		});
		btn0.setForeground(new Color(0, 204, 255));
		btn0.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btn0.setFocusable(false);
		btn0.setBorder(null);
		btn0.setBackground(new Color(0, 0, 102));
		btn0.setAlignmentX(0.5f);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setForeground(new Color(0, 204, 255));
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = t1.getText();
		        t1.setText(str.substring(0,str.length()-1));
		        result();
			}
		});
		btnBack.setForeground(new Color(0, 204, 255));
		btnBack.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btnBack.setFocusable(false);
		btnBack.setBorder(null);
		btnBack.setBackground(new Color(0, 0, 102));
		btnBack.setAlignmentX(0.5f);
		
		JButton btnAC = new JButton("AC");
		btnAC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAC.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAC.setForeground(new Color(0, 204, 255));
			}
		});
		btnAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unit1.setSelectedIndex(0);
				unit2.setSelectedIndex(0);
				t1.setText(null);
				t2.setText(null);
				U1.setText("Unit");
				U2.setText("Unit");
			}
		});
		btnAC.setForeground(new Color(0, 204, 255));
		btnAC.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		btnAC.setFocusable(false);
		btnAC.setBorder(null);
		btnAC.setBackground(new Color(0, 0, 102));
		btnAC.setAlignmentX(0.5f);
		
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addComponent(btn7, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(btn8, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(btn9, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_btnPanel.createSequentialGroup()
									.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(63)
									.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_btnPanel.createSequentialGroup()
									.addComponent(btn_dot, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(64)
									.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAC, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(btn0, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
							.addGap(62)
							.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack)
								.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_dot, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn0, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAC, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		btnPanel.setLayout(gl_btnPanel);
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
		SciMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scientific_Cal Cal=new Scientific_Cal(false, page_arr, index);
				Cal.frame.setVisible(true);
				frame.dispose();
			}
		});
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
		LengthMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Length_Converter Con=new Length_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		LengthMenu.setOpaque(true);
		LengthMenu.setForeground(new Color(255, 255, 255));
		LengthMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		LengthMenu.setBackground(new Color(0, 0, 64));
		FileMenu.add(LengthMenu);
		
		JMenuItem TempMenu = new JMenuItem("Temperature");
		TempMenu.setOpaque(true);
		TempMenu.setEnabled(false);
		TempMenu.setForeground(new Color(255, 255, 255));
		TempMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		TempMenu.setBackground(new Color(0, 0, 90));
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