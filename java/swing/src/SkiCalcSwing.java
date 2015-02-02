// SkiCalcSwing.java
// by Conor Gilmer (conor.gilmer@gmail.com)

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//import java.net.UnknownHostException;
//import java.io.IOException;
import java.sql.*;
//import java.text.DecimalFormat;
import java.text.MessageFormat;
//import java.util.*;
import java.util.ResourceBundle;

public class SkiCalcSwing  extends JFrame 
   implements ActionListener, MenuListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static  String displayMsg = "Ski  Trip Calculator";
    
	public ResourceBundle res = ResourceBundle.getBundle("STI");
	
    public float cost = 0;
    public float flights =0;
    public float transfers= 20;
    public float accom = 202;
    public float skihire = 110;
    public float skipass =120;

    public String dataFile = "skidata.csv";
    
   public SkiCalcSwing () 
   {
	   /** Basic window stuff: name, size	    * a	    */
	  //Util.debugOff();
	  setTitle("Ski Trip Calculator");
	  setSize(400, 600);
	  Toolkit tk = Toolkit.getDefaultToolkit();
	  Dimension screenSize = tk.getScreenSize();
	  
      setLocation(((screenSize.width - getSize().width)/2),
				  ((screenSize.height - getSize().height)/2));
      /** Need this to close the window       */
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
	  
	  /** Set up Menu 	   */
	  menuBar = new JMenuBar();
	  setJMenuBar(menuBar);

	  helpMenu = new JMenu("Help");
	  helpMenu.addMenuListener(this);
	  
	  aboutItem = new JMenuItem("About");
	  aboutItem.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

	  readmeItem = new JMenuItem("Read Me");
	  readmeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
	  
	  resetItem = new JMenuItem("Reset Trip Details");
	  resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
      resetItem.setEnabled(false);

      saveTripItem = new JMenuItem("Save Trip Details");
      saveTripItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
      saveTripItem.setEnabled(true);

      viewTripsItem = new JMenuItem("View Trips");
      viewTripsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
      viewTripsItem.setEnabled(true);
      menuBar.add(makeMenu(helpMenu, new Object[]{ aboutItem, readmeItem, saveTripItem, viewTripsItem, resetItem}, this));

	  /** End menu set-up   */
	  JPanel tripPanel = new JPanel();
	  tripPanel.setLayout(new GridLayout(0, 2, 5, 5));
	  tripPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Enter the Details of your Trip"));
	 
//	  JLabel cardSpace = new JLabel(" "); 
//	  tripPanel.add(cardSpace);

	  JPanel choicePanel = new JPanel();
	  choicePanel.setLayout(new GridLayout(1, 2));
	  choicePanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Save or Reset"));
	  Save = new JButton("Save");
	  Save.setEnabled(true);
	  Save.addActionListener(this);
	  choicePanel.add(Save);
	  
	  Reset = new JButton("Reset");
	  Reset.setEnabled(false);
	  Reset.addActionListener(this);
	  choicePanel.add(Reset);
 
	  resultsTextArea = new JTextArea();
	  JScrollPane resultsPanel = new JScrollPane(resultsTextArea);
	  resultsPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Display Results"));
	  @SuppressWarnings("unused")
	ButtonGroup directionGroup = new ButtonGroup();

	  destinationLabel = new JLabel();
	  destinationLabel.setText("Destination:");
	  tripPanel.add(destinationLabel);	  
	  
	  destinationField = new JTextField();
	  destinationField.setText(res.getString("Location"));
	  tripPanel.add(destinationField);
	 

	  flightsLabel = new JLabel();
	  flightsLabel.setText("Cost of Flights:");
	  tripPanel.add(flightsLabel);	  
	  
	  flightsField = new JTextField();
	  flightsField.setText(res.getString("Flights"));
	  tripPanel.add(flightsField);
	 
	  transferLabel = new JLabel();
	  transferLabel.setText("Cost of Transfers:");
	  tripPanel.add(transferLabel);	  
	  
	  transferField = new JTextField();
	  transferField.setText(res.getString("Transfers"));
	  tripPanel.add(transferField);
	 
	  accomLabel = new JLabel();
	  accomLabel.setText("Cost of Accommodation:");
	  tripPanel.add(accomLabel);	  
	  
	  accomField = new JTextField();
	  accomField.setText( res.getString("Accommodation") );
	  tripPanel.add(accomField);

	  skipassLabel = new JLabel();
	  skipassLabel.setText("Cost of Skipass:");
	  tripPanel.add(skipassLabel);	  
	  
	  skipassField = new JTextField();
	  skipassField.setText( res.getString("SkiPass") );
	  tripPanel.add(skipassField);
	  
	  skihireLabel = new JLabel();
	  skihireLabel.setText("Cost of Ski Hire:");
	  tripPanel.add(skihireLabel);	  
	  
	  skihireField = new JTextField();
	  skihireField.setText( res.getString("SkiHire") );
	  tripPanel.add(skihireField); 
	  
	  Calculate = new JButton("Calculate");
	  Calculate.setEnabled(true);
	  Calculate.addActionListener(this);
	  tripPanel.add(Calculate);

	  Clear = new JButton("Clear");
	  Clear.setEnabled(true);
	  Clear.addActionListener(this);
	  tripPanel.add(Clear);
	  
      Container contentPane = getContentPane();
	  contentPane.add(resultsPanel, "Center");
	  contentPane.add(tripPanel, "North");
	  contentPane.add(choicePanel, "South");

   }

   public JRadioButton addRadioButton(JPanel p, ButtonGroup g, String name, boolean selected)
   {  JRadioButton button          = new JRadioButton(name, selected);
      button.addActionListener(this);
      g.add(button);
      p.add(button);
      return button;
   }

   public JCheckBox addCheckBox(JPanel p, String name)
   {  JCheckBox checkBox = new JCheckBox(name);
      //checkBox.addActionListener(this);
      p.add(checkBox);
      return checkBox;
   }
   
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent evt)
   {  

  Object source = evt.getSource();
  if (source == Calculate)
	  {
	  		flights = Float.valueOf((flightsField.getText()).trim()).floatValue();
	  		transfers = Float.valueOf((transferField.getText()).trim()).floatValue();
	  		accom = Float.valueOf((accomField.getText()).trim()).floatValue();
	  		skipass = Float.valueOf((skipassField.getText()).trim()).floatValue();
	  		skihire = Float.valueOf((skihireField.getText()).trim()).floatValue();
	  		cost = flights + transfers + accom + skipass + skihire;
           displayLine("Ski Trip Costs:");
           displayLine("Destination:  \t\t" + destinationField.getText());
           displayLine("Flights:  \t\t" + floattoeuro(flights));
           displayLine("Transfers:  \t\t" + floattoeuro(transfers));
           displayLine("Accommodation:\t" + floattoeuro(accom));
           displayLine("Ski Pass:  \t\t" + floattoeuro(skipass));
           displayLine("Ski Hire:  \t\t" + floattoeuro(skihire));
           displayLine("Total Cost \t =\t" + floattoeuro(cost));
           
           
           Reset.setEnabled(true);
           resetItem.setEnabled(true);
	   
	  }
  else if (source == Save)
	  {
           displayMessage("Save");
           Reset.setEnabled(true);
           resetItem.setEnabled(true);
           // TODO : save skitrip object write/append to file
	  }

	  else if(source == aboutItem)
	  {
		  new About(this).show();
	  }
	else if(source == readmeItem)
	  {

		  new ReadMe(this).show();
	  }
         else if (source == Reset)
	{	
	 // runsField.setText( Integer.toString(runsno));
                Reset.setEnabled(false);
                resetItem.setEnabled(false);
		displayMessage("Reset Game");}
         else if (source == resetItem)
	{	
	 // runsField.setText( Integer.toString(runsno));
                Reset.setEnabled(false);
                resetItem.setEnabled(false);
		displayMessage("Reset Game");}
      repaint();
   }

   @SuppressWarnings("deprecation")
