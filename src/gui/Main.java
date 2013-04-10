package gui;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;


public class Main {
  
 public static void main(String[] args) throws FileNotFoundException{	 
	 String GUIname = JOptionPane.showInputDialog("Please name your GUI");
	 if(GUIname == null || GUIname.length() < 1){
		 System.exit(0);
	 }
	 else{
		 GUI gui = new GUI(GUIname);
		 gui.setVisible(true);
	 }
 }
}