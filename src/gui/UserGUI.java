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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import componenttree.ComponentTreeStruct;
import componenttree.ContainerItem;

@SuppressWarnings("serial")
public class UserGUI extends JInternalFrame{
	private JPanel userPanel;
	private ComponentTreeStruct tree = new ComponentTreeStruct();
	private Point curLocation;
	private ArrayList<JLabel> addedComponentsList;
	private boolean saved;
	public UserGUI() { 
		super("", false,false,false,false);
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
        saved = false;
	}
	
	public JPanel getPanel(){
		return userPanel;
	}	
	
	public ComponentTreeStruct getTreeStruct(){
		return tree;
	}
	
	public boolean isSaved(){
		return saved;
	}
	
	public void changeSaved(){
		saved = true;
	}
	
	public void layoutBorderSetter(Container parent){	
		parent.removeAll();
		parent.setLayout(new BorderLayout());
		String location = "";
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
			ContainerItem child = new ContainerItem(blankPanel, "JPanel",blankPanel.getSize());
			ContainerItem p = new ContainerItem(this, "JInternalFrame", this.getSize());				
			tree.addBorderChild(p, child, location, "JPanel", blankPanel.getSize());
			tree.getRoot().addChildComponent(child);			
		}
		repaint();
		validate();
	}
	
	public void layoutGridSetter(Container parent, int x, int y){
		parent.removeAll();
		parent.setLayout(new GridLayout(x, y));
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				JPanel blankPanel = new JPanel(null);
				blankPanel.setBackground(Color.gray);
				blankPanel.setBorder(BorderFactory.createLineBorder(Color.black));				
				parent.add(blankPanel);
				ContainerItem child = new ContainerItem(blankPanel, "JPanel", blankPanel.getSize());
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
		parent.removeAll();
		parent.setLayout(new FlowLayout());
	}
	
	public void layoutAbsolute(Container parent){
		parent.removeAll();
		parent.setLayout(null);
	}
		
	public void changeUserFrame(final Resizable dropped, Dimension d, String type){
		JPanel parent = new JPanel(null);
		Resizable target = null;
		boolean isRoot = true;
		if(userPanel.getComponentAt(curLocation) instanceof Resizable){			
			target = (Resizable) userPanel.getComponentAt(curLocation);
			parent = (JPanel) target.getComp();
		    dropped.changeSize(parent.getSize());
			isRoot = false;
		}
		else{
			parent = (JPanel) userPanel.getComponentAt(curLocation);
		}		
		if(parent != null){
			dropped.setBounds(curLocation.x, curLocation.y, d.width, d.height);
			parent.add(dropped);
			if(d != null && type != null){
				if(d.getHeight() == 0 && d.getWidth() == 0){
					dropped.setPreferredSize(parent.getSize());
				}else{
					dropped.setPreferredSize(d);
				}
			if(isRoot){
				tree.addChild(tree.getRoot(), dropped.getItem(), type, dropped.getSize());
			}
			else{
				tree.addChild(target.getContItem(), dropped.getItem(), type, dropped.getSize());
			}
		
			}
			repaint();	
			validate();
			parent.setLayout(null);
		}		
		else{
			JOptionPane.showMessageDialog(this, "The place you have tried to place your component is invalid");
		}		
		addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent me) {
	          requestFocus();
	          dropped.repaint();
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
	
	public void removeComponent(Resizable c){		
		c.getParent().remove(c);
		tree.removeChild(c.getCompItem());
		repaint();
		validate();
	}
	
	public ArrayList<JLabel> getCompList(){
		return addedComponentsList;
	}
	
	public void addToCompList(JLabel added){
		addedComponentsList.add(added);
	}
	
	public void addMenuBar(Resizable comp, Dimension d, String type){
		JMenuBar c = (JMenuBar) comp.getComp();
		c.add(new JMenu("File"));
		userPanel.setLayout(new BorderLayout());
		userPanel.add(c, BorderLayout.NORTH);
		repaint();
		revalidate();
		tree.addChild(tree.getRoot(), comp.getItem(), type, c.getSize());
	}
	
	public void addMenu(Resizable comp){		
		if(userPanel.getComponentAt(curLocation) instanceof Resizable || userPanel.getComponentAt(curLocation) instanceof JPanel){
			JOptionPane.showMessageDialog(this, "You cannot add that here");
		}
		else{
			String name = JOptionPane.showInputDialog("What would you like to call your menu?");
			JMenu menu = new JMenu(name);
			JMenuBar mb = (JMenuBar) userPanel.getComponentAt(curLocation);
			mb.add(menu);
		}
	}
	
	public void addMenuItem(Resizable comp){
		if(userPanel.getComponentAt(curLocation) instanceof JMenu){
			String name = JOptionPane.showInputDialog("What do you want to call your menu item?");
			JMenuItem item = new JMenuItem(name);
			JMenu m = (JMenu) userPanel.getComponentAt(curLocation);
			m.add(item);
		}
		else{
			JOptionPane.showMessageDialog(this, "You cannot add that here");
		}
	}
}