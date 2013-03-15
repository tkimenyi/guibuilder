package codegenerating;
import javax.swing.*;
public class BuiltGui extends JPanel {
public static void main(String[] args){
JFrame frame = new JFrame("user gui");
JPanel jpanel1=new JPanel();
JButton jbutton1=new JButton();
JButton jbutton1=new JButton();

jpanel1.add(jbutton1);
jpanel1.add(jbutton1);

frame.add(jpanel1);

frame.setVisible(true);
frame.setSize(800,800);
}
}
