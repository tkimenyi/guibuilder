package codegenerating;
import componenttree.ComponentItem;
import componenttree.ContainerItem;
import gui.ResizableBorder;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.Border;


public class Generator {	
	private final String import1 = "import javax.swing.*;";
	private final String import2 = "import java.awt.*;";
	private final String import3 = "import java.awt.event.ActionEvent;";
	private final String import4 = "import java.awt.event.ActionListener;";
	private final String classHeader = "public class";
	private final String mainMethod = "public static void main(String[] args){\n"; 
	private final String mainFrameDeclaration = "JFrame frame = new JFrame(";
	private final String blackline = "BorderFactory.createLineBorder(Color.black)";
	private final String override = "@Override";
	private String extend = " extends JPanel";
	private String packageName = "codegenerating;";
	
	private StringBuilder instanceVariables;
	private StringBuilder codeToDeclare;
	private StringBuilder codeToAdd;
	private StringBuilder codeOntoFrame;
	private StringBuilder codeToMethod;
	private StringBuilder codeToAction;
	private StringBuilder giveActList;

	public Generator(){
		 instanceVariables = new StringBuilder();
		 codeToAdd= new StringBuilder();
		 codeToDeclare= new StringBuilder();
		 codeOntoFrame= new StringBuilder();
		 codeToMethod = new StringBuilder();
		 codeToAction = new StringBuilder();
		 giveActList = new StringBuilder();
	}
	
	public void setPackage(String pack) {
		packageName = pack;
	}
	
