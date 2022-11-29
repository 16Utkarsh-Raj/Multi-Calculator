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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Converter {

	public JFrame frame;
	private JButton Backbtn;
	private JButton Nxtbtn;
	
	private static String[] page_arr=new String[100];
	private int index=0;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Converter window = new Converter(false, page_arr, -1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public Converter(Boolean prev_nxt_clicked, String[] elements, int indx) {
		initialize();
		prev_next obj=new prev_next(elements);
		page_arr=obj.add(prev_nxt_clicked, "cnvrtr", indx);
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
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 650, 411);
		frame.setTitle("Converter");
		frame.setLocationRelativeTo(null); // For setting frame center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 100, 0, 100, 100, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_panel);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(new Color(0, 0, 51));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 11;
		gbc_panel_2.gridwidth = 11;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		mainPanel.add(subPanel, gbc_panel_2);
		
		JLabel lblCnvrtr = new JLabel("Converter");
		lblCnvrtr.setForeground(new Color(255, 255, 255));
		lblCnvrtr.setBackground(new Color(0, 0, 51));
		lblCnvrtr.setHorizontalAlignment(SwingConstants.CENTER);
		lblCnvrtr.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		
		JButton btnWeight = new JButton("Weight and Mass");
		btnWeight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnWeight.setForeground(new Color(0, 204, 255));
				btnWeight.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnWeight.setForeground(new Color(255, 255, 255));
				btnWeight.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Weight_Converter Con=new Weight_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnWeight.setBorder(null);
		btnWeight.setFocusable(false);
		btnWeight.setForeground(new Color(255, 255, 255));
		btnWeight.setBackground(new Color(0, 0, 102));
		btnWeight.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		
		JButton btnLength = new JButton("Length");
		btnLength.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLength.setForeground(new Color(0, 204, 255));
				btnLength.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLength.setForeground(new Color(255, 255, 255));
				btnLength.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Length_Converter Con=new Length_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnLength.setForeground(Color.WHITE);
		btnLength.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		btnLength.setFocusable(false);
		btnLength.setBorder(null);
		btnLength.setBackground(new Color(0, 0, 102));
		
		JButton btnTemp = new JButton("Temperature");
		btnTemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTemp.setForeground(new Color(0, 204, 255));
				btnTemp.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnTemp.setForeground(new Color(255, 255, 255));
				btnTemp.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Temp_Converter Con=new Temp_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnTemp.setForeground(Color.WHITE);
		btnTemp.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		btnTemp.setFocusable(false);
		btnTemp.setBorder(null);
		btnTemp.setBackground(new Color(0, 0, 102));
		
		JButton btnTime = new JButton("Time");
		btnTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTime.setForeground(new Color(0, 204, 255));
				btnTime.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnTime.setForeground(new Color(255, 255, 255));
				btnTime.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Time_Converter Con=new Time_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnTime.setForeground(Color.WHITE);
		btnTime.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		btnTime.setFocusable(false);
		btnTime.setBorder(null);
		btnTime.setBackground(new Color(0, 0, 102));
		
		JButton btnAngle = new JButton("Angle");
		btnAngle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAngle.setForeground(new Color(0, 204, 255));
				btnAngle.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAngle.setForeground(new Color(255, 255, 255));
				btnAngle.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Angle_Converter Con=new Angle_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnAngle.setForeground(Color.WHITE);
		btnAngle.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		btnAngle.setFocusable(false);
		btnAngle.setBorder(null);
		btnAngle.setBackground(new Color(0, 0, 102));
		
		JButton btnData = new JButton("Data");
		btnData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnData.setForeground(new Color(0, 204, 255));
				btnData.setBackground(new Color(0, 0, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnData.setForeground(new Color(255, 255, 255));
				btnData.setBackground(new Color(0, 0, 102));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Data_Converter Con=new Data_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnData.setForeground(Color.WHITE);
		btnData.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 18));
		btnData.setFocusable(false);
		btnData.setBorder(null);
		btnData.setBackground(new Color(0, 0, 102));
		
		GroupLayout gl_panel_2 = new GroupLayout(subPanel);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(186)
							.addComponent(lblCnvrtr, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 186, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLength, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTemp, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAngle, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(btnData, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTime, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnWeight, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))))
					.addGap(21))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(23)
					.addComponent(lblCnvrtr, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLength, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnWeight, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTemp, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTime, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAngle, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnData, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
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
		CnvrtrMenu.setEnabled(false);
		CnvrtrMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		CnvrtrMenu.setHorizontalAlignment(SwingConstants.CENTER);
		CnvrtrMenu.setForeground(new Color(255, 255, 255));
		CnvrtrMenu.setFont(new Font("Rockwell", Font.PLAIN, 13));
		CnvrtrMenu.setBackground(new Color(0, 0, 90));
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
				Time_Converter Con=new Time_Converter(false, page_arr, index);
				Con.frame.setVisible(true);
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
