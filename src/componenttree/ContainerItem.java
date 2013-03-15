package componenttree;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JComponent;

public class ContainerItem extends ComponentItem{
	
	private JComponent representedComponent;
	private ArrayList<ComponentItem> children;

	public ContainerItem(JComponent value, String type){
		
		super(value, type);
		this.representedComponent = value;
		this.children = new ArrayList<ComponentItem>();
	}
		
	public LayoutManager getLayout(){return this.representedComponent.getLayout();}
	
	public void addChildComponent(ComponentItem child){this.children.add(child);}
	
	public boolean removeChildComponent(ComponentItem child){return this.children.remove(child);}
	
	public ArrayList<ComponentItem> getChildren(){return this.children;}

}
