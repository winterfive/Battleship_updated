// A simple app that takes two 2D arrays
// and adds the corresponding elements
// and places them in a new array, C
// which is printed row by row

import java.lang.reflect.Array;

public class array {

	public static void main(String args[]) {	
		
		int[][] anArray = {
			{3, 5},
			{5, 7},
			{2, 4}
			};
									
		int[][] anotherArray = {
			{2, 3},
			{0, 2},
			{1, 8}
			};
		
		int[][] C = matrixAdd(anArray, anotherArray);
		
		for(int[] row : C) {
	        printRow(row);
	    };		
	};
	
	public static int[][] matrixAdd(int[][] A, int[][] B) {
		// 2D matrix, 2D matrix -> 2D matrix
		// adds the corresponding elements and stores
		// them in a third 2D matrix, C.
		// array C is returned
		    
	    int rowsA = A.length;
	    int colsA = A[0].length;
	    
	    int[][] C = new int[rowsA][colsA];
	    
	    for (int rows = 0; rows < A.length; rows++) {
	        for (int cols = 0; cols < A[rows].length; cols++) {
	            C[rows][cols] = A[rows][cols] + B [rows][cols];
	        };
	    };
	    
	    return C;
	};	
	
	public static void printRow(int[] row) {
		// int -> int
		// prints out each row of an array
    
        for (int i : row) {
            System.out.print(i + " ");
        };
        
        // move to new line
        System.out.println();
    };
	
}
