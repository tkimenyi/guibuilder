package gui;
import static org.junit.Assert.*;

import java.awt.Point;
import java.io.FileNotFoundException;

import org.junit.Test;

//couldn't really figure out what to test here...there is a lot of GUI code that we human tested

public class GUIBuilderTest {
	@Test
	public void test() throws FileNotFoundException {
		ComponentsPanel comp = new ComponentsPanel();
		GUI gui = new GUI("Brandon");
		PopUp popup = new PopUp();
		UserGUI ugui = new UserGUI("Brandon");
		Main main = new Main();		
		
	}
}
