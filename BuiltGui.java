package codegenerating;
import javax.swing.*;
import java.awt.*;
public class BuiltGui extends JPanel {
public static void main(String[] args){
JFrame frame = new JFrame("user gui");

JPanel jpanel1 = new JPanel();
jpanel1.setPreferredSize(new Dimension(40,40));
JButton jbutton1 = new JButton();
jbutton1.setPreferredSize(new Dimension(34,10));
jpanel1.add(jbutton1);
jpanel1.add(jtextarea1);
JTextArea jtextarea1 = new JTextArea();
jpanel1.add(jbutton1);
jpanel1.add(jtextarea1);
jpanel1.add(jbutton1);
jpanel1.add(jtextarea1);


}
}

