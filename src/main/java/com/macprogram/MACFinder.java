package com.macprogram;

import javax.swing.*;

// https://www.javatpoint.com/java-swing -- as a reference for learning
// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html -- layouts...

public class MACFinder
{
	// constants for button/textfield sizes etc..
	private final int BUTTON_WIDTH = 400;
	private final int BUTTON_HEIGHT = 75;
	private final int TEXTFIELD_WIDTH = 200;
	private final int TEXTFIELD_HEIGHT = 30;
	
	JTextField student_ID, first_name, last_name;
	JPanel panel;
	
	public MACFinder()
	{
		 // FRAME
		 JFrame frame = new JFrame("Robot Setup");		 
		 //frame.setSize(750, 750);
		 frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // may change this
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 // PANEL
		 panel = new JPanel();
		 panel.setSize(300, 300);
		 GroupLayout layout = new GroupLayout(panel);
		 layout.setAutoCreateGaps(true);
         layout.setAutoCreateContainerGaps(true);
         panel.setLayout(layout);
		 
		 // TEXTFIELDS
		 student_ID = new JTextField("Student ID"); // should only take numbers (equal to 7 digits)
		 student_ID.setBounds(50, 50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		 first_name = new JTextField("First name");
		 first_name.setBounds(50, 100, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		 last_name = new JTextField("Last name");
		 last_name.setBounds(50, 150, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		 
         // Set for horizontal and vertical group
         layout.setHorizontalGroup(layout.createSequentialGroup()
        		 .addComponent(student_ID).addComponent(first_name).addComponent(last_name)
                 .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                		 .addComponent(student_ID).addComponent(first_name))));
         layout.setVerticalGroup(
                 layout.createSequentialGroup().addComponent(student_ID).addComponent(first_name).addComponent(last_name));
         // Set the window to be visible as the default to be false
		 
		 frame.add(panel);
		 frame.add(student_ID); frame.add(first_name); frame.add(last_name);
		 frame.pack();
		 frame.setVisible(true); 
	}
	
    public static void main(String[] args)
    {
        System.out.println("Welcome... starting GUI");
        
        // invokes constructor (for frame)
        new MACFinder();
    }    
}
