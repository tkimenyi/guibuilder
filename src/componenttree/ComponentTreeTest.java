package componenttree;

import javax.swing.*;

import static org.junit.Assert.*;

import java.awt.*;

import org.junit.Test;

public class ComponentTreeTest {

	@Test
	public void test() {
		ComponentManager tree = new ComponentManager();
		assertEquals(tree.getSize(), 0);
		ContainerItem comp = new ContainerItem(new JPanel(), "JPanel");
		ControlItem button = new ControlItem(new JButton(),"JButton");
		ContainerItem mom = new ContainerItem(new JPanel(), "JFrame");
		tree.setRoot(mom);
		tree.addChild(mom, comp, "JPanel", new Dimension(600,600));
		tree.addChild(comp, button, "JButton", new Dimension (100,30));
		assertEquals(tree.getSize(), 2);
		ContainerItem root = tree.getRoot();
		assertEquals(root, mom);
		assertEquals(button.getParent(), comp);
		System.out.println(tree.toString());
	}
	
	private ComponentItem toTest(){
		ContainerItem root = new ContainerItem(new JPanel(), "JPanel");
		ContainerItem panel1 = new ContainerItem(new JPanel(), "JPanel");
		ContainerItem panel2 = new ContainerItem(new JPanel(), "JPanel");
		
		ControlItem area1 = new ControlItem(new JTextArea(), "JTextArea");

		ControlItem textfield1 = new ControlItem(new JTextField(), "JTextField");

		ControlItem button1 = new ControlItem(new JButton(), "JButton");
		panel1.addChildComponent(area1);
		panel1.addChildComponent(textfield1);
		panel1.addChildComponent(button1);
		root.addChildComponent(panel1);
		root.addChildComponent(panel2);
		
		ControlItem list1 = new ControlItem(new JList(), "JList");
		panel2.addChildComponent(list1);
		ControlItem spinner1 = new ControlItem(new JSpinner(), "JSpinner");
		panel2.addChildComponent(spinner1);
	
		return root;
	}

}
