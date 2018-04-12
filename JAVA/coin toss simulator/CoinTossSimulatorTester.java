package pa1;

import java.util.Scanner;


/* This is a Tester which will be used to Test CoinTossSimulatorClass
 * 
 */
public class CoinTossSimulatorTester {
	
	public static void main(String[] args){
		
		Scanner in=new Scanner(System.in);
		CoinTossSimulator toss=new CoinTossSimulator();
		System.out.println("Enter number of trials:");
		int numTrials=in.nextInt();
		if (numTrials<=0) {//Check ensuring number of trials are always positive
			do {
				System.out.println("ERROR- Number of trials must be greater than 0. ");
				System.out.println("Enter number of trials : ");
				numTrials=in.nextInt();			
			} while(numTrials<=0); 			
		}			
		toss.run(numTrials);
		System.out.println("Number of Trials [Expected : " + numTrials + "]" + " : " + toss.getNumTrials() );
		System.out.println("Two-Head Tosses : " + toss.getTwoHeads());
		System.out.println("Two-Tail Tosses : " + toss.getTwoTails());
		System.out.println("One Head One Tail Tosses : " + toss.getHeadTails());
		int totalTrials = toss.getHeadTails()+toss.getTwoHeads()+toss.getTwoTails(); //Check to ensure if the total number of times coin is tossed is the sum of the results
		if (numTrials == totalTrials) {
			boolean x=true;
			System.out.println("Tosses Add Up Correctly ? : " + x);
		}	
		else
		{
			boolean x=false;
			System.out.println("Tosses Add Up Correctly ? : " + x);
		}
		System.out.println("Percentage Two Heads : " + toss.getPercentTwoHeads() + "%");
		System.out.println("Percentage Two Tails : " + toss.getPercentTwoTails() + "%");
		System.out.println("Percentage One Head One Tail : " + toss.getPercentOneHeadOneTail() + "%");
	}
}
