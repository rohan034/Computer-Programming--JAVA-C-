package pa1;

import java.util.Scanner;
import javax.swing.JFrame;

/* This Class will create a frame to display graphical output.
 * It requires input for number of trials from the user.
 * It uses the object of class CoinSimComponent to create the graphical output and displays it in 500 tall by
 * 800 wide frame
 * 
 */
public class CoinSimViewer {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		Scanner in= new Scanner(System.in);
		System.out.println("Enter number of trials:");
		int numTrials=in.nextInt();
		if (numTrials<=0) {//Check ensuring number of trials are always positive
			do {
				System.out.println("ERROR- Number of trials must be greater than 0.");
				System.out.println("Enter number of trials : ");
				numTrials=in.nextInt();			
			} while(numTrials<=0); 			
		}
		frame.setSize(800, 500);
		frame.setTitle("Draw BAR graph");
	 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	CoinSimComponent toss= new CoinSimComponent(numTrials);
	 	frame.add(toss);
	 	frame.setVisible(true);		
	}
}
