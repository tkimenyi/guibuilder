package brandon.gui;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PopUp extends JFrame{
	JPanel panel = new JPanel();
	JOptionPane message = new JOptionPane();
	int choice = 0;
	String GUIname;
	
	public PopUp(){
		super("Welcome to GUI Builder");
		StartAll();
	}

	@SuppressWarnings("static-access")
	public void StartAll(){
		setLayout(new BorderLayout());
		setSize(500, 250);
		setLocation(825, 425);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Color.white);
		message.showMessageDialog(this,"Hello user! Welcome to the GUI Builder! Please press continue!");
		GUIname = message.showInputDialog(this, "Please name your GUI");
		
	}	
}