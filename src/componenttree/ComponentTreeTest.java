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
		ContainerItem panel4 = new ContainerItem(new JPanel(), "JPanel", new Dimension(45, 45));
		ContainerItem panel5 = new ContainerItem(new JPanel(), "JPanel", new Dimension(230, 45));
		ContainerItem panel6 = new ContainerItem(new JPanel(), "JPanel", new Dimension(49, 45));
		
		ComponentItem area1 = new ComponentItem(new JTextArea(), "JTextArea",new Dimension(100,100));

		ComponentItem textfield1 = new ComponentItem(new JTextField(), "JTextField",new Dimension(100,100));
		ComponentTreeStruct manager = new ComponentTreeStruct();
		manager.setRoot(root);
		
		ComponentItem button1 = new ComponentItem(new JButton(), "JButton",new Dimension(100,100));
		manager.setRoot(root);
		assertTrue(manager.getRoot() ==root);
		manager.addChild(root, panel1, "JPanel", new Dimension(34, 34));
		manager.addChild(panel1,  area1, "JPanel", new Dimension(34, 56));
		manager.addChild(panel1,  textfield1, "JPanel", new Dimension(34, 56));
		manager.addChild(panel1,  button1, "JPanel", new Dimension(34, 56));
		manager.addChild(root,  panel2, "JPanel", new Dimension(34, 56));
		manager.addChild(root,  panel3, "JPanel", new Dimension(34, 56));
		manager.addBorderChild(panel1, panel4, "west", "JPanel", new Dimension(100,100));
		assertTrue(panel4.getBorderLocation() == "west");
		assertTrue(panel4.getParent() == panel1);
		assertTrue(panel1.removeChildComponent(panel4) ==true);
		
		assertTrue(manager.getRoot() == root);		
		assertTrue(root.getName().equalsIgnoreCase("jpanel1"));
		assertTrue(root.getPreferredSize().getWidth() == 100);
		assertTrue(panel1.getParent()==root);
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
		assertTrue(panel1.getText() == "");
		manager.addGridChild(panel1, panel5, 50, 50, "JPanel", new Dimension(100,100));
		assertTrue(panel5.getParent() ==panel1);
		System.out.println(panel5.getGridLocation());
		assertTrue(panel5.getGridLocation().x == 50);
		assertTrue(panel5.getGridLocation().y == 50);
		manager.addGridChild(panel1, panel6, 50, 50, 15, 15, "JPanel", new Dimension(100,100));
		assertTrue(panel6.getParent() == panel1);
		assertTrue(panel6.getGridSpan().x == 15);
		assertTrue(panel6.getGridSpan().y == 15);
	}
	

}
