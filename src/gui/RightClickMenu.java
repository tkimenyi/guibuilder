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
	private Resizable resizable;
	private JPopupMenu pop; 
	private JMenuItem resize, delete, chooseLayout, setText, addEvents;
	private UserGUI userGUI;
	private GUI mainGUI;
	public RightClickMenu(Resizable c, UserGUI g, boolean isJPanel, GUI gui){
		comp = c.getComp();
		resizable = c;
		userGUI = g;
		mainGUI = gui;
		pop = new JPopupMenu();
		resize = new JMenuItem("Resize");
		pop.add(resize);
		delete = new JMenuItem("Delete");
		pop.add(delete);
		addEvents = new JMenuItem("Add Events");
		pop.add(addEvents);
		comp.addMouseListener(this);
		resize.addActionListener(this);
		delete.addActionListener(this);
		addEvents.addActionListener(this);
		if(comp instanceof JButton){
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
			Object[] choices = mainGUI.getLayoutMap().keySet().toArray();
			String selection = (String)JOptionPane.showInputDialog(userGUI, "Select a layout manager.", "Please", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
			if(selection.equals("Grid Layout"))
			{
				userGUI.layoutGridSetter(resizable.getContItem(), 5, 5);
				resizable.getContItem().setLayout("grid");
			}
			else if (selection.equals("Border Layout"))
			{
				userGUI.layoutBorderSetter(resizable.getContItem());
				resizable.getContItem().setLayout("border");
			}
			else if (selection.equals("Flow Layout"))
			{
				userGUI.layoutFlowSetter((Container)comp);
			}
			else{
				userGUI.layoutAbsolute((Container)comp);
			}
		}
		if(evt.getSource() == setText){			
			String s = JOptionPane.showInputDialog("Please give me the name");
			((JButton) comp).setText(s);			
		}
		if(evt.getSource() == addEvents){
			if(userGUI.isFirstEvent){
				mainGUI.getGen().actionListenerMethod("actionPerformed(ActionEvent evt) ", "void", 
						 "{ \n\t if(evt.getSource() == " + resizable.getCompItem().getName() +"){\n\t\tSystem.out.println(\"Hello\");\n\t}"
						, true, resizable.getCompItem().getName());
				userGUI.isFirstEvent = false;
			}
			else{
				mainGUI.getGen().actionListenerMethod("", "",
						"\n\t if(evt.getSource() == " + resizable.getCompItem().getName() +"){\n\t\tSystem.out.println(\"Hello\");\n\t}" 
						,false, resizable.getCompItem().getName());
			}
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
		userGUI.removeComponent(resizable);
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
