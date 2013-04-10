package componenttree;

import javax.swing.*;

import static org.junit.Assert.*;

import java.awt.*;

import org.junit.Test;

public class ComponentTreeTest {

	@Test
	public void testComponentTree(){
		ContainerItem root = new ContainerItem(new JPanel(), "JPanel",new Dimension(100,100));
		ContainerItem panel1 = new ContainerItem(new JPanel(), "JPanel",new Dimension(100,100));
		ContainerItem panel2 = new ContainerItem(new JPanel(), "JPanel",new Dimension(100,100));
		ContainerItem panel3 = new ContainerItem(new JPanel(), "JPanel", new Dimension(23, 45));

		ControlItem area1 = new ControlItem(new JTextArea(), "JTextArea",new Dimension(100,100));

		ControlItem textfield1 = new ControlItem(new JTextField(), "JTextField",new Dimension(100,100));
		ComponentTreeStruct manager = new ComponentTreeStruct();
		
		ControlItem button1 = new ControlItem(new JButton(), "JButton",new Dimension(100,100));
		manager.addChild(root, panel1, "JPanel", new Dimension(34, 34));
		manager.addChild(panel1,  area1, "JPanel", new Dimension(34, 56));
		manager.addChild(panel1,  textfield1, "JPanel", new Dimension(34, 56));
		manager.addChild(panel1,  button1, "JPanel", new Dimension(34, 56));
		manager.addChild(root,  panel2, "JPanel", new Dimension(34, 56));
		manager.addChild(root,  panel3, "JPanel", new Dimension(34, 56));	
		assertTrue(manager.getRoot() == root);
		assertTrue(manager.getSize() == 7);
		assertTrue(root.getName().equalsIgnoreCase("jpanel1"));
		assertTrue(root.getPreferredSize().getWidth() == 100);
		String name = root.getName();
		assertEquals(name, "JPanel1");
		assertEquals(root.getPreferredSize(), new Dimension(100,100));
		assertEquals(root.getType(), "JPanel");
		root.setBorderLocation("north");
		root.setGridLocation(22, 44);
		root.setGridSpan(4, 5);
		assertEquals(root.getBorderLocation(), "north");
		assertEquals(root.getGridLocation(), new Point(22,44));
		assertEquals(root.getGridSpan(), new Point(4,5));
		
	}
	

}
