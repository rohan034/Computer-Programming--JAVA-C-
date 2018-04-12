package pa1;


//Name:ROHAN DESAI
//USC NetID:2363232418
//CS 455 PA1
//Spring 2017

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Scanner;
import javax.swing.JComponent;

/*
 * In drawing the rectangular bar using object of BAR class we need to provide the coordinates of top left corner of the rectancgle accurately.
 * Formula used are as below
x coordinate=left edge of the bar 
y coordinate= getWidth() - labelbottom - labelheight- heightofBar

These values will change for each object of class bar and parameters will be passed keeping in mind we want
three bars at 1/4th,1/2 and 3/4th width of the output screen. This will help to ensure that even if the frame
width changes the orientation of each object of bar class will be where it was earlier i.e at 1/4,1/2 and 3/4
of the width of the screen.
*/


public class CoinSimComponent extends JComponent
{
	private static final double BAR_WIDTH=20.0;
	private static final double LABEL_BOTTOM=30.0;
	private static final String LABEL_1="TWO HEADS";
	private static final String LABEL_2="ONE HEAD ONE TAIL";	
	private static final String LABEL_3="TWO TAILS";
	private static final Color COLOR_LABEL_1=Color.RED;
	private static final Color COLOR_LABEL_2=Color.BLUE;
	private static final Color COLOR_LABEL_3=Color.GREEN;
	private int numTrials;
	
	public CoinSimComponent(int trials){
		numTrials=trials;
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2=(Graphics2D) g;
		Bar graph1,graph2,graph3;
		CoinTossSimulator toss=new CoinTossSimulator();
		Scanner in=new Scanner(System.in);
		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		toss.run(numTrials);
		
		//finding label heights and setting the label with max height as a general height for all labels incase the labels have different heights 
		Rectangle2D labelBounds_label1 = font.getStringBounds(LABEL_1, context);
		double labelHeight1 = labelBounds_label1.getHeight();
		Rectangle2D labelBounds_label2 = font.getStringBounds(LABEL_2, context);
		double labelHeight2 = labelBounds_label2.getHeight();
		Rectangle2D labelBounds_label3 = font.getStringBounds(LABEL_3, context);
		double labelHeight3 = labelBounds_label2.getHeight();
		double labelHeight=Math.max(labelHeight1,Math.max(labelHeight2,labelHeight3));
		double maxPossibleBarHeight = getHeight() - (2 * LABEL_BOTTOM) - labelHeight;// defines the maximum display area for the rectangular bars
		
		
		double percentTwoHeads = toss.getPercentTwoHeads();
		double heightOfBar1 = percentTwoHeads*maxPossibleBarHeight/100;//scaling the height of the bar depending on max height of rectangular bar possible
		double x1=(getWidth()/4)-(BAR_WIDTH/2); //Set X and Y coordinates of top left corner of the first rectangular bar and passing them as parameters for bar object
		double y1=getHeight()-LABEL_BOTTOM-labelHeight-heightOfBar1;
		graph1=new Bar((int)x1, (int)y1, (int)BAR_WIDTH, (int)heightOfBar1, percentTwoHeads/100, COLOR_LABEL_1, LABEL_1 + " : " + toss.getTwoHeads() + "(" + toss.getPercentTwoHeads() + ")") ;//casting because parameters are integer value	
		graph1.draw(g2);

		double percentOneHeadOneTail = toss.getPercentOneHeadOneTail();
		double heightOfBar2 = percentOneHeadOneTail*maxPossibleBarHeight/100;
		double x2=getWidth()-(getWidth()/4)-BAR_WIDTH;
		double y2=getHeight()-LABEL_BOTTOM-labelHeight-heightOfBar2;
		graph2=new Bar((int)x2, (int)y2, (int)BAR_WIDTH, (int)heightOfBar2, percentOneHeadOneTail/100, COLOR_LABEL_2, LABEL_2 + " : " + toss.getHeadTails() + "(" + toss.getPercentOneHeadOneTail() + ")" );	
		graph2.draw(g2);
		
		double percentTwoTails = toss.getPercentTwoTails();
		double heightOfBar3 = percentTwoTails*maxPossibleBarHeight/100;
		double x3=(getWidth()/2)-BAR_WIDTH/2;
		double y3=getHeight()-LABEL_BOTTOM-labelHeight-heightOfBar3;
		graph3=new Bar((int)x3, (int)y3, (int)BAR_WIDTH, (int)heightOfBar3, percentTwoTails/100, COLOR_LABEL_3, LABEL_3 + " : " + toss.getTwoTails() + "(" + toss.getPercentTwoTails()+")");	
		graph3.draw(g2);
		
	}
}
