package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import componenttree.ComponentItem;
import componenttree.ComponentManager;
import componenttree.ContainerItem;

@SuppressWarnings("serial")
public class UserGUI extends JInternalFrame {
	private JPanel userPanel;
	private ComponentManager tree = new ComponentManager();
	int mousex = 0;
	int mousey = 0;
	public UserGUI(String name) {
		super(name, false,false,false,false);
		setSize(550, 600);
		setVisible(true);		
		userPanel = new JPanel();
        userPanel.setPreferredSize(new Dimension(550, 600));
        userPanel.setBackground(Color.white);
        add(userPanel);
        userPanel.setLayout(new FlowLayout());		
		this.setSize(550,600);
		ContainerItem mom = new ContainerItem(userPanel, "JPanel");
		tree.setRoot(mom);
	}
	
	public JPanel getPanel(){
		return userPanel;
	}	
	
	public ComponentManager getTreeStruct(){
		return tree;
	}
	
	public void layoutBorderSetter(Container parent){	
		parent.setLayout(new BorderLayout());
		String location = "";
		parent.removeAll();
		for(int i = 0; i < 5; i++){
			JPanel blankPanel = new JPanel();
			blankPanel.setBackground(Color.gray);
			blankPanel.setBorder(BorderFactory.createLineBorder(Color.red));
			
			if(i % 2 == 0){
				blankPanel.setPreferredSize(new Dimension(600, 100));
			}
			else{
				blankPanel.setPreferredSize(new Dimension(100, 600));
			}
			if(i == 0){
				parent.add(blankPanel, BorderLayout.NORTH);
				location = "north";
			}
			else if(i == 1){
				parent.add(blankPanel, BorderLayout.EAST);
				location = "east";
			}
			else if(i == 2){
				parent.add(blankPanel, BorderLayout.SOUTH);
				location = "south";
			}
			else if(i == 3){
				parent.add(blankPanel, BorderLayout.WEST);
				location = "west";
			}
			else{
				parent.add(blankPanel, BorderLayout.CENTER);
				location = "center";
			}
			ContainerItem child = new ContainerItem(blankPanel, "JPanel");
			ContainerItem p = new ContainerItem(this, "JInternalFrame");				
			tree.getRoot().addBorderChild(p, child, location, "JPanel", blankPanel.getSize());
			tree.getRoot().addChildComponent(child);
			parent.setPreferredSize(new Dimension(600,600));
		}
		repaint();
		validate();
	}
	
	public void layoutGridSetter(Container parent, int x, int y){
		parent.setLayout(new GridLayout(x, y));
		parent.removeAll();
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				JPanel blankPanel = new JPanel();
				blankPanel.setBackground(Color.gray);
				blankPanel.setBorder(BorderFactory.createLineBorder(Color.blue));				
				parent.add(blankPanel);
				ContainerItem child = new ContainerItem(blankPanel, "JPanel");
				ContainerItem p = new ContainerItem(this, "JInternalFrame");
				tree.getRoot().addGridChild(p, child, i, j, "JPanel", blankPanel.getSize());
				tree.getRoot().addChildComponent(child);
				repaint();
				validate();
			}
		}	
	}
		
	public void changeUserFrame(Component c, Dimension d, String type){
		JPanel parent = (JPanel) userPanel.getComponentAt(mousex,mousey);
		if(parent != null){
			if(d.getHeight() == 0 && d.getWidth() == 0){
				c.setPreferredSize(parent.getSize());
			}else{
				c.setPreferredSize(d);
			}
			parent.add(c);			
			c.setLocation(getLastMouseLocation());
			((JComponent) c).setBorder(BorderFactory.createLineBorder(Color.black));
			ContainerItem p = new ContainerItem(parent, type);
			ComponentItem comp = new ComponentItem((JComponent) c, type);
			tree.addChild(p, comp, "JPanel", c.getSize());
			repaint();
			validate();
		}
		else{
			JOptionPane.showMessageDialog(this, "The place you have tried to place your component is invalid");
		}
	}
	
	public void setPanelCoordinates(int x, int y){
		mousex = x;
		mousey = y;
	}
	
	public Point getLastMouseLocation(){
		return new Point(mousex, mousey);
	}
}