package pa1;

//Name:
//USC NetID:
//CS 455 PA1
//Spring 2017

/**
* class CoinTossSimulator
* 
* Simulates trials of tossing two coins and allows the user to access the
* cumulative results.
* 
* NOTE: we have provided the public interface for this class.  Do not change
* the public interface.  You can add private instance variables, constants, 
* and private methods to the class.  You will also be completing the 
* implementation of the methods given. 
* 
* Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
* 
*/
import java.util.Random;
public class CoinTossSimulator {


	private int twoHeads;
	private int twoTails;
	private int oneHead_oneTail;
	private int noOfTrials;
	private Random generator;
		/**
		   Creates a coin toss simulator with no trials done yet.
		*/
		public CoinTossSimulator() {	
			twoHeads=0;
			twoTails=0;
			oneHead_oneTail=0;
			noOfTrials=0;
			generator=new Random();
		}


		/**
		   Runs the simulation for numTrials more trials. Multiple calls to this
		   without a reset() between them add these trials to the simulation
		   already completed.
		   
		   We give 1 as Heads and 0 as tails. so depending on combination of the two coins tossed we will count whether the 
		   coin toss resulted in two heads, two tails or onehead and one tail. For eg. if both coins have 1,1 the it is two heads
		   0,0 then two tails and 0,1 or 1,0 is one head one tail   
		   
		   @param numTrials  number of trials to for simulation; must be >= 1
		 */
		public void run(int numTrials) {
			for (int i=1; i<= numTrials; i++)
			{
				noOfTrials++;
				int output1= generator.nextInt(2);
				int output2=generator.nextInt(2);
				if (output1==1 && output2==1) {
						twoHeads++;
				}
				else if (output1==0 && output2==0) {
						twoTails++;
				}
				else {
						oneHead_oneTail++;
				}
			}					
		}
		/**
		   Get number of trials performed since last reset.
		*/
		public int getNumTrials() {
		    return noOfTrials;
		}


		/**
   		Get number of trials that came up two heads since last reset.
		 */
		public int getTwoHeads() {
		    return twoHeads; 
		}


		/**
		  Get number of trials that came up two tails since last reset.
		*/  
		public int getTwoTails() {
		    return twoTails; 
		}


		/**
		  Get number of trials that came up one head and one tail since last reset.
		*/
		public int getHeadTails() {
		    return oneHead_oneTail; 
		}
		
		/**Returns the percentage of two heads in coin toss
		 */
		public double getPercentTwoHeads(){
			return (double)twoHeads/noOfTrials*100.0;
		}
		
		/**Returns the percentage of two tails in coin toss
		 */
		public double getPercentTwoTails(){
			return (double)twoTails/noOfTrials*100.0;
		}
		
		/**Returns the percentage of One head and one tail in coin toss
		 */
		public double getPercentOneHeadOneTail(){
			return (double)oneHead_oneTail/noOfTrials*100.0;
		}
		/**
		   Resets the simulation, so that subsequent runs start from 0 trials done.
		 */
		public void reset() {
			twoHeads=0;
			twoTails=0;
			oneHead_oneTail=0;
		
		}

}

