public class Map{
  
  public Map() {
  }
  public void createPath(String[][] grid){
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[0].length; c++) {
        if (grid[c][r].contentEquals("0")){
          grid[c][r] = " ";
        }
        grid[c][2] = "-";
      }
  }
  }
  public void generateGrid(String[][] grid) {
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[0].length; c++) {
        grid[c][r] = "0";
      }
    }
  }
  
  public void printGrid(String[][] grid) {
    for (int r = 0; r < grid.length; r++){
      System.out.println("");
      for (int c = 0; c < grid[0].length; c++){
       System.out.print(grid[c][r]+" ");
       }
      } 
   }
  }
