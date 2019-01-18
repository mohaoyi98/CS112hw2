/* File: BigInt.java
 * Date: Fall 2018
 * Author:  CS112 Instructor
 *          Code adapted from Wayne Snyder
 *
 * Purpose: Skeleton Code for the BigInt class
 * 
 * Implemented by: Mohao Yi
 * 
 * This program defines a class of object called BigInt.
 */
package hw2;

// This is a class of BigInt objects that can represent and operate 
// on positive integers within a larger range than the range of int
public class BigInt  {

    private static final int SIZE = 20;      // length of arrays representing big ints
    private static final int[] NaBI = { -1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };  // error value
    private int[] A = new int[SIZE];  // The value unique to each BigInt

    // CONSTRUCTORS
    
    // Default Constructor which creates a BigInt of NaBI
    public BigInt() {
    	this.A = NaBI;
    }

    // Validates and Constructs a BigInt based on an integer
    public BigInt(int n){
    	
    	int digitAtIndexI;
    	
    	// n is invalid iff n is negative, since n 
    	// cannot exceed the upper bound of BigInt.
    	if (n < 0) {
    		
    		this.A = NaBI;
    		
    	} else {
    		
    		this.A = new int[SIZE];
    		
    		// iterates and stores each digit in n to this.A from back to front
    		// The loop should end when there is no more digit in n.
    		// Again, we don't need to worry about index being out of range, since
    		// n is of int type, which cannot be longer than a BigInt
    		for (int i = SIZE - 1; n > 0; i--) {
    			
        		// gets and updates the ith digit in this.A
        		digitAtIndexI = n % 10;
        		this.A[i] = digitAtIndexI;
        		
        		// updates n by dropping the last digit in n,
        		// to get the (i-1)th digit in the next loop
        		n = (n - digitAtIndexI) / 10;
        		
        	}
    		
    	}
    	
    }

    // Validates and Constructs a BigInt based on a string
    public BigInt(String s){
    	
    	String DIGITS_STRING = "0123456789";
    	boolean isValidString = true;
    	String currentCharString;
    	
    	// The following condition block tests if s is invalid.
    	// s is invalid if s is an empty String or 
    	// s is longer than the maximum size of a BigInt.
    	if (s.length() > SIZE || s.length() == 0) {
    		
    		isValidString = false;
    		
    	} else {
    		
    		// iterates and checks whether each character in s is valid
    		for (int i = 0; i < s.length() - 1; i++) {
        		
        		currentCharString = s.substring(i, i + 1);
        		
        		// s is invalid if any character in it does not represent a 
    			// single digit decimal nonnegative integer.
        		if (! DIGITS_STRING.contains(currentCharString)) {
        			isValidString = false;
        			break;
        		}
        		
        	}
    		
    		// check the last character in s separately to avoid index error
        	currentCharString = s.substring(s.length() - 1);
        	if (! DIGITS_STRING.contains(currentCharString)) {
        		isValidString = false;
        	}
        	
    	}
    	
    	if (! isValidString) {
    		
    		this.A = NaBI;
    		
    	} else {
    		
    		this.A = new int[SIZE];
    		
    		// iterates and updates each digit in the field A
    		for (int i = s.length() - 1; i >= 0; i--) {
    			
        		int indexInS = i;
        		int indexInA = i + SIZE - s.length();
        		
        		// the value which an integer character represents 
        		// can be computed as the following
        		int digitAtIndex = s.charAt(indexInS) - 48;
        		
        		this.A[indexInA] = digitAtIndex;

        	}
    		
    	}
    	
    }

