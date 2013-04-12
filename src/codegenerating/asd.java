package codegenerating;

import javax.swing.*;
import java.awt.*;

public class asd extends JPanel {
	public static void main(String[] args){
		JFrame frame = new JFrame("Btra");
		
		JPanel jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel1.setPreferredSize(new Dimension(100,100));
		
		frame.add(jpanel1);
		frame.setSize(594,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
