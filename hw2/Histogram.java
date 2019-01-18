/*
 *
 * Name: Mohao Yi
 * Date: October 1st, 2018
 * Section: B
 *
 * Program Description
 * This class defines some methods for displaying the histogram of a dataset.
 */
package hw2;

import java.util.*;

public class Histogram { 
	
    public static final int MAX_NUMBERS = 20;  // the maximum amount of numbers whose frequency the histogram can display
    public static final int NUM_BINS = 10;     // the number of bins in the histogram
    public static final int UPPER_BOUND = 100; // the maximum number whose frequency the histogram can display
    public static final int LOWER_BOUND = 0;   // the minimum number whose frequency the histogram can display
    public static final int BIN_SIZE = (UPPER_BOUND - LOWER_BOUND) / NUM_BINS; // the size of the bins in the histogram
    public static final int SENTINAL_VALUE = LOWER_BOUND - 1; // the sentinal value to signal the end of an input process

    // Constructs and returns the header string 
    // to be displayed when running this program
    public static String getHeaderAsString() {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append("Welcome to the Histogram Program!");
        sb.append(System.getProperty("line.separator"));
        sb.append("This program will print out a histogram of the numbers");
        sb.append(System.getProperty("line.separator"));
        sb.append("input by the user; enter exactly " + MAX_NUMBERS + " doubles");
        sb.append(System.getProperty("line.separator"));

        return sb.toString();
    }
    
    // This method constructs and returns a String 
    // representation of the input histogram.
    public static String getHistogramAsString(int[] histogram) {
    	
    	// a String instance to represent the String version of the histogram
    	String histogramAsString;
    	
    	// a StringBuilder instance to help construct the histogram String
    	StringBuilder sb = new StringBuilder();
    	
    	// a String that represents the range info about a bin (e,g. [0..10]: )
    	String rangeInfo;
    	
        // the # of spaces used to line up the "*"s in the histogram String
    	int spaceNumber;
    	
    	// iterates among the bins, and construct the info about each bin
    	for (int i = 0; i < NUM_BINS; i++) {
    		
    		if (i == 0) {
    			// construct the range info about the 0th Bin separately, since
    			// it begins with "[", which is different from the other Bins
    			rangeInfo = "[" + LOWER_BOUND + ".." 
  			                + (LOWER_BOUND + BIN_SIZE) + "]: ";
    		} else if (i == NUM_BINS) {
    			// construct the range info about the last bin, since 
    			// its length depends on the upper bound
    	    	rangeInfo = "(" + (LOWER_BOUND + (NUM_BINS - 1) * BIN_SIZE) 
    	    			    + ".." + UPPER_BOUND + "]: ";
    		} else {
    			// construct the range info about the medium bins
    			rangeInfo = "(" + (LOWER_BOUND + BIN_SIZE * i) + 
       	    		        ".." + (LOWER_BOUND + BIN_SIZE * (i + 1)) + "]: ";
    		}
    		sb.append(rangeInfo);
    		
    	    // add correct amount of spaces so that the "*"s in 
    		// the histogram can line up with each other
    		spaceNumber = 16 - rangeInfo.length();
    		for (int spacei = 0; spacei < spaceNumber; spacei++) {
    			sb.append(" ");
    		}
    	    
    		// loop to add "*"s to the end of the string to 
        	// represent that fall in that Bin
    		// Note that each element in histogram is the # of numbers that
        	// fall in the corresponding bin
    	    for (int j = 1; j <= histogram[i]; j++) {
    	    	sb.append("*");
    	    }
    	    
    	    // construct the end letters of each bin
    	    // no end letters for the last bin
    	    if (i < NUM_BINS - 1) {
    	    	sb.append(" "); 
    	    	sb.append(System.getProperty("line.separator"));
    	    }
    	}
    	
    	histogramAsString = sb.toString();
    	return histogramAsString;
    }
    
    // This method checks whether a number is in the specified 
    // range of LOWER_BOUND and UPPER_BOUND, and returns 
    // true if the number is in the range, and false otherwise.
    public static boolean validInput(double n) {
        return (n >= LOWER_BOUND) && (n <= UPPER_BOUND); 
    }
    
    // This method accepts the array of numbers as an input argument, 
    // computes and returns the array that represents the histogram.
	public static int[] calculateHistogram(double[] numbers) {
		
		// an integer array that stores the # of numbers falls in each Bin
		int[] histogram = new int[NUM_BINS];
		
		// This for loop iterates over every number in the array "numbers", 
		// determines which bin that number falls in, and updates that bin. 
		
		// get wrong here originally: for (int i = 0; i < MAX_NUMBERS; i++)
		// need to check whether i is also a valid index for numbers in that 
		// the array numbers has less numbers than the theoretical maximum length
		for (int i = 0; i < MAX_NUMBERS && i < numbers.length; i++) {
			
			// the index of the bin in histogram which numbers[i] falls in
			int indexOfBin;
			
			// We only deal with the valid numbers in the array of numbers.
			if (Histogram.validInput(numbers[i])) {
				
				// We need to check whether the ith number is the LOWER_BOUND
				// due to the specialty of the 0th Bin. (It is left inclusive.)
				if (numbers[i] == LOWER_BOUND) {
				
					histogram[0]++;
				
				} else {
				
					// For a non-lower_bound number that is not a multiple of the 
					// size of bin, the index of the bin which it falls in can be 
					// computed from (int) ((numbers[i] - LOWER_BOUND) / BIN_SIZE).
					if (numbers[i] % BIN_SIZE != 0) {
					
						indexOfBin = (int) ((numbers[i] - LOWER_BOUND) / BIN_SIZE);
				
					// For a non-lower_bound number that is a multiple of the size 
					// of bin, the index of the bin which it falls in can be 
					// computed from (int) ((numbers[i] - LOWER_BOUND)/ BIN_SIZE)-1
					} else {
					
						indexOfBin = ((int) ((numbers[i] - LOWER_BOUND)/ BIN_SIZE)) - 1;
					}
			
					histogram[indexOfBin] ++;
				}
				
			}
			
		}	
		
	    return histogram; 
	}
    
	// implementation of the methods defined in this class
    public static void main(String[] args) {  
    	
    	double[] test = {1};
    	int[] out = Histogram.calculateHistogram(test);
    	System.out.println(Arrays.toString(out));
    	
        double[] numbers = new double[MAX_NUMBERS];
        int[] histogram = new int[NUM_BINS];
        
        for (int i = 0; i < MAX_NUMBERS; i++) {
        	numbers[i] = SENTINAL_VALUE;
        }
        
        // Begin with a welcome message
        System.out.println(getHeaderAsString()); 
        
        Scanner console = new Scanner(System.in);
        // populates the String array
        for (int i = 0; i < MAX_NUMBERS; i++) {
        	System.out.print("Please give the next number "
              + "(to stop, input" + SENTINAL_VALUE + "): ");
        	
        	double nextNumber = console.nextDouble();
        	
        	if (nextNumber == SENTINAL_VALUE) {
        		break;
        	}
        	
        	while (!Histogram.validInput(nextNumber)) {
        		System.out.print("Your number is invalid, "
        				          + "please give another one: "); 
        		nextNumber = console.nextDouble();
        	}
        	
        	numbers[i] = nextNumber;
        }
        
        System.out.println("You have finished inputting, and these "
        	+ "are your input numbers: \n" + Arrays.toString(numbers));

        histogram = Histogram.calculateHistogram(numbers);
        
        System.out.println("The following is the histogram of your inputs: \n");
        System.out.println(Histogram.getHistogramAsString(histogram));
        
        console.close();       
    }   
}
