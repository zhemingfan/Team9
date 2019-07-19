import java.util.Scanner;
/**
 * This class creates an Enemy object that has a starting health.
 * Instance variables:
 * col = column
 * row = row
 * startIndex = starting point of enemy
 * endIndex = ending point of enemy
 * enemyHealth = enemy health
 */
public class Enemy extends Map{
   int col;
   int row;
   int startIndex = 0;
   int endIndex = 4;
   int enemyHealth;
  /**
   * Constructor
   * @param enemyHealth
   * 
   */
  public Enemy(int enemyHealth) {
     this.enemyHealth = enemyHealth;
  }
  /**
   * getter method that returns the x coordinate of the specified enemy.
   * @param grid
   * @param anEnemy
   * @return row
   */
  /* BLOCKED OUT DUE TO ISSUES CONVERTING ENEMYHEALTH TO STRING
  public int getXCoord(String[][]grid,Enemy anEnemy) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; r++) {
        if (grid[c][r] == healthToString(anEnemy)) {
          col = c;
        }
      }
    }
    return col;
  }
  */
  /**
   * getter method that returns the y coordinate of the specified enemy.
   * @param grid
   * @param anEnemy
   * @return row
   */
  /* BLOCKED OUT DUE TO ISSUES CONVERTING ENEMYHEALTH TO STRING
  public int getYCoord(String[][]grid,Enemy anEnemy) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; r++) {
        if (grid[c][r] == healthToString(anEnemy)) {
          row = r;
        }
      }
    }
    return row;
  }
  */
  /**
   * A getter method that returns the health of specified enemy
   * @return enemyHealth
   */
  public int getEnemyHealth() {
    return enemyHealth;
  }
  /**
   * Function takeDamage has enemy take damage from tower.
   * @param damage
   * @return row
   */
  public void takeDamage(int damage) {
    enemyHealth -= damage;
  }
  public String[][] generateEnemy(String[][] grid,Enemy anEnemy) {
    System.out.println("ENEMY HAS ARRIVED");
    int startpoint = startIndex;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[startpoint][2] == "-") {
          grid[startpoint][2] = "5";
        }
      }
    }
    return grid;
  }
  /**
   * a single move method for the specified enemy.
   * @param grid
   * @param col
   */
  public void moveEnemy(String[][] grid,int col) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if(grid[col][2] == "5") {
          if((col+1) < 5) {
            grid[col+1][2] = "5";
            grid[col][2] = "-";
          } else if ((col+1) == 5) {
              grid[col][2] = "-";
              System.out.println("YOU TOOK DAMAGE");
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
  public void enemyFunction(Enemy anEnemy,Map aMap, String[][]grid, Scanner input) {
    int col = startIndex;
    String enter = input.nextLine();
    while (enter != "no") {
      anEnemy.moveEnemy(grid,col);
      printGrid(grid);
      System.out.println("Press Enter");
      enter = input.nextLine();
      col+=1;   
    }
  }
}
