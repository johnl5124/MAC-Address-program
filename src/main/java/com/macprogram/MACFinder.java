package com.macprogram;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.awt.event.*;
import javax.swing.*;

// https://www.javatpoint.com/java-swing -- as a reference for learning
// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html -- layouts...

public class MACFinder extends JFrame implements ActionListener
{
	// constants for button/textfield sizes etc..
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 20;
	private final int TEXTFIELD_WIDTH = 100;
	private final int TEXTFIELD_HEIGHT = 20;
	
	JTextField student_ID, first_name, last_name;
	JButton confirmButton, cancelButton;
	JPanel panel;
	
	InetAddress localHost;
	
	public MACFinder()
	{	
		// FRAME 
		setSize(640, 400);
		setLocation(750, 750);
		setTitle("Robot Setup");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // border layout is good
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg").getImage());
		 
		// IMAGE
//		ImageIcon icon = new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg");
//		Image scaleImage = icon.getImage().getScaledInstance(28, 28,Image.SCALE_DEFAULT);
//		getContentPane().add(new JLabel(new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg")));
		 
		 // TEXTFIELDS
		student_ID = new JTextField(); // should only take numbers (equal to 7 digits)
		student_ID.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		student_ID.setHorizontalAlignment(JTextField.CENTER);
		student_ID.addKeyListener(new KeyAdapter() 
		{
	        public void keyPressed(KeyEvent ke) 
	        {
	            String value = student_ID.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') // -- need to include backspace && ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) 
	            {
	               student_ID.setEditable(true);
	            } 
	            else 
	            {
	               student_ID.setEditable(false);
	               System.out.println("* Enter only numeric digits(0-9)");
	            }
	         }
	      });
		add(student_ID, Component.CENTER_ALIGNMENT); 
		 
		first_name = new JTextField();
		first_name.addKeyListener(new KeyAdapter() 
		{
	        public void keyPressed(KeyEvent ke) 
	        {
	            String value = first_name.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') 
	            {
	               first_name.setEditable(false);
	               System.out.println("Error, only input letters");
	            } 
	            else 
	            {
	               first_name.setEditable(true);
	            }
	         }
	      });
		first_name.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		first_name.setHorizontalAlignment(JTextField.CENTER);
		add(first_name, Component.CENTER_ALIGNMENT); 
		 
		last_name = new JTextField();
		last_name.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		last_name.setHorizontalAlignment(JTextField.CENTER);
		add(last_name, Component.CENTER_ALIGNMENT); 
		 
		// BUTTONS
		confirmButton = new JButton("Confirm");
		//confirmButton.setBounds(50, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		add(confirmButton, Component.CENTER_ALIGNMENT);
		confirmButton.addActionListener(this);
		 
		cancelButton = new JButton("Cancel");
		//cancelButton.setBounds(50, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		add(cancelButton, Component.RIGHT_ALIGNMENT);
		
		setVisible(true); 
	}
	
    public static void main(String[] args) throws Exception
    {
        System.out.println("Welcome... starting GUI");
        
        // invokes constructor (for frame)
        new MACFinder();
        
     // gets Internet address of local machine
     		InetAddress ip = InetAddress.getLocalHost();
     		System.out.println("Current IP address: " + ip);
     		
     		// get network interface that has ip address bound to it
     		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
     		
     		// get MAC address from the network interface in byte
     		byte[] mac = network.getHardwareAddress();
     		System.out.println("Current MAC address: " + mac);
     		
     		// displays MAC address
     		StringBuilder sb = new StringBuilder();
     		for(int i = 0; i < mac.length; i++)
     		{
     			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
     		}
     		
     		System.out.println(sb.toString());
        
       // MACFinder GM = new MACFinder();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (student_ID.getText().equals(""))
		{
			System.out.println("Error, please enter your student ID");
		}
		else if (student_ID.getText().length() != 7)
		{
			System.out.println("Error, your student ID must be 7 digits in length");
		}
		else if (first_name.getText().equals(""))
		{
			System.out.println("Error, please enter your first name");
		}
		else if (last_name.getText().equals(""))
		{
			System.out.println("Error, please enter your last name");
		}
		else
		{
			System.out.println("Your student ID is: " + student_ID.getText());
			System.out.println("Your first name is: " + first_name.getText());
			System.out.println("Your last name is: " + last_name.getText());
		}
	}    
	
	public void getMac() throws Exception
	{		
		// gets Internet address of local machine
		InetAddress ip = InetAddress.getLocalHost();
		System.out.println("Current IP address: " + ip);
		
		// get network interface that has ip address bound to it
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		
		// get MAC address from the network interface in byte
		byte[] mac = network.getHardwareAddress();
		System.out.println("Current MAC address: " + mac);
		
		// displays MAC address
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mac.length; i++)
		{
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		
		System.out.println(sb.toString());
	}
}
