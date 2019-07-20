/**
 * 
 * Map Class that creates/updates/prints map
 * with path.
 *
 */

public class Map extends Game{
  int xlength = 5;
  int ywidth = 5;
  
  public Map() {
  }
  /**
   * Generates a grid of (xlength)x(ywidth) units.
   * @return String[][]
   */
  public String[][] generateGrid() {
    String[][] grid = new String[xlength][ywidth];
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        grid[c][r] = "0";
        createPath(grid,c,r);
      }
    }
    return grid;
  }
  /**
   * Creates path on map.
   * @param grid
   * @param column
   * @param row
   */
  public void createPath(String[][] grid,int column,int row) {
    grid[column][2] = "-";
    grid[4][3] = "-";
    grid[4][4] = "-";
       
  }
  /**
   *  Prints the grid.
   * @param grid
   */
  public void printGrid(String[][] grid) {
    for (int r = 0; r < grid.length; r++){
      System.out.println("");
      for (int c = 0; c < grid[r].length; c++){
         System.out.print(grid[c][r]+" ");
      }
    }
    System.out.println("\n");
  }
}
