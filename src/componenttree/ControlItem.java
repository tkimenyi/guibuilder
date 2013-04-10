package componenttree;
import java.awt.Dimension;

import javax.swing.JComponent;

public class ControlItem extends ComponentItem{
	private JComponent component;
	
	
	public ControlItem(JComponent component,String type, Dimension size){
		super(component, type, size);
		this.component = component;
	}
	
	public JComponent getComponent(){return this.component;}

	}
