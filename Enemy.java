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
public class Enemy extends Game{
   int x = 0;
   int y = 2;
   int startXIndex = 0;
   int startYIndex = 2;
   int endIndex = 4;
   int dealtDamage;
   private int enemyHealth;

  /**
   * Constructor
   * @param enemyHealth
   * @param x
   * @param y
   * 
   */
  public Enemy() {
    
  }
  public Enemy(int enemyHealth, int dealtDamage) {
     this.enemyHealth = enemyHealth;
     this.x = 0;
     this.y = 2;
     this.dealtDamage = dealtDamage;
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
   * @return x: int
   */
  
  public int getXCoord() {
    return x;
  }
  
  /**
   * getter method that returns the y coordinate of the specified enemy.
   * @return y: int
   */
  
  public int getYCoord() {
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
   * Returns health of specified enemy as a string.
   * @param anEnemy
   * @return health: String
   */
  public String healthToString(Enemy anEnemy) {
    String eHealth = Integer.toString(anEnemy.getEnemyHealth());
    return eHealth;
  }
  /**
   * function takeDamage has enemy take damage from tower.
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
   */
  public void generateEnemy(String[][] grid,Enemy anEnemy) {
    System.out.println("ENEMY HAS ARRIVED");
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[startXIndex][2] == "-") {
          grid[x][y] = healthToString(anEnemy);
        }
      }
    }
  }
  public void moveAlgorithm(String[][] grid,Enemy anEnemy) {
    
  }
  /**
   * a single move method for the specified enemy.
   * @param grid
   * @param anEnemy
   */

  public void moveRight(String[][] grid,Enemy anEnemy) {
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if ((this.x + 1) < 5) {
          if(grid[this.x+1][this.y].equals("-")) {
            grid[this.x+1][this.y] = healthToString(anEnemy);
            grid[this.x][this.y] = "-";
          }
        }
        else if ((this.x+1) == grid[r].length) {
          grid[this.x][2] = "-";
          break;
        }
      }
    }
    this.x += 1;
  }
  /**
   * a single move method for the specified enemy.
   * @param grid
   * @param anEnemy
   */
  public void moveLeft(String[][] grid,Enemy anEnemy) {
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if ((this.x - 1) > 0) {
          if(grid[this.x-1][this.y].equals("-")) {
            grid[this.x-1][this.y] = healthToString(anEnemy);
            grid[this.x][this.y] = "-";
          }
        }
        else if ((this.x) == 0) {
          grid[this.x][this.y] = "-";
          break;
        }
      }
    }
    this.x -= 1;
  }
  /**
   * a single move method for the specified enemy.
   * @param grid
   * @param anEnemy
   */
  public void moveUp(String[][] grid,Enemy anEnemy) {
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if((this.y-1) > 0) {
          if(grid[this.x][this.y-1].equals("|")) {
            grid[this.x][this.y-1] = healthToString(anEnemy);
            grid[this.x][this.y] = "|";
          }
        }
        else if (this.y == 0) {
          grid[this.x][this.y] = "|";
          break;
        }
      }
    }
    this.y -= 1;
  }
  /**
   * a single move method for the specified enemy.
   * @param grid
   * @param anEnemy
   */
  public void moveDown(String[][] grid,Enemy anEnemy) {
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if((this.y+1) < grid.length) {
          if(grid[this.x][this.y+1].equals("|"))
          grid[this.x][this.y+1] = healthToString(anEnemy);
          grid[this.x][this.y] = "|";
        } 
        else if (this.y+1 == grid.length) {
          grid[this.x][this.y] = "|";
          break;
        }
      }
    }
    this.y += 1;
  }
  /**
   * method that returns a boolean if an enemy has crossed the map
   * @param aMap
   * @param anEnemy
   * @return boolean
   */
  public boolean checkEnemyCrossed(Map aMap, Enemy anEnemy) {
    return anEnemy.getXCoord() > aMap.xlength || anEnemy.getYCoord() > aMap.ywidth;
  }
  /**
   * method that attacks Player if an Enemy has crossed
   * @param aMap
   * @param aPlayer
   * @param anEnemy
   */
  public void attack(Map aMap, Player aPlayer,Enemy anEnemy) {
    if(checkEnemyCrossed(aMap, anEnemy)) {
      aPlayer.takeDamage(dealtDamage);
      System.out.println("You have taken damage.");
      System.out.println(aPlayer.getHealth());
    }
  }
  
  /**
   * using moveEnemy method, this function will take the enemy from
   * startIndex to endIndex
   * @param anEnemy
   * @param aMap
   * @param grid
   * @param input
   * 
   */
  public static void enemyWave1(Enemy anEnemy,Player aPlayer, Map aMap, String[][]grid, Scanner input,Defender aDefense) {
    String enter = input.nextLine();
    System.out.println("W A V E  1");
    while (enter != "no") {
      
      anEnemy.moveRight(grid,anEnemy); 
      anEnemy.attack(aMap, aPlayer, anEnemy);
      //aDefense.attack(anEnemy,grid);
      aMap.printGrid(grid);
      System.out.println(anEnemy.getXCoord());
      System.out.println("Press Enter");
      enter = input.nextLine();
    }
  }
}
