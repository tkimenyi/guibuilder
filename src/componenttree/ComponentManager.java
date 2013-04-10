package componenttree;
import java.awt.Dimension;

public class ComponentManager {

	private ContainerItem root;
	private int size;

	public ComponentManager(){
		this.root = null;
		this.size = 0;
	}

	public void setRoot(ContainerItem component){
		this.root = component;
	}

	public int getSize(){
		return this.size;
	}

	public ContainerItem getRoot(){
		return this.root;
	}


	//needs to be double-checked in brandon's code
	public void addChild(ContainerItem parent, ComponentItem child, String type, Dimension size){
		this.size++;
		if(root == null){
			root = parent;
			this.size++;
		}
		parent.addChildComponent(child);
		child.setType(type);
		child.setPreferredSize(size);
		child.setParent(parent);
	}
	/*add child using border layout*/
	public void addBorderChild(ContainerItem parent, ComponentItem child, String borderLocation, String type, Dimension size){
		addChild(parent, child, type, size);
		child.setBorderLocation(borderLocation);
	}

	/*add child with grid layout*/
	public void addGridChild(ContainerItem parent, ComponentItem child, int xLoc, int yLoc, String type, Dimension size){
		child.setGridLocation(xLoc, yLoc);
		addChild(parent, child, type, size);
	}

	public void addGridChild(ContainerItem parent, ComponentItem child, int xLoc, int yLoc, int rowSpan, int colSpan, String type, Dimension size){
		addGridChild(parent, child, xLoc, yLoc, type, size);
		child.setGridSpan(rowSpan, colSpan);
	}
}
