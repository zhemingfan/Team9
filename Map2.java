import java.util.Arrays;

public class Map2 {
	/*
	private int dim;
	
	public Map(int dim){
		int[][] arr = new int[dim][dim];
		for (int i = 0; i < dim; i++) { 
            for (int j = 0; j < dim; j++) { 
                System.out.print(arr[i][j] + " "); 
            } 
            
            System.out.println(); 
		}
		System.out.println();
	}
	*/
	

	
	public static void main(String[] args) {
		int dim = 5;
		int[][] arr = new int[dim][dim];
		for (int i = 0; i < dim; i++) { 
            for (int j = 0; j < dim; j++) { 
            	
                System.out.print(arr[i][j] + " "); 
            } 
            arr[2][0] = 5;
            System.out.println(); 
		}
		
		
		/*Map fivebyfive = new Map(5);
		System.out.println(fivebyfive);
		*/
		
	    for (int row = 0; row < arr.length; row++) {
	        for (int col = 0; col < arr[row].length; col++) {
	            if ((arr[row][col] == 5) && (col < arr[row].length -1)) {
	            		arr[row][col+1] = 5;
	            		arr[row][col] = 0;

	           }
            System.out.print(arr[row][col] + " ");
	        }
        System.out.println(); 
	    }

	}
}
	
