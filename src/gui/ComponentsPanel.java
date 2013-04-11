//this is the components panel on the right hand side. Any changes for that should be done here. 

package gui;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ComponentsPanel extends JPanel {		
       private ComponentsMap cmap;    
        
        protected JPanel containers, layouts, controls, menus;
        public ComponentsPanel(){
                this.containers = new JPanel();
                this.controls = new JPanel();
                this.menus = new JPanel(); 
                cmap = new ComponentsMap();  
                layComponents();
        }
        
        public ComponentsMap createNewMap(){
        	cmap = new ComponentsMap();
        	return cmap;
        }       
        
		public void addLabelToContainers(Component c, JLabel addThis){
        	containers.add(addThis);
        }

        public void addLabelToControls(Component c, JLabel addThis){
        	controls.add(addThis);
        }
        public void addLabelToMenus(Component c, JLabel addThis){
        	menus.add(addThis);
        }
        
        public Dimension getDimension(String compName){
        	Dimension ret = new Dimension(0,0);
        	for(int i = 0; i < cmap.getContainers().length; i++){
        		if(compName.equals(cmap.getContainers()[i])){
        			ret = cmap.getConatinerDimension()[i];
        		}
        	}
        	for(int i = 0; i < cmap.getControls().length; i++){
        		if(compName.equals(cmap.getControls()[i])){
        			ret = cmap.getControlsDimension()[i];
        		}
        	}
			return ret;
        }
        
        public ComponentsMap getCmap(){
        	return cmap;
        }
        
        public void layComponents(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));                
            TitledBorder border = BorderFactory.createTitledBorder("Available Components");
            border.setTitleJustification(TitledBorder.CENTER);
            this.setBorder(border);
            
            containers.setLayout(new BoxLayout(containers, BoxLayout.Y_AXIS));
            TitledBorder contBorder = BorderFactory.createTitledBorder("Containers");
            contBorder.setTitleJustification(TitledBorder.RIGHT);
            containers.setBorder(contBorder);
            
            JScrollPane containerScroller = new JScrollPane(containers);
            
            menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));
            TitledBorder menuBorder = BorderFactory.createTitledBorder("Menus");
            menuBorder.setTitleJustification(TitledBorder.RIGHT);
            menus.setBorder(menuBorder);
            JScrollPane menuScroller = new JScrollPane(menus);

            JScrollPane layoutScroller = new JScrollPane(layouts);
            controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
            TitledBorder controlBorder = BorderFactory.createTitledBorder("Control Components");
            controlBorder.setTitleJustification(TitledBorder.RIGHT);
            controls.setBorder(controlBorder);
            
            JScrollPane controlScroller = new JScrollPane(controls);
            this.add(containerScroller);
            this.add(menuScroller);
            this.add(layoutScroller);
            this.add(controlScroller);
    }
}