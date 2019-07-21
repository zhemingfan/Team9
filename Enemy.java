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
public class Enemy{
   int x = 0; // Starting enemy x coordinate
   int y = 2; // Starting enemy y coordinate
   int startXIndex = 0;
   int startYIndex = 2;
   int endXIndex = 9;
   int endYIndex = 6;
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
  public Enemy(int enemyHealth, int dealtDamage,int moneyGained) {
     this.enemyHealth = enemyHealth;
     this.x = 0;
     this.y = 2;
     this.moneyGained = moneyGained;
     this.dealtDamage = dealtDamage;
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
   * 
   * @param grid
   * @param anEnemy
   */
  public void moveEnemy(String[][] grid,Enemy anEnemy,Player aPlayer) {
    boolean reached = false;
    this.x = this.getXCoord();
    this.y = this.getYCoord();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if((this.x+1) < endXIndex+1 && grid[this.x+1][this.y].equals("-")) {
          grid[this.x+1][this.y] = healthToString(anEnemy);//moves character right
          grid[this.x][this.y] = "-";
        }       
        else if ((this.x+1) < endXIndex+1 && grid[this.x+1][this.y].equals(".")) {
          grid[this.x][this.y+1] = healthToString(anEnemy);//moves character down
          grid[this.x][this.y] = "-";
          reached = true;
        }
        else if ((this.x) == endXIndex && grid[endXIndex][endYIndex].equals(healthToString(anEnemy))) {
          grid[this.x][this.y] = "-";
          break;
        }
      }
    }
    if (reached) {
      this.y+=1;
    } else {
    this.x += 1;
    }
  }

////////////// ATTACK/TAKE DAMAGE METHODS //////////////////
  /**
   * method that returns a boolean if an enemy has crossed the map
   * @param aMap
   * @param anEnemy
   * @return boolean
   */
  public boolean hasCrossed() {
    return this.getXCoord() == endXIndex && this.getYCoord() == endYIndex;
  }
  /**
   * method that attacks Player if an Enemy has crossed
   * @param aMap
   * @param aPlayer
   * @param anEnemy
   */
  public void attack(Player aPlayer) {
      aPlayer.takeDamage(dealtDamage);
      System.out.println("Y O U  H A V E  T A K E N  D A M A G E!!!!\n");
      System.out.println("Health: "+aPlayer.getHealth());
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
