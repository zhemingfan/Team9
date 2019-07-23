import java.util.Scanner;

public class Defender extends Player{
  private int damage;
  private int range;
  private int xCoord;
  private int yCoord;
  Scanner coordInput = new Scanner(System.in);

  
  public Defender(String name, String[][] grid,int aDamage,int aRange) {
    damage = aDamage;
    range = aRange;
    setXYCoord(grid,aDamage,aRange);
    placeDefense(grid,name);
    System.out.println("\n("+this.getXCoord()+","+this.getYCoord()+")\n");

  }
//////////////////////////////////VARIABLE METHODS//////////////////////////////////
  public int getRange() {
    return range;
  }
  public int getDamage() {
    return damage;
  }
  public int getXCoord() {
    return xCoord;
  }
  public int getYCoord() {
    return yCoord;
  }
////////////////////////////////////////////////////////////////////////////////////
  
//////////////////////////////X AND Y COORDINATE SETTER////////////////////////////
  public void setXYCoord(String[][] grid,int damage,int range) {
    // Set x coordinate
    System.out.println("Enter X coordinate:");
    xCoord = coordInput.nextInt();
    while (xCoord < 0 || xCoord > 9) {
      System.out.println("Your X coordinate is Out of Range.\nPlease Enter a Value Between 0 and 9");
      xCoord = coordInput.nextInt();
     }
    System.out.println("Enter Y coordinate:");
    yCoord = coordInput.nextInt();
    while (yCoord < 0 || yCoord > 9) { // for 10x10
      System.out.println("Your Y coordinate is Out of Range.\nPlease Enter a Value Between 0 and 9"); // for 10x10
      yCoord = coordInput.nextInt();
     }
    // Will call the function recursively if the coordinates contain a path
    while (containsPath(grid,xCoord,yCoord)) {
      System.out.println("Your coordinates contain a path\nPlease enter valid X and Y coordinates.\n");
      setXYCoord(grid,damage,range); // recursion
    }  
  }
  /**
   * method that places defender on grid.
   * @param grid
   * @param input
   */
  public void placeDefense(String[][] grid,String name) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[xCoord][yCoord] == ".") {
          grid[xCoord][yCoord] = name;
        }
      }
    }
  }
 
  /**
   * method that checks if x and y coordinates contain "-" path.
   * @param grid
   * @param xCoord
   * @param yCoord
   * @return boolean
   */
  public boolean containsPath(String[][] grid,int xCoord, int yCoord) {
    boolean result = false;
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[xCoord][yCoord].equals("_")) {
          result = true;
        }
      } 
    }
    return result;
  }
///////////////////////////////////////////////////////////////////////////////////////////
}
