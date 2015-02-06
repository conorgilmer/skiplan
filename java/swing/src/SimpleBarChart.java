//Simple bar chart
//modified by Conor Gilmer
//include colours for bar items
//package webwayz.plot;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

  public class SimpleBarChart extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private double[] value;
  private String[] languages;
  private Color[] partycolours;
  private String title;

  public SimpleBarChart(double[] val, String[] lang, Color[] parties, String t) {
  languages = lang;
  partycolours = parties;
  value = val;
  title = t;
  }
  public void paintComponent(Graphics graphics) {
  super.paintComponent(graphics);
  if (value == null || value.length == 0)
  return;
  double minValue = 0;
  double maxValue = 0;
  for (int i = 0; i < value.length; i++) {
  if (minValue > value[i])
  minValue = value[i];
  if (maxValue < value[i])
  maxValue = value[i];
  }
  Dimension dim = getSize();
  int clientWidth = dim.width;
  int clientHeight = dim.height;
  int barWidth = clientWidth / value.length;
  Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
  FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
  Font labelFont = new Font("Book Antiqua", Font.PLAIN, 10);
  FontMetrics labelFontMetrics = graphics.getFontMetrics(labelFont);
  int titleWidth = titleFontMetrics.stringWidth(title);
  int q = titleFontMetrics.getAscent();
  int p = (clientWidth - titleWidth) / 2;
  graphics.setFont(titleFont);
  graphics.drawString(title, p, q);
  int top = titleFontMetrics.getHeight();
  int bottom = labelFontMetrics.getHeight();
  if (maxValue == minValue)
  return;
  double scale = (clientHeight - top - bottom) / (maxValue - minValue);
  q = clientHeight - labelFontMetrics.getDescent();
  graphics.setFont(labelFont);
  for (int j = 0; j < value.length; j++) {
  int valueP = j * barWidth + 1;
  int valueQ = top;
  int height = (int) (value[j] * scale);
  if (value[j] >= 0)
  valueQ += (int) ((maxValue - value[j]) * scale);
  else {
  valueQ += (int) (maxValue * scale);
  height = -height;
  }
  graphics.setColor(partycolours[j]);
  //graphics.setColor(Color.blue);
  graphics.fillRect(valueP, valueQ, barWidth - 2, height);
  graphics.setColor(Color.black);
  graphics.drawRect(valueP, valueQ, barWidth - 2, height);
  int labelWidth = labelFontMetrics.stringWidth(languages[j]);
  p = j * barWidth + (barWidth - labelWidth) / 2;
 graphics.drawString(languages[j], p, q);
 // graphics.drawString(Double.toString(value[j]), p, q);
  }
  }
 public static void main(double [] value, String [] languages, Color [] partycolours, String charttitle, int wwidth, int wheight) {
  JFrame frame = new JFrame();
  frame.setSize(wwidth, wheight);
//  double[] value= new double[7];
  //String[] languages = new String[7];
  //Color[] partycolours = new Color[7];
/*//  value[0] = 20;
  value[0] = -7.35;
  languages[0] = "Fianna Fail";
  partycolours[0] = Color.green;

//  value[1] = 76;
  value[1] = 9.68;
  languages[1] = "Fine Gael";
  partycolours[1] = Color.blue;

  //value[2] = 37;
  value[2] = 4.89;
  languages[2] = "Labour";
  partycolours[2] = Color.red;

  //value[3] = 14;
  value[3] = -1.47;
  languages[3] = "Sinn Fein";
  partycolours[3] = Color.yellow;

  //value[4] = 0;
  value[4] = -1.8;
  languages[4] = "Greens";
  partycolours[4] = Color.cyan;

//  value[5] = 5;
  value[5] = 0.51;
  languages[5] = "ULA";
  partycolours[5] = Color.magenta;
  
  //value[6] = 14;
  value[6] = -4.47;
  languages[6] = "Others";
  partycolours[6] = Color.gray;
*/
  frame.getContentPane().add(new SimpleBarChart(value, languages, partycolours,
    charttitle));

  WindowListener winListener = new WindowAdapter() {
  public void windowClosing(WindowEvent event) {
  System.exit(0);
  }
  };
  frame.addWindowListener(winListener);
  frame.setVisible(true);
  }
}
