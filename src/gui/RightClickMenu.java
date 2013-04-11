//this class gives us the right click menu that each added component will have. When adding or taking away from the menu, it should be done here.

package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class RightClickMenu extends JPopupMenu implements MouseListener,ActionListener {
	private Component comp;
	private Component compd;
	private JPopupMenu pop; 
	private JMenuItem resize, delete,chooseLayout, setText;
	private UserGUI userGUI;

	public RightClickMenu(Component c, Component d, UserGUI g, boolean isJPanel){
		comp = c;
		compd = d;
		userGUI = g;
		pop = new JPopupMenu();
		resize = new JMenuItem("Resize");
		pop.add(resize);
		delete = new JMenuItem("Delete");
		pop.add(delete);
		comp.addMouseListener(this);
		resize.addActionListener(this);
		delete.addActionListener(this);
		if(compd instanceof JButton){
			setText = new JMenuItem("Set Text");
			pop.add(setText);
			setText.addActionListener(this);
		}
		if(isJPanel){
			chooseLayout = new JMenuItem("Select a Layout");
			pop.add(chooseLayout);	
			chooseLayout.addActionListener(this);
		}
	}
	
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

    private void doPop(MouseEvent e){
        pop.show(e.getComponent(), e.getX(), e.getY());
    }

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == resize){
			resizeAction();
		}		
		if(evt.getSource() == delete){
			deleteAction();
		}
		if(evt.getSource() == chooseLayout){
			userGUI.layoutGridSetter((Container) comp, 5,5);
		}
		if(evt.getSource() == setText){			
			String s = JOptionPane.showInputDialog("Please give me the name");
			((JButton) compd).setText(s);			
		}
	}
	
	private void resizeAction(){
		String s = JOptionPane.showInputDialog("Please give me a Dimension...i.e 100,300");
		if(s !=null && s.length() != 0){
			String[] xy = s.split(",");
			if( xy.length == 2 && isInteger(xy[0]) && isInteger(xy[1])){
				int x = Integer.parseInt(xy[0]);		
				int y = Integer.parseInt(xy[1]);
				System.out.println("X: " + x + " " + "Y:" + y);
				userGUI.resizeComponent(comp, new Dimension(x,y));
			}
			else{
				JOptionPane.showMessageDialog(this, "Your input was not an acceptable dimension");
				resizeAction();
			}
		}
	}
	
	public void deleteAction(){
		userGUI.removeComponent(comp);
	}
	
	
	public boolean isInteger(String str) {
	    int size = str.length();
	    for (int i = 0; i < size; i++) {
	        if (!Character.isDigit(str.charAt(i))) {
	            return false;
	        }
	    }
	    return size > 0;
	}
}
