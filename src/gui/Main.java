package gui;

import java.io.FileNotFoundException;


public class Main {
 public static void main(String[] args) throws FileNotFoundException{	 
	 PopUp window = new PopUp();
	 if(window.GUIname == null || window.GUIname.length() < 1){
		 System.exit(0);
	 }
	 else{
		 GUI gui = new GUI(window.GUIname);
		 gui.setVisible(true);
	 }
 }
}