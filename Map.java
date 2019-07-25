/**
 * 
 * Map Class that creates/updates/prints map
 * with path.
 *
 */
/*
 * Screen Record Script
 * WAVE 1. let enemy run through map
 * WAVE 2. input ARCHER in (0,1)
 *         input SNIPER in (5,1)
 *         input SNIPER in (6,5)
 * WAVE 3. input ARCHER in (3,3)
 *         finish screen record.
 */

public class Map extends Game{
  int xlength = 10;
  int ywidth = 10;
  String path = "_";
  String empty = ".";
  
  public Map() {
  }
  /**
   * 
   * Generates a grid of (xlength)x(ywidth) units.
   * @return String[][]
   */
  public String[][] generateGrid() {
    String[][] grid = new String[xlength][ywidth];
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        grid[c][r] = empty;
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
    /*
    grid[0][2] = path;
    grid[1][2] = path;
    grid[2][2] = path;
    grid[3][2] = path;
    grid[4][2] = path;
    */
    
    grid[0][2] = path;
    grid[1][2] = path;
    grid[2][2] = path;
    grid[3][2] = path;
    grid[4][2] = path;
    grid[4][3] = path;
    grid[4][4] = path;
    grid[4][5] = path;
    grid[4][6] = path;
    grid[5][6] = path;
    grid[6][6] = path;
    grid[7][6] = path;
    grid[8][6] = path;
    grid[9][6] = path;
         
  }

  /**
   *  Prints the grid.
   * @param grid
   */
  public void printGrid(String[][] grid) {
    for (int r = 0; r < grid.length; r++){
      System.out.println("\n");
      for (int c = 0; c < grid[r].length; c++){
         System.out.print(grid[c][r]+"   ");
      }
    }
    System.out.println("\n");
  }
}


  