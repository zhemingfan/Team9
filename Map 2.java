import java.util.Arrays;

public class Map {
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
		
		//System.out.println(Arrays.deepToString(arr));
	    for (int row = 0; row < arr.length; row++) {
	    	//System.out.println("");
	        for (int col = 0; col < arr[row].length; col++) {
	            if (arr[2][4] != 5) {
	            	if (arr[row][col] == 5) {
	            		arr[row][col+1] = 5;
	            		arr[row][col] = 0;
	            		
	            		System.out.println(Arrays.deepToString(arr).replace("], ", "\n").replace("[", "").replace(",", "").replace("]]", ""));
	            			
	                    System.out.println(); 

	            		}

	           }
	            
	            
	        }
	    }

	}
}
	
