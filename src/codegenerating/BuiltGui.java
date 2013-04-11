package codegenerating;

import javax.swing.*;
import java.awt.*;

public class BuiltGui extends JPanel {
	public static void main(String[] args){
		JFrame frame = new JFrame("user gui");
		
		JPanel jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel1.setPreferredSize(new Dimension(100,100));
		JButton jbutton2 = new JButton();
		jbutton2.setBounds(380,181,100,30);
		jbutton2.setBorder(BorderFactory.createLineBorder(Color.black));
				jbutton2.setPreferredSize(new Dimension(100,30));
		JButton jbutton4 = new JButton();
		jbutton4.setBounds(223,240,100,30);
		jbutton4.setBorder(BorderFactory.createLineBorder(Color.black));
				jbutton4.setPreferredSize(new Dimension(100,30));
		JButton jbutton6 = new JButton();
		jbutton6.setBounds(284,117,100,30);
		jbutton6.setBorder(BorderFactory.createLineBorder(Color.black));
				jbutton6.setPreferredSize(new Dimension(100,30));
		JButton jbutton8 = new JButton();
		jbutton8.setBounds(134,189,100,30);
		jbutton8.setBorder(BorderFactory.createLineBorder(Color.black));
				jbutton8.setPreferredSize(new Dimension(100,30));
		JEditorPane jeditorpane2 = new JEditorPane();
		jeditorpane2.setBounds(350,375,100,100);
		jeditorpane2.setBorder(BorderFactory.createLineBorder(Color.black));
				jeditorpane2.setPreferredSize(new Dimension(100,100));
		JEditorPane jeditorpane4 = new JEditorPane();
		jeditorpane4.setBounds(146,333,100,100);
		jeditorpane4.setBorder(BorderFactory.createLineBorder(Color.black));
				jeditorpane4.setPreferredSize(new Dimension(100,100));
		JEditorPane jeditorpane6 = new JEditorPane();
		jeditorpane6.setBounds(438,29,100,100);
		jeditorpane6.setBorder(BorderFactory.createLineBorder(Color.black));
				jeditorpane6.setPreferredSize(new Dimension(100,100));
		jpanel1.add(jbutton2);
		jpanel1.add(jbutton4);
		jpanel1.add(jbutton6);
		jpanel1.add(jbutton8);
		jpanel1.add(jeditorpane2);
		jpanel1.add(jeditorpane4);
		jpanel1.add(jeditorpane6);
		
		frame.add(jpanel1);
		frame.setSize(650,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
