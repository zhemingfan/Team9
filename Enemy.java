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
public class Enemy extends Player{
   int x = 0; // Starting enemy x coordinate
   int y = 2; // Starting enemy y coordinate
   int startXIndex = 0;
   int startYIndex = 2;
   int endIndex = 4;
   int dealtDamage; // Enemy damage inflicted on Player
   int enemyHealth;
   int moneyGained;


////////////// CONSTRUCTORS //////////////////
  /**
   * Constructor
   * @param enemyHealth
   * @param x
   * @param y
   *
   */
  public Enemy() {
  }

  public Enemy(int enemyHealth, int dealtDamage,int moneyGained) {
     this.enemyHealth = enemyHealth;
     this.x = 0;
     this.y = 2;
     this.moneyGained = moneyGained;
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

////////////// GET METHODS //////////////////

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

//////////////SET METHODS //////////////////
  /**
   * a method that generates a new enemy and places it on a grid
   * @param grid
   * @param anEnemy
   */
  public void generateEnemy(String[][] grid,Enemy anEnemy) {
    System.out.println("ENEMY HAS ARRIVED");
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[startXIndex][startYIndex] == "-") {
          grid[x][y] = healthToString(anEnemy);
        }
      }
    }
  }

////////////// MOVE METHODS //////////////////
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


  public void moveEnemy(Map aMap, String[][] grid,Enemy anEnemy) {
  boolean reached = false;
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if((this.x+1) < 5) {
          grid[this.x+1][this.y] = healthToString(anEnemy);
          grid[this.x][this.y] = "-";
          //aMap.printGrid(grid);
        }

        else if (((this.x+1) == grid[r].length) && (grid[this.x][2] != "-")) {
          reached = true;
          grid[this.x][this.y] = "-";
          grid[this.x][this.y+1] = healthToString(anEnemy);
          //aMap.printGrid(grid);
        }

        else if (((this.x+1) == grid[r].length) && (grid[this.x][3] != "-") && reached == true) {
            grid[this.x][this.y+1] = "-";
            grid[this.x][this.y+2] = healthToString(anEnemy);
          }

      }
    }
    this.x += 1;

  }
////////////// ATTACK/TAKE DAMAGE METHODS //////////////////
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
      System.out.println("Y O U  H A V E  T A K E N  D A M A G E!!!!\n");
      System.out.println("Health: "+aPlayer.getHealth());
    }
  }
  /**
   * function takeDamage has enemy take damage from tower.
   * @param damage
   *
   */
  public void takeDamage(int damage) {
    this.enemyHealth -= damage;
  }
  public void killEnemy(String[][] grid) {
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        grid[this.x][this.y] = "-";

        }
      }

    }
  }
/*
  public int getBounty(){
    return this.bounty;
  }
  */
