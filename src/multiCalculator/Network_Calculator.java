package multiCalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Network_Calculator {

	public JFrame frame;
	private JTextField IP_Field_1;
	private JTextField IP_Field_2;
	private JTextField IP_Field_3;
	private JTextField IP_Field_4;
	private JTextField mask;
	private JButton Backbtn;
	private JButton Nxtbtn;

	private String output="Binary";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private static String[] page_arr=new String[100];
	private int index=0;
	
	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Network_Calculator window = new Network_Calculator(false, page_arr, -1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public Network_Calculator(Boolean prev_nxt_clicked, String[] elements, int indx) {
		initialize();
		prev_next obj=new prev_next(elements);
		page_arr=obj.add(prev_nxt_clicked, "IP", indx);
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


	
	// Function for checking the limit of IP fields
	private void IP_limit(String field,double val) {
		if(val>255) {
			JOptionPane.showMessageDialog(null, "Please Enter the IP address fields in range (0-255)");
			if(field.equals("field1"))
				IP_Field_1.setText(null);
			else if(field.equals("field2"))
				IP_Field_2.setText(null);
			else if(field.equals("field3"))
				IP_Field_3.setText(null);
			else if(field.equals("field4"))
				IP_Field_4.setText(null);
		}
	}
	
	// Function for checking the limit of mask field
	private void MASK_limit(double val) {
		if(val>32) {
			JOptionPane.showMessageDialog(null, "Please Enter the CIDR value in range (0-32)");
			mask.setText(null);
		}
	}
	
	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 889, 389);
		frame.setTitle("Network Calculator");
		frame.setLocationRelativeTo(null); // For setting frame center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 100, 0, 100, 100, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_panel);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(new Color(0, 0, 51));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 10;
		gbc_panel_2.gridwidth = 11;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		mainPanel.add(subPanel, gbc_panel_2);
		
		JLabel Ntwrk_Calc = new JLabel("Network Calculator");
		Ntwrk_Calc.setForeground(Color.WHITE);
		Ntwrk_Calc.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 22));
		Ntwrk_Calc.setBackground(new Color(163, 162, 221));
		
		JLabel IP_Address = new JLabel("IP Address");
		IP_Address.setForeground(Color.WHITE);
		IP_Address.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		
		IP_Field_1 = new JTextField();
		IP_Field_1.addKeyListener(new KeyAdapter() {
			// Accepting only numeric values
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				IP_limit("field1",Double.parseDouble(IP_Field_1.getText()));
			}
		});
		IP_Field_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				IP_Field_1.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				IP_Field_1.setBackground(new Color(0, 0, 102));
			}
		});
		IP_Field_1.setForeground(new Color(0, 204, 255));
		IP_Field_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		IP_Field_1.setColumns(10);
		IP_Field_1.setBorder(null);
		IP_Field_1.setBackground(new Color(0, 0, 102));
		
		IP_Field_2 = new JTextField();
		IP_Field_2.addKeyListener(new KeyAdapter() {
			// Accepting only numeric values
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				IP_limit("field2",Double.parseDouble(IP_Field_2.getText()));
			}
		});
		IP_Field_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				IP_Field_2.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				IP_Field_2.setBackground(new Color(0, 0, 102));
			}
		});
		IP_Field_2.setForeground(new Color(0, 204, 255));
		IP_Field_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		IP_Field_2.setColumns(10);
		IP_Field_2.setBorder(null);
		IP_Field_2.setBackground(new Color(0, 0, 102));
		
		IP_Field_3 = new JTextField();
		IP_Field_3.addKeyListener(new KeyAdapter() {
			// Accepting only numeric values
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				IP_limit("field3",Double.parseDouble(IP_Field_3.getText()));
			}
		});
		IP_Field_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				IP_Field_3.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				IP_Field_3.setBackground(new Color(0, 0, 102));
			}
		});
		IP_Field_3.setForeground(new Color(0, 204, 255));
		IP_Field_3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		IP_Field_3.setColumns(10);
		IP_Field_3.setBorder(null);
		IP_Field_3.setBackground(new Color(0, 0, 102));
		
		IP_Field_4 = new JTextField();
		IP_Field_4.addKeyListener(new KeyAdapter() {
			// Accepting only numeric values
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				IP_limit("field4",Double.parseDouble(IP_Field_4.getText()));
			}
		});
		IP_Field_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				IP_Field_4.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				IP_Field_4.setBackground(new Color(0, 0, 102));
			}
		});
		IP_Field_4.setForeground(new Color(0, 204, 255));
		IP_Field_4.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		IP_Field_4.setColumns(10);
		IP_Field_4.setBorder(null);
		IP_Field_4.setBackground(new Color(0, 0, 102));
		
		JLabel mask_Label = new JLabel("CIDR value");
		mask_Label.setForeground(Color.WHITE);
		mask_Label.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		
		JLabel slash = new JLabel("/");
		slash.setForeground(Color.WHITE);
		slash.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		
		mask = new JTextField();
		mask.addKeyListener(new KeyAdapter() {
			// Accepting only numeric values
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				MASK_limit(Double.parseDouble(mask.getText()));
			}
		});
		mask.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mask.setBackground(new Color(0, 0, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mask.setBackground(new Color(0, 0, 102));
			}
		});
		mask.setForeground(new Color(0, 204, 255));
		mask.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		mask.setColumns(10);
		mask.setBorder(null);
		mask.setBackground(new Color(0, 0, 102));
		
		JLabel note = new JLabel("Choose an Output Form");
		note.setForeground(Color.WHITE);
		note.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 16));
		
		JRadioButton BinBtn = new JRadioButton("Binary");
		JRadioButton DecBtn = new JRadioButton("Decimal");
		
		BinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BinBtn.setForeground(new Color(0, 204, 255));
				DecBtn.setForeground(new Color(0, 102, 255));
			}
		});
		BinBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				output="Binary";
			}
		});
		buttonGroup.add(BinBtn);
		BinBtn.setSelected(true);
		BinBtn.setHorizontalAlignment(SwingConstants.CENTER);
		BinBtn.setForeground(new Color(0, 204, 255));
		BinBtn.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		BinBtn.setFocusable(false);
		BinBtn.setBackground(new Color(0, 0, 51));
		BinBtn.setAlignmentX(0.5f);
		
		DecBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecBtn.setForeground(new Color(0, 204, 255));
				BinBtn.setForeground(new Color(0, 102, 255));
			}
		});
		DecBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				output="Decimal";
			}
		});
		buttonGroup.add(DecBtn);
		DecBtn.setForeground(new Color(0, 102, 255));
		DecBtn.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		DecBtn.setFocusable(false);
		DecBtn.setBackground(new Color(0, 0, 51));
		DecBtn.setAlignmentX(0.5f);
		
		JButton Calc_Btn = new JButton("Calculate");
		Calc_Btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Calc_Btn.setForeground(new Color(0, 204, 255));
				Calc_Btn.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Calc_Btn.setForeground(new Color(255, 255, 255));
				Calc_Btn.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!IP_Field_1.getText().equals("") && !IP_Field_2.getText().equals("") && !IP_Field_3.getText().equals("") && !IP_Field_4.getText().equals("") && !mask.getText().equals("")) {
					// Read value from textfield
					int f1=Integer.parseInt(IP_Field_1.getText());
					int f2=Integer.parseInt(IP_Field_2.getText());
					int f3=Integer.parseInt(IP_Field_3.getText());
					int f4=Integer.parseInt(IP_Field_4.getText());
			        		        
			        // Create integer bitmask
			        int bitMask=Integer.parseInt(mask.getText().toString());
			        
			        String strBinIP="", strBinNet="", strBinBC="", DecMask="", DecNet="", ntwrk_class="";
			        String[] Ip_Num,Net_Num,BC_Num=new String[3];
			        
			        // Ip address in decimal form
			        String DecIp=f1+"."+f2+"."+f3+"."+f4;
			        
			        // Making network address and class
			        if(f1>=0 && f1<128) {
			        	ntwrk_class="A";
			        	DecNet=f1+".0.0.0";
			        }
			        else if(f1>=128 && f1<192) {
			        	ntwrk_class="B";
			        	DecNet=f1+"."+f2+".0.0";
			        }
			        else if(f1>=192 && f1<224) {
			        	ntwrk_class="C";
			        	DecNet=f1+"."+f2+"."+f3+".0";
			        }
			        else if(f1>=224 && f1<240) {
			        	ntwrk_class="D";
			        	DecNet="N/A";
			        }
			        else if(f1>=240 && f1<=255) {
			        	ntwrk_class="E";
			        	DecNet="N/A";
			        }
			        
			        // Making 32 bit Network address
			        if(DecNet!="N/A") {
			        	Net_Num=DecNet.split("[.]");
			        	
			        	for(int i=0; i<Net_Num.length; i++) {
		        			// Changing decimal to binary string
			        		strBinNet+=String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(Net_Num[i]))));
			        	}
			        }
			        else
			        	strBinNet=DecNet;
			        
			        // Making broadcast address
			        String DecBC = f1+"."+f2+"."+f3+".255";
			        
			        // Making 32 bit IP address, Broadcast address
		        	Ip_Num=DecIp.split("[.]");
			        BC_Num=DecBC.split("[.]");
		        	
		        	for(int i=0; i<4; i++) {
	        			// Changing decimal to binary string
		        		strBinIP+=String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(Ip_Num[i]))));
		        		strBinBC+=String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(BC_Num[i]))));
		        	}
		        	
		        	// Making 32 bit subnet mask
			        int BinMask = ~0 << (32-bitMask);
			        
			        // Converting subnet mask to binary submask
			        String strBinMask=Integer.toBinaryString(BinMask);
			        
			        // Split with period
			        int j=0;
			        for(int i=0; i<32; i++) {
			        	if(Character.isDigit(strBinIP.charAt(i))) {
			        		j++;
			        		if((j)%8==0) {
			        			strBinIP=strBinIP.substring(0,i+1)+"."+strBinIP.substring(i+1);
				        		strBinMask=strBinMask.substring(0,i+1)+"."+strBinMask.substring(i+1);
				        		if(DecNet!="N/A")
				        			strBinNet=strBinNet.substring(0,i+1)+"."+strBinNet.substring(i+1);
				        		strBinBC=strBinBC.substring(0,i+1)+"."+strBinBC.substring(i+1);
				        	}
			        	}
			        }
			        
			        // Making subnet mask
			        String Mask_num[]=strBinMask.split("[.]");
			        
			        for(int i=0; i<Mask_num.length; i++) {
			        	// Changing decimal to binary string
			        	DecMask+=Integer.parseInt(Mask_num[i],2);
			        	
			        	if(i!=Mask_num.length-1)
			        		DecMask+=".";
			        }
			        
			        // Calculating host per subnet
			        int val=32-bitMask;
			        int host_P_sub=(int) Math.pow(2, val);
			        
			        // Calculating number of subnet
			        int n_subnet=host_P_sub/bitMask;
			        
			        if(output=="Binary") {
			        	Binary_IP IP=new Binary_IP(false, page_arr, index);
						IP.frame.setVisible(true);
						IP.display(strBinIP,strBinMask,strBinNet,strBinBC,n_subnet,host_P_sub,ntwrk_class);
						frame.dispose();
			        }
			        else if(output=="Decimal") {
			        	Decimal_IP IP=new Decimal_IP(false, page_arr, index);
						IP.frame.setVisible(true);
						IP.display(DecIp,DecMask,DecNet,DecBC,n_subnet,host_P_sub,ntwrk_class);
						frame.dispose();
			        }
				}
				else
					JOptionPane.showMessageDialog(null, "Please Enter The Missing Value!!");
			}
		});
		Calc_Btn.setForeground(Color.WHITE);
		Calc_Btn.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		Calc_Btn.setFocusable(false);
		Calc_Btn.setBorder(null);
		Calc_Btn.setBackground(new Color(0, 0, 102));
		
		JButton reset_btn = new JButton("Reset");
		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IP_Field_1.setText(null);
				IP_Field_2.setText(null);
				IP_Field_3.setText(null);
				IP_Field_4.setText(null);
				mask.setText(null);
			}
		});
		reset_btn.setForeground(Color.WHITE);
		reset_btn.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		reset_btn.setFocusable(false);
		reset_btn.setBorder(null);
		reset_btn.setBackground(new Color(0, 0, 102));
		
		GroupLayout gl_panel_2 = new GroupLayout(subPanel);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(241)
								.addComponent(BinBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGap(120)
								.addComponent(DecBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(279)
								.addComponent(Ntwrk_Calc))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(IP_Address, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(IP_Field_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(IP_Field_2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(IP_Field_3, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(IP_Field_4, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(mask_Label, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(slash, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(mask, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(297)
								.addComponent(note, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(185)
							.addComponent(Calc_Btn, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addGap(119)
							.addComponent(reset_btn, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(30)
					.addComponent(Ntwrk_Calc, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(mask, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(slash, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(mask_Label, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(IP_Field_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(IP_Field_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(IP_Field_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(IP_Field_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(IP_Address, Alignment.TRAILING))
					.addGap(26)
					.addComponent(note, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(BinBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(DecBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(Calc_Btn, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(reset_btn, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
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
		NetwrkMenu.setEnabled(false);
		NetwrkMenu.setForeground(new Color(255, 255, 255));
		NetwrkMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		NetwrkMenu.setBackground(new Color(0, 0, 90));
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