package pa3;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

   private static final int START_X = 10; // top left of corner of maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   Maze maze;
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
      this.maze=maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   Graphics2D g2= (Graphics2D) g;
	   int mazeRows=maze.numRows();
	   int mazeCols=maze.numCols();
	   for(int i=0; i<mazeRows; i++){
		   for(int j=0; j<mazeCols; j++){
			   boolean loc=maze.hasWallAt(new MazeCoord(i, j));
			   if (loc) {
				   g2.setColor(Color.BLACK);
				   Rectangle2D rect=new Rectangle(START_X+ BOX_WIDTH*j, START_Y+BOX_HEIGHT*i, BOX_WIDTH, BOX_HEIGHT);
				   g2.fill(rect);
			   }
			   else{
				   g2.setColor(Color.WHITE);
				   Rectangle2D rect=new Rectangle(START_X+ BOX_WIDTH*j, START_Y+BOX_HEIGHT*i, BOX_WIDTH, BOX_HEIGHT);
				   g2.fill(rect);   
			  }
		   }
	   }
	   int entryLocX=maze.getEntryLoc().getRow();
	   int entryLocY=maze.getEntryLoc().getCol();
	   g2.setColor(Color.YELLOW);
	   Rectangle2D entry=new Rectangle(START_X + (BOX_WIDTH*entryLocY) + INSET, START_Y + (BOX_HEIGHT*entryLocX) + INSET, BOX_WIDTH-INSET*2, BOX_HEIGHT-INSET*2);
	   g2.fill(entry);
	   int exitLocX=maze.getExitLoc().getRow();
	   int exitLocY=maze.getExitLoc().getCol();
	   g2.setColor(Color.GREEN);
	   Rectangle2D exit=new Rectangle(START_X + (BOX_WIDTH*exitLocY) + INSET, START_Y + (BOX_HEIGHT*exitLocX) + INSET, BOX_WIDTH-INSET*2, BOX_HEIGHT-INSET*2);
	   g2.fill(exit);
	   
	   LinkedList<MazeCoord> path=new LinkedList<>();
	   path=maze.getPath();
	   if(path.size() != 0){
		   ListIterator<MazeCoord> iter=path.listIterator();
		   MazeCoord currentLoc=iter.next();
		   while(iter.hasNext()){
			   MazeCoord previousLoc=currentLoc;
			   currentLoc=iter.next();
			   int previousLocRow=previousLoc.getRow();
			   int previousLocCol=previousLoc.getCol();
			   int currentLocRow=currentLoc.getRow();
			   int currentLocCol=currentLoc.getCol();
			   g2.setColor(Color.BLUE);
			   g2.drawLine((previousLocCol*BOX_WIDTH) + START_X + (BOX_WIDTH/2), (previousLocRow*BOX_HEIGHT) + START_Y + (BOX_HEIGHT/2), (currentLocCol*BOX_WIDTH) + START_X + (BOX_WIDTH/2), (currentLocRow*20) + START_Y + (BOX_HEIGHT/2)); 
					  
			 }
	   }
   }
   
}


