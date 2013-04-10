package componenttree;
import gui.ComponentsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
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
	
	public ComponentItem(JComponent comp,String t, Dimension s){
		component = comp;
		type = t;
		gridSpan = new Point(1, 1);
		gridLocation = new Point(-1, -1);
		parent = null;
		int currentNumInstance = varNames.get(type);
		currentNumInstance += 1;
		instanceCounter= currentNumInstance;
		varNames.put(type, currentNumInstance);
		setPreferredSize(s);

	}
	
	public String getName(){
		return type + instanceCounter;
	}
	


	public String getType(){return type;}
	public String getBorderLocation(){return borderLocation;}
	
	public Point getGridLocation(){return gridLocation;}

	public Point getGridSpan(){return gridSpan;}

	public ContainerItem getParent(){return parent;}

	public void setGridLocation(int x, int y){gridLocation.setLocation(x, y);}

	public void setGridSpan(int rows, int cols){gridSpan.setLocation(rows, cols);}

	public void setParent(ContainerItem container){
		parent = container;
	}
	
	public void setType(String t){
		type = t;
	}
	
	public JComponent getComponent(){return component;}

	public void setBorderLocation(String location){borderLocation = location;}

	public void setPreferredSize(Dimension s){
		component.setPreferredSize(s);
	}

	public Dimension getPreferredSize(){return component.getPreferredSize();}
	
	public Rectangle getBounds(){return component.getBounds();}
	
	public float[] getBackground() {return component.getBackground().getRGBColorComponents(null);}		

}