    // Validates and Constructs a BigInt based on an Array
    public BigInt(int[] A){
    	boolean isValidArray = true;
    	
    	// The following condition block tests if A is invalid.
    	// A is invalid if A is an empty array or 
    	// A is longer than the maximum size of a BigInt.
    	if (A.length > SIZE || A.length == 0) {
    		
    		isValidArray = false;
    		
    	} else {
    		
    		for (int i = 0; i < A.length; i++) {
    			
        		// A is invalid if any element in it is not a 
    			// single digit decimal nonnegative integer. 
        		if (A[i] > 9 || A[i] < 0) {
        			isValidArray = false;
        			break;
        		}
        		
        	}
    		
    	}
    	
    	if (isValidArray) {
    		
    		// constructs and uses a for loop to populate the field A
    		this.A = new int[SIZE];
    		
    		// i is the index of an element in A
    		for (int i = (A.length - 1); i >= 0 ; i--) {
    			
    			// indexInFieldA is the index of the entry 
    			// in this.A where A[i] should belong to 
    			int indexInFieldA = i + SIZE - A.length;
    			this.A[indexInFieldA] = A[i];
    			
    		}
    		
    	} else {
    		
    		this.A = NaBI;
    		
    	}
    		
    }

    // OTHER METHODS
    
    // constructs and returns a string representation of an big integer object
    // (with leading zeros suppressed); returns "NaBI" if it is invalid
    public String toString() {
    	
      boolean isValidBigInt = true;
      String bigIntString = "";
      
      // checks whether the called BigInt object is invalid
      // it is invalid iff any member of field A is not a single 
      // decimal non-negative integer digit
      for (int i = 0; i < SIZE; i++) {
    	  
    	  if (this.A[i] < 0 || this.A[i] > 9) {
    		  isValidBigInt = false;
    		  break;
    	  }
    	  
      }
      
      if (isValidBigInt) {
    	  
    	  int firstNonzeroId;
    	  
    	  // Find the index of the highest non-zero digit in the BigInt
    	  for (firstNonzeroId = 0; firstNonzeroId < SIZE; firstNonzeroId++) {
    		  
    		  if (this.A[firstNonzeroId] != 0) {
    			  break;
    		  }
    		  
    	  }
    	  
    	  // constructs the string representation of the BigInt
    	  // avoiding the leading zeros
    	  for (int i = firstNonzeroId; i < SIZE; i++) {
    		  bigIntString += this.A[i];
    	  }
    	  
    	  // BigIntString == "" means there is no non-zero digit
    	  // in the BigInt, and thus it is zero.
    	  if (bigIntString == "") {
    		  bigIntString += "0";
    	  }
    	  
      } else {
    	  
    	  bigIntString = "NaBI";
    	  
      }
      
      return bigIntString;
    }

    // returns -1, 0, or 1, depending on whether this < other, 
    // this == other, or this > other, respectively.
    // Note that this comparison also works for NaBI's.
    public int compareTo(BigInt other) {
    	
    	// assume they are equal by default
    	int compareResult = 0;
    	
    	// iterates and finds the highest pair of corresponding digits
    	// in this and other that are different
    	// The comparison of this and other should depend on them.
    	for (int i = 0; i < SIZE; i++) {
    		if (this.A[i] < other.A[i]) {
    			compareResult = -1;
    			break;
    		}
    		if (this.A[i] > other.A[i]) {
    			compareResult = 1;
    			break;
    		}
    	}
    	
    	return compareResult;
    }

    // adds two big integers and returns a new array representing their sum; if
    // the result overflows, i.e., contains more than SIZE digits, returns NaBI
    public BigInt add(BigInt other) {
    	// first constructs a string representation of the sum; then uses the 
    	// corresponding constructor to construct the BigInt sum (if valid)
    	
    	BigInt sum;
    	String sumString = "";
    	// the carry bit when computing the sum
    	int carry = 0;
    	int digitSum;
    	
    	// Adding NaBI gives another NaBI.
    	if (this.A == NaBI || other.A == NaBI) {
    		
    		sum = new BigInt();
    		
    	} else {
    		
    		// simulates the elementary school addition method, 
    		// and represents the sum as a string
    		for (int i = SIZE - 1; i >= 0; i--) {
    		
    			digitSum = this.A[i] + other.A[i] + carry;
    			carry = digitSum / 10;
    			sumString = (digitSum % 10) + sumString;
    		
    		}
    		
    		// overflow happens when the sum of the left-most two digits 
    		// is larger than 10, in which case the carry remains non-zero
    		if (carry != 0) {
    			sum = new BigInt();
    		} else {
    			sum = new BigInt(sumString);
    		}
    	
    	}
    	
    	return sum;
    }
    
