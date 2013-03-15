package codegenerating;
import javax.swing.JComponent;
import javax.swing.JPanel;

import componenttree.ComponentItem;
import componenttree.ComponentManager;
import componenttree.ContainerItem;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.*;
import java.util.ArrayList;


public class Generator {
	
	private String packageName = "package codegenerating;";
	private final String import1 = "import javax.swing.*;";
	private final String className = "public class BuiltGui extends JPanel {";
	private final String closingBracket="}";
	private final String mainMethod = "public static void main(String[] args){"; 
	private final String mainFrameDeclaration = "JFrame frame = new JFrame(\"user gui\");";
	private ArrayList<String> generatedLines; 
	private StringBuilder codeToDeclare;
	private StringBuilder codeToAdd;
	private StringBuilder allCode;
	private ArrayList<String> declaredVariables;
	private StringBuilder codeOntoFrame;
	private String setFrameVisible = "frame.setVisible(true);";
	private String setFrameSize = "frame.setSize(800,800);";
	private BufferedWriter writer;
	private int current = 1;
	
	private String[] controlItems = {"JButton", "JCheckBox", "JComboBox","JEditorPane","JLabel", "JList", 
			 "JPasswordField", "JProgressBar", "JRadioButton", "JScrollBar", 
			 "JSeparator", "JSlider", "JSpinner", "JTable", "JTextArea", "JTextPane",
			 "JToggleButton", "JPanel", "JScrollPane", "JSplitPane", "JTabbedPane", "JToolbar"};	
	
	public Generator(){
		 generatedLines= new ArrayList<String>();
		 codeToAdd= new StringBuilder();
		 codeToDeclare= new StringBuilder();
		 codeOntoFrame= new StringBuilder();
		 allCode = new StringBuilder();
		 declaredVariables = new ArrayList<String>();
	}
	
	//put the code in the file
	public void generateFile(String code){
		try{
			File codeFile = new File("src/codegenerating/BuiltGui.java");			
			if (!codeFile.exists()){
				codeFile.createNewFile();
			}
			FileWriter fw = new FileWriter(codeFile.getAbsoluteFile());
			writer = new BufferedWriter(fw);
			writer.append(code);
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// add imports from the GUI.java class
	public void addImports(){
		
	}
	
	public void addCode(){
		generatedLines.add(packageName);
		generatedLines.add(import1);
		generatedLines.add(className);
		generatedLines.add(mainMethod);
		generatedLines.add(mainFrameDeclaration);
		generatedLines.add(codeToDeclare.toString());
		generatedLines.add(codeToAdd.toString());
		generatedLines.add(codeOntoFrame.toString());
		generatedLines.add(setFrameVisible);
		generatedLines.add(setFrameSize);
		generatedLines.add(closingBracket);
		generatedLines.add(closingBracket);
	}
	//get the code to put on the file
	public String getCode(){
		for(int i=0;i<generatedLines.size();i++){
			allCode.append((generatedLines.get(i))+"\n");
		}
		return allCode.toString();
	}
	
	//
	public boolean isDeclared(String componentName, String componentType){
		
		String toAdd = componentType + " "+ componentName + "=" + "new" + " " + componentType + "();";
		for(String s: generatedLines){
			if((s.equals(toAdd))){
				return true;
			}
		}
		return false;
	}
	
	public void addDeclarations(String componentName, String componentType ){
		String declare = componentType + " "+ componentName + "=" + "new" + " " + componentType + "();\n";
		if(!(isDeclared(componentName, componentType)))codeToDeclare.append(declare);
	}
	
	// generate code for adding a component to the main panel
	public void addComponentToContainerCode(String component, String container){	
		String addComponentCode ="" +container+ ".add(" +component+ ");\n";
		codeToAdd.append(addComponentCode);
	}

	// generate code for setting the size of a particular component
	public void setSizeOfComponentCode(String comp, int width, int height){
		String setSizeCode = comp + "setPreferredSize(" + "new Dimension(" + width + "," + height + "));\n"; 
		codeToAdd.append(setSizeCode);
	}
	
	// generate code for adding an event handler to the component
	public void addEventCode(String component, int choice){
		
		switch(choice){
			
		}
	}
	
	// method generator for handling event 
	public void methodGenerator(String methodName, String returnType){
		String methodSignature = "public " + returnType + " " + methodName + "{ \n\n\n\n" + "}";
		codeToAdd.append(methodSignature);
	}
	
	//method to create the layout
	public void setSelectedLayoutCode(String container, String layout){
		String setLayoutCode;
		if(layout == "BorderLayout"){
			
		}
	}

	
	public String createName(String type){
		String name="";
		String tomodify="";
		for(int j = 0; j < controlItems.length; j++){
			type = type.toLowerCase();
			if(type.equals(controlItems[j].toLowerCase())){
				tomodify = controlItems[j].toLowerCase();
			}
		}
		if(declaredVariables.size()>=0){
			if(declaredVariables.contains(tomodify+Integer.toString(current))){				
				name = tomodify+Integer.toString(current);
				
			}
			else{
				name = tomodify+Integer.toString(current);
			}
		}
		if(current == 1){
			name = tomodify+Integer.toString(1);
			current += 1;
		}
		declaredVariables.add(name);
		return name;
	}
	
	public void getTreeGenerated(ComponentItem item){		
		if(item instanceof ContainerItem){
			ArrayList<ComponentItem> children = ((ContainerItem) item).getChildren();
			String type = item.getType();
			String name = item.getName().toLowerCase();
			addDeclarations(name,type);
			for (ComponentItem itemChild: children){	
				getTreeGenerated(itemChild);
				String childName = itemChild.getName().toLowerCase();				
				addComponentToContainerCode(childName, name);				
			}
			addToFrame(name);
			current += 1;
		}else{
			String type = item.getType();
			String name = item.getName().toLowerCase();
			addDeclarations(name,type);
		}
	}
	public void addToFrame(String name){
		String codeToFrame = "frame" + ".add"+"("+name+");\n";
		codeOntoFrame.append(codeToFrame);
	}
	
}
