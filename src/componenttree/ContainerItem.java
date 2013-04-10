package componenttree;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

public class ContainerItem extends ComponentItem implements Iterable<ComponentItem>{
	private ArrayList<ComponentItem> children;

	public ContainerItem(JComponent value, String type, Dimension size){
		
		super(value, type, size);
		children = new ArrayList<ComponentItem>();
	}
		
	public LayoutManager getLayout(){return getComponent().getLayout();}
	
	public void addChildComponent(ComponentItem child){
		children.add(child);
	}
	
	public boolean removeChildComponent(ComponentItem child){return children.remove(child);}
	
	public Iterator<ComponentItem> iterator(){return children.iterator();}

	

}
