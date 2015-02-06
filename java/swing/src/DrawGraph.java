import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;




@SuppressWarnings("serial")
public class DrawGraph extends JPanel {

   public static ResourceBundle res = ResourceBundle.getBundle("Graph");
   private static final int MAX_SCORE = Integer.valueOf(res.getString("max"));
   private static final int PREF_W = 800;
   private static final int PREF_H = 650;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.green;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int Y_HATCH_CNT = 10;
   private List<Integer> scores;

   public DrawGraph(List<Integer> scores) {
      this.scores = scores;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

      // create hatch marks for y axis. 
      for (int i = 0; i < Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = 0; i < scores.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }

      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   private static void createAndShowGui() {
    //  List<Integer> scores = new ArrayList<Integer>();
      List<Integer> scoresret = readDataFile("skidata.csv");
      //int maxDataPoints = 11;
   //   int maxScore = 1800;
   //   for (int i = 0; i < maxDataPoints ; i++) {
     //    scores.add(scoresret);
     // }
      DrawGraph mainPanel = new DrawGraph(scoresret);

      JFrame frame = new JFrame("Ski Trip Graph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

 /* read from a file and print on screen */
      private static List<Integer> readDataFile(String filename) {
              try {
            	  	/*int i [] = new int[11]  ;
            	  	int p =0;
                      File dataFile         = new File(filename);
                      FileReader fileReader = new FileReader(dataFile);
                      BufferedReader reader = new BufferedReader(fileReader);
                      String line = null;
                      System.out.print("Contents of \"" + filename + "\""+ res.getString("title") +"\n");
                      while ((line = reader.readLine()) != null) {
                    	     String []  s = line.split(",");
                    	     System.out.print("Amt "+p+" = " + s[11].toString() +"\n");
                    	     i[p] = Integer.valueOf(s[11].toString());
                    	     p++;
                      }
                      System.out.print("\nEnd of \"" + filename + "\"\n");
                      reader.close();
                      */
            	  ArrayList<Integer> yList = new ArrayList<Integer>();
          	    ArrayList<String> xList = new ArrayList<String>();
          		//int row =0;
                  File dataFile         = new File(filename);
                  FileReader fileReader = new FileReader(dataFile);
                  BufferedReader reader = new BufferedReader(fileReader);
                  //BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
                  String line = null;
                  float fval = 0;
            //      int ival = (int)fyou;
                  while ((line = reader.readLine()) != null) {
                         //displayLine(line);
                	     String []  s = line.split(",");
                	     xList.add(s[0]);
                	     fval =Float.valueOf(s[11]);
                	     yList.add((int)fval); 
                	     //row++;
                  }
              	double [] yNumbers = new double[yList.size()];
          		String [] xItems = new String[xList.size()];
          	    
          	    for(int j =0;j<yList.size();j++){
          			  yNumbers[j] = yList.get(j);
          			  xItems[j] =xList.get(j);
          			}
                      
                   reader.close();   
                      
			return yList;
              } catch (IOException x) {
                      System.err.format("IOException: %s%n", x);
			return null;
              }
      } /* end of readFile */



   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}
