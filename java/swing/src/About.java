//About.java


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class About extends JDialog
{  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String applicationVersion = "v1.0";
	public About(JFrame parent)
   {  super(parent, "About Ski Calc", true);         

      Box b = Box.createVerticalBox();
      b.add(Box.createGlue());
      b.add(new JLabel("Calculate the costs of a ski trip in Java Swing."));
	  b.add(new JLabel("Display previously entered trip details"));
	  b.add(new JLabel("Write new trip to a file"));
	  b.add(new JLabel("Generate a Report on all Trips and store in a file."));
	  b.add(new JLabel("Author: Conor Gilmer (Webwayz Ltd.)"));
	  b.add(new JLabel("Version Info: " + applicationVersion));

      b.add(Box.createGlue());
      getContentPane().add(b, "Center");
                
      JPanel p2 = new JPanel();
      JButton ok = new JButton("Ok");
      p2.add(ok);
      getContentPane().add(p2, "South");

      ok.addActionListener(new ActionListener() 
         {  public void actionPerformed(ActionEvent evt) 
            { setVisible(false); } 
         } );

	  
      setSize(350, 250);
	  
	  	// try to put dialog in nice position
		Point parentPos = parent.getLocation();
		Dimension parentSize = parent.getSize();
		setLocation(parentPos.x + ((parentSize.width - getSize().width)/2),
					parentPos.y + ((parentSize.height - getSize().height)/2));
	
   }
}
