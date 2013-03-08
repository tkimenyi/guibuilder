package brandon.gui;
import java.awt.Component;
import java.awt.Dimension;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CompPanel extends JPanel {		
        private String[] containerNames = {"JPanel", "JScrollPane", "JSplitPane", "JTabbedPane", "JToolbar"};
        private Component[] containerComp = {new JPanel(), new JScrollPane(), new JSplitPane(), new JTabbedPane(),
				 new JToolBar()};        
        private Dimension[] containerDimensions = {new Dimension(600,100),new Dimension(600,100),new Dimension(600,100),
        										   new Dimension(600,100),new Dimension(600,100)};
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private String[] controlNames = {"JButton", "JCheckBox", "JComboBox","JEditorPane","JLabel", "JList", 
        								 "JPasswordField", "JProgressBar", "JRadioButton", "JScrollBar", 
        								 "JSeparator", "JSlider", "JSpinner", "JTable", "JTextArea", "JTextPane",
        								 "JToggleButton"};
        private Component[] controlComp = {new JButton(), new JCheckBox(), new JComboBox(), new JEditorPane(),
				 new JLabel(), new JList(), new JPasswordField("TextArea1"), new JProgressBar(), new JRadioButton(), new JScrollBar(),
				 new JSeparator(), new JSlider(), new JSpinner(), new JTable(), new JTextArea("TextArea1"), new JTextPane(),
				 new JToggleButton()};
        private Dimension[] controlDimensions = {new Dimension(100,30),new Dimension(600,100),new Dimension(600,100),
        										 new Dimension(600,100),new Dimension(600,100),new Dimension(100,30),
        										 new Dimension(600,100),new Dimension(600,100),new Dimension(600,100),
        										 new Dimension(600,100),new Dimension(100,30),new Dimension(600,100),
        										 new Dimension(600,100),new Dimension(600,100),new Dimension(600,100),
        										 new Dimension(100,30),new Dimension(600,100),new Dimension(600,100)};
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        private String[] menuNames = {"JCheckBoxMenuItem", "JMenu", "JMenuBar","JMenuItem","JPopupMenu", 
        							  "JRadioButtonMenuItem"};
        private Component[] menuComp = {new JCheckBoxMenuItem(), new JMenu(), new JMenuBar(), new JMenuItem(),
				 new JPopupMenu(), new JRadioButtonMenuItem()};
        private Dimension[] menuDimensions = {new Dimension(600,100),new Dimension(600,100),new Dimension(600,100),new Dimension(600,100),new Dimension(600,100)};
        
        protected JPanel containers, layouts, controls, menus;
        private HashMap<String, Component> components;
        public CompPanel(){
                this.containers = new JPanel();
                this.controls = new JPanel();
                this.menus = new JPanel();
                this.components = new HashMap<String,Component>();
                layComponents();
        }

        
        public String[] getContainerNames(){
        	return containerNames;
        }
        
        public Component[] getContainerComps(){
        	return containerComp;
        }
        
        public String[] getControls(){
        	return controlNames;
        }
        
        public Component[] getControlComps(){
        	return controlComp;
        }
        
        public String[] getMenus(){
        	return menuNames;
        }
        
        public Component[] getMenuComps(){
        	return menuComp;
        }
        
        public HashMap<String, Component> getComponentsMap(){
        	return components;
        }
        
        public void addLabelToContainers(Component c, JLabel addThis){
        	containers.add(addThis);
        	components.put(addThis.getText(), c);
        }

        public void addLabelToControls(Component c, JLabel addThis){
        	controls.add(addThis);
        	components.put(addThis.getText(), c);
        }
        public void addLabelToMenus(Component c, JLabel addThis){
        	menus.add(addThis);
        	components.put(addThis.getText(), c);
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