public static void main(String[] args)
   {  Frame f = new SkiCalcSwing();
      f.show();  
   }
   
   /* float to euro convert float to string currency formatted for displaying on screen*/
    public String floattoeuro(float input){
	//DecimalFormat df = new DecimalFormat();
	//df.setMaximumFractionDigits(2);
	 String str = String.format("�%.02f", input);
	
	//String str = df.format(input);
	return str;
    } /* end of floattoeuro */
   
   
   @SuppressWarnings("deprecation")
private void displayMessage(String message)
   {
	  
	  Time time = new Time(System.currentTimeMillis());
	  resultsTextArea.setFont(boldFont);
	  resultsTextArea.append(MessageFormat.format("{0}:\r\n\t", time.toLocaleString()));
	  resultsTextArea.append(message + "\n");
   } /* end of displayMessage */

      private void displayLine(String message)
	   {
	  
	  resultsTextArea.setFont(boldFont);
	  resultsTextArea.append(message + "\n");
	   } /* end of displayLine */

/*
      private void log(String message)
	   {
      	System.out.print(message);
	 //displayLine(message); 
	   }
*/

  


   public void menuSelected(MenuEvent evt)
   {
	   
   }
   public void menuDeselected(MenuEvent evt)
   {
   }
   public void menuCanceled(MenuEvent evt)
   {
   }
   
   public static JMenu makeMenu(Object parent, Object[] items, Object target)
   {
	   JMenu m = null;
	   if (parent instanceof JMenu)
		   m = (JMenu)parent;
	   else if (parent instanceof String)
		   m = new JMenu((String) parent);
	   else
		   return null;
	   
	   for (int i= 0; i < items.length; i++)
	   {
		   if (items[i] == null)
			   m.addSeparator();
		   else
			   m.add(makeMenuItem(items[i], target));
	   }
	   
	   return m;
   } /* end of makeMenu */
   
   public static JMenuItem makeMenuItem(Object item, Object target)
   {
	   JMenuItem r = null;
	   if (item instanceof String)
		   r = new JMenuItem((String)item);
	   else if (item instanceof JMenuItem)
		   r = (JMenuItem)item;	   
	   else return null;
	   if (target instanceof ActionListener)
		   r.addActionListener((ActionListener) target);
	   return r;
	   
   } /* end of makeMenuItem */

   /* labels and fields for inputs */
   private JLabel     destinationLabel;
   private JTextField destinationField;
   private JLabel     flightsLabel;
   private JTextField flightsField;
   private JLabel     transferLabel;
   private JTextField transferField;
   private JLabel     accomLabel;
   private JTextField accomField;
   private JLabel     skipassLabel;
   private JTextField skipassField;
   private JLabel     skihireLabel;
   private JTextField skihireField;
  
   /* menubar and menuitems */
   private JMenuBar   menuBar;
   private JMenuItem  aboutItem;
   private JMenuItem  readmeItem;   
   private JMenuItem  saveTripItem;
   private JMenuItem  viewTripsItem;
   private JMenuItem  resetItem;   
   private JMenu      helpMenu;
   
   /* display area */
   private JTextArea  resultsTextArea;
   
   /* buttons */
   private JButton    Calculate;
   private JButton    Clear;
   private JButton    Reset;
   private JButton    Save;   
   
   /* fonts  */
   Font boldFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
   
}


