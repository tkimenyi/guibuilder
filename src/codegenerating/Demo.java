package codegenerating;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Demo extends JPanel{
	JButton testButton;
	JMenuBar newMenu;
	
	public Demo(){
		testButton = new JButton("test");
		testButton.setSize(20,30);
		this.setLayout(new BorderLayout());
		this.add(testButton, BorderLayout.AFTER_LAST_LINE);
	}
	
	public static void createFrame(){
		JFrame mainFrame = new JFrame("Code generator demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 500);
		
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.getContentPane().add(new Demo(), BorderLayout.CENTER);
		
		// display the window
		mainFrame.setVisible(true);
		
	}
	
//	public static void main(String [] args){
//		createFrame();
//		JButton button = new JButton("coded");
//		JPanel panel = new JPanel();
//		System.out.println(button.toString());
//		generator newgen = new generator();
//		newgen.generateCodeFile();
//		newgen.addComponentToPanelCode("button", "panel");
//		System.out.println("just generated some code");
//	}
}

