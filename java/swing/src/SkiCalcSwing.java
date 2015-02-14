/* SkiCalcSwing.java
 * list trips stored in a csv file
 * add a new trip and calculate cost
 * by Conor Gilmer (conor.gilmer@gmail.com)
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
/* pdf generation classes */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class SkiCalcSwing  extends JFrame 
   implements ActionListener, MenuListener
{

	private static final long serialVersionUID = 1L;
	static  String displayMsg = "Ski  Trip Calculator";
    
	/* variables */
    public float cost         = 0;
    public String destination = "";
    public float flights      = 0;
    public float transfers    = 0;
    public float accom        = 0;
    public float skihire      = 0;
    public float skipass      = 0;

    /* i/o filenames */
	public ResourceBundle res = ResourceBundle.getBundle("STI"); /* some default values */
    public String dataFile    = "skidata.csv";
    public String reportFile  = "report.csv";
    
	public Color[] defcolours = {Color.green,Color.blue,Color.red, Color.gray, Color.magenta, Color.cyan, Color.orange, Color.yellow};

    
   public SkiCalcSwing () 
   {
	   /** Basic window stuff: name, size	    * a	    */
	  //Util.debugOff();
	  setTitle("Ski Trip Calculator");
	  setSize(750, 650);
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
      
      generateReportItem = new JMenuItem("Generate Report");
      generateReportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
      generateReportItem.setEnabled(true);
      
      generatePDFReportItem = new JMenuItem("Generate PDF Report");
      generatePDFReportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
      generatePDFReportItem.setEnabled(true);
  
      barChartItem = new JMenuItem("Bar Chart");
      barChartItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
      barChartItem.setEnabled(true);
 
      lineChartItem = new JMenuItem("Line Chart");
      lineChartItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
      lineChartItem.setEnabled(true);
  
      
      menuBar.add(makeMenu(helpMenu, new Object[]{ aboutItem, readmeItem}, this));
      
      tripMenu = new JMenu("Trips");  
      menuBar.add(makeMenu(tripMenu, new Object[]{viewTripsItem, saveTripItem, generateReportItem, generatePDFReportItem, barChartItem, lineChartItem, resetItem}, this));
	  /** End menu set-up   */
      
	  JPanel tripPanel = new JPanel();
	  tripPanel.setLayout(new GridLayout(0, 2, 5, 5));
	  tripPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Enter the Details of your Trip"));
	 
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

	  Report = new JButton("Report");
	  Report.setEnabled(true);
	  Report.addActionListener(this);
	  tripPanel.add(Report);
	  
      Container contentPane = getContentPane();
	  contentPane.add(resultsPanel, "Center");
	  contentPane.add(tripPanel,    "North" );
	  contentPane.add(choicePanel,  "South" );

   } /* end of SkiCalcSwing */

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
	  	 	destination = destinationField.getText();
	  	 	if (validInput())
	  	 	{
	  	
	  	 	flights = Float.valueOf((flightsField.getText()).trim()).floatValue();
	  		transfers = Float.valueOf((transferField.getText()).trim()).floatValue();
	  		accom = Float.valueOf((accomField.getText()).trim()).floatValue();
	  		skipass = Float.valueOf((skipassField.getText()).trim()).floatValue();
	  		skihire = Float.valueOf((skihireField.getText()).trim()).floatValue();
	  		cost = flights + transfers + accom + skipass + skihire;
	  		SkiTripInfo skiTrip = new SkiTripInfo(destination, flights, transfers, accom, skipass,skihire, 0, 0, 0, 0, 0, cost);
            displayLine("Ski Trip Costs:");
            displayLine("Destination:  \t\t" + destinationField.getText());
            displayLine("Flights:  \t\t" + floattoeuro(flights));
            displayLine("Transfers:  \t\t" + floattoeuro(transfers));
            displayLine("Accommodation:\t" + floattoeuro(accom));
            displayLine("Ski Pass:  \t\t" + floattoeuro(skipass));
            displayLine("Ski Hire:  \t\t" + floattoeuro(skihire));
            displayLine("Total Cost \t =\t" + floattoeuro(cost));

            displayLine("from object");
            displayLine(skiTrip.toString());
         //  writeToFile(destination + ","+ flights +","+ transfers+","+ accom+","+ skipass+","+skihire+", 0, 0, 0, 0, 0,"+ cost, dataFile);   
            Reset.setEnabled(true);
            resetItem.setEnabled(true);
	  	 	}
	  }
  else if (source == Save || source == saveTripItem)
	  {
            displayLine("Saving to " + dataFile);
	  	 	destination = destinationField.getText();
	  	 	if (validInput())
	  	 	{
	  		flights = Float.valueOf((flightsField.getText()).trim()).floatValue();
	  		transfers = Float.valueOf((transferField.getText()).trim()).floatValue();
	  		accom = Float.valueOf((accomField.getText()).trim()).floatValue();
	  		skipass = Float.valueOf((skipassField.getText()).trim()).floatValue();
	  		skihire = Float.valueOf((skihireField.getText()).trim()).floatValue();
	  		
	  		cost = flights + transfers + accom + skipass + skihire;
	  		SkiTripInfo skiTrip = new SkiTripInfo(destination, flights, transfers, accom, skipass,skihire, 0, 0, 0, 0, 0, cost);
	        writeToFile(skiTrip.csvRecord(), dataFile);
            Save.setEnabled(false);
            resetItem.setEnabled(true);
            Reset.setEnabled(true);
	  	 	}
	  }
  else if(source == viewTripsItem)
  {
	  readDataFile(dataFile);
	  SkiTripsReport str = generateReport(dataFile);
	  displayLine("\n" +str.toLine());

	  Save.setEnabled(true);
  }
  else if (source == Report || source == generateReportItem){
	  displayLine("generating report");
	  SkiTripsReport tripsReport = generateReport(dataFile);
	  displayLine(tripsReport.toString());
	  writeToFile(tripsReport.toString() + "\n", reportFile);
  }
  
  else if (source == generatePDFReportItem){
	  displayLine("generating PDF report");
	  SkiTripsReport tripsReport = generateReport(dataFile);
      genPDFReport(tripsReport);
      
	  //displayLine(tripsReport.toString());
	  //writeToFile(tripsReport.toString() + "\n", reportFile);
  }


  else if(source == barChartItem)
  {
	     displayLine("Bar Charting");
	     readDataFileGenBarChart(dataFile);
	  
  }


  else if(source == lineChartItem)
  {
	     displayLine("Line Charting");
	     //readDataFileGenBarChart(dataFile);
	     DrawGraph.main(null);
	  
  }
	  else if(source == aboutItem)
	  {
		  new About(this).show();
	  }
	else if(source == readmeItem)
	  {

		  new ReadMe(this).show();
	  }
         else if (source == Reset || source == resetItem)
	{	
        	    destinationField.setText("Where?");
        	    flightsField.setText("");
        	    transferField.setText("");
        	    accomField.setText("");
        	    skipassField.setText("");
        	    skihireField.setText("");
                Reset.setEnabled(false);
                resetItem.setEnabled(false);
                Save.setEnabled(true);
                saveTripItem.setEnabled(true);
		displayLine("Reset Input Fields");
		}
   } /* end of actionPerformed */

   @SuppressWarnings("deprecation")
