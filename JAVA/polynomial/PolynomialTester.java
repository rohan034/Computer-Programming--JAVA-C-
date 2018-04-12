package pa2;

import java.util.ArrayList;

public class PolynomialTester {
	public static void main(String[] args){
		
		Polynomial poly1,poly2,poly3,poly4,poly5,poly6,poly7;
		System.out.println("Testing Constructors");
		System.out.println("Constructor to create empty object for class polynomial: ");
		poly3= new Polynomial();
		System.out.print("Expecting empty array  :  " + poly3.toFormattedString());
		System.out.println();
		System.out.println();
		System.out.println("Constructor to create initialsed object for class polynomial: ");
		poly1= new Polynomial(new Term(3,-5));
		System.out.print("Expecting [3, -5] array  :  " + poly1.toFormattedString());
		System.out.println();
		System.out.println();
		/*poly2=new Polynomial(new Term(-3,2));
		System.out.print("Expecting [-3, 2] array  :  " + poly2.toFormattedString());
		System.out.println();
		System.out.println();
		poly4=new Polynomial(new Term(6,5));
		System.out.print("Expecting [6,5] array  :  " + poly4.toFormattedString());
		System.out.println();
		System.out.println();
		poly5=new Polynomial(new Term(2,2));
		System.out.print("Expecting [2, 2] array  :  " + poly5.toFormattedString());
		System.out.println();
		System.out.println();
		poly6=new Polynomial(new Term(2,0));
		System.out.print("Expecting [2, 10] array  :  " + poly6.toFormattedString());
		System.out.println();
		System.out.println();
		Polynomial summation= new Polynomial();
		summation=poly6.add(poly5);
		poly7= summation.add(poly1);
		System.out.println(" Summation of polynomial(Expected:[2,10,3,2] " + summation.toFormattedString());
		System.out.println(" Summation of polynomial(Expected:[2,10,3,5,3,2] " + poly7.toFormattedString());
		poly3=poly7.add(poly2);
		System.out.println(" Summation of polynomial(Expected:[2,10,3,2] " + poly3.toFormattedString());
		System.out.println(" Evaluation for x=4.5 : " + poly1.eval(-10.0));
	*/
	}

}
