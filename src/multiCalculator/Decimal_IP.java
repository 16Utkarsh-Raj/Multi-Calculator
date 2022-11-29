package multiCalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Decimal_IP {

	public JFrame frame;
	private JTextField Dec_IP;
	private JTextField DecSubmask;
	private JTextField DecNtwrk;
	private JTextField DecBrod;
	private JTextField ntwrkAvailable;
	private JTextField HostSub;
	private JTextField netwrkClass;
	private JButton Backbtn;
	private JButton Nxtbtn;
	
	private static String[] page_arr=new String[100];
	private int index=0;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decimal_IP window = new Decimal_IP(false, page_arr, -1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public Decimal_IP(Boolean prev_nxt_clicked, String[] elements, int indx) {
		initialize();
		prev_next obj=new prev_next(elements);
		page_arr=obj.add(prev_nxt_clicked, "Dec_IP", indx);
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
	
	public void display(String DecIp, String DecMask, String DecNet,String DecBC, int n_subnet, int host_P_sub, String ntwrk_class){
		// Setting output in decimal field
        Dec_IP.setText(DecIp);
        DecSubmask.setText(DecMask);
        DecNtwrk.setText(DecNet);
        DecBrod.setText(DecBC);
        
        // Setting no. of subnet, host per subnet, class
        ntwrkAvailable.setText(Integer.toString(n_subnet));
        HostSub.setText(Integer.toString(host_P_sub));
        netwrkClass.setText(ntwrk_class);
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 634, 359);
		frame.setTitle("Decimal IP");
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
		
		JLabel Binary_Label = new JLabel("Decimal");
		Binary_Label.setForeground(Color.WHITE);
		Binary_Label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel Dec_IP_Label = new JLabel("IP");
		Dec_IP_Label.setForeground(Color.WHITE);
		Dec_IP_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		Dec_IP = new JTextField();
		Dec_IP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Dec_IP.setBackground(new Color(0, 0, 170));
				Dec_IP.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Dec_IP.setBackground(new Color(0, 0, 102));
				Dec_IP.setForeground(new Color(0, 204, 255));
			}
		});
		Dec_IP.setForeground(new Color(0, 204, 255));
		Dec_IP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Dec_IP.setEditable(false);
		Dec_IP.setColumns(10);
		Dec_IP.setBorder(null);
		Dec_IP.setBackground(new Color(0, 0, 102));
		
		JLabel DecSubmask_Label = new JLabel("Subnet Mask");
		DecSubmask_Label.setForeground(Color.WHITE);
		DecSubmask_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		DecSubmask = new JTextField();
		DecSubmask.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DecSubmask.setBackground(new Color(0, 0, 170));
				DecSubmask.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				DecSubmask.setBackground(new Color(0, 0, 102));
				DecSubmask.setForeground(new Color(0, 204, 255));
			}
		});
		DecSubmask.setForeground(new Color(0, 204, 255));
		DecSubmask.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		DecSubmask.setEditable(false);
		DecSubmask.setColumns(10);
		DecSubmask.setBorder(null);
		DecSubmask.setBackground(new Color(0, 0, 102));
		
		JLabel DecNtwrk_Label = new JLabel("Network Address");
		DecNtwrk_Label.setForeground(Color.WHITE);
		DecNtwrk_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		DecNtwrk = new JTextField();
		DecNtwrk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DecNtwrk.setBackground(new Color(0, 0, 170));
				DecNtwrk.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				DecNtwrk.setBackground(new Color(0, 0, 102));
				DecNtwrk.setForeground(new Color(0, 204, 255));
			}
		});
		DecNtwrk.setForeground(new Color(0, 204, 255));
		DecNtwrk.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		DecNtwrk.setEditable(false);
		DecNtwrk.setColumns(10);
		DecNtwrk.setBorder(null);
		DecNtwrk.setBackground(new Color(0, 0, 102));
		
		JLabel DecBrod_Label = new JLabel("Broadcast Address");
		DecBrod_Label.setForeground(Color.WHITE);
		DecBrod_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		DecBrod = new JTextField();
		DecBrod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DecBrod.setBackground(new Color(0, 0, 170));
				DecBrod.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				DecBrod.setBackground(new Color(0, 0, 102));
				DecBrod.setForeground(new Color(0, 204, 255));
			}
		});
		DecBrod.setForeground(new Color(0, 204, 255));
		DecBrod.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		DecBrod.setEditable(false);
		DecBrod.setColumns(10);
		DecBrod.setBorder(null);
		DecBrod.setBackground(new Color(0, 0, 102));
		
		JLabel ntwrkAvailable_Label = new JLabel("Number of Subnet");
		ntwrkAvailable_Label.setForeground(Color.WHITE);
		ntwrkAvailable_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		ntwrkAvailable = new JTextField();
		ntwrkAvailable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ntwrkAvailable.setBackground(new Color(0, 0, 170));
				ntwrkAvailable.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ntwrkAvailable.setBackground(new Color(0, 0, 102));
				ntwrkAvailable.setForeground(new Color(0, 204, 255));
			}
		});
		ntwrkAvailable.setForeground(new Color(0, 204, 255));
		ntwrkAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ntwrkAvailable.setEditable(false);
		ntwrkAvailable.setColumns(10);
		ntwrkAvailable.setBorder(null);
		ntwrkAvailable.setBackground(new Color(0, 0, 102));
		
		JLabel HostSub_Label = new JLabel("Host Per Subnet");
		HostSub_Label.setForeground(Color.WHITE);
		HostSub_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		HostSub = new JTextField();
		HostSub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				HostSub.setBackground(new Color(0, 0, 170));
				HostSub.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				HostSub.setBackground(new Color(0, 0, 102));
				HostSub.setForeground(new Color(0, 204, 255));
			}
		});
		HostSub.setForeground(new Color(0, 204, 255));
		HostSub.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		HostSub.setEditable(false);
		HostSub.setColumns(10);
		HostSub.setBorder(null);
		HostSub.setBackground(new Color(0, 0, 102));
		
		JLabel netwrkClass_Label = new JLabel("Network Class");
		netwrkClass_Label.setForeground(Color.WHITE);
		netwrkClass_Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		netwrkClass = new JTextField();
		netwrkClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				netwrkClass.setBackground(new Color(0, 0, 170));
				netwrkClass.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				netwrkClass.setBackground(new Color(0, 0, 102));
				netwrkClass.setForeground(new Color(0, 204, 255));
			}
		});
		netwrkClass.setForeground(new Color(0, 204, 255));
		netwrkClass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		netwrkClass.setEditable(false);
		netwrkClass.setColumns(10);
		netwrkClass.setBorder(null);
		netwrkClass.setBackground(new Color(0, 0, 102));
		
		GroupLayout gl_panel_2 = new GroupLayout(subPanel);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(Dec_IP_Label, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(Dec_IP, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(DecSubmask_Label, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(DecSubmask, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(DecNtwrk_Label, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(DecNtwrk, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(DecBrod_Label, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(DecBrod, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(ntwrkAvailable_Label, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(ntwrkAvailable, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(netwrkClass_Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(HostSub_Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
									.addGap(10)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(netwrkClass)
										.addComponent(HostSub, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(263)
							.addComponent(Binary_Label)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(Binary_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(Dec_IP_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(Dec_IP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(DecSubmask_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(DecSubmask, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(DecNtwrk_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(DecNtwrk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(DecBrod_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(DecBrod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(ntwrkAvailable_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(ntwrkAvailable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(HostSub_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(HostSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(netwrkClass_Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(netwrkClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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