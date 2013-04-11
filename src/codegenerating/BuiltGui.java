package codegenerating;

import javax.swing.*;
import java.awt.*;

public class BuiltGui extends JPanel {
	public static void main(String[] args){
		JFrame frame = new JFrame("user gui");
		
		JPanel jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel1.setPreferredSize(new Dimension(100,100));
		JPanel jpanel3 = new JPanel();
		jpanel3.setBounds(180,42,337,366);
		jpanel3.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel3.setPreferredSize(new Dimension(100,100));
		JButton jbutton2 = new JButton();
		jbutton2.setBounds(110,5,100,30);
		jbutton2.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton2.setPreferredSize(new Dimension(100,30));
		JCheckBox jcheckbox2 = new JCheckBox();
		jcheckbox2.setBounds(258,236,50,50);
		jcheckbox2.setBorder(BorderFactory.createLineBorder(Color.black));
		jcheckbox2.setPreferredSize(new Dimension(50,50));
		JPasswordField jpasswordfield2 = new JPasswordField();
		jpasswordfield2.setBounds(51,113,100,30);
		jpasswordfield2.setBorder(BorderFactory.createLineBorder(Color.black));
		jpasswordfield2.setText("TextArea1");
		jpasswordfield2.setPreferredSize(new Dimension(100,30));
		jpanel1.add(jpanel3);
		jpanel1.add(jbutton2);
		jpanel1.add(jcheckbox2);
		jpanel1.add(jpasswordfield2);
		
		frame.add(jpanel1);
		frame.setSize(594,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