public static void main(String[] args)
   {  Frame f = new SkiCalcSwing();
      f.show();  
   } /* end of main */
   
    /* float to euro convert float to string currency formatted for displaying on screen*/
    public String floattoeuro(float input){
	//DecimalFormat df = new DecimalFormat();
	//df.setMaximumFractionDigits(2);
	 String str = String.format("Û%.02f", input);
	
	//String str = df.format(input);
	return str;
    } /* end of floattoeuro */
   

      /* display a Line of text */	
      private void displayLine(String message)
	   {
	  
	  resultsTextArea.setFont(boldFont);
	  resultsTextArea.append(message + "\n");
	   } /* end of displayLine */

      
      /* write a string to a file, if file exists append string to file. */
      private void writeToFile(String str, String filename) {
              try {
                      File dataFile   = new File(filename);
                      PrintWriter out = null;
                      if ( dataFile.exists() && !dataFile.isDirectory() ) {
                              out = new PrintWriter(new FileOutputStream(new File(filename), true));
                              out.append(str);
                              out.close();
                              displayLine("\nRow added to file "+filename+"\n");
                      }
                      else {
                              out = new PrintWriter(filename);
                              out.append(str); /* out.println(str); avoid 2 carriage returns */
                              out.close();
                              displayLine("\nNew file "+ filename + " created and row added.\n");
                      }
              } catch (IOException iox) {
                      //do stuff with exception
                      iox.printStackTrace();
              }
      } /* end of writeToFile */
      

      /* read from a file and print on screen */
      private void readDataFile(String filename) {
              try {
                      File dataFile         = new File(filename);
                      FileReader fileReader = new FileReader(dataFile);
                      BufferedReader reader = new BufferedReader(fileReader);
                      //BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
                      String line = null;
                      displayLine("Contents of \"" + filename + "\"\n");
                      /* Headings */
             	      displayLine("Resort\tFlights\tTransfers\tHotel\tSki pass\tSki hire\tTotal" );
                      while ((line = reader.readLine()) != null) {
                             //displayLine(line);
                    	     String []  s = line.split(",");
                    	     displayLine(s[0] +"\t"+ s[1] +"\t"+s[2] +"\t" +s[3] +"\t" +s[4] +"\t" +s[5] +"\t" + s[11]);
                      }
                      displayLine("\nEnd of \"" + filename + "\"\n");
                      reader.close();
              } catch (IOException x) {
                      System.err.format("IOException: %s%n", x);
              }
      } /* end of readFile */
      
      /* read from a file and print on screen */
      private SkiTripsReport generateReport(String filename) {
              try {
            	  
                  int    trips        = 0; 
                  double avgFlights   = 0; 
                  double avgTransfers = 0;
                  double avgAccom     = 0;
                  double avgSkipass   = 0;
                  double avgSkihire   = 0;
                  double avgCost      = 0; 
                  
                      File dataFile         = new File(filename);
                      FileReader fileReader = new FileReader(dataFile);
                      BufferedReader reader = new BufferedReader(fileReader);
                      //BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
                      String line = null;
                      //displayLine("Contents of \"" + filename + "\"\n");
                      while ((line = reader.readLine()) != null) {
                 	     String []  s = line.split(",");
                             trips = trips + 1;
                             avgFlights= avgFlights + Float.valueOf(s[1]);
                             avgTransfers= avgTransfers + Float.valueOf(s[2]);
                             avgAccom= avgFlights + Float.valueOf(s[3]);
                             avgSkihire= avgSkihire + Float.valueOf(s[5]);
                             avgSkipass= avgSkipass + Float.valueOf(s[4]);
                             avgCost= avgCost + Float.valueOf(s[11]);         	     
                      }
                      avgFlights = avgFlights / trips;
                      avgTransfers = avgTransfers / trips;
                      avgAccom     = avgAccom / trips;
                      avgSkipass   = avgSkipass /trips;
                      avgSkihire   = avgSkihire / trips;
                      avgCost      = avgCost / trips;
                      //displayLine("\nEnd of \"" + filename + "\"\n");
                      reader.close();
                      SkiTripsReport tripsReport = new SkiTripsReport(trips, avgFlights, avgTransfers,avgAccom,avgSkipass,avgSkihire,avgCost);
               //       genPDFReport(tripsReport);
                      return tripsReport;
              } catch (IOException x) {
                      System.err.format("IOException: %s%n", x);
              }
			return null;
      } /* end of generateReport */
      
    
  	public void genPDFReport(SkiTripsReport tReport){
		String pdfFilename = "skiplanoutput.pdf";
		//String intext = "in here ya";
        PDDocument doc = null;
        PDPage page = null;
        displayLine("Generate PDF"+ pdfFilename +"\n");

       try{
           doc = new PDDocument();
           page = new PDPage();

           //System.out.print("Create PDF Document and Page \n");

           int linesize =25;
           doc.addPage(page);
           PDFont font  = PDType1Font.HELVETICA_BOLD;
           PDFont font1 = PDType1Font.HELVETICA;
           
            // adding an image to pdfbox output
           Image image = ImageIO.read(new File("logo.png"));
           BufferedImage bufferedImage = (BufferedImage) image;
           
           PDXObjectImage ximage = new PDPixelMap(doc, bufferedImage);

           //Calculate the image display ratio
        //   float width = PDPage.PAGE_SIZE_A4.getWidth()-30;
       //    float displayRatio = (float)width/bufferedImage.getWidth();
       //    float w=(float)(bufferedImage.getWidth()*displayRatio);
       //    float h=(float)(bufferedImage.getHeight()*displayRatio);
           
           PDPageContentStream content = new PDPageContentStream(doc, page);
           
           content.beginText();
           // set font and font size
           content.setFont( font, 15);
           content.moveTextPositionByAmount(100, 750);
           content.drawString( "Report");
           content.endText();
           
           content.drawImage(ximage, 300, 650);

           content.beginText();
           content.setFont( font, 12 );
           content.moveTextPositionByAmount( 100, 700 );
           content.drawString("**** Ski Holiday Report ****\n ");
           content.endText();

           BufferedReader reader = new BufferedReader(new StringReader(tReport.toString()));
           reader.readLine();
           
           String line = null;
           while ((line = reader.readLine()) != null) {
            	
           linesize = linesize + 20;
           content.beginText();
           content.setFont( font1, 12 );
           content.moveTextPositionByAmount( 100, 700 - linesize );
           content.drawString(line);
           content.endText();
           }
           
           linesize = linesize + 25;
           content.beginText();
           content.setFont( font, 12 );
           content.moveTextPositionByAmount( 100, 700 - linesize );
           content.drawString("**** End of Ski Holiday Report ****\n ");
           content.endText();
           
           content.close();
           
           
           
           
           
           
           //System.out.print("Writing to " + pdfFilename);
          doc.save(pdfFilename);
          doc.close();
          displayLine("File \""+ pdfFilename +"\" written.");
    } catch (Exception e){
        System.out.println(e);
    }
	}
      
     
  	
    /* read from a file and generate a bar chart */
    private void readDataFileGenBarChart(String filename) {
            try {
            	
            	    ArrayList<Double> yList = new ArrayList<Double>();
            	    ArrayList<String> xList = new ArrayList<String>();
            		//int row =0;
                    File dataFile         = new File(filename);
                    FileReader fileReader = new FileReader(dataFile);
                    BufferedReader reader = new BufferedReader(fileReader);
                    //BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
                    String line = null;
                    displayLine("Contents of \"" + filename + "\"\n");
                    /* Headings */
           	      displayLine("Resort\tFlights\tTransfers\tHotel\tSki pass\tSki hire\tTotal" );
                    while ((line = reader.readLine()) != null) {
                           //displayLine(line);
                  	     String []  s = line.split(",");
                  	     displayLine(s[0] +"\t"+ s[1] +"\t"+s[2] +"\t" +s[3] +"\t" +s[4] +"\t" +s[5] +"\t" + s[11]);
                
                  	     xList.add(s[0]);
                  	     yList.add(Double.valueOf(s[11])); 
                  	     //row++;
                    }
                	double [] yNumbers = new double[yList.size()];
            		String [] xItems = new String[xList.size()];
            	    
            	    for(int j =0;j<yList.size();j++){
            			  yNumbers[j] = yList.get(j);
            			  xItems[j] =xList.get(j);
            			}
            	    
            	    
                    displayLine("\nEnd of \"" + filename + "\"\n");
                    reader.close();
  
           	     String barChartTitle = "Ski Trip Bar Chart";
        	      int elmt = yNumbers.length;
        		 Color [] colours = new Color[elmt];
        		 for (int i=0; i<elmt; i++)
        		    {
        		      colours[i] = defcolours[i];
        		    }
        		  SimpleBarChart.main(yNumbers, xItems, colours, barChartTitle, 500, 400);
                    
                    
                    
            } catch (IOException x) {
                    System.err.format("IOException: %s%n", x);
            }
    } /* end of readFileGenBarChart */
  	
  	
  	
  	

   /* validate input values in invalid return false and text in display area and in textfield */   
   public boolean validInput(){
 		if (!isNumber(flightsField.getText())) {
  	 		displayLine("enter a number in flights field");
  	 		flightsField.setText("Enter a number");
  	 		return false; 	 		
  		} else if (!isNumber(transferField.getText()))	{
  	 		displayLine("enter a number in transfer field");
  	 		transferField.setText("Enter a number");
  	 		return false;
  		} else if (!isNumber(accomField.getText()))	{
  	 		displayLine("enter a number in accommodation field");
  	 		accomField.setText("Enter a number");
  	 		return false;
  		} else if (!isNumber(skipassField.getText()))	{
  	 		displayLine("enter a number in Ski Pass field");
  	 		skipassField.setText("Enter a number");
  	 		return false;
  		} else if (!isNumber(skihireField.getText()))	{
  	 		displayLine("enter a number in Ski Hire field");
  	 		skihireField.setText("Enter a number");
  	 		return false;
  		}
  	 		else
  	 			return true;

   } /* end of validInput */
      
   /* validate if the number is a float */   
   private static boolean isNumber(String n) {
  		try {
  			//Integer.parseInt(n);
  			Float.parseFloat(n);
  			return true;
  		} catch (NumberFormatException nfe) {
  			return false;
  		}
  	} /* end of isNumber */


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
   private JMenuItem  generateReportItem;
   private JMenuItem  generatePDFReportItem;
   private JMenuItem  resetItem;   
   private JMenuItem  barChartItem;
   private JMenuItem  lineChartItem;
   private JMenu      helpMenu;
   private JMenu 	  tripMenu;
   
   /* display area */
   private JTextArea  resultsTextArea;
   
   /* buttons */
   private JButton    Calculate;
   private JButton    Report;
   private JButton    Reset;
   private JButton    Save;   
   
   /* fonts  */
   Font boldFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
   
}



