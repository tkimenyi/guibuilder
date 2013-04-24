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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import componenttree.ComponentTreeStruct;
import componenttree.ContainerItem;

@SuppressWarnings("serial")
public class UserGUI extends JInternalFrame{
	
	private JPanel userPanel;
	private ComponentTreeStruct tree = new ComponentTreeStruct();
	private Point curLocation;
	private ArrayList<JLabel> addedComponentsList;
	private boolean saved;
	public boolean isFirstEvent;
	private ContainerItem mom;
	public UserGUI() {
		super("", false,false,false,false);
		setSize(550, 600);
		setVisible(true);	
		userPanel = new JPanel(null);
        userPanel.setPreferredSize(new Dimension(550, 600));
        userPanel.setBackground(Color.white);
        add(userPanel);        
		this.setSize(550,600);
		mom = new ContainerItem(userPanel, "JPanel", userPanel.getSize());
		tree.setRoot(mom);
		curLocation = new Point(0,0);
        addedComponentsList = new ArrayList<JLabel>();
        saved = false;
        isFirstEvent = true;
	}
	
	public JPanel getPanel(){
		return userPanel;
	}	
	
	public ContainerItem getMomPanel(){
		return mom;
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
	
	public void layoutBorderSetter(ContainerItem parent){	
		parent.getComponent().removeAll();
		parent.getComponent().setLayout(new BorderLayout());
		String location = "";	
		for(int i = 0; i < 5; i++){
			JPanel blankPanel = new JPanel(null);
			final Resizable resizer = new Resizable(blankPanel, parent.getComponent().getSize(), 0);
			blankPanel.setBackground(Color.gray);
			blankPanel.setBorder(BorderFactory.createLineBorder(Color.black));			
			if(i % 2 == 0){
				blankPanel.setSize(new Dimension(600, 100));
			}
			else{
				blankPanel.setSize(new Dimension(100, 600));
			}
			if(i == 0){
				parent.getComponent().add(resizer, BorderLayout.NORTH);
				location = "north";
			}
			else if(i == 1){
				parent.getComponent().add(resizer, BorderLayout.EAST);
				location = "east";
				blankPanel.setBackground(Color.GREEN);
			}
			else if(i == 2){
				parent.getComponent().add(resizer, BorderLayout.SOUTH);
				location = "south";
			}
			else if(i == 3){
				parent.getComponent().add(resizer, BorderLayout.WEST);
				location = "west";
				blankPanel.setBackground(Color.magenta);
			}
			else{
			    parent.getComponent().add(resizer, BorderLayout.CENTER);
				location = "center";
				//blankPanel.setPreferredSize(new Dimension(300,300));
			}			
			ContainerItem blank = new ContainerItem(resizer, "JPanel", parent.getComponent().getSize());
			resizer.setContainerItem(blank);
			tree.addBorderChild(parent, blank, location, "JPanel", blankPanel.getSize());
			parent.setLayout("border");
			blankPanel.setName(location);
		}
		repaint();
		validate();
	}
	
	public void layoutGridSetter(ContainerItem parent, int x, int y){
		parent.getComponent().removeAll();
		parent.getComponent().setLayout(new GridLayout(x, y));
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				JPanel blankPanel = new JPanel(null);
				final Resizable resizer = new Resizable(blankPanel, parent.getComponent().getSize(), 0);
				blankPanel.setBackground(Color.gray);
				blankPanel.setBorder(BorderFactory.createLineBorder(Color.black));				
				parent.getComponent().add(resizer);
				ContainerItem blank = new ContainerItem(resizer, "JPanel", parent.getComponent().getSize());
				resizer.setContainerItem(blank);
				tree.addGridChild(parent, blank, i, j, "JPanel", blankPanel.getSize());
				parent.setLayout("grid");
				blankPanel.setName("grid" + i + j);
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
		if(userPanel.getComponentAt(curLocation) instanceof Resizable){			
			target = (Resizable) userPanel.getComponentAt(curLocation);
			parent = (JPanel) target.getComp();
		    dropped.changeSize(parent.getSize());
			tree.addChild(target.getContItem(), dropped.getItem(), type, dropped.getSize());		
		}
		else if(userPanel.getComponentAt(curLocation) instanceof JPanel){			
			parent = (JPanel) userPanel.getComponentAt(curLocation);
			tree.addChild(mom, dropped.getItem(), type, dropped.getSize());
		}		
		if(parent != null){
			//if parent is not the root, then we put the component into the corner of the selected container
			if(parent != mom.getComponent()){
				dropped.setBounds(parent.getX(), parent.getY(), d.width, d.height);
			}
			else {
				dropped.setBounds(curLocation.x, curLocation.y, d.width, d.height);
			}
			parent.add(dropped);
			if(d != null && type != null){
				if(d.getHeight() == 0 && d.getWidth() == 0){
					dropped.setPreferredSize(parent.getSize());
				}else{
					dropped.setPreferredSize(d);
				}
			}
			repaint();	
			validate();
			parent.setLayout(null);
		}		
		else{
			JOptionPane.showMessageDialog(this, "The place you have tried to place your component is invalid");
		}		
		addMouseListener(new MouseAdapter(){public void mousePressed(MouseEvent me) {requestFocus();dropped.repaint();}});


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
		if(c.getCompItem()!=null)
		{
		tree.removeChild(c.getCompItem());
		}
		else
		{
			tree.removeChild(c.getContItem());
		}
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
			repaint();
			revalidate();
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
	
	public Component getParentType(Resizable target, Resizable dropped){
		if(target.getComp() instanceof JPanel){
			System.out.println("JPanel");
			((JPanel) target.getComp()).add(dropped);
			return ((JPanel)target.getComp());
		}
		else if(target.getComp() instanceof JScrollPane){
			System.out.println("JScrollPane");
			((JScrollPane) target.getComp()).add(dropped);
			return ((JScrollPane)target.getComp());
		}
		else if(target.getComp() instanceof JSplitPane){
			System.out.println("JSplitPane");
			((JSplitPane) target.getComp()).add(dropped, 1);
			return ((JSplitPane)target.getComp());
		}
		else if(target.getComp() instanceof JTabbedPane){
			System.out.println("JTabbed");
			((JTabbedPane) target.getComp()).add(dropped);
			return ((JTabbedPane)target.getComp());
		}
		else{
			System.out.println("Whoops");
			return target;
		}
	}
}