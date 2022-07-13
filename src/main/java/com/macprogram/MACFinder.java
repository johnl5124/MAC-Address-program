package com.macprogram;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
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
	
	public MACFinder()
	{
		
		 // FRAME
		 setSize(640, 400);
		 setLocation(750, 750);
		 setTitle("Robot Setup");
//		 JFrame frame = new JFrame("Robot Setup");		 
//		 frame.setSize(750, 750);
		 setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // border layout is good
		 setLocationRelativeTo(null);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setIconImage(new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg").getImage());
		 
//		 //PANEL
// 		 panel = new JPanel();
// 		 panel.setSize(300, 300);
// 		 panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		 
//		 // IMAGE
//		 ImageIcon icon = new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg");
//		 Image scaleImage = icon.getImage().getScaledInstance(28, 28,Image.SCALE_DEFAULT);
//		 getContentPane().add(new JLabel(new ImageIcon("/Users/johnlloyd/Documents/Coding/Brunel Internship/BrunelLogo.jpg")));
		 
		 // TEXTFIELDS
		 student_ID = new JTextField("Student ID"); // should only take numbers (equal to 7 digits)
		 student_ID.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		 add(student_ID, Component.CENTER_ALIGNMENT); 
		 
		 first_name = new JTextField("First name");
		 first_name.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		 add(first_name, Component.CENTER_ALIGNMENT); 
		 
		 last_name = new JTextField("Last name");
		 last_name.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		 add(last_name, Component.CENTER_ALIGNMENT); 
		 
		 // BUTTONS
		 confirmButton = new JButton("Confirm");
		 //confirmButton.setBounds(50, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		 add(confirmButton, Component.CENTER_ALIGNMENT);
		 confirmButton.addActionListener(this);
		 
		 cancelButton = new JButton("Cancel");
		 //cancelButton.setBounds(50, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		 add(cancelButton, Component.RIGHT_ALIGNMENT);
		
		 // Set the window to be visible as the default to be false
		 //add(panel);
		 //pack();
		 setVisible(true); 
	}
	
    public static void main(String[] args)
    {
        System.out.println("Welcome... starting GUI");
        
        // invokes constructor (for frame)
        new MACFinder();
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == confirmButton)
		{
			System.out.println("Your student ID is: " + student_ID.getText());
		}
		
	}    
}
