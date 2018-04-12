package pa2;

import java.util.Scanner;

public class PolynomialCalculator1 
{
	public static void main(String args[])
	{
		Polynomial[] p = new Polynomial[10];
		Scanner in = new Scanner(System.in);
		while(true)
		{
			System.out.print("cmd>");
			String commandline = in.nextLine();
			Scanner scan= new Scanner(commandline);
			while (scan.hasNext()){
				String command1=scan.next();
				
			}
			String[] commandSplit = commandline.split(" ");
			String command1 = commandSplit[0];
			if(command1.equals("quit")){
				break;
			}
			else if(command1.equalsIgnoreCase("create")){ // "    create 4" - doesnt work; "create    	4"-worrks ; out of bounds error check
				Polynomial orig = new Polynomial();
				System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by");
				String coeffsAndExpo = in.nextLine();
				Scanner stringSc = new Scanner(coeffsAndExpo);
				while(stringSc.hasNextDouble()){
					double coeff = stringSc.nextDouble();
					int expo= stringSc.nextInt();
					Term term= new Term(coeff,expo);
					Polynomial poly= new Polynomial(term);
					orig = orig.add(poly);
				}
				p[Integer.parseInt(commandSplit[1])] = orig;
			}
			else if(command1.equalsIgnoreCase("eval")){
				System.out.println("Enter a floating point value for x : ");
				double valueOfx=in.nextDouble();
				double result= p[Integer.parseInt(commandSplit[1])].eval(valueOfx);
				System.out.println(result);

			}
			
			else if(command1.equalsIgnoreCase("print")){
			System.out.println(p[Integer.parseInt(commandSplit[1])].toFormattedString());
			
			}
			else if(command1.equalsIgnoreCase("add")){
				int resultIndex= Integer.parseInt(commandSplit[1]);
				int index_1stPoly= Integer.parseInt(commandSplit[2]);
				int index_2ndPoly= Integer.parseInt(commandSplit[3]);
				p[resultIndex]=p[index_1stPoly].add(p[index_2ndPoly]);
			}
		}
	}
}

