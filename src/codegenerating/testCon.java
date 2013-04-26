package codegenerating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testCon extends JPanel implements ActionListener{
	JPanel jpanel1;
	JButton jbutton1;
	JTextArea jtextarea1;
	JTextField jtextfield1;
	
	public testCon(){
		
		JFrame frame = new JFrame("testConnection");
		
		jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel1.setPreferredSize(new Dimension(400,400));
		jbutton1 = new JButton();
		jbutton1.setPreferredSize(new Dimension(34,10));
		jtextarea1 = new JTextArea();
		jtextfield1 = new JTextField();
		jpanel1.setLayout(new GridLayout( 6, 6));
		jpanel1.add(jbutton1,-1, -1);
		jpanel1.add(jtextarea1,-1, -1);
		jpanel1.add(jtextfield1,-1, -1);
		
		jbutton1.addActionListener(this);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent evt) { 
		 if(evt.getSource() == jbutton1){
			System.out.println("Hello");
		}}
		
		public static void main(String[] args){
			
			testCon run = new testCon();
			run.setVisible(true);
		}
	}
