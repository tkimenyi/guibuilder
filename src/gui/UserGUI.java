//this class is the User's Frame that is being built.  This is where all the changes to the frame are made and where the components are added
// to the tree structure. Most anything dealing with that frame needs to be done in this class.

package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import componenttree.ComponentItem;
import componenttree.ComponentTreeStruct;
import componenttree.ContainerItem;

@SuppressWarnings("serial")
public class UserGUI extends JInternalFrame{
	private JPanel userPanel;
	private ComponentTreeStruct tree = new ComponentTreeStruct();
	private Point curLocation;
	protected ArrayList<JLabel> addedComponentsList;
	public UserGUI(String name) {
		super(name, false,false,false,false);
		setSize(550, 600);
		setVisible(true);		
		userPanel = new JPanel(null);
        userPanel.setPreferredSize(new Dimension(550, 600));
        userPanel.setBackground(Color.white);
        add(userPanel);        
		this.setSize(550,600);
		ContainerItem mom = new ContainerItem(userPanel, "JPanel", userPanel.getSize());
		tree.setRoot(mom);
		curLocation = new Point(0,0);
        addedComponentsList = new ArrayList<JLabel>();
	}
	
	public JPanel getPanel(){
		return userPanel;
	}	
	
	public ComponentTreeStruct getTreeStruct(){
		return tree;
	}
	
	public void layoutBorderSetter(Container parent){	
		parent.setLayout(new BorderLayout());
		String location = "";
		parent.removeAll();
		for(int i = 0; i < 5; i++){
			JPanel blankPanel = new JPanel(null);
			blankPanel.setBackground(Color.gray);
			blankPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
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
			ComponentItem child = new ComponentItem(blankPanel, "JPanel",blankPanel.getSize());
			ContainerItem p = new ContainerItem(this, "JInternalFrame", this.getSize());				
			tree.addBorderChild(p, child, location, "JPanel", blankPanel.getSize());
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
				JPanel blankPanel = new JPanel(null);
				blankPanel.setBackground(Color.gray);
				blankPanel.setBorder(BorderFactory.createLineBorder(Color.black));				
				parent.add(blankPanel);
				ComponentItem child = new ComponentItem(blankPanel, "JPanel", blankPanel.getSize());
				ContainerItem p = new ContainerItem(this, "JInternalFrame", this.getSize());
				tree.addGridChild(p, child, i, j, "JPanel", blankPanel.getSize());
				tree.getRoot().addChildComponent(child);
				child.setGridLocation(i, j);
			}
		}
		repaint();
		validate();
	}
	
	public void layoutFlowSetter(Container parent){
		parent.setLayout(new FlowLayout());
	}
	
	public void layoutAbsolute(Container parent){
		parent.removeAll();
		parent.setLayout(null);
	}
		
	public void changeUserFrame(final Component c, Dimension d, String type){
		JPanel parent = (JPanel) userPanel.getComponentAt(curLocation);	
		if(parent != null){		
			c.setBounds(curLocation.x,curLocation.y, d.width, d.height);
			parent.add(c);
			if(d != null && type != null){
				if(d.getHeight() == 0 && d.getWidth() == 0){
					c.setPreferredSize(parent.getSize());
				}else{
					c.setPreferredSize(d);
				}
				ContainerItem p = new ContainerItem(parent, type, d);
				ComponentItem comp1 = new ComponentItem((JComponent) c, type, d);
				comp1.setGridLocation(c.getBounds().x, c.getBounds().y);
				tree.addChild(p, comp1, type, c.getSize());
			}
			repaint();
			validate();
		}
		else{
			JOptionPane.showMessageDialog(this, "The place you have tried to place your component is invalid");
		}		
		addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent me) {
	          requestFocus();
	          c.repaint();
	        }
	      });
	}
	
	public void setCurLocation(int x, int y){
		curLocation = new Point(x,y);
	}
	
	public void resizeComponent(Component c, Dimension d){
		c.setPreferredSize(d);
		repaint();
		validate();		
	}
	
	public void removeComponent(Component c){
		c.getParent().remove(c);
		repaint();
		validate();
	}
}