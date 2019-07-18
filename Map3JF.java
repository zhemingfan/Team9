import java.util.Arrays;

public class Map3JF{
  
  public void createPath(String[][] grid){
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[0].length; c++) {
        if (grid[r][c].contentEquals("0")){
          grid[r][c] = "1";
        }
        grid[2][c] = "-";
      }
  }
  }
  public void generateGrid(String[][] grid) {
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[0].length; c++) {
        grid[r][c] = "0";
      }
    }
  }
  
  public void printGrid(String[][] grid) {
    for (int r = 0; r < grid.length; r++){
      System.out.println("");
      for (int c = 0; c < grid[0].length; c++){
       System.out.print(grid[r][c]+" ");
       }
      
      } 
    System.out.println();

   }
  
  public void createInitialGrid(String[][] grid) {
	 generateGrid(grid);
	 createPath(grid);
	 printGrid(grid);
  }
  
  public void placeEnemies(String[][] grid, int col, int row, String symbol) {
	  grid[col][row] = symbol;
  }
  

  public void runEnemies(String[][] arr) {
	  for (int row = 0; row < arr.length; row++) {
	        for (int col = 0; col < arr[row].length; col++) {
	            if (arr[2][4] != "5") { //TODO clean this up so that it's ending location
	            	if (arr[row][col] == "5") {
	            		arr[row][col+1] = "5";
	            		arr[row][col] = "-";
	            		//DO NOT REMOVE THE TWO PRINT FUNCTIONS BELOW
	            		System.out.println(Arrays.deepToString(arr).replace("], ", "\n").replace("[", "").replace(",", "").replace("]]", ""));
	            			
	                    System.out.println(); 

	            		}

	           }
	            
	            
	        }
	    }
  }
  
  
  
  
  
  
  }