	public void putCodeInFile(String code, String filename, String savedir){		
		try{
			File codeFile = new File(savedir + '/' + filename + ".java");			
			if (codeFile.exists()){
				codeFile.delete();
			}
			codeFile.createNewFile();
			codeToAdd= new StringBuilder();
			codeToDeclare= new StringBuilder();
			codeOntoFrame= new StringBuilder();
			codeToMethod = new StringBuilder();
			codeToAction = new StringBuilder();
			instanceVariables = new StringBuilder();
			giveActList = new StringBuilder();
			
			FileWriter fw = new FileWriter(codeFile.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			writer.append(code);
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void addVarDeclarations(String componentName, String componentType ){
		String declare = componentType + " "+ componentName + "=" + "new" + " " + componentType + "();\n";
		if(!codeToDeclare.toString().contains(declare)){
			codeToDeclare.append(declare);
		}
	}

	private ArrayList<String> generateLines(String guiname, String filename){
		ArrayList<String> generatedLines = new ArrayList<String>();
		generatedLines.add("package " + packageName);
		generatedLines.add("");
		generatedLines.add(import1);
		generatedLines.add(import2);
		if(codeToAction.length() > 1){
			codeToAction.append("}");
			generatedLines.add(import3);
			generatedLines.add(import4);
		}
		generatedLines.add("");
		generatedLines.add(classHeader + " " + filename + extend + "{");
		generatedLines.add(instanceVariables.toString());
		generatedLines.add("\tpublic " + filename + "(){\n\t\t");
		generatedLines.add("\t\t" + mainFrameDeclaration + '"' + guiname  + '"' + ");");
		generatedLines.add("\t\t" + codeToDeclare.toString());
		generatedLines.add("\t\t" + codeToAdd.toString());
		generatedLines.add("\t\t" + giveActList.toString());
		generatedLines.add("\t" + "\t" + codeOntoFrame.toString());
		generatedLines.add("\t" + "}");		
		generatedLines.add("\t" + codeToAction + "\n\t");
		generatedLines.add("\t" + mainMethod);
		generatedLines.add("\t\t" + filename + " run = new " + filename + "();");
		generatedLines.add("\t\trun.setVisible(true);");
		generatedLines.add("\t" + "}");
		generatedLines.add("}");
		return generatedLines;
	}
	

	
	public String getCode(String guiname, String filename){
		ArrayList<String> generatedLines = generateLines(guiname, filename);
		StringBuilder allCode = new StringBuilder();
		for(int i=0;i<generatedLines.size();i++){
			allCode.append((generatedLines.get(i))+"\n");
		}
		
		return fixTabs(allCode.toString());
	}
	
	//This will return a correctly tab-formatted string of code.
	private String fixTabs(String code)
	{	code = code.replace("\t", "");
		int tabindex = 0;
		StringBuilder builder = new StringBuilder();
		String[] lines =  code.split("\n");
		for (String line: lines){
			
			if (line.contains("}")){
				tabindex--;
			}
			String s = "";
			for (int i = 0; i < tabindex; i++)
				s+="\t";
			builder.append(s+line+"\n");	
			if(line.contains("{")){
				tabindex++;
			}
		}
		return builder.toString();		
	}

	public void actionListenerMethod(String methodName, String returnType, String usercode, boolean isFirstEvent, String name){
		if(isFirstEvent){
			extend += " implements ActionListener";
			codeToAction.append(override + "\n");
			String methodSignature = "\tpublic " + returnType + " " + methodName + usercode;
			codeToAction.append(methodSignature);
		}
		else{			
			codeToAction.append(usercode);
		}
		giveActList.append(name + ".addActionListener(this);\n");
	}
	
	public void blankMethodGenerator(String methodName, String returnType){
		String methodSignature = "public " + returnType + " " + methodName + "\n\t// User code here. Be sure to add actionListener to your component first!\n\n\n\n\t}";
		codeToMethod.append(methodSignature);
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
	
	public String getBoundStmt(ComponentItem item){
		Rectangle dim = item.getBounds();
		int width = dim.width;
		int height = dim.height;
		int x = dim.x;
		int y = dim.y;
		if(height!=0 && width!=0){
			String setBoundsCode = item.getName().toLowerCase() + ".setBounds(" + x + "," + y + "," + width + "," + height + ");\n"; 
			return setBoundsCode;
		}
		return "";
	}
	
	public String getBorderStmt(ComponentItem item){
		Border dim = item.getBorder();		
		if(dim instanceof ResizableBorder){
			String setBoundsCode = item.getName().toLowerCase() + ".setBorder(" + blackline + ");\n";
			return setBoundsCode;
		}
		return "";
	} 
	
	public String getTextStmt(ComponentItem item){
		String text = item.getText();
		if(text.equals("")){
			return "";
		}
		else {
			String setTextCode = item.getName().toLowerCase() +".setText(" + '"' + text + '"' + ");\n";
			return String.format(setTextCode, text);
		}
	}

	public String getDeclaration(ComponentItem item){
		return item.getName().toLowerCase() + " = " + "new" + " " + item.getType() + "();\n";
	}
	
	public String getDeclarationPanel(ComponentItem item){
		return item.getName().toLowerCase() + " = " + "new" + " " + item.getType() + "(null);\n";
	}
	
	public String getBackgroundCode(ComponentItem item){
		return item.getName().toLowerCase() + ".setBackground(Color.white);\n";
	}
	
	//handles containers
	public StringBuilder getContainerCode(ContainerItem item){
		String layout= item.getLayoutType();
		StringBuilder res = new StringBuilder();
		String parentName = item.getName().toLowerCase();
		String layoutType = "";
		if(item.iterator().hasNext() || item.getName().equals("jpanel1")){			
			if(layout.equals("border")){
				res.append(parentName + "." + "setLayout(new BorderLayout());\n");
				layoutType = "border";
			}else if(layout.equals("grid")){
				int rows = 6;
				int cols = 6;
				res.append(parentName + "." + "setLayout(new GridLayout( " + rows + ", " + cols +"));\n");
				layoutType = "grid";
			}
		}
		Iterator<ComponentItem> children = item.iterator();
		ComponentItem child; 
		ArrayList<ComponentItem> reversedChildren = new ArrayList<ComponentItem>();
		while(children.hasNext()){
		//	reversedChildren.add(children.next());
		//}
		//for(int i = reversedChildren.size()-1; i > -1; i--){
			child = children.next();//reversedChildren.get(i);
			String childName = child.getName().toLowerCase();
			String addStmt = "";
			if(layoutType.equals("border")){
				addStmt = parentName + ".add(" + childName + ",BorderLayout." + child.getBorderLocation().toUpperCase() + ");\n";
			}else if(layoutType.equals("grid")){
				int rowLoc = (int)(child.getGridLocation().getX());
				int colLoc = (int)(child.getGridLocation().getY());
				addStmt = parentName + ".add("+childName + "," + (rowLoc) + ", " + (colLoc) + ");\n";
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
		System.out.println(item.getName());
		ContainerItem parent = item.getParent();
		if(parent == null){			
			res.append(getDeclarationPanel(item));		
			instanceVariables.append("\t" + item.getType() + " " + item.getName() + ";\n");
			res.append("\t" + "\t");
			res.append(getBackgroundCode(item));
		}
		else{
			System.out.println("Parent " + item.getParent().getName());
			instanceVariables.append("\t" + item.getType() + " " + item.getName() + ";\n");
			res.append(getDeclaration(item));			
			res.append("\t" + "\t");
			res.append(getBoundStmt(item));			
			res.append("\t" + "\t");
			res.append(getBorderStmt(item));			
			res.append("\t" + "\t");
			if(getTextStmt(item).length() > 1){
				res.append(getTextStmt(item));			
				res.append("\t" + "\t");
			}
		}
		res.append(getSizeStmt(item));		
		res.append("\t" + "\t");			
		if(item instanceof ContainerItem){
			Iterator<ComponentItem> children = ((ContainerItem) item).iterator();
			ComponentItem child;
			while(children.hasNext()){
				child = children.next();
				if(child instanceof ContainerItem){
					res.append(getComponentCode((ContainerItem) child));
				}
				else{
					res.append(getComponentCode(child));
				}
			}
			res.append(getContainerCode((ContainerItem) item));
		}
		return res;
	}
	
	// adds containers to the frame
	public void addToFrame(ComponentItem item){
		String name = item.getName().toLowerCase();
		String setFrameVisible = "frame.setVisible(true);\n";
		String setFrameSize = "frame.setSize(594,700);\n";
		String codeToFrame = "frame" + ".add"+"("+name+");\n";
		String exit = "frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n";
		codeOntoFrame.append(codeToFrame);
		codeOntoFrame.append("\t" + "\t");
		codeOntoFrame.append(setFrameSize);
		codeOntoFrame.append("\t" + "\t");
		codeOntoFrame.append(setFrameVisible);
		codeOntoFrame.append("\t" + "\t");
		codeOntoFrame.append(exit);
	}	
}
