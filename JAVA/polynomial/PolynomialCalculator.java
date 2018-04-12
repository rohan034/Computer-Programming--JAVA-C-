package pa2;


//Name:ROHAN DESAI
//USC loginid: rohannde@usc.edu
//CS 455 PA2
//Spring 2017

import java.util.Scanner;

public class PolynomialCalculator
{
	
	public static void main(String args[])
	{
		System.out.println("POLYNOMIAL CALCULATOR PROGRAM ( type 'help' for more information )");
		Polynomial[] p = new Polynomial[10];
		for (int i=0; i<p.length; i++){
			p[i]=new Polynomial();
		}
		Scanner in = new Scanner(System.in);
		boolean loopCondition= true;
		while(loopCondition)
		{
			System.out.print("cmd>");
			String commandline = in.nextLine();
			Scanner scan= new Scanner(commandline);
			while (scan.hasNext()){
				String command1=scan.next();
				if(command1.equalsIgnoreCase("quit")){
					loopCondition=exit();
				}
				else if(command1.equalsIgnoreCase("create")){ // " out of bounds error check
					createPolynomial(p,scan);
				}
				else if(command1.equalsIgnoreCase("eval")){
					evaluatePolynomial(p, scan);
				}
				else if(command1.equalsIgnoreCase("print")){
					printPolynomial(p, scan);
				}	
				else if(command1.equalsIgnoreCase("add")){
					addPolynomial(p, scan);
				}
				else if(command1.equalsIgnoreCase("help")){
					help();
				}
				else{
					System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
				}
			}
		}
	}
	
	
	
	/* This is the help option for the user which explains the commands which can be used for Polynomial Calculator
	 * 
	 * 
	 */
	public static void help(){
		System.out.println("Various Common options which can be used are listed below :");
		System.out.println("1. create - to create a new polynomial using the values of coefficients and exponents given by the user. Eg Typing 'create 0' creates a polynomial at 0th index location, create 1 at 1st index location, create 7 at 7th index location and so on");
		System.out.println("2. print- This command prints polynomial at the specified index location. For eg print 2 will print the polynomial store at 2nd index location");
		System.out.println("3. eval- This command is used to evaluate polynomial for given decimal value of x. For eg eval 0 will evaluate polynomial stored at 0th index location for a given value of x to be specified by the user. ");
		System.out.println("4. add- adds to polynomials. for eg add 2 0 1 means poly2=poly1 + poly 0 where 0,1,2 are index locations");
		System.out.println("5. quit- to exit the program.");	
	}
	
	
	/*This private method exits the program.
	 * 
	 */
	public static boolean exit(){
		return false;
	}
	
	
	/* creates a Polynomial in descending powers of exponents.
	 * 
	 */
	public static void createPolynomial(Polynomial[] p, Scanner scan){
		
		String index=scan.next();
		if (Integer.parseInt(index)>=0  &&  Integer.parseInt(index)<=9){ // checking on the index notation of polynomial
			Polynomial orig = new Polynomial();
			Scanner in= new Scanner(System.in);
			int noOfValues; // variable used to keep check on missing the last exponent value
			String coeffsAndExpo;
			Scanner numbers, checkOnNumbers;
			do{
				System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by");
				coeffsAndExpo = in.nextLine();
				noOfValues=0;
				Scanner checkOnnumbers = new Scanner(coeffsAndExpo);
				numbers=new Scanner(coeffsAndExpo);
				while(checkOnnumbers.hasNextDouble()){
					checkOnnumbers.nextDouble();
					noOfValues++;
				}
				if (noOfValues%2!=0){
					System.out.println("WARNING : Coeffecient and Exponents not in pair. Enter even no of values");
				}
			}while(noOfValues%2!=0);// checking for even number of values to create the polynomial
				
			while(numbers.hasNextDouble()){
				double coeff = numbers.nextDouble();
				int expo= numbers.nextInt();
				Term term= new Term(coeff,expo);
				Polynomial poly= new Polynomial(term);
				orig = orig.add(poly);
			}
			
			p[Integer.parseInt(index)] = orig; // creates a polynomial at specified index location 
		}
		else
		{
		System.out.println("ERROR: incorrect value of polynomial index. Must be between 0 and 9 inclusive.");
		}
	}
	
	/*Auxiliary method to evaluate the polynomial for specified value of x. Displays 0.0 if no polynomial is at the specified index location.
	 * 
	 */
	public static void evaluatePolynomial(Polynomial[] p, Scanner scan){
		String index=scan.next();
		if (Integer.parseInt(index)>=0  &&  Integer.parseInt(index)<=9){
			Scanner in=new Scanner(System.in);
			System.out.println("Enter a floating point value for x : ");
			double valueOfx=in.nextDouble();
			double result= p[Integer.parseInt(index)].eval(valueOfx);
			System.out.println(result);

		}
		else
		{
			System.out.println("ERROR: incorrect value of polynomial index. Must be between 0 and 9 inclusive.");
		}
	}
	
		
	
	/*prints the polynomial at the specified index location. Displays 0 if no polynomial is stored at the specified index location 
	 * 
	 */
	public static void printPolynomial(Polynomial[] p, Scanner scan){
		String index=scan.next();
		if (Integer.parseInt(index)>=0  &&  Integer.parseInt(index)<=9){
		
			System.out.println(p[Integer.parseInt(index)].toFormattedString());
	
		}
		else{
			System.out.println("ERROR: incorrect value of polynomial index. Must be between 0 and 9 inclusive.");
		}
	}
	
	/*adds two polynomials and specified index location to create a new polynomial and storing it at the specified index location
	 * 
	 */
	public static void addPolynomial(Polynomial[] p, Scanner scan){
		String index1=scan.next();
		String index2=scan.next();
		String index3=scan.next();
		int resultIndex= Integer.parseInt(index1);
		int indexPolyA= Integer.parseInt(index2);
		int indexPolyB= Integer.parseInt(index3);
		if(resultIndex>=0 && resultIndex<=9 && indexPolyA>=0 && indexPolyA<=9 && indexPolyB>=0 && indexPolyB<=9)
		{	
			p[resultIndex]=p[indexPolyA].add(p[indexPolyB]);		
		}
		else{
			System.out.println("ERROR: incorrect value of polynomial index. Must be between 0 and 9 inclusive.");
		}
	}
}
