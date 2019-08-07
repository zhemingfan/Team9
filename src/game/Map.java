package game;
import parents.Point;
/** TEXT BASED VERSION
 * Map Class that creates/updates/prints map
 * with path.
 * @author Team 9
 */
public class Map {
  private int COLUMN;
  private int ROW;
  private String[][] gridMap = new String[ROW][COLUMN];
  
  /** Once object is created, will create 2D array of [ROW]x[COLUMN].
   */
  public Map() {
    COLUMN = 10;
    ROW = 10;
    String[][] temp = { 
                        {"r","s","r",".",".",".",".","r",".",".",},
                        {".","-",".",".","r",".",".",".",".",".",},
                        {".","0","-","-","-","-","-","-","1",".",},
                        {".",".",".","r",".",".",".",".","-",".",},
                        {".","3","-","-","-","-","-","-","2",".",},
                        {"r","-",".",".",".",".",".",".","r",".",},
                        {".","4","-","-","-","-","-","-","5",".",},
                        {".",".","r",".",".",".",".",".","-",".",},
                        {".","7","-","-","-","-","-","-","6","r",},
                        {".","8","r",".",".","r",".",".",".",".",}
                      };
    gridMap = temp;              
  }
  
  /**
   * returns a [ROW]x[COLUMN] grid.
   *       r   s   r   .   .   .   .   r   .   .   
   *       .   -   .   .   r   .   .   .   .   .   
   *       .   0   -   -   -   -   -   -   1   .   
   *       .   .   .   r   .   .   .   .   -   .   
   *       .   3   -   -   -   -   -   -   2   .   
   *       r   -   .   .   .   .   .   .   r   .   
   *       .   4   -   -   -   -   -   -   5   .   
   *       .   .   r   .   .   .   .   .   -   .   
   *       .   7   -   -   -   -   -   -   6   r   
   *       .   8   r   .   .   r   .   .   .   . 
   * 0 => n : check points, including start and end
   * - : path
   * s : start point
   * . : tile where defense can be placed
   * T : tower
   * 
   * @return String[][]
   */
  public String[][] getGridMap() { 
    return gridMap;
  }
  
  /**
   * Generate the array with all the check points. Takes in "offset" to convert the rows and columns
   * to x and y coordinates.
   * i.e.: row: 1, column: 1. The UI is 10 tiles by 10 tiles, each tile is 50 by 50px.
   *    Thus, the x and y coordinates is (50, 50).
   * @param offsetX
   * @param offsetY
   * @return Point[]
   */
  public Point[] getCheckPoints() {
    // This portion finds out how many checkpoints we have and create an array of corresponding size.
    int numberOfChecks = 0;
    for (int r = 0; r < ROW; r++){
      for (int c = 0; c < COLUMN; c++){
         char tile = gridMap[r][c].charAt(0);
         if (Character.isDigit(tile)) {
                 numberOfChecks +=1;
         };
      };
    };      
    Point[] checkPoints = new Point[numberOfChecks];
    
    // This portion reads the grid, generate the points and put the point into the checkPoints array at the corresponding index.
    for (int r = 0; r < ROW; r++){
      for (int c = 0; c < COLUMN; c++){
        char tile = gridMap[r][c].charAt(0);
        if (Character.isDigit(tile)) {
          Point coords = new Point(c , r);
          checkPoints[ Integer.parseInt(gridMap[r][c])] = coords;
          gridMap[r][c]="-";
        };
      };
    };
    return checkPoints;
  }
  
  /** Displays grid with labeled axis' when placing towers.
   */
  public void displayTowerGrid() {
    System.out.println("\n");
    String[][] towerGrid = gridMap;
    for (int r = 0; r < towerGrid.length; r++) {
      System.out.print("  "+r);
    }
    System.out.println("");
    for (int r = 0; r < towerGrid.length; r++){
      System.out.print("  \n"+r);
      for (int c = 0; c < towerGrid[r].length; c++){
        System.out.print(" "+towerGrid[r][c]+" ");
      }
      System.out.println("");
    }
    System.out.println("\n");
  }

  
  /** Takes gridMap and prints it out as a string.
   */
  public void display() {
    for (int r = 0; r < gridMap.length; r++){
      System.out.println("\n");
      for (int c = 0; c < gridMap[r].length; c++){
        System.out.print(gridMap[r][c]+"   ");
      }
    }
    System.out.println("\n\n");
  }

  
}


  