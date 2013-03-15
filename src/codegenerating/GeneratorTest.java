package codegenerating;

import static org.junit.Assert.*;

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

import org.junit.Ignore;
import org.junit.Test;

import componenttree.ComponentManager;
import componenttree.ContainerItem;
import componenttree.ControlItem;

public class GeneratorTest {
	Generator gen = new Generator();
	Generator gen1 = new Generator();
	
	@Test
	public void test() {

		gen.addDeclarations("button", "JButton");
		gen.addComponentToContainerCode("button", "panel");
		gen.addCode();
		String generatedCode = gen.getCode();

		gen.generateFile(generatedCode);

		try {
			BufferedReader reader  = new BufferedReader(new FileReader("src/codegenerating/BuiltGui.java"));
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
	public void testCompilable(){
		assertTrue(isCompilableJava(gen.getCode(), "BuiltGui"));
	}

	
	@Test
	public void testConnection(){
		ComponentManager mng= new ComponentManager();
		ContainerItem root = new ContainerItem(new JPanel(), "JPanel");
		ControlItem button = new ControlItem(new JButton(), "JButton");
		ControlItem textarea = new ControlItem(new JTextArea(), "JTextArea");
		mng.setRoot(root);
		mng.getRoot().addChild(root, button, button.getType(), null);
		
		gen1.getTreeGenerated(root);
		gen1.addCode();
		String generatedCode = gen1.getCode();
		//assertTrue(isCompilableJava(gen1.getCode(), "BuiltGui"));
		gen1.generateFile(generatedCode);
		System.out.println(generatedCode);
	}

	
}
