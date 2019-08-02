import java.util.ArrayList;
import java.util.Scanner;

/** TEXT BASED VERSION
 * Tower Super Class
 * Is placed on the Map and attacks Enemy object.
 * Sub Classes: TowerWater, TowerIce
 * Extends Point Class for xCoord, yCoord.
 * @author Team 9
 */
public class Tower extends Point{
  protected int damage;
  protected int range;
  protected int price;
  protected String name;
  Scanner coordInput = new Scanner(System.in);

  /** Sub class initiated after player chooses tower.
   */
  public Tower() {
    super();
  }
  
  /**
   * @return price Tower Price
   */
  public int getPrice() {
    return price;
  }
  
  /**
   * @return range Tower Attack Range
   */
  public int getRange() {
    return range;
  }
  
  /**
   * @return damage Tower Damage
   */
  public int getDamage() {
    return damage;
  }
  
  /**
   * @return name of Tower
   */
  public String getName() {
    return name;
  }
  
  /** Overrides the method in the parent class and ensures that the x coordinate is within range of the map.
   * @param x xCoord from Point Class
   */
  @Override
  public void setXCoord(int x) {
    while (x < 0 || x > 9) {
      System.out.println("Your X coordinate is Out of range.\nPlease Enter a Value Between 0 and 9");
      x = coordInput.nextInt();
    }
    super.setXCoord(x);
  }
  
  /** Overrides the method in the parent class and ensures that the x coordinate is within range of the map.
   * @param y yCoord from Point Class
   */
  @Override
  public void setYCoord(int y) {
    while (y < 0 || y > 9) { 
      System.out.println("Your Y coordinate is Out of range.\nPlease Enter a Value Between 0 and 9"); // for 10x10
      y = coordInput.nextInt();
    }
    super.setYCoord(y);
  }
  
  /** Takes setXCoord and setYCoord in this class to check if the x,y coordinate is valid.
   * @param grid
   */
  public void setXYCoord(String[][] grid) {
    System.out.println("Enter X coordinate:");
    int x = coordInput.nextInt();
    setXCoord(x); 
    System.out.println("Enter Y coordinate:");
    int y = coordInput.nextInt();
    setYCoord(y);
    while (!isClear(grid)) {
      System.out.println("You can't place your defender here.\nPlease enter valid X and Y coordinates.\n");
      setXYCoord(grid);
    }
  }
  
  /** Takes setXYCoord method to place tower on map.
   * @param grid gridMap from Map Class
   */
  public void placeTower(String[][] grid) {
    setXYCoord(grid);
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
          grid[getYCoord()][getXCoord()] = getName();
        }
      }
    }
  
  /**
   * method that checks if x and y coordinates are valid on the map.
   * @param grid gridMap from Map Class
   * @return boolean if xy coordinate is valid
   */
  public boolean isClear(String[][] grid) {
    boolean result = false;
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[getYCoord()][getXCoord()].equals(".")) {
          result = true;
        }
      } 
    }
    return result;
  }
  

  ////////////// ATTACK METHODS ////////////////
  
  /** Uses the parent class method that takes the Euclidean distance between two points.
   * Checks if the enemy is within range of the tower.
   * @param anEnemy Enemy Class Object
   * @return boolean if enemy is within range
   */
  private boolean enemyIsWithinRange(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= range;
  }
  
  /** Returns the enemy that is within range of the tower by iterating through a list of enemies.
   * @param currentEnemies list of enemies
   * @return target Enemy
   */
  public Enemy findTarget(ArrayList<Enemy> currentEnemies) {
    Enemy target = null;
    for(Enemy item: currentEnemies) {
      if (enemyIsWithinRange(item)) {
        target = item;
        break;
      }
    };
    return target;
  }
  
  /** Main tower attack method and takes in findTarget method above as argument.
   * @param anEnemy findTarget 
   */
  public void attack(Enemy anEnemy) {
    if (anEnemy != null) anEnemy.takeDamage(damage);
  }
  

}

