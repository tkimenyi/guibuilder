package brandon.gui;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener, DragGestureListener,
DragSourceListener, DropTargetListener, Transferable{
        private static final long serialVersionUID = 1L;  
        private JMenuBar MenuBar;
        private JMenu File,Edit,CompEdit,Layout;
        private JMenuItem Open,Save,SaveAs,New,Close,Resize,Delete,CompResize,Border,Grid;
        protected JPanel trashBin;
        protected CompPanel compPanel;
        protected UserGUI userFrame;
        private JScrollPane compScroll;
        public String[] componentList;
        private JSplitPane split;
        String SavingDirectory = System.getProperty("desktop.dir");
        static final DataFlavor[] dataflavor = { null };
		Object object;			
		static {
			try {
				dataflavor[0] = new DataFlavor(
						DataFlavor.javaJVMLocalObjectMimeType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
	public GUI(String name) throws FileNotFoundException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        setLocation(200, 100);
        setTitle("Swing GUI Builder");
        setLayout(new BorderLayout());
        createComponentList(); 
        createUsersGUI(name);
        createMenu();
	}
	
	public void createUsersGUI(String name){
        userFrame = new UserGUI(name);        
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userFrame, compScroll);
        split.setDividerLocation(600);
        add(split);
        trashBin = new JPanel();
        trashBin.setBackground(Color.white);
        JLabel label = new JLabel("Components from GUI");
        trashBin.add(label);
        trashBin.setPreferredSize(new Dimension(800, 50));
        add(trashBin, BorderLayout.SOUTH); 
	}
	
	public void createComponentList(){
        compPanel = new CompPanel();
        compPanel.setPreferredSize(new Dimension(200,700));
        compScroll = new JScrollPane(compPanel);
        //compScroll.setPreferredSize(new Dimension(200,700));  
        addDragListeners();
	}
	
	public void createMenu(){
        MenuBar = new JMenuBar();
        //below is the File Menu
        File = new JMenu("File");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        SaveAs = new JMenuItem("Save As");
        New = new JMenuItem("New");
        Close = new JMenuItem("Close");
        File.add(new JSeparator());
        File.add(New); File.add(Open); File.add(Save); File.add(SaveAs); File.add(Close); 
        MenuBar.add(File);        
        New.addActionListener(this);Open.addActionListener(this);Save.addActionListener(this);SaveAs.addActionListener(this);Close.addActionListener(this);
        //below is the Edit menu
        Edit = new JMenu("Edit");
        Resize = new JMenuItem("Resize");       
        Edit.add(new JSeparator());
        Edit.add(Resize); 
        MenuBar.add(Edit);
        Resize.addActionListener(this);
        //below is Comp. Edit menu
        CompEdit = new JMenu("Component Edit");
        CompResize = new JMenuItem("Resize");
        Delete = new JMenuItem("Delete");        
        CompEdit.add(new JSeparator());
        CompEdit.add(CompResize); CompEdit.add(Delete);
        MenuBar.add(CompEdit);
        CompResize.addActionListener(this);Delete.addActionListener(this);
        //add Layout
        Layout = new JMenu("Layout Select");
        Border = new JMenuItem("BorderLayout");
        Grid = new JMenuItem("GridLayout");
        Layout.add(new JSeparator());
        Layout.add(Border);Layout.add(Grid);
        Border.addActionListener(this);Grid.addActionListener(this);
        MenuBar.add(Layout);
        
        add(MenuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == New){	
			System.out.println("New pushed");
		}
		if(evt.getSource() == Open){	
	         JFileChooser chooser = new JFileChooser(SavingDirectory);
	         chooser.setDialogTitle("Pick the file you wish to load");       
	         int result = chooser.showDialog(null, "Select");
	         if(result != JFileChooser.APPROVE_OPTION){ 
	         }         
		     //File selected = chooser.getSelectedFile();
		}
		if(evt.getSource() == Save){
			//this should simply save what we have, not bring up the file chooser
			JFileChooser fileChooser = new JFileChooser(SavingDirectory);    
	        fileChooser.showSaveDialog(null);
	        File f = fileChooser.getSelectedFile();
	        if(f != null ) {
	        	if(canSaveFile(f)) {
	                saveFile();
	            }
	        } else {
	                System.out.println("No file selected");
	        }
	    } 
		if(evt.getSource() == SaveAs){	
			JFileChooser fileChooser = new JFileChooser(SavingDirectory);    
	        fileChooser.showSaveDialog(null);
	        File f = fileChooser.getSelectedFile();
	        if(f != null ) {
	        	if(canSaveFile(f)) {
	                saveFile();
	            }
	        } else {
	                System.out.println("No file selected");
	        }
		}
		if(evt.getSource() == Close){
			int answer = JOptionPane.showConfirmDialog(this, "Are you sure you wish to close?" );
			System.out.println(answer);
			if(answer == 0){
				this.remove(userFrame);	
				userFrame.setVisible(false);
				repaint();
			}
		}
		if(evt.getSource() == Resize){
			boolean run = true;
			while(run){
				String x = JOptionPane.showInputDialog(this, "Please give me the width you desire(>400)");
				if(x != null){
					String y = JOptionPane.showInputDialog(this, "Please give me the height you desire(>400)");
					if(y != null){
						if(x.length() > 0 && y.length() >0){
							int x1 = Integer.parseInt(x);
							int y1 = Integer.parseInt(y);
							if(x1 < 400 || y1 < 400){	
								JOptionPane.showMessageDialog(this, "The width or height was too small, please resize to 400x400 or above.");
							}
							else
								this.setSize(new Dimension(x1,y1));				
								run= false;
						}
					}
				}
				run = false;
			}
		}
		if(evt.getSource() == Border){
			userFrame.layoutBorderSetter();
			//changedLayout();
		}
		if(evt.getSource() == Grid){			
			userFrame.layoutGridSetter(4,5);
			//changedLayout();
		}
		if(evt.getSource() == Delete){	
			System.out.println("Delete pushed");
		}
	}
	
	public Boolean rightDirectory(File f) {
        if (f.getParent().equalsIgnoreCase(SavingDirectory)){
                return true;
        } else {
                return false;
        }
	}

	public Boolean canSaveFile(File f) {
        Boolean CanSave = rightDirectory(f);
        if(!CanSave) JOptionPane.showMessageDialog(this, "You can not save to that directory.", "Bad directory", JOptionPane.PLAIN_MESSAGE);
        return CanSave;
	}
	
	public void saveFile(){
		System.out.println("This method will generate code");
	}
	
	public void updateGUI(){
		repaint();
		validate();
	}
	
	/*public void changedLayout(){
	 *this will get all the components from safari's tree class and put them as JLabels into the trash bin.
		for(int i = 0; i < userFrame.userComponents.size(); i++){
			trashBin.add(userFrame.userComponents.get(i).getName(), this);
		}
		userFrame.userComponents.clear();		
	}*/
	
	public void addDragListeners(){
		String[] containers = compPanel.getContainerNames();
		Component[] contComp = compPanel.getContainerComps();
		String[] controls = compPanel.getControls();
		Component[] controlComp = compPanel.getControlComps();
		String[] menus = compPanel.getMenus();
		Component[] menuComp = compPanel.getMenuComps();
		for(int i = 0; i < containers.length; i++){
            final JLabel curLabel = new JLabel(containers[i]);
            removeDup(curLabel);
            compPanel.addLabelToContainers(contComp[i], curLabel);
		} 
		for(int i = 0; i < controls.length; i++){
            final JLabel curLabel = new JLabel(controls[i]);
            removeDup(curLabel);
            compPanel.addLabelToControls(controlComp[i],curLabel);
		}
		for(int i = 0; i < menus.length; i++){
            final JLabel curLabel = new JLabel(menus[i]);
            removeDup(curLabel);
            compPanel.addLabelToMenus(menuComp[i], curLabel);
		}
	}
    public void removeDup(JLabel curLabel){
            curLabel.setFocusable(true);
            curLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            curLabel.setBorder(BorderFactory.createRaisedBevelBorder());            
            
    		DragSource dragSource = new DragSource();
    		new DropTarget(this,DnDConstants.ACTION_MOVE, this);                		
    		dragSource.createDefaultDragGestureRecognizer(curLabel, DnDConstants.ACTION_MOVE, this);
    		dragSource.addDragSourceListener(null);
    		curLabel.addMouseListener(new MouseAdapter(){    			
    			public void mouseReleased(MouseEvent e){
    				userFrame.setPanelCoordinates(e.getX(), e.getY());
    			}
    		});
		}	
	
	public Object getTransferData(DataFlavor flavor) {
		if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
			return object;
		} else {
			return null;
		}
	}

	public DataFlavor[] getTransferDataFlavors() {
		return dataflavor;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
	}

	// DragGestureListener method.
	public void dragGestureRecognized(DragGestureEvent dge) {
		dge.getDragSource().startDrag(dge, null, this, this, SystemFlavorMap.getDefaultFlavorMap());
	}

	// DragSourceListener methods.
	public void dragDropEnd(DragSourceDropEvent dsde) {
		userFrame.setPanelCoordinates(dsde.getX(), dsde.getY());		
	}

	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println("DragSourceDragEvent Fired");
	}

	public void dragExit(DragSourceEvent dse) {
		System.out.println("DragSourceEvent Fired");
	}

	public void dragOver(DragSourceDragEvent dsde) {
		object = dsde.getSource();
	}

	public void dropActionChanged(DragSourceDragEvent dsde) {
		System.out.println("DragSourceDragEvent Fired");
	}

	// DropTargetListener methods.
	public void dragEnter(DropTargetDragEvent dtde) {
		JLabel lb = new JLabel("test");
		lb.setBounds((int)dtde.getLocation().getX(), (int) dtde.getLocation().getY(), 200, 40);
		lb.setVisible(true);
	}

	public void dragExit(DropTargetEvent dte) {
	}

	public void dragOver(DropTargetDragEvent dtde) {
		dropTargetDrag(dtde);
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		dropTargetDrag(dtde);
	}

	void dropTargetDrag(DropTargetDragEvent dtde) {
		dtde.acceptDrag(dtde.getDropAction());
	}

	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(dtde.getDropAction());		
		try {
			Object source = dtde.getTransferable().getTransferData(
					dataflavor[0]);
			Component component = ((DragSourceContext) source).getComponent();
			Container oldContainer = component.getParent();
			userFrame.setPanelCoordinates(dtde.getLocation().x, dtde.getLocation().y);
			if(component instanceof JLabel){
				String compName = ((JLabel) component).getText();
				HashMap<String, Component> comps = compPanel.getComponentsMap();
				Component c = comps.get(compName);
				userFrame.changeUserFrame(c);
				updateGUI();
			}
			oldContainer.validate();
			oldContainer.repaint();
			userFrame.validate();
			userFrame.repaint();
			updateGUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dtde.dropComplete(true);
	}
}
