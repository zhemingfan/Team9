package game;
import java.util.ArrayList;

import parents.Point;

/**
 *
 * Map Class that creates/updates/prints map
 * with path.
 *
 */

public class Map{
  private int COLUMN;
  private int ROW;
  private String[][] gridMap = new String[ROW][COLUMN];
  
  /**
   * Generates a (ROW)x(COLUMN) map which will generate a 2D array that represents the background to be painted on GUI.
   * Example:
   *        r 0 r - - - - r - -
   *        - p - - r - - - - -
   *        - 1 p p p p p p 2 -
   *        - - - r - - - - p -
   *        - 4 p p p p p p 3 -
   *        r p - - - - - - r -
   *        - 5 p p p p p p 6 -
   *        - - r - - - - - p -
   *        - 8 p p p p p p 7 r
   *        - - r - - r - - - -
   * 0 => n : check points, including start and end
   * p : path
   * c : check point
   * - : tile where defense can be placed
   * D : defender
   */
  public Map() {
    COLUMN = 10;
    ROW = 10;

    String[][] temp = { 
			{"r","s","r","-","-","-","-","r","-","-",},
            {"-","p","-","-","r","-","-","-","-","-",},
            {"-","0","p","p","p","p","p","p","1","-",},
            {"-","-","-","r","-","-","-","-","p","-",},
            {"-","3","p","p","p","p","p","p","2","-",},
            {"r","p","-","-","-","-","-","-","r","-",},
            {"-","4","p","p","p","p","p","p","5","-",},
            {"-","-","r","-","-","-","-","-","p","-",},
            {"-","7","p","p","p","p","p","p","6","r",},
            {"-","8","r","-","-","r","-","-","-","-",}
    	};
    gridMap = temp;
  }
 
  /**
   * Returns the 2D array that represents the background to be painted on GUI.
   * @return The 2D array that represents the background to be painted on GUI.
   */
  public String[][] generateGrid() {
    
    return gridMap;
  }
  
  /**
   * Changes the 2D array gridMap if the player chooses ZigZag as their map.
   */
  public void makeZigZagGrid() {
	  String[][] temp = { 
				{"r","s","r","-","-","-","-","r","-","-",},
	            {"-","p","-","-","r","-","-","-","-","-",},
	            {"-","0","p","p","p","p","p","p","1","-",},
	            {"-","-","-","r","-","-","-","-","p","-",},
	            {"-","3","p","p","p","p","p","p","2","-",},
	            {"r","p","-","-","-","-","-","-","r","-",},
	            {"-","4","p","p","p","p","p","p","5","-",},
	            {"-","-","r","-","-","-","-","-","p","-",},
	            {"-","7","p","p","p","p","p","p","6","r",},
	            {"-","8","r","-","-","r","-","-","-","-",}
	    	};
	   gridMap = temp;
  }
  /**
   * Changes the 2D array gridMap if the player chooses Loopy as their map.
   */
  public void makeLoopyGrid() {
	  String[][] alternate = { 
			{"r","-","-","r","-","-","-","-","r","-",},
			{"-","1","p","p","2","-","8","p","7","-",},
			{"r","p","-","-","p","-","p","-","p","-",},
			{"s","0","r","-","p","-","p","r","p","-",},
			{"r","-","-","-","p","-","p","-","p","-",},
			{"-","5","p","p","p","p","p","p","6","-",},
			{"-","p","-","r","p","-","p","r","-","-",},
			{"-","4","p","p","3","-","p","-","-","-",},
			{"r","-","-","-","-","r","9","p","p","10",},
			{"-","-","-","-","-","-","-","-","-","-",},
			
    	};
    gridMap = alternate;
  }
  
  /**
   * Returns the number of columns of the 2D array gridMap.
   * @return the number of columns of the 2D array gridMap
   */
  public int getColumn() {
	  return COLUMN;
  }
  /**
   * Returns the number of rows of the 2D array gridMap.
   * @return the number of rows of the 2D array gridMap
   */
  public int getRow() {
	  return ROW;
  }
  /**
   * Check if the tile on the grid is "-" at given row and column and  therefore can have a tower put on it.
   * @param row
   * @param column
   * @return Whether player can place a tower at given row and column 
   */
  public boolean canPlaceDefense(int row, int column) {
	  return ( gridMap[row][column].equals("-") );
  }
  /**
   * Update the grid at specified row and column to "D" so that this tile cannot have defender put on it anymore.
   * @param row
   * @param column
   */
  public void updateDefender(int row, int column) {
	  if (canPlaceDefense (row, column)) {
		  gridMap[row][column] = "D";
	  }
  }
  
  /**
   * Generates and returns the array with all the check points that enemies have to move to on the map.
   * Takes in "offset" to convert the rows and columns to x and y coordinates.
   * Example: row: 1, column: 1. The UI is 10 tiles by 10 tiles, each tile is 50 by 50px.
   * 	Thus, the x and y coordinates is (50, 50).
   * @param offsetX
   * @param offsetY
   * @return The array with all the check points that enemies have to move to on the map.
   */
  public Point[] getCheckPoints(double offsetX, double offsetY) {
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
	  // i.e.: Offset is 50 by 50. At row 1, column 1, find "1". Creates Point(50, 50). Put this point into the checkPoints array at index 1.
	  for (int r = 0; r < ROW; r++){
		      for (int c = 0; c < COLUMN; c++){
		    	 char tile = gridMap[r][c].charAt(0);
		         if (Character.isDigit(tile)) {
		        	 Point coords = new Point(c * offsetX, r * offsetY);
		        	 checkPoints[Integer.parseInt(gridMap[r][c])] = coords;
		         };
		      };
		    };
	  return checkPoints;
  }
  
  /**
   * Returns the start point where enemies enter.
   * @param offsetX
   * @param offsetY
   * @return The start where enemies enter.
   */
  public Point getStartPoint(double offsetX, double offsetY) {
	  Point start = new Point(0,0);
	  for (int r = 0; r < ROW; r++){
	      for (int c = 0; c < COLUMN; c++){
	    	 String tile = gridMap[r][c];
	         if (tile.equals("s")) {
	        	 start = new Point(c*offsetX, r*offsetY);
	        	 break;
	         };
	      };
	    };
	   
	  return start;
  }
  
  /**
   * Returns the end point where the enemies will damage the Player and disappear
   * @param offsetX
   * @param offsetY
   * @return the end point where the enemies will damage the Player and disappear.
   */
  public Point getEndPoint(double offsetX, double offsetY) {
	  int end = this.getCheckPoints(offsetX, offsetY).length;
	  return this.getCheckPoints(offsetX, offsetY)[end - 1];
  }
  
  /**
   * Return a string that can be printed onto the console for debugging
   * @return String
   */
  
  public String display() {
	String view = new String();
    for (int r = 0; r < ROW; r++){
      for (int c = 0; c < COLUMN; c++){
         view += (gridMap[r][c] + " ");
      };
      view += ("\n");
    };
  System.out.println(view);
  return view;
  }

}
