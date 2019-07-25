import java.util.ArrayList;

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
  
  public Map() {
    COLUMN = 10;
    ROW = 10;
  }
  /**
   * Generates a (ROW)x(COLUMN) grid.
   *        - s - - - - - - - -
   *        - o - - - - - - - -
   *        - c o o o o o o c -
   *        - - - - - - - - o -
   *        - c o o o o o o c -
   *        - o - - - - - - - -
   *        - c o o o o o o c -
   *        - - - - - - - - o -
   *        - e o o o o o o c -
   *        - - - - - - - - - -
   * s : start point
   * o : path
   * c : check point
   * - : tile where defense can be placed
   * e : end/Player's base
   * D : defender
   * 
   * @return String[][]
   */
   
  //multiply by offset... since the actual coordinate is 100,200 for 1,2 in the array below 
  public String[][] generateGrid() { //two layers... one back layer of JUST the map, and then another layer for the towers.
    String[][] def = { 
    			{"-","0","-","-","-","-","-","-","-","-",},
                {"-","p","-","-","-","-","-","-","-","-",},
                {"-","1","p","p","p","p","p","p","2","-",},
                {"-","-","-","-","-","-","-","-","p","-",},
                {"-","4","p","p","p","p","p","p","3","-",},
                {"-","p","-","-","-","-","-","-","-","-",},
                {"-","5","p","p","p","p","p","p","6","-",},
                {"-","-","-","-","-","-","-","-","p","-",},
                {"-","8","p","p","p","p","p","p","7","-",},
                {"-","-","-","-","-","-","-","-","-","-",}
    };
    gridMap = def;
    return gridMap;
  }
  
  public int getColumn() {
	  return COLUMN;
  }
  
  public int getRow() {
	  return ROW;
  }
  
  private boolean canPlaceDefense(int row, int column) {
	  return ( gridMap[row][column].equals("o") );
  }
  
  public void updateDefender(int row, int column) {
	  if (canPlaceDefense (row, column)) {
		  gridMap[row][column] = "D";
	  }
  }
  
  //looks through map and looks at which tiles start with a digit.. knows it is a checkpoint tile, creates an array of points with those checkpoints
  //parse tiles with a number into an integer, and add that coordinate 
  public Point[] getCheckPoints(double offsetX, double offsetY) {
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
	  
	  for (int r = 0; r < ROW; r++){
		      for (int c = 0; c < COLUMN; c++){
		    	 char tile = gridMap[r][c].charAt(0);
		         if (Character.isDigit(tile)) {
		        	 Point coords = new Point(c * offsetX, r * offsetY);
		        	 checkPoints[ Integer.parseInt(gridMap[r][c])] = coords;
		         };
		      };
		    };
	  return checkPoints;
  }
  
  public Point getStartPoint(double offsetX, double offsetY) {
	  return this.getCheckPoints(offsetX, offsetY)[0];
  }
  
  public Point getEndPoint(double offsetX, double offsetY) {
	  int end = this.getCheckPoints(offsetX, offsetY).length;
	  return this.getCheckPoints(offsetX, offsetY)[end - 1];
  }
  
  public String display() {
	String view = new String();
    for (int r = 0; r < ROW; r++){
      for (int c = 0; c < COLUMN; c++){
         view += (gridMap[r][c] + " ");
      };
      view += ("\n");
    };
  return view;
  }
  
  public static void main(String[] args) {
	Map map = new Map();
	map.generateGrid();
    System.out.println(map.display());
    
    Point start = map.getStartPoint(50, 50);
    System.out.println( start.toString() );
    
    Point end = map.getEndPoint(50, 50);
    System.out.println( end.toString()  + "\n" );
    
    for (Point x: map.getCheckPoints(50, 50)) {
    	System.out.println(x.toString());
    }
    
  }
}
