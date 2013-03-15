package componenttree;
import javax.swing.JComponent;

public class ControlItem extends ComponentItem{
	private JComponent component;
	
	
	public ControlItem(JComponent component,String type){
		super(component, type);
		this.component = component;
	}
	
	public JComponent getComponent(){return this.component;}

	}
