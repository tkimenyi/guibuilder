package gui;
import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

//couldn't really figure out what to test here...there is a lot of GUI code that we human tested

public class GUIBuilderTest {
	@Test
	public void test() throws FileNotFoundException {
		ComponentsPanel compPan = new ComponentsPanel();
		GUI gui = new GUI("Brandon");
		PopUp popup = new PopUp();
		UserGUI ugui = new UserGUI("Brandon");
		Main main = new Main();	
		JButton button = new JButton();
		button.setSize(100,100);
		compPan.addLabelToContainers(button, new JLabel());
		
		Dimension dim = new Dimension(100,100);
		dim.setSize( 100,100);
		System.out.println("dim = " +dim +"button dim = " + compPan.getDimension("button"));
		//assertEquals(compPan.getDimension("button"), dim);
		
		
	}
}
