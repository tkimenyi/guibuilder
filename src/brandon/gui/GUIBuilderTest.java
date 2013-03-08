package brandon.gui;
import static org.junit.Assert.*;

import java.awt.Point;
import java.io.FileNotFoundException;

import org.junit.Test;



public class GUIBuilderTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws FileNotFoundException {
		CompPanel comp = new CompPanel();
		GUI gui = new GUI("Brandon");
		PopUp popup = new PopUp();
		UserGUI ugui = new UserGUI("Brandon");
		Main main = new Main();		
		
		ugui.setPanelCoordinates(50, 60);
		Point last = ugui.getLastMouseLocation();
		assertEquals(new Point (50,60), last);
	}

}
