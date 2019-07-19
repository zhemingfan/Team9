/**
 *
 * Map Class that creates/updates/prints map
 * with path.
 *
 */

public class Map extends Game{


  public Map() {
  }
  /**
   * Generates a grid of nxn units.
   * @param grid
   */
  public void generateGrid(String[][] grid) {
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[0].length; c++) {
        grid[c][r] = "0";
        grid[c][2] = "-";
      }
    }
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
