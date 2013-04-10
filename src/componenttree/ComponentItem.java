package componenttree;
import gui.ComponentsPanel;

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
	private static ComponentsPanel panel = new ComponentsPanel();

	private static HashMap<String, Integer> varNames = new HashMap<String, Integer>();
	static{
		for(int i = 0; i < panel.getCmap().getContainers().length; i++){
			varNames.put(panel.getCmap().getContainers()[i],0);
		}
		for(int j = 0; j < panel.getCmap().getControls().length; j++){
			varNames.put(panel.getCmap().getControls()[j],0);
		}
		for(int k = 0; k < panel.getCmap().getMenus().length; k++){
			varNames.put(panel.getCmap().getMenus()[k],0);
		}
		varNames.put("JFrame", 0);
		varNames.put("JInternalFrame", 0);
	};
	
	private int instanceCounter;
	
	public ComponentItem(JComponent component,String type, Dimension size){
		this.component = component;
		this.type = type;
		gridSpan = new Point(1, 1);
		gridLocation = new Point(-1, -1);
		this.parent = null;
		int currentNumInstance = varNames.get(type);
		currentNumInstance += 1;
		this.instanceCounter= currentNumInstance;
		varNames.put(type, currentNumInstance);
		this.setPreferredSize(size);

	}
	
	public String getName(){
		return this.type + this.instanceCounter;
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
	
	public void setType(String type){
		this.type = type;
	}
	
	public JComponent getComponent(){return this.component;}

	public void setBorderLocation(String location){this.borderLocation = location;}

	public void setPreferredSize(Dimension size){
		this.component.setPreferredSize(size);
	}

	public Dimension getPreferredSize(){return this.component.getPreferredSize();}

}
