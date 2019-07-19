import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class creates an Enemy object that has a starting health.
 * Instance variables:
 * col : int
 * row : int
 * startIndex : int
 * endIndex : int
 * enemyHealth : private int
 * enemyList : ArrayList<Enemy>
 */
public class Enemy extends Map{
   int x;
   int y;
   int startIndex = 0;
   int endIndex = 4;
   int bounty;
   private int enemyHealth;
   ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
  /**
   * Constructor
   * @param enemyHealth
   * @param x
   * @param y
   *
   */
  public Enemy(int enemyHealth, int x, int y) {
     this.enemyHealth = enemyHealth;
     this.x = x;
     this.y = y;
     this.moneyOnDeath = 50;
  }
  /**
   * Copy Constructor
   * @param toCopy
   */
  public Enemy(Enemy toCopy) {
    enemyHealth = toCopy.enemyHealth;
    x = toCopy.x;
    y = toCopy.y;
  }
  /**
   * getter method that returns the x coordinate of the specified enemy.
   * @param grid
   * @param anEnemy
   * @return x: int
   */

  public int getXCoord(String[][]grid,Enemy anEnemy) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[c][r].equals(healthToString(anEnemy))) {
          x = c;
        }
      }
    }
    return x;
  }

  /**
   * getter method that returns the y coordinate of the specified enemy.
   * @param grid
   * @param anEnemy
   * @return y: int
   */

  public int getYCoord(String[][]grid,Enemy anEnemy) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[c][r].equals(healthToString(anEnemy))) {
          y = r;
        }
      }
    }
    return y;
  }

  /**
   * A getter method that returns the health of specified enemy
   * @return enemyHealth: int
   */
  public int getEnemyHealth() {
    return enemyHealth;
  }

  /**
   * A getter method that returns the list of enemies for a specified wave.
   * @return enemyList: ArrayList<Enemy>
   */
  public ArrayList<Enemy> getEnemyList() {
    ArrayList<Enemy> anEnemyList = new ArrayList<Enemy>();
    for (Enemy anEnemy : enemyList) {
      anEnemyList.add(new Enemy(anEnemy));
    }
    return enemyList;
  }
  /**
   * A method adding enemies into an array list.
   * @param anEnemy
   */
  public void addEnemy(Enemy anEnemy) {
    enemyList.add(new Enemy(anEnemy));
  }
  /**
   * Returns health of specified enemy as a string.
   * @param anEnemy
   * @return health: String
   */
  public String healthToString(Enemy anEnemy) {
    String eHealth = Integer.toString(anEnemy.getEnemyHealth());
    return eHealth;
  }
  /**
   * Function takeDamage has enemy take damage from tower.
   * @param damage
   *
   */
  public void takeDamage(int damage) {
    this.enemyHealth -= damage;
  }
  /**
   * a method that generates a new enemy and places it on a grid
   * @param grid
   * @param anEnemy
   * @return grid : String[][]
   */
  public String[][] generateEnemy(String[][] grid,Enemy anEnemy) {
    System.out.println("ENEMY HAS ARRIVED");
    int startpoint = startIndex;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[startpoint][2] == "-") {
          grid[startpoint][2] = ""+anEnemy.getEnemyHealth();
        }
      }
    }
    return grid;
  }
  /**
   * a single move method for the specified enemy.
   * @param grid
   * @param anEnemy
   */
  public void moveEnemy(String[][] grid,Enemy anEnemy) {
    int col = getXCoord(grid,anEnemy);
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if(grid[col][2].equals(healthToString(anEnemy))){
          if((col+1) < 5) {
            grid[col+1][2] = healthToString(anEnemy);
            grid[col][2] = "-";
          } else if ((col+1) == grid[r].length) {
              grid[col][2] = "-";
              System.out.println("YOU TOOK DAMAGE");
              break;
            }
          }
        }
      }
    }
  /**
   * using moveEnemy method, this function will take the enemy from
   * startIndex to endIndex
   * @param anEnemy
   * @param aMap
   * @param grid
   * @param input
   */
  public void enemyWave1(Enemy anEnemy,Map aMap, String[][]grid, Scanner input) {
    String enter = input.nextLine();
    while (enter != "no") {
      anEnemy.moveEnemy(grid,anEnemy);
      printGrid(grid);
      System.out.println("Press Enter");
      enter = input.nextLine();
    }
  }

  public boolean isKilled(){
    return true;
  }

  public int getBounty(){
    return this.bounty;
  }

  public void giveBounty(Player p){
    p.money += getBounty();
  }
}
