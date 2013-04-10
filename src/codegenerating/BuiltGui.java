package codegenerating;

import javax.swing.*;
import java.awt.*;

public class BuiltGui extends JPanel {
	public static void main(String[] args){
		JFrame frame = new JFrame("user gui");
		
		JPanel jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel1.setPreferredSize(new Dimension(100,30));
		JList jlist2 = new JList();
		jlist2.setBounds(0,27,554,461);
		jlist2.setBorder(BorderFactory.createLineBorder(Color.black));
				jlist2.setPreferredSize(new Dimension(200,400));
		JButton jbutton2 = new JButton();
		jbutton2.setBounds(45,636,100,30);
		jbutton2.setBorder(BorderFactory.createLineBorder(Color.black));
				jbutton2.setPreferredSize(new Dimension(100,30));
		JButton jbutton4 = new JButton();
		jbutton4.setBounds(-4,587,63,50);
		jbutton4.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton4.setText("L");
		jbutton4.setPreferredSize(new Dimension(100,30));
		JButton jbutton6 = new JButton();
		jbutton6.setBounds(56,587,61,50);
		jbutton6.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton6.setText("D");
		jbutton6.setPreferredSize(new Dimension(100,30));
		JButton jbutton8 = new JButton();
		jbutton8.setBounds(117,586,63,50);
		jbutton8.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton8.setText("R");
		jbutton8.setPreferredSize(new Dimension(100,30));
		JButton jbutton10 = new JButton();
		jbutton10.setBounds(56,545,62,50);
		jbutton10.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton10.setText("U");
		jbutton10.setPreferredSize(new Dimension(100,30));
		JTextArea jtextarea2 = new JTextArea();
		jtextarea2.setBounds(197,509,361,127);
		jtextarea2.setBorder(BorderFactory.createLineBorder(Color.black));
		jtextarea2.setText("Editor: ");
		jtextarea2.setPreferredSize(new Dimension(100,100));
		JTextField jtextfield2 = new JTextField();
		jtextfield2.setBounds(197,483,359,30);
		jtextfield2.setBorder(BorderFactory.createLineBorder(Color.black));
		jtextfield2.setText("TextField");
		jtextfield2.setPreferredSize(new Dimension(100,30));
		JScrollBar jscrollbar2 = new JScrollBar();
		jscrollbar2.setBounds(554,0,30,636);
		jscrollbar2.setBorder(BorderFactory.createLineBorder(Color.black));
				jscrollbar2.setPreferredSize(new Dimension(30,100));
		JSlider jslider2 = new JSlider();
		jslider2.setBounds(1,2,553,30);
		jslider2.setBorder(BorderFactory.createLineBorder(Color.black));
				jslider2.setPreferredSize(new Dimension(100,30));
		jpanel1.add(jlist2);
		jpanel1.add(jbutton2);
		jpanel1.add(jbutton4);
		jpanel1.add(jbutton6);
		jpanel1.add(jbutton8);
		jpanel1.add(jbutton10);
		jpanel1.add(jtextarea2);
		jpanel1.add(jtextfield2);
		jpanel1.add(jscrollbar2);
		jpanel1.add(jslider2);
		
		frame.add(jpanel1);
		frame.setSize(650,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
