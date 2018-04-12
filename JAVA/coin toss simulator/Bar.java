package pa1;

//Name:ROHAN DESAI
//USC NetID:2363232418
//CS 455 PA1
//Spring 2017

//we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
* Bar class
* A labeled bar that can serve as a single bar in a bar graph.
* The text for the label is centered under the bar.
* 
* NOTE: we have provided the public interface for this class. Do not change
* the public interface. You can add private instance variables, constants,
* and private methods to the class. You will also be completing the
* implementation of the methods given.
* 
*/
public class Bar {


private int labelBottom;
private int barLeftEdge;
private int barWidth;
private int barHeight;
private int heightOfBar;
private double pixelPerUnit;
private Color colorOfBar;
private String labelName;
private int widthOfLabel;
private int heightOfLabel;

		/**
		   Creates a labeled bar.  You give the height of the bar in application
		   units (e.g., population of a particular state), and then a scale for how
		   tall to display it on the screen (parameter scale). 
		
		   @param bottom  location of the bottom of the label
		   @param left  location of the left side of the bar
		   @param width  width of the bar (in pixels)
		   @param barHeight  height of the bar in application units
		   @param scale  how many pixels per application unit
		   @param color  the color of the bar
		   @param label  the label at the bottom of the bar
		*/
		public Bar(int left, int bottom, int width, int barHeight,
		           double scale, Color color, String label) {
			labelBottom=bottom;
			barLeftEdge=left;
			barWidth=width;
			heightOfBar=barHeight;
			pixelPerUnit=scale;
			colorOfBar=color;
			labelName=label;
		}

		/**
		   Draw the labeled bar. 
		   
		    
		   
		   @param g2  the graphics context
		*/
		public void draw(Graphics2D g2) {
			g2.setColor(colorOfBar);
			Rectangle graph=new Rectangle(barLeftEdge,labelBottom,barWidth,heightOfBar);
			g2.fill(graph);
			Font font = g2.getFont();
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D labelBounds = font.getStringBounds(labelName, context);
			double labelWidth = labelBounds.getWidth();
			int labelHeight = (int)labelBounds.getHeight();
			widthOfLabel=(int) labelWidth;//casting width label as drawString requires INT parameters
			g2.drawString(labelName , barLeftEdge-widthOfLabel/2,  labelBottom+heightOfBar+labelHeight);//Here 'labelbottom' will actually contain value of y coordinate of the rectangular bar
		}
		
		
		/* These are Return Values for Variables in this class which arent used.
		 * 
		 * 
		 * 
		 * 
		 * 
		 *	public int getLabelWidth(){
		 * 		return widthOfLabel;
			}
			public int getLabelHeight(){
				return heightOfLabel;
			}
			public int getLabelBottom(){
				return labelBottom;
			}
			public int getBarLeftEdge(){
				return barLeftEdge;
			}
			public int getHeightOfBar(){
				return heightOfBar;
			}
			public Color getcolorOfBar(){
				return colorOfBar;
			}
			public String getlabelName(){
				return labelName;
			}
			public double getscale(){
				return pixelPerUnit;
			}

		 */
		
		
				
}