package pa3;

import java.util.LinkedList;
import java.util.ListIterator;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   private int numRows;
   private int numCols;
   private MazeCoord entryLoc;
   private MazeCoord exitLoc;
   private boolean[][] mazeLayout,mazePosVisited;// mazePositionVisited keeps a track of the Positions traversed during the search algorithm
   private LinkedList<MazeCoord> mazePath;// stores the final path out of the maze if found
   
   
  

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
	   this.entryLoc=startLoc;
	   this.exitLoc=exitLoc;
	   mazeLayout=mazeData;
	   this.numRows=mazeLayout.length;
	   this.numCols=mazeLayout[0].length;
	   mazePosVisited=new boolean[numRows][numCols];
	   for(int i=0;i<numRows;i++){
		   for(int j=0; j<numCols; j++){
			   mazePosVisited[i][j]=false;
		   }
	   }
	   mazePath=new LinkedList<MazeCoord>();
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
   public int numRows() {
      return numRows;  
   }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {
      return numCols;  
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
	   int locRow=loc.getRow(); 
	   int locCol=loc.getCol();
	   boolean wall=mazeLayout[locRow][locCol];
	   return wall; 
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return entryLoc;   
   }
   
   
   /**
     Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return exitLoc;   
   }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

      return mazePath;

   }


   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
   public boolean search()  {
	   MazeCoord currentLoc;
	   
	   int currentLocRow=entryLoc.getRow(); 
	   int currentLocCol=entryLoc.getCol();
	   mazePosVisited[currentLocRow][currentLocCol]=true;
	   currentLoc=new MazeCoord(currentLocRow, currentLocCol);
	   mazePath.add(currentLoc);
	   boolean pathFind=pathFinder(currentLoc);
	   return pathFind;
   }
   
   /*Helper method for search algorithm. Note search for exit path from the Maze follows the pattern right, left, down, up, which is explained below:
    * 	- Search algorithm takes place as follows :
    * 			> for each new location the check for the next move always begins by checking the right location.
    * 			> Iff movement towards right is not possible then movement towards left is checked for.
    * 			> Similarly, Iff movement towards left is not possible downward movement is checked for followed by upwards movement.
    * 			> If for a given location no movement is further possible, the Algorithm retracts to the last position which had more than one option for 
    * 				movement and checks other possibilities.
    */
   	private boolean pathFinder(MazeCoord currentLoc)
   	{
   		if (mazeLayout[currentLoc.getRow()][currentLoc.getCol()]==true)//To check if Entrry Location In the Maze is a Wall or not
   		{
   			return false;
   		}
   		boolean pathFound=false;
   		if(currentLoc.getRow()==exitLoc.getRow() && currentLoc.getCol()==exitLoc.getCol()){
		   return true;
   		}
   		if(!pathFound)
   		{	
   			pathFound=oneMoveRight(currentLoc);
   		}
   		if(!pathFound){
   			pathFound=oneMoveLeft(currentLoc);
   		}
		if(!pathFound){
			pathFound=oneMoveUp(currentLoc);
		}
		if(!pathFound){
			pathFound=oneMoveDown(currentLoc);
		}
   		
   		return pathFound;
   		
	

   }
   	// One move to the right of existing location
   	private boolean oneMoveRight(MazeCoord currentLoc){
   		MazeCoord newLoc;
   		boolean pathFound=false;
   		int newLocRow=currentLoc.getRow(); 
   		int newLocCol=currentLoc.getCol()+1;
   		newLoc=new MazeCoord(newLocRow, newLocCol);
   		if(newLocCol<numCols){
		   	if (mazeLayout[newLocRow][newLocCol]==FREE) {
		   		if(mazePosVisited[newLocRow][newLocCol]==false){
		   			
		   			mazePosVisited[newLocRow][newLocCol]=true;
		   			mazePath.add(newLoc);
		   			pathFound=pathFinder(newLoc);
		   			if (pathFound==false){
		   				mazePath.removeLast();
		   			}
		   		}	
		   	}
   		}
   		return pathFound;
   		
   	}

   	// One move to the Left of existing location
   	private boolean oneMoveLeft(MazeCoord currentLoc){
   		MazeCoord newLoc;
   		boolean pathFound=false;
   		int newLocRow=currentLoc.getRow(); 
   		int newLocCol=currentLoc.getCol()-1;
   		newLoc=new MazeCoord(newLocRow, newLocCol);
   		if(newLocCol>=0){
		   	if (mazeLayout[newLocRow][newLocCol]==FREE) {
		   		if(mazePosVisited[newLocRow][newLocCol]==false){
		   			
		   			mazePosVisited[newLocRow][newLocCol]=true;
		   			mazePath.add(newLoc);
		   			pathFound=pathFinder(newLoc);
		   			if (pathFound==false){
		   				mazePath.removeLast();
		   			}
		   		}	
		   	}
   		}
   		return pathFound;	
   	}

   	// One move to the Down of existing location
   	private boolean oneMoveDown(MazeCoord currentLoc){
   		MazeCoord newLoc;
   		boolean pathFound=false;
   		int newLocRow=currentLoc.getRow()+1; 
   		int newLocCol=currentLoc.getCol();
   		newLoc=new MazeCoord(newLocRow, newLocCol);
   		if(newLocRow<numRows){
		   	if (mazeLayout[newLocRow][newLocCol]==FREE) {
		   		if(mazePosVisited[newLocRow][newLocCol]==false){
		   			
		   			mazePosVisited[newLocRow][newLocCol]=true;
		   			mazePath.add(newLoc);
		   			pathFound=pathFinder(newLoc);
		   			if (pathFound==false){
		   				mazePath.removeLast();
		   			}
		   		}	
		   	}
   		}
   		return pathFound;
   	}
   	

   	// One move to the up of existing location
   	private boolean oneMoveUp(MazeCoord currentLoc){
   		MazeCoord newLoc;
   		boolean pathFound=false;
   		int newLocRow=currentLoc.getRow()-1; 
   		int newLocCol=currentLoc.getCol();
   		newLoc=new MazeCoord(newLocRow, newLocCol);
   		if(newLocRow>=0){
		   	if (mazeLayout[newLocRow][newLocCol]==FREE) {
		   		if(mazePosVisited[newLocRow][newLocCol]==false){
		   			
		   			mazePosVisited[newLocRow][newLocCol]=true;
		   			mazePath.add(newLoc);
		   			pathFound=pathFinder(newLoc);
		   			if (pathFound==false){
		   				mazePath.removeLast();
		   			}
		   			
		   		}	
		   	}
   		}
   		return pathFound;
   	}
 
}