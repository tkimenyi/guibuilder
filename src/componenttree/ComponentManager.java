package componenttree;

import gui.UserGUI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ComponentManager {

	private ContainerItem root;
	private ComponentItem focusedItem;
	private int size;

	public ComponentManager(){
		this.root = null;
		this.focusedItem = null;
		this.size = 0;
	}

	public void setRoot(ContainerItem component){
		this.root = component;
		this.focusedItem = root;
	}

	public int getSize(){
		return this.size;
	}

	public ContainerItem getRoot(){
		return this.root;
	}

	public ComponentItem getFocusedItem(){
		return this.focusedItem;
	}

	public void setFocusedItem(ComponentItem item){
		this.focusedItem = item;
	}
	
	public void addChild(ContainerItem parent, ComponentItem child, String type, Dimension size){
		this.size++;
		root.addChild(parent, child, type, size);
		root.addChildComponent(child);
	}
}
