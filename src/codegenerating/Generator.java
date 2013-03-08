package codegenerating;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.io.*;


public class Generator {
	
	private final String import1 = "import javax.swing.*;";
	private final String className = "public class BuiltGUi extends JPanel {";
	private final String closingBracket="\n};";
	
	BufferedWriter writer;
	
	public Generator(){
		try{
			File codeFile = new File("src/BuitGUI.java");			
			if (!codeFile.exists()){
				codeFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(codeFile.getAbsoluteFile());
			writer = new BufferedWriter(fw);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// add imports from the GUI.java class
	public void addImports(){
		
	}
	
	public void generateCodeFile(){
		try{
			writer.append(import1 + "\n");
			writer.append(className +"\n" + "\n");
			writer.append(closingBracket);
			//writer.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	// generate code for adding a component to the main panel
	public void addComponentToPanelCode(String component, String panel){	
		String addComponentCode ="" +panel+ ".add(" +component+ ")";
		
		try{
			writer.append(addComponentCode);
			//writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	// generate code for setting the size of a particular component
	public void setSizeOfComponentCode(String comp, int width, int height){
		String setSize = comp + "setPreferredSize(" + "new Dimension(" + width + "," + height + "));\n"; 
		
		try{
			writer.append(setSize);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	// generate code for adding an event handler to the component
	public void addEventCode(JComponent c, int choice){
		
		switch(choice){
			
		}
	}
	
	// method generator
	public void methodGenerator(String methodName, String returnType){
		String methodSignature = "public " + returnType + " " + methodName + "{ \n\n\n\n" + "}";
		try{
			writer.append(methodSignature);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//method to create the layout
	public void getCreatedLayout(){
		
	}
	
}
