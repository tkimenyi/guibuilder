package codegenerating;

import javax.swing.*;
import java.awt.*;

public class BuiltGui extends JPanel {
	public static void main(String[] args){
		JFrame frame = new JFrame("user gui");
		
		JPanel jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel1.setPreferredSize(new Dimension(200,400));
		JCheckBox jcheckbox2 = new JCheckBox();
		jcheckbox2.setBounds(267,221,50,50);
		jcheckbox2.setPreferredSize(new Dimension(50,50));
		JButton jbutton2 = new JButton();
		jbutton2.setBounds(332,318,100,30);
		jbutton2.setPreferredSize(new Dimension(100,30));
		JComboBox jcombobox2 = new JComboBox();
		jcombobox2.setBounds(283,366,100,30);
		jcombobox2.setPreferredSize(new Dimension(100,30));
		JEditorPane jeditorpane2 = new JEditorPane();
		jeditorpane2.setBounds(389,157,100,100);
		jeditorpane2.setPreferredSize(new Dimension(100,100));
		JCheckBox jcheckbox4 = new JCheckBox();
		jcheckbox4.setBounds(233,157,50,50);
		jcheckbox4.setPreferredSize(new Dimension(50,50));
		JComboBox jcombobox4 = new JComboBox();
		jcombobox4.setBounds(211,301,100,30);
		jcombobox4.setPreferredSize(new Dimension(100,30));
		JScrollBar jscrollbar2 = new JScrollBar();
		jscrollbar2.setBounds(252,462,30,100);
		jscrollbar2.setPreferredSize(new Dimension(30,100));
		JList jlist2 = new JList();
		jlist2.setBounds(-8,95,200,400);
		jlist2.setPreferredSize(new Dimension(200,400));
		jpanel1.add(jcheckbox2);
		jpanel1.add(jbutton2);
		jpanel1.add(jcombobox2);
		jpanel1.add(jeditorpane2);
		jpanel1.add(jcheckbox4);
		jpanel1.add(jcombobox4);
		jpanel1.add(jscrollbar2);
		jpanel1.add(jlist2);
		
		frame.add(jpanel1);
		frame.setSize(650,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
