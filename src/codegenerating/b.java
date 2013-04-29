package codegenerating;

import javax.swing.*;
import java.awt.*;

public class b extends JPanel{
	JPanel jpanel1;
	JMenuBar jmenubar1;
	JMenu jmenu1;
	JMenu jmenu2;
	
	public b(){
		
		JFrame frame = new JFrame("b");
		
		jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jmenubar1 = new JMenuBar();
		jmenubar1.setBorder(BorderFactory.createLineBorder(Color.black));
		jmenu1 = new JMenu();
		jmenu1.setBorder(BorderFactory.createLineBorder(Color.black));
		jmenu1.setText("File");
		jmenu1.setPreferredSize(new Dimension(37,19));
		jmenu2 = new JMenu();
		jmenu2.setBorder(BorderFactory.createLineBorder(Color.black));
		jmenu2.setText("Safari");
		jmenu2.setPreferredSize(new Dimension(45,19));
		jmenubar1.add(jmenu1);
		jmenubar1.add(jmenu2);
		jpanel1.add(jmenubar1);
		
		
		frame.add(jpanel1);
		frame.setSize(594,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args){
		
		b run = new b();
		run.setVisible(true);
	}
}
