package gui;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import codegenerating.Generator;


public class GUI extends JFrame implements ActionListener, DragGestureListener,
DragSourceListener, DropTargetListener, Transferable, ChangeListener{
        private static final long serialVersionUID = 1L;  
        private JMenuBar MenuBar;
        private JMenu File,Edit,CompEdit,Layout;
        private JMenuItem Open,Save,SaveAs,New,Close,Resize,Delete,CompResize,Border,Grid;
        protected JPanel trashBin;
        protected ComponentsPanel compPanel;
        protected UserGUI userFrame, curFrame;
        private JScrollPane compScroll;
        public String[] componentList;
        private JSplitPane split;
        private JTabbedPane userTab;
        private Generator gen;
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
        gen = new Generator();
	}
	
	private void createUsersGUI(String name){
        userFrame = new UserGUI(name);                 
        userTab = new JTabbedPane();
        userTab.add(userFrame);
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userTab, compScroll);
        split.setDividerLocation(600);
        add(split);        
        curFrame = userFrame;
        userTab.addChangeListener(this);
		setTabName(name);
		setUpTrashBin();
	}
	
	private void setUpTrashBin(){
		trashBin = new JPanel();
        trashBin.setBackground(Color.white);
        JLabel label = new JLabel("Components from GUI");
        trashBin.add(label);
        trashBin.setPreferredSize(new Dimension(800, 50));
        add(trashBin, BorderLayout.SOUTH); 
	}
	
	private void createComponentList(){
        compPanel = new ComponentsPanel();
        compPanel.setPreferredSize(new Dimension(200,700));
        compScroll = new JScrollPane(compPanel);  
        addDragListeners();
	}
	
	private void createMenu(){
        MenuBar = new JMenuBar();
        add(MenuBar, BorderLayout.NORTH);
        createFileMenu();
        createEditMenu();
        createCompEditMenu();
        createLayoutMenu();
	}
	private void createFileMenu(){
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
	}
	private void createEditMenu(){
        Edit = new JMenu("Edit");
        Resize = new JMenuItem("Resize");       
        Edit.add(new JSeparator());
        Edit.add(Resize); 
        MenuBar.add(Edit);
        Resize.addActionListener(this);
	}
	private void createCompEditMenu(){
        CompEdit = new JMenu("Component Edit");
        CompResize = new JMenuItem("Resize");
        Delete = new JMenuItem("Delete");        
        CompEdit.add(new JSeparator());
        CompEdit.add(CompResize); CompEdit.add(Delete);
        MenuBar.add(CompEdit);
        CompResize.addActionListener(this);Delete.addActionListener(this);
	}
	private void createLayoutMenu(){
        Layout = new JMenu("Layout Select");
        Border = new JMenuItem("BorderLayout");
        Grid = new JMenuItem("GridLayout");
        Layout.add(new JSeparator());
        Layout.add(Border);Layout.add(Grid);
        Border.addActionListener(this);Grid.addActionListener(this);
        MenuBar.add(Layout);       
	}
	
	public void setTabName(String name){
		int cur = userTab.getTabCount()-1;
		userTab.setSelectedIndex(cur);
		userTab.setTitleAt(cur, name);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == New){	
			String name = JOptionPane.showInputDialog("What do you want to name your GUI?");
			UserGUI newFrame = new UserGUI(name);
			userTab.add(newFrame);
			userTab.getComponentAt(userTab.getSelectedIndex()).setName(name);
			curFrame = newFrame;
			setTabName(name);
		}
		if(evt.getSource() == Open){	
	         JFileChooser chooser = new JFileChooser(SavingDirectory);
	         chooser.setDialogTitle("Pick the file you wish to load");       
	         int result = chooser.showDialog(null, "Select");
	         if(result != JFileChooser.APPROVE_OPTION){ 
	         }         
		}
		if(evt.getSource() == Save){
			//this should simply save what we have, not bring up the file chooser
			/*JFileChooser fileChooser = new JFileChooser(SavingDirectory);    
	        fileChooser.showSaveDialog(null);
	        File f = fileChooser.getSelectedFile();
	        if(f != null ) {
	        	if(canSaveFile(f)) {
	                saveFile();
	            }
	        } else {
	                System.out.println("No file selected");
	        }*/
			System.out.println(userFrame.getTreeStruct().getRoot().getChildren());
			gen.getTreeGenerated(userFrame.getTreeStruct().getRoot());
			gen.addCode();
			String code = gen.getCode();
			gen.generateFile(code);
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
	        //thierry.generate(userFrame.getTreeStruct());
		}
		if(evt.getSource() == Close){
			int answer = JOptionPane.showConfirmDialog(this, "Are you sure you wish to close?" );
			if(answer == 0){			
				if(userTab.getTabCount() > 1){
					userTab.remove(userTab.getSelectedIndex());
				}
				else{
					JOptionPane.showMessageDialog(this, "You only have one tab open!");
				}
				repaint();
			}
		}
		if(evt.getSource() == Resize){
			boolean run = true;
			while(run){
				String x = JOptionPane.showInputDialog(this, "Please give me the width you desire(>800)");
				if(x != null){
					String y = JOptionPane.showInputDialog(this, "Please give me the height you desire(>800)");
					if(y != null){
						if(x.length() > 0 && y.length() >0){
							int x1 = Integer.parseInt(x);
							int y1 = Integer.parseInt(y);
							if(x1 < 800 || y1 < 800){	
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
			curFrame.layoutBorderSetter(curFrame.getPanel());
			//changedLayout();
		}
		if(evt.getSource() == Grid){			
			curFrame.layoutGridSetter(curFrame.getPanel(),4,5);
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
		System.out.println("This method will call the method that will generate code");
	}
	
	public void updateGUI(){
		repaint();
		validate();
	}
	
	public void changedLayout(){	 
		System.out.println("The layout has been changed; we need to get all the Nodes from the tree and put these names in the trash bin");		
	}
	
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
	}

	public void dragExit(DragSourceEvent dse) {
	}

	public void dragOver(DragSourceDragEvent dsde) {
		object = dsde.getSource();
	}

	public void dropActionChanged(DragSourceDragEvent dsde) {
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
			curFrame.setPanelCoordinates(dtde.getLocation().x, dtde.getLocation().y);
			if(component instanceof JLabel){
				String compName = ((JLabel) component).getText();
				HashMap<String, Component> comps = compPanel.getComponentsMap();
				Component c = comps.get(compName);
				Dimension d = compPanel.getDimension(compName);
				curFrame.changeUserFrame(c, d, compName);
				updateGUI();
			}
			oldContainer.validate();
			oldContainer.repaint();
			curFrame.validate();
			curFrame.repaint();
			updateGUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dtde.dropComplete(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		curFrame = (UserGUI) userTab.getComponentAt(userTab.getSelectedIndex());		
	}
}
