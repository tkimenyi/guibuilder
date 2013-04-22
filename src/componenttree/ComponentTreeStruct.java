package componenttree;
import java.awt.Dimension;


public class ComponentTreeStruct {

	private ContainerItem root;
	private int size;

	public ComponentTreeStruct(){
		root = null;
		size = 0;
	}

	public void setRoot(ContainerItem component){
		root = component;
	}

	public int getSize(){
		return size;
	}

	public ContainerItem getRoot(){
		return root;
	}
	//needs to be double-checked in brandon's code
	public void addChild(ContainerItem parent, ComponentItem child, String type, Dimension s){
		//System.out.println(parent.getName() + " " + child.getName());
		//put root here to make everything add to original panel
		parent.addChildComponent(child);
		size++;		
		child.setType(type);
		child.setPreferredSize(s);
		child.setParent(parent);
	}
	
	/*add child using border layout*/
	public void addBorderChild(ContainerItem parent, ComponentItem child, String borderLocation, String type, Dimension s){
		addChild(parent, child, type, s);
		child.setBorderLocation(borderLocation);
	}

	/*add child with grid layout*/
	public void addGridChild(ContainerItem parent, ComponentItem child, int xLoc, int yLoc, String type, Dimension s){
		addChild(parent, child, type, s);
		child.setGridLocation(xLoc, yLoc);
	}

	public void addGridChild(ContainerItem parent, ComponentItem child, int xLoc, int yLoc, int rowSpan, int colSpan, String type, Dimension size){
		addGridChild(parent, child, xLoc, yLoc, type, size);
		child.setGridSpan(rowSpan, colSpan);
	}
	
	public void removeChild(ComponentItem c){
		c.getParent().removeChildComponent(c);			
	}
}
