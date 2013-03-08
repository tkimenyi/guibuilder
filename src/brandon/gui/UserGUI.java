package brandon.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import safari.structure.CompNode;

@SuppressWarnings("serial")
public class UserGUI extends JInternalFrame {
	private JPanel userPanel;
	CompNode tree = new CompNode(null);
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
	}
	
	public JPanel getPanel(){
		return userPanel;
	}	

	public void layoutBorderSetter(){	
		userPanel.setLayout(new BorderLayout());
		userPanel.removeAll();
		for(int i = 0; i < 5; i++){
			JPanel blankPanel = new JPanel();
			blankPanel.setBackground(Color.gray);
			blankPanel.setBorder(BorderFactory.createLineBorder(Color.red));
			tree.addChild(blankPanel);
			if(i % 2 == 0){
				blankPanel.setPreferredSize(new Dimension(600, 100));
			}
			else{
				blankPanel.setPreferredSize(new Dimension(100, 600));
			}
			if(i == 0)userPanel.add(blankPanel, BorderLayout.NORTH);
			else if(i == 1)userPanel.add(blankPanel, BorderLayout.EAST);
			else if(i == 2)userPanel.add(blankPanel, BorderLayout.SOUTH);
			else if(i == 3)userPanel.add(blankPanel, BorderLayout.WEST);
			else{
				userPanel.add(blankPanel, BorderLayout.CENTER);
				userPanel.setPreferredSize(new Dimension(600,600));
			}
		}
		repaint();
		validate();
	}
	
	public void layoutGridSetter(int x, int y){
		userPanel.setLayout(new GridLayout(x, y));
		userPanel.removeAll();
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				JPanel blankPanel = new JPanel();
				blankPanel.setBackground(Color.gray);
				blankPanel.setBorder(BorderFactory.createLineBorder(Color.blue));				
				userPanel.add(blankPanel);
				tree.addChild(blankPanel);
				repaint();
				validate();
			}
		}	
	}
		
	//we will want to change this to remove the specific component 
	public void changeUserFrame(Component c){
		JPanel parent = (JPanel) userPanel.getComponentAt(mousex,mousey);
		if(parent != null){
			//this is where we want to get the size of the component to make it look good.
			c.setPreferredSize(new Dimension(100, 30));
			parent.add(c);			
			c.setLocation(getLastMouseLocation());
			tree.addChild(c);
			((JComponent) c).setBorder(BorderFactory.createLineBorder(Color.black));
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