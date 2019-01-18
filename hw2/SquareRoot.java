package hw2;

/*
 *  first name: Mohao
 *  last name: Yi
 *  cs112 section (A or B): B
 *    
 *  Date: October 1, 2018
 *      
 *  Program Description: This class defines some methods 
 *  for calculating the square root (rounded down) of an integer
 *
 */

public class SquareRoot {
	private static int REMOVE_ME_FROM_METHOD=-1;
	
	// This method takes in a non-negative int value, uses binary search to 
	// compute and return its square root (rounded down to the nearest integer)
	public static int findSquareRoot(int number)
	{	
		// the lower bound and the upper bound of the range of binary search
	    // a square root must be within the range of [0, number]
		int lowerBound = 0;
	    int upperBound = number;
	    // the possible square root to be further examined
	    int squareRoot = (lowerBound + upperBound) / 2;
	    
	    // use binary search to find the square root; keep looping as 
	    // long as there exist other candidates for the square root
	    while (lowerBound < upperBound) {
	    	
	    	if (squareRoot * squareRoot < number) {
	    		
	    		lowerBound = squareRoot + 1;
	    		squareRoot = (lowerBound + upperBound) / 2;
	    		
	    	} else if (squareRoot * squareRoot > number) {
	    		
	    		upperBound = squareRoot - 1;
	    		squareRoot = (lowerBound + upperBound) / 2;
	    		
	    	} else {
	    		
	    		break; // we find the exact square root here
	    		
	    	}
	    	
	    } // After the loop, floor(the exact square root) 
	      // <= squareRoot <= ceiling(the exact square root).
	    
	    // So, we can round down the square root as shown in the following.
	    if (squareRoot * squareRoot > number) {
	    	squareRoot -= 1;
	    }
	    
	    return squareRoot;
	}

	public static void main(String[] args) {

	    System.out.println(SquareRoot.findSquareRoot(4)); //must print 2
	    System.out.println(SquareRoot.findSquareRoot(5)); //must print 2
	    System.out.println(SquareRoot.findSquareRoot(6)); //must print 2
	    System.out.println(SquareRoot.findSquareRoot(7)); //must print 2
	    System.out.println(SquareRoot.findSquareRoot(8)); //must print 2
	    System.out.println(SquareRoot.findSquareRoot(9)); //must print 3
	    System.out.println(SquareRoot.findSquareRoot(10)); //must print 3
	    System.out.println(SquareRoot.findSquareRoot(0)); //must print 0
	    System.out.println(SquareRoot.findSquareRoot(1)); //must print 1
	    System.out.println(SquareRoot.findSquareRoot(17)); //must print 4
	    System.out.println(SquareRoot.findSquareRoot(16)); //must print 4
	    
	    for (int i = 101; i <= 200 ; i++) {
	    	System.out.println(SquareRoot.findSquareRoot(i) == (int)Math.sqrt(i));
	    }
	}
	
}

