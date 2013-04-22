package codegenerating;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Test;

import componenttree.ComponentItem;
import componenttree.ComponentTreeStruct;
import componenttree.ContainerItem;

public class GeneratorTest {
	Generator gen = new Generator();
	Generator gen1 = new Generator();
	
	
	@Test
	public void test() {

		gen.addVarDeclarations("button", "JButton");
		String generatedCode = gen.getCode("test", "test");
		gen.putCodeInFile(generatedCode, "test", "src/codegenerating/");

		try {
			BufferedReader reader  = new BufferedReader(new FileReader("src/codegenerating/test.java"));
			String line = null;
			StringBuilder strBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while((line=reader.readLine())!=null){
				strBuilder.append(line);
				strBuilder.append(ls);
			}
			String codeFromFile = strBuilder.toString();
			assertTrue(generatedCode.equals(codeFromFile));
			System.out.println("code from file:\n" + codeFromFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static boolean isCompilableJava(String program, String className){
		try {
			String filename = className + ".java";
			PrintWriter out = new PrintWriter(new FileWriter(filename));
			out.println(program);
			out.close();

			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			int result = compiler.run(null, null, null, filename);
			return result == 0;
		} catch (IOException ioe) {
			return false;
		}
	}
	
	@Test
	public void testConnection(){
		ComponentTreeStruct mng= new ComponentTreeStruct();
		ContainerItem root = new ContainerItem(new JPanel(), "JPanel",new Dimension(400,400));
		ComponentItem button = new ComponentItem(new JButton(), "JButton",new Dimension(40,40));
		ComponentItem textarea = new ComponentItem(new JTextArea(), "JTextArea",new Dimension(130,40));
		ComponentItem textfield = new ComponentItem(new JTextArea(), "JTextField",new Dimension(140,30));
		mng.setRoot(root);
		mng.addChild(root, button, button.getType(), null);
		mng.addChild(root,textarea,textarea.getType(),null);	
		mng.addChild(root, textfield, textfield.getType(), null);		
		
		gen1.setTreeGenerated(mng.getRoot());
		String generatedCode1 = gen1.getCode("testConnection", "testCon");
		gen1.putCodeInFile(generatedCode1, "testCon", "src/codegenerating/");
		assertTrue(isCompilableJava(generatedCode1, "testCon"));
		System.out.println(generatedCode1);
		gen1.addToFrame(mng.getRoot());
	}

	
}
