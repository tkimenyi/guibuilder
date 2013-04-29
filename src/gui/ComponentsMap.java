package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ComponentsMap extends HashMap<String, Component> {
	private HashMap<String, Component> components;
	 private String[] containerNames = {"JPanel", "JScrollPane", "JSplitPane", "JTabbedPane", "JToolBar"};
     private Component[] containers = {new JPanel(), new JScrollPane(), new JSplitPane(1, new JPanel(), new JPanel()), new JTabbedPane(), new JToolBar()};
     private Dimension[] containerDimensions = {new Dimension(100,100),new Dimension(100,100),new Dimension(100,100),
			   new Dimension(100,100),new Dimension(100,100)};
    
     private String[] controlNames = {"JButton", "JCheckBox", "JComboBox","JEditorPane","JList", 
     								 "JPasswordField", "JProgressBar", "JRadioButton", "JScrollBar", 
     								 "JSeparator", "JSlider", "JSpinner", "JTable", "JTextArea", "JTextPane",
     								 "JToggleButton", "JTextField"};
     private Component[] controls = {new JButton(), new JCheckBox(), new JComboBox(), new JEditorPane(),new JList(), 
     		 new JPasswordField("TextArea1"), new JProgressBar(), new JRadioButton(), new JScrollBar(),
				 new JSeparator(), new JSlider(), new JSpinner(), new JTable(), new JTextArea("TextArea1"), new JTextPane(),
				 new JToggleButton(), new JTextField("TextField")};
     private Dimension[] controlDimensions = {new Dimension(100,50),new Dimension(50,50),new Dimension(150,50),
     										 new Dimension(100,100),new Dimension(200,400),
     										new Dimension(150,50),new Dimension(150,50),new Dimension(50,50),
     										 new Dimension(30,100),new Dimension(100,30),new Dimension(100,30),
     										 new Dimension(100,30),new Dimension(300,200),new Dimension(100,100),
     										 new Dimension(100,30),new Dimension(50,50), new Dimension(100,30)};
             
     private String[] menuNames = {"JMenuBar", "JMenu"};
     private Component[] menus = {new JMenuBar(), new JMenu()}; 
	
	public ComponentsMap(){
		components = new HashMap<String, Component>();
		for(int i = 0; i < containers.length;i++){
			addToMap(containerNames[i], containers[i]);
		}
		for(int i = 0; i < controls.length;i++){
			addToMap(controlNames[i], controls[i]);
		}
		for(int i = 0; i < menus.length;i++){
			addToMap(menuNames[i], menus[i]);
		}
	}
	
	public HashMap<String, Component> getComponentsMap(){
		return components;
	}
	
	public void addToMap(String name, Component c){
		components.put(name, c);
	}
	
	public String[] getContainers(){
    	return containerNames;
    }
    
    public Component[] getContainerComps(){
    	return containers;
    }
    
    public String[] getControls(){
    	return controlNames;
    }
    
    public Component[] getControlComps(){
    	return controls;
    }
    
    public String[] getMenus(){
    	return menuNames;
    }
    
    public Component[] getMenuComps(){
    	return menus;
    }
    
    public Dimension[] getControlsDimension(){
    	return controlDimensions;
    }
    
    public Dimension[] getConatinerDimension(){
    	return containerDimensions;
    }
}
