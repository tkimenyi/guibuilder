package componenttree;

import javax.swing.*;

import static org.junit.Assert.*;

import java.awt.*;

import org.junit.Before;
import org.junit.Test;

public class ComponentTreeTest {

        ContainerItem root, panel1, panel2, panel3, panel4, panel5, panel6;
        ComponentItem area1,textfield1, button1 ;
        ComponentTreeStruct manager;
        
        @Before
        public void runvariables(){
         root = new ContainerItem(new JPanel(), "JPanel",new Dimension(100,100));
         panel1 = new ContainerItem(new JPanel(), "JPanel",new Dimension(100,100));
         panel2 = new ContainerItem(new JPanel(), "JPanel",new Dimension(100,100));
         panel3 = new ContainerItem(new JPanel(), "JPanel", new Dimension(23, 45));
         panel4 = new ContainerItem(new JPanel(), "JPanel", new Dimension(45, 45));
         panel5 = new ContainerItem(new JPanel(), "JPanel", new Dimension(230, 45));
         panel6 = new ContainerItem(new JPanel(), "JPanel", new Dimension(49, 45));
         area1 = new ComponentItem(new JTextArea(), "JTextArea",new Dimension(100,100));
         textfield1  = new ComponentItem(new JTextField(), "JTextField",new Dimension(100,100));
         button1 = new ComponentItem(new JButton(), "JButton",new Dimension(100,100));
         manager = new ComponentTreeStruct();
        }
        
        public void addChildren(){
                manager.addChild(root, panel1, "JPanel", new Dimension(34, 34));
                manager.addChild(panel1,  area1, "JPanel", new Dimension(34, 56));
                manager.addChild(panel1,  textfield1, "JPanel", new Dimension(34, 56));
                manager.addChild(panel1,  button1, "JPanel", new Dimension(34, 56));
                manager.addChild(root,  panel2, "JPanel", new Dimension(34, 56));
                manager.addChild(root,  panel3, "JPanel", new Dimension(34, 56));
        }
        
        public void testAddBorderLayout(){
                manager.addBorderChild(panel1, panel4, "west", "JPanel", new Dimension(100,100));
                assertTrue(panel4.getBorderLocation() == "west");
                assertTrue(panel4.getParent() == panel1);
                assertTrue(panel1.removeChildComponent(panel4) ==true);
        }
        
        public void testAddGridChild(){
                manager.addGridChild(panel1, panel5, 50, 50, "JPanel", new Dimension(100,100));
                assertTrue(panel5.getParent() ==panel1);
                System.out.println(panel5.getGridLocation());
                assertTrue(panel5.getGridLocation().x == 50);
                assertTrue(panel5.getGridLocation().y == 50);
        }
        
        public void testAddGridChildSpan(){
                manager.addGridChild(panel1, panel6, 50, 50, 15, 15, "JPanel", new Dimension(100,100));
                assertTrue(panel6.getParent() == panel1);
                assertTrue(panel6.getGridSpan().x == 15);
                assertTrue(panel6.getGridSpan().y == 15);
        }
        
        
        @Test
        public void testComponentTree(){
                
                manager.setRoot(root);
                assertTrue(manager.getRoot() ==root);
                
                addChildren();
                assertTrue(manager.getRoot() == root);  
                assertTrue(root.getName().equalsIgnoreCase("jpanel1"));
                assertEquals(root.getType(), "JPanel");
                assertTrue(root.getPreferredSize().getWidth() == 100);
                assertTrue(panel1.getParent()==root);
                assertEquals(root.getPreferredSize(), new Dimension(100,100));
                
                testAddBorderLayout();
                testAddGridChild();
                testAddGridChildSpan();
                
        }

}