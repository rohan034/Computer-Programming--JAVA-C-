package pa3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class MazeTester {
	public static void main(String[] args) throws FileNotFoundException{
		Maze maze;
		boolean[][] maze2D;
		Scanner inFileName= new Scanner(System.in);
		System.out.println("Enter the file name to be read : ");
		String fileName = inFileName.next();
		System.out.println(fileName);
		File file=new File(fileName);
		
		
		//reading data
		Scanner readFile=new Scanner(file);
		int numRows=readFile.nextInt();
		int numCols=readFile.nextInt();
		maze2D=new boolean[numRows][numCols];
		
		for(int i=0; i<numRows; i++)
		{
			String mazeLine=readFile.next();
			char[] mazeLayout=mazeLine.toCharArray();
			int length=0;
			while(length<mazeLayout.length){
				length++;
				for(int j=0; j<numCols; j++)
				{
					if(mazeLayout[j]=='0')
					{
						maze2D[i][j]=false;							
					}
					else if(mazeLayout[j]=='1')
					{
						maze2D[i][j]=true;
					}
				}
			}
		}
		int entryLocRow=readFile.nextInt();
		int entryLocCol=readFile.nextInt();
		int exitLocRow=readFile.nextInt();
		int exitLocCol=readFile.nextInt();
		MazeCoord entryLoc=new MazeCoord(entryLocRow, entryLocCol);
		MazeCoord exitLoc=new MazeCoord(exitLocRow, exitLocCol);
		maze=new Maze(maze2D, entryLoc, exitLoc);
		
		//maze search
		boolean pathFound=maze.search();
		System.out.println(pathFound);
		
		//creating a frame to add drawn object
		JFrame frame=new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MazeComponent mazeLayout=new MazeComponent(maze);
		frame.add(mazeLayout);
		frame.setVisible(true);
		
		
		for(MazeCoord pathCoord:maze.getPath()){
			System.out.println(pathCoord.toString());
		}
		

	}
}