    // This is a helper method for .mul(BigInt other). 
    // returns the product of a BigInt and an int
    public BigInt mulInt(int n) {
    	// first constructs a string representation of the product; then uses
    	// the corresponding constructor to convert it into BigInt (if valid)
    	
    	BigInt product;
    	String productString = "";
    	int carry = 0;
    	// the result of digit product (i.e. digit * n) + carry
    	int digitResult;
    	
    	// multiplies a NaBI gives another NaBI
    	if (this.A == NaBI) {
    		
    		product = new BigInt();
    		
    	} else {
    		
    		// simulates the elementary school product method
    		for (int i = SIZE - 1; i >= 0; i--) {
    			digitResult = this.A[i] * n + carry;
    			carry = digitResult / 10;
    			productString = (digitResult % 10) + productString;
    		}
    		
    		// Overflow happens when carry remains non-zero after calculation.
    		if (carry != 0) {
    			product = new BigInt();
    		} else {
    			product = new BigInt(productString);
    		}
    		
    	}
    	
    	return product;
    }

    // Takes advantage of .mulInt(n) method to compute and return the product
    // of two BigInts; if it overflows, returns NaBI
    public BigInt mul(BigInt other) {
    	
    	BigInt product = new BigInt(0);
    	// the product of this and each significant digit of other
    	// The weight of that significant digit is taken into consideration.
    	BigInt lineResult;
    	
    	// Multiplying an NaBI gives another NaBI.
    	if (this.A == NaBI || other.A == NaBI) {
    		product = new BigInt();
    	} else {
    		
    		// simulates the elementary product method
    		// i.e. computes the product of this with each significant digit of
    		// other (with the weight of that significant digit taken into 
    		// consideration); then adds them up to get the final product
    		for (int i = SIZE - 1; i >= 0; i--) {
    			// Note that we do not need to check overflow here, since
    			// that work can be done by both .mulInt() and .add().
    			lineResult = this.mulInt(other.A[i] * 
    						(int) Math.pow(10, (SIZE - i - 1)));
    			product = product.add(lineResult);
    		}
    			
    	}
    		
    	return product;
    }


