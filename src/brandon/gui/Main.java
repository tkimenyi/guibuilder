package brandon.gui;

import java.io.FileNotFoundException;


public class Main {
 public static void main(String[] args) throws FileNotFoundException{	 
	 PopUp window = new PopUp();
	 GUI gui = new GUI(window.GUIname);
	 gui.setVisible(true);
 }
}