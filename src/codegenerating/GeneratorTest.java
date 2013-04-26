package codegenerating;

import static org.junit.Assert.*;

import java.awt.Dimension;
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
        Generator gen1 = new Generator();
        

        public static boolean isCompilableJava(String program, String className) throws IOException{
        	String filename = className + ".java";
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            out.println(program);
            out.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int result = compiler.run(null, null, null, filename);
            return result == 0;
        }
        
        @Test
        public void testConnection() throws IOException{
                ComponentTreeStruct mng= new ComponentTreeStruct();
                ContainerItem root = new ContainerItem(new JPanel(), "JPanel",new Dimension(400,400));
                ComponentItem button = new ComponentItem(new JButton(), "JButton",new Dimension(40,40));
                ComponentItem textarea = new ComponentItem(new JTextArea(), "JTextArea",new Dimension(130,40));
                ComponentItem textfield = new ComponentItem(new JTextArea(), "JTextField",new Dimension(140,30));
                mng.setRoot(root);
                root.setLayout("grid");
                mng.addChild(root, button, button.getType(), null);
                mng.addChild(root,textarea,textarea.getType(),null);    
                mng.addChild(root, textfield, textfield.getType(), null); 
                gen1.actionListenerMethod("actionPerformed(ActionEvent evt) ", "void", 
						 "{ \n\t if(evt.getSource() == " + "jbutton1" +"){\n\t\tSystem.out.println(\"Hello\");\n\t}"
						, true, "jbutton1");             
                
                gen1.setTreeGenerated(mng.getRoot());
                String generatedCode1 = gen1.getCode("testConnection", "testCon");
                gen1.putCodeInFile(generatedCode1, "testCon", "src/codegenerating/");
                gen1.addToFrame(mng.getRoot());
                assertTrue(isCompilableJava(generatedCode1, "testCon"));
        }

        
}