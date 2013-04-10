package codegenerating;
import componenttree.ComponentItem;
import componenttree.ContainerItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Generator {
	
	private String packageName = "package codegenerating;";
	private final String import1 = "import javax.swing.*;";
	private final String import2 = "import java.awt.*;";
	private final String className = "public class BuiltGui extends JPanel {";
	private final String closingBracket="}";
	private final String mainMethod = "public static void main(String[] args){"; 
	private final String mainFrameDeclaration = "JFrame frame = new JFrame(\"user gui\");";
	private ArrayList<String> generatedLines; 
	private StringBuilder codeToDeclare;
	private StringBuilder codeToAdd;
	private StringBuilder allCode;
	private StringBuilder codeOntoFrame;
	private BufferedWriter writer;
	
	public Generator(){
		 generatedLines= new ArrayList<String>();
		 codeToAdd= new StringBuilder();
		 codeToDeclare= new StringBuilder();
		 codeOntoFrame= new StringBuilder();
		 allCode = new StringBuilder();
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
	
	//check if a variable is declared
		public boolean isDeclared(String componentName, String componentType){
			String toAdd = componentType + " "+ componentName + "=" + "new" + " " + componentType + "();";
			for(String s: generatedLines){
				if((s.equals(toAdd))){
					return true;
				}
			}
			return false;
		}
	
	// add declarations of variables
		public void addDeclarations(String componentName, String componentType ){
			String declare = componentType + " "+ componentName + "=" + "new" + " " + componentType + "();\n";
			if(!(isDeclared(componentName, componentType))){
				codeToDeclare.append(declare);
			}
		}
	
	// array list of generated lines of code
	public void addCode(){
		generatedLines.add(packageName);
		generatedLines.add("");
		generatedLines.add(import1);
		generatedLines.add(import2);
		generatedLines.add("");
		generatedLines.add(className);
		generatedLines.add("\t" + mainMethod);
		generatedLines.add("\t" + "\t" + mainFrameDeclaration);
		generatedLines.add("\t" + "\t" + codeToDeclare.toString());
		generatedLines.add("\t" + "\t" + codeToAdd.toString());
		generatedLines.add("\t" + "\t" + codeOntoFrame.toString());
		generatedLines.add("\t" + closingBracket);
		generatedLines.add(closingBracket);
	}
	
	//get the code to put on the file
	public String getCode(){
		for(int i=0;i<generatedLines.size();i++){
			allCode.append((generatedLines.get(i))+"\n");
		}
		return allCode.toString();
	}

	// method generator for handling event 
	public void methodGenerator(String methodName, String returnType){
		String methodSignature = "public " + returnType + " " + methodName + "{ \n\n\n\n" + "}";
		codeToAdd.append(methodSignature);
	}
	
	//sets the properties from the tree structure
	public void setTreeGenerated(ComponentItem item){	
		codeToAdd.append(getComponentCode(item));
	}
	public String getSizeStmt(ComponentItem item){
		Dimension dim = item.getPreferredSize();
		int width = dim.width;
		int height = dim.height;
		if(height!=0 && width!=0){
			String setSizeCode = item.getName().toLowerCase() + ".setPreferredSize(" + "new Dimension(" + width + "," + height + "));\n"; 
			return setSizeCode;
		}
		return "";
	}

	public String getDeclaration(ComponentItem item){
		return item.getType() + " "+ item.getName().toLowerCase() + " = " + "new" + " " + item.getType() + "();\n";
	}
	//handles containers
	public StringBuilder getContainerCode(ContainerItem item){
		LayoutManager layout= ((ContainerItem)item).getLayout();
		StringBuilder res = new StringBuilder();
		String parentName = item.getName().toLowerCase();
		String layoutType = "";
		if(layout instanceof BorderLayout){
			res.append(parentName + "." + "setLayout(new BorderLayout())");
			layoutType = "border";
		}else if(layout instanceof GridLayout){
			int rows = ((GridLayout) layout).getRows();
			int cols = ((GridLayout) layout).getColumns();
			res.append(parentName + "." + "setLayout(new GridLayout( " + rows + ", " + cols +"));\n");
			layoutType = "grid";
		}
		Iterator<ComponentItem> children = ((ContainerItem)item).iterator();
		while(children.hasNext()){
			ComponentItem child = children.next();
			String childName = child.getName().toLowerCase();
			String addStmt = "";
			if(layoutType.equals("border")){
				addStmt = parentName + ".add(" + childName + ",BorderLayout." + child.getBorderLocation().toUpperCase() + ");\n";
			}else if(layoutType.equals("grid")){
				int rowLoc = (int)(child.getGridLocation().getX());
				int colLoc = (int)(child.getGridLocation().getY());
				addStmt = parentName + ".add("+childName + "," + rowLoc + ", " + colLoc + ");\n";
			}else{
				addStmt = parentName + ".add(" + childName + ");\n";
			}
			res.append(addStmt);			
			res.append("\t" + "\t");
		}
		return res;
	}
	
	//code for the entire tree
	public StringBuilder getComponentCode(ComponentItem item){
		StringBuilder res = new StringBuilder();
		res.append(getDeclaration(item));	
		res.append("\t" + "\t");
		res.append(getSizeStmt(item));		
		res.append("\t" + "\t");
		ContainerItem parent = item.getParent();
		if(parent != null){
			res.append(getContainerCode(parent));			
		}			
		/*Iterator<ComponentItem> children = parent.iterator();
		ComponentItem child;
		while(children.hasNext()){
			child = children.next();
			res.append(getComponentCode(child));			
		}*/
		res.append(getContainerCode((ContainerItem) item));		
		res.append("\t" + "\t");
		return res;
	}
	
	// adds containers to the frame
	public void addToFrame(ComponentItem item){
		String name = item.getName().toLowerCase();
		String setFrameVisible = "frame.setVisible(true);\n";
		String setFrameSize = "frame.setSize(800,800);\n";
		String codeToFrame = "frame" + ".add"+"("+name+");\n";
		String exit = "frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n";
		codeOntoFrame.append(codeToFrame);
		codeOntoFrame.append(setFrameSize);
		codeOntoFrame.append(setFrameVisible);
		codeOntoFrame.append(exit);
	}
	
}
