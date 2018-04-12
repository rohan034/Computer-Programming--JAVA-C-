package pa2;

//Name:ROHAN DESAI
//USC loginid: rohannde@usc.edu
//CS 455 PA2
//Spring 2017


import java.util.ArrayList;
import java.lang.String;
import java.lang.Math;


/**
A polynomial. Polynomials can be added together, evaluated, and
converted to a string form for printing.
*/
public class Polynomial {

private ArrayList<Term> termInPoly;	// Always in decreasing power of exponential values.
	 /**
	    Creates the 0 polynomial
	 */
	 public Polynomial() {
		 
		 termInPoly = new ArrayList<Term>();
		 assert isValidPolynomial();
	 }
	
	
	 /**
	    Creates polynomial with single term given
	  */
	 public Polynomial(Term term) {
		 termInPoly= new ArrayList<Term>();
		 termInPoly.add(term);		
		 assert isValidPolynomial();
	 }
	
	
	 /**
	    Returns the Polynomial that is the sum of this polynomial and b
	    (neither poly is modified)
	  */
	 public Polynomial add(Polynomial b) {
		 Polynomial sum= new Polynomial();
		 int iPoly1=0;	//Next Element to Consider in the First Array
		 int iPoly2=0;	//Next Element to Consider in the Second Array
		 while (iPoly1<termInPoly.size() && iPoly2<b.termInPoly.size()) {
			 Term termPoly1=termInPoly.get(iPoly1);
			 Term termPoly2=b.termInPoly.get(iPoly2);
			 if(termPoly1.getExpon()==termPoly2.getExpon()){
				 double addedCoeff=termPoly1.getCoeff()+termPoly2.getCoeff();
				 Term termAddition=new Term(addedCoeff,termPoly1.getExpon());
				 sum.termInPoly.add(termAddition);
				 iPoly1++;
				 iPoly2++;
			 }
			 else if (termPoly1.getExpon()>termPoly2.getExpon()) {
				sum.termInPoly.add(termPoly1);
				iPoly1++;
			 }
			 else if (termPoly1.getExpon()<termPoly2.getExpon()) {
				sum.termInPoly.add(termPoly2);
				iPoly2++;
			 }
		 }
		 while (iPoly1<termInPoly.size()) {
			Term remainingTerms=termInPoly.get(iPoly1);
			sum.termInPoly.add(remainingTerms);
			iPoly1++;
				 
		 }
		 while (iPoly2<b.termInPoly.size()) {
			 Term remainingTerms=b.termInPoly.get(iPoly2);
			 sum.termInPoly.add(remainingTerms);
			 iPoly2++;
		 }
		 assert sum.isValidPolynomial();
		 return sum;
	}
	
	 /**
	    Returns the value of the poly at a given value of x.
	  */
	 public double eval(double x) {
		double valueOfPoly=0.0;         // dummy code.  just to get stub to compile
		for(int i=0; i<termInPoly.size(); i++){
			valueOfPoly+=(termInPoly.get(i).getCoeff()) * (Math.pow(x,termInPoly.get(i).getExpon()));
		}
		return valueOfPoly;
	 }
	
	
	 /**
	    Return a String version of the polynomial with the 
	    following format, shown by example:
	    zero poly:   "0.0"
	    1-term poly: "3.2x^2"
	    4-term poly: "3.0x^5 + -x^2 + x + -7.9"
	
	    Polynomial is in a simplified form (only one term for any exponent),
	    with no zero-coefficient terms, and terms are shown in
	    decreasing order by exponent.
	 */
	 public String toFormattedString() {
		 String s="";
		 for(int i=0; i<termInPoly.size(); i++)
		 {
			Term t1= termInPoly.get(i);
			double coeff= t1.getCoeff();
			int expon=t1.getExpon();
			if (i==0) 
			{
				if (coeff==0){
					s+="";
				}
				else if(expon==0){
					s+=coeff;
				}
				else
				{
					s+= coeff + "x^" + expon;
				}
				
			}
			else {
				if (coeff==0){
					s+="";
				}
				else if(expon==0){
					s+= "+" + coeff;
				}
				else
				s+= " + " + coeff + "x^" + expon;
			}
			
		}
		 if (s.equals("")){
			 s="0";
		 }
		 return s;
	 }
	
	
	 // **************************************************************
	 //  PRIVATE METHOD(S)
	
	 /**
	    Returns true iff the poly data is in a valid state.
	 */
	 private boolean isValidPolynomial() {
		boolean polySequence=true; 
		for(int i=0; i<termInPoly.size()-1; i++){
			if( (termInPoly.get(i).getExpon())  <  (termInPoly.get(i+1).getExpon()) ){
				polySequence=false;
			}
		}
		return polySequence;     
	}

}

