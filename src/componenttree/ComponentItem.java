package componenttree;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import javax.swing.JComponent;


public class ComponentItem {
	private String type;
	private JComponent component;
	private Point gridLocation;
	private Point gridSpan;
	private String borderLocation = "";
	private ContainerItem parent;

	private static HashMap<String, Integer> varNames = new HashMap<String, Integer>();
	static{
		varNames.put("JPanel", 0);
		varNames.put("JFrame", 0);
		varNames.put("JInternalFrame", 0);
		varNames.put("JScrollPane", 0);
		varNames.put("JSplitPane", 0);
		varNames.put("JTabbedPane", 0);
		varNames.put("JTextPane", 0);
		varNames.put("JMenu", 0);
		varNames.put("JMenuBar", 0);
		varNames.put("JMenuItem", 0);
		varNames.put("JPopupMenu", 0);
		varNames.put("JRadioButtonMenuItem", 0);
		varNames.put("JButton", 0);
		varNames.put("JCheckbox", 0);
		varNames.put("JCombobox", 0);
		varNames.put("JEditorpane", 0);
		varNames.put("JLabel", 0);
		varNames.put("JList", 0);
		varNames.put("JPasswordField", 0);
		varNames.put("JProgressBar", 0);
		varNames.put("JSeparator", 0);
		varNames.put("JSlider", 0);
		varNames.put("JSpinner", 0);
		varNames.put("JTable", 0);
		varNames.put("JTextArea", 0);
		varNames.put("JToggleButton", 0);  
		varNames.put("JTextField", 0);  
		
	};
	
	private int instanceCounter;
	
	public ComponentItem(JComponent component,String type){
		this.component = component;
		this.type = type;
		gridSpan = new Point(1, 1);
		gridLocation = new Point(-1, -1);
		this.parent = null;
		int currentNumInstance = varNames.get(type);
		currentNumInstance += 1;
		this.instanceCounter= currentNumInstance;
		varNames.put(type, currentNumInstance);

	}
	
	public String getName(){
		return this.type + this.instanceCounter;
	}
	
	/*add child using border layout*/
	public void addBorderChild(ContainerItem parent, ComponentItem child, String borderLocation, String type, Dimension size){
		ComponentItem panel = addChild(parent, child, type, size);
		panel.setBorderLocation(borderLocation);
		parent.addChildComponent(child);
	}
	public ComponentItem addChild(ContainerItem parent, ComponentItem child, String type, Dimension size){
		parent.addChildComponent(child);
		child.setParent(parent);
		parent.setPreferredSize(size);
		parent.addChildComponent(child);
		return child;
	}

	public void addGridChild(ContainerItem parent, ComponentItem child, int xLoc, int yLoc, String type, Dimension size){
		ComponentItem panel = addChild(parent, child, type,size);
		panel.setGridLocation(xLoc, yLoc);
		panel.setPreferredSize(size);	
		parent.addChildComponent(child);
	}

	public void addGridChild(ContainerItem parent, ComponentItem child, int xLoc, int yLoc, int rowSpan, int colSpan, Dimension size){
		parent.addChildComponent(child);
		child.setParent(parent);
		child.setGridLocation(xLoc, yLoc);
		child.setGridSpan(rowSpan, colSpan);
		child.setPreferredSize(size);
		parent.addChildComponent(child);
	}

	public String getType(){return this.type;}
	public String getBorderLocation(){return this.borderLocation;}
	
	public Point getGridLocation(){return this.gridLocation;}

	public Point getGridSpan(){return this.gridSpan;}

	public ContainerItem getParent(){return this.parent;}

	public void setGridLocation(int x, int y){this.gridLocation.setLocation(x, y);}

	public void setGridSpan(int rows, int cols){this.gridSpan.setLocation(rows, cols);}

	public void setParent(ContainerItem container){
		this.parent = container;
	}
	
	public JComponent getComponent(){return this.component;}

	public void setBorderLocation(String location){this.borderLocation = location;}

	public void setPreferredSize(Dimension size){
		this.component.setPreferredSize(size);
	}

	public Dimension getPreferredSize(){return this.component.getPreferredSize();}

}
