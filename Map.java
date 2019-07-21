/**
 * 
 * Map Class that creates/updates/prints map
 * with path.
 *
 */

public class Map extends Game{
  int xlength = 10;
  int ywidth = 10;
  
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
        grid[c][r] = ".";
        createPath(grid);
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
  public void createPath(String[][] grid) {
    grid[0][2] = "-";
    grid[1][2] = "-";
    grid[2][2] = "-";
    grid[3][2] = "-";
    grid[4][2] = "-";
    grid[4][3] = "-";
    grid[4][4] = "-";
    grid[4][5] = "-";
    grid[4][6] = "-";
    grid[5][6] = "-";
    grid[6][6] = "-";
    grid[7][6] = "-";
    grid[8][6] = "-";
    grid[9][6] = "-";
       
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