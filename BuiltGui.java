package codegenerating;

import javax.swing.*;
import java.awt.*;

public class BuiltGui extends JPanel {
	public static void main(String[] args){
		JFrame frame = new JFrame("user gui");
		
		JPanel jpanel1 = new JPanel(null);
jpanel1.setPreferredSize(new Dimension(400,400));
		JButton jbutton1 = new JButton();
		jbutton1.setPreferredSize(new Dimension(34,10));
		JTextArea jtextarea1 = new JTextArea();
				JTextField jtextfield1 = new JTextField();
				jpanel1.add(jbutton1);
		jpanel1.add(jtextarea1);
		jpanel1.add(jtextfield1);
		
		
	}
}

