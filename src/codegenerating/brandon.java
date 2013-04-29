package codegenerating;

import javax.swing.*;
import java.awt.*;

public class brandon extends JPanel{
	JPanel jpanel1;
	JPanel jpanel2;
	JPanel jpanel3;
	JPanel jpanel4;
	JPanel jpanel5;
	JPanel jpanel6;
	JPanel jpanel7;
	JButton jbutton1;
	JCheckBox jcheckbox1;
	JPanel jpanel8;
	JScrollBar jscrollbar1;
	JComboBox jcombobox1;
	JMenuBar jmenubar1;
	JMenu jmenu1;
	JMenu jmenu2;
	JSlider jslider1;
	
	public brandon(){
		
		JFrame frame = new JFrame("brandon");
		
		jpanel1 = new JPanel(null);
		jpanel1.setBackground(Color.white);
		jpanel2 = new JPanel(null);
		jpanel2.setBounds(54,333,369,296);
		jpanel2.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel2.setPreferredSize(new Dimension(100,100));
		jpanel3 = new JPanel(null);
		jpanel3.setBounds(10,10,349,100);
		jpanel3.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel3.setPreferredSize(new Dimension(600,100));
		jpanel4 = new JPanel(null);
		jpanel4.setBounds(259,110,100,76);
		jpanel4.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel4.setPreferredSize(new Dimension(100,600));
		jpanel5 = new JPanel(null);
		jpanel5.setBounds(10,186,349,100);
		jpanel5.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel5.setPreferredSize(new Dimension(600,100));
		jpanel6 = new JPanel(null);
		jpanel6.setBounds(10,110,100,76);
		jpanel6.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel6.setPreferredSize(new Dimension(100,600));
		jpanel7 = new JPanel(null);
		jpanel7.setBounds(110,110,149,76);
		jpanel7.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel7.setPreferredSize(new Dimension(600,100));
		jpanel2.setLayout(new BorderLayout());
		jpanel2.add(jpanel3,BorderLayout.NORTH);
		jpanel2.add(jpanel4,BorderLayout.EAST);
		jpanel2.add(jpanel5,BorderLayout.SOUTH);
		jpanel2.add(jpanel6,BorderLayout.WEST);
		jpanel2.add(jpanel7,BorderLayout.CENTER);
		jbutton1 = new JButton();
		jbutton1.setBounds(212,101,100,50);
		jbutton1.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton1.setPreferredSize(new Dimension(100,50));
		jcheckbox1 = new JCheckBox();
		jcheckbox1.setBounds(388,102,50,50);
		jcheckbox1.setBorder(BorderFactory.createLineBorder(Color.black));
		jcheckbox1.setPreferredSize(new Dimension(50,50));
		jpanel8 = new JPanel(null);
		jpanel8.setBounds(7,69,213,255);
		jpanel8.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel8.setPreferredSize(new Dimension(100,100));
		jscrollbar1 = new JScrollBar();
		jscrollbar1.setBounds(78,98,30,100);
		jscrollbar1.setBorder(BorderFactory.createLineBorder(Color.black));
		jscrollbar1.setPreferredSize(new Dimension(30,100));
		jpanel8.add(jscrollbar1);
		jcombobox1 = new JComboBox();
		jcombobox1.setBounds(394,207,150,50);
		jcombobox1.setBorder(BorderFactory.createLineBorder(Color.black));
		jcombobox1.setPreferredSize(new Dimension(150,50));
		jmenubar1 = new JMenuBar();
		jmenubar1.setBorder(BorderFactory.createLineBorder(Color.black));
		jmenu1 = new JMenu();
		jmenu1.setBorder(BorderFactory.createLineBorder(Color.black));
		jmenu1.setText("File");
		jmenu1.setPreferredSize(new Dimension(37,19));
		jmenu2 = new JMenu();
		jmenu2.setBorder(BorderFactory.createLineBorder(Color.black));
		jmenu2.setText("Edit");
		jmenu2.setPreferredSize(new Dimension(39,19));
		jmenubar1.add(jmenu1);
		jmenubar1.add(jmenu2);
		jslider1 = new JSlider();
		jslider1.setBounds(274,279,265,37);
		jslider1.setBorder(BorderFactory.createLineBorder(Color.black));
		jslider1.setPreferredSize(new Dimension(100,30));
		jpanel1.add(jpanel2);
		jpanel1.add(jbutton1);
		jpanel1.add(jcheckbox1);
		jpanel1.add(jpanel8);
		jpanel1.add(jcombobox1);
		jpanel1.add(jmenubar1);
		jpanel1.add(jslider1);
		
		
		frame.add(jpanel1);
		frame.setSize(594,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args){
		
		brandon run = new brandon();
		run.setVisible(true);
	}
}