    // Unit Test: Here is where we put tests to verify that this class works properly; it is
    // not used except for debugging and testing purposes, and will be ignored when you use
    // this class as as a static library.
    public static void main(String [] args) {

        System.out.println("\nUnit Test for BigInt Library.\n");

        // Note that we may call the methods without the prefix BigInt.
        // because we are testing from inside the class; if you call these
        // methods from outside the class you must use the prefix, e.g., BigInt.bigIntToString(A).

        System.out.println("Test 1: Should be\n1234567");
        int[] A = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt B1 = new BigInt(A);
        System.out.println(B1.toString());
        System.out.println();

        System.out.println("Test 2: Should be\n0");
        int[] B = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt B2 = new BigInt(B);
        System.out.println(B2.toString());
        System.out.println();

        System.out.println("Test 3: Should be\nNaBI");
        int[] C = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt B3 = new BigInt(C);
        System.out.println(B3.toString());
        System.out.println();

        System.out.println("Test 4: Should be\n1234567");
        B1 = new BigInt( 1234567 );
        System.out.println(B1.toString());
        System.out.println();

        System.out.println("Test 5: Should be \n0");
        B2 = new BigInt(0);
        System.out.println(B2.toString());
        System.out.println();

        System.out.println("Test 6: Should be\nNaBI");
        B3 = new BigInt( -4 );
        System.out.println(B3.toString());
        System.out.println();

        System.out.println("Test 7: Should be\n3141592");
        B1 = new BigInt("3141592");
        System.out.println(B1.toString());
        System.out.println();

        System.out.println("Test 8: Should be\n0");
        B2 = new BigInt("0");
        System.out.println(B2.toString());
        System.out.println();

        System.out.println("Test 9: Should be \nNaBI");
        B3 = new BigInt("234h56");
        System.out.println(B3.toString());
        System.out.println();

        System.out.println("Test 10: Should be\nNaBI");
        B3 = new BigInt("23456379238472937829384272939472937429374");
        System.out.println(B3.toString());
        System.out.println();

        System.out.println("Test 11: Should be\n0");
        B1 = new BigInt("12375");
        B2 = new BigInt("12375");
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 12: Should be\n-1");
        B2 = new BigInt("12378");
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 13: Should be\n1");
        System.out.println(B2.compareTo(B1));
        System.out.println();

        System.out.println("Test 14: Should be\n0");
        B1 = new BigInt(0);
        B2 = new BigInt(0);
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 15: Should be\n-1");
        B2 = new BigInt("12375");
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 16: Should be\n1");
        System.out.println(B2.compareTo(B1));
        System.out.println();

        System.out.println("Test 17: Should be\n123456789123456789");
        B1 = new BigInt("36182736036182736");
        B2 = new BigInt("87274053087274053");
        System.out.println(B1.add(B2));
        System.out.println();

        System.out.println("Test 18: Should be\n123456789123456789");
        System.out.println(B2.add(B1));
        System.out.println();


        System.out.println("Test 19: Should be\n3141592653598");
        B1 = new BigInt(0);
        B2 = new BigInt("3141592653598");
        System.out.println(B1.add(B2));
        System.out.println();

        System.out.println("Test 20: Should be\n10000000000000000000");
        B1 = new BigInt("9999999999999999999");
        B2 = new BigInt(1);
        System.out.println(B1.add(B2));
        System.out.println();

        System.out.println("Test 21: Should be\nNaBI");
        B1 = new BigInt("99999999999999999999");
        System.out.println(B1.add(B2));
        System.out.println();

        System.out.println("Test 22: Should be\n5670");
        B1 = new BigInt("135");
        B2 = new BigInt("42");
        System.out.println(B1.mul(B2));
        System.out.println();

        System.out.println("Test 23: Should be \n99999999999999999999");
        B1 = new BigInt("99999999999999999999");
        B2 = new BigInt(1);
        System.out.println(B1.mul(B2));
        System.out.println();

        System.out.println("Test 24: Should be \nNaBI");
        B2 = B2.add(B2);
        System.out.println(B1.mul(B2));
        System.out.println();
        
        
        System.out.println("Test 25: Should be \n-1");
        int[] test25 = {1, 2, 3, 4, 5};
        B1 = new BigInt();
        B2 = new BigInt(test25);
        System.out.println(B1.compareTo(B2));
        System.out.println();
        
        System.out.println("Test 26: Should be \n0");
        int[] test26 = {1, -2, 3, 4, 5};
        B1 = new BigInt();
        B2 = new BigInt(test26);
        System.out.println(B1.compareTo(B2));
        System.out.println();
        
        System.out.println("Test 27: Should be \n1");
        int[] test27 = {1, -2, 3, 4, 5};
        B1 = new BigInt(0);
        B2 = new BigInt(test27);
        System.out.println(B1.compareTo(B2));
        System.out.println();
        
        System.out.println("Test 28: Should be \n123450");
        int[] test28 = {1, 2, 3, 4, 5};
        B2 = new BigInt(test28);
        System.out.println(B2.mulInt(10));
        System.out.println();
    }
}
