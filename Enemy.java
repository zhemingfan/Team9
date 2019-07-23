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
public class Enemy extends Wave{
   int x; // Starting enemy x coordinate
   int y; // Starting enemy y coordinate
   int startXIndex = 0;
   int startYIndex = 2;
   int endXIndex = 9; 
   int endYIndex = 6;
   int dealtDamage; // Enemy damage inflicted on Player
   int enemyHealth;
   int moneyGained;
   String name;
   String path = "_";
   String empty = ".";
   
   
////////////// CONSTRUCTORS //////////////////
  /**
   * Constructor
   * @param enemyHealth
   * @param x
   * @param y
   * 
   */
  public Enemy(String aName,int anEnemyHealth, int damageDealt,int gainedMoney,int xcoord, int ycoord) {
    name = aName;
    enemyHealth = anEnemyHealth;
     x = xcoord;
     y = ycoord;
     moneyGained = gainedMoney;
     dealtDamage = damageDealt;
     
  }
  //////////////GET METHODS //////////////////
  /**
   * getter method that returns the x coordinate of the specified enemy.
   * @return x: int
   */
  public String getName() {
    return name;
  }
  
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

//////////////SET METHODS //////////////////
  /**
   * a method that generates a new enemy and places it on a grid
   * @param grid
   * @param anEnemy
   */
  public void generateEnemy(String[][] grid,Enemy anEnemy) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[startXIndex][startYIndex] == path) {
          grid[x][y] = anEnemy.getName();
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
        if((this.x+1) < endXIndex+1 && grid[this.x+1][this.y].equals(path)) {
          if (anEnemy.getEnemyHealth() <= 0) {
            grid[this.x+1][this.y] = path;
          }
          grid[this.x+1][this.y] = anEnemy.getName();//moves character right
          grid[this.x][this.y] = path;
          if (anEnemy.getEnemyHealth() <= 0) {
            grid[this.x][this.y] = path;
          }
        }       
        else if ((this.y+1) < endYIndex+1 && grid[this.x][this.y+1].equals(path)) {
          if (anEnemy.getEnemyHealth() <= 0) {
            grid[this.x][this.y+1] = path;
          }
          grid[this.x][this.y+1] = anEnemy.getName();//moves character down
          grid[this.x][this.y] = path;
          if (anEnemy.getEnemyHealth() <= 0) {
            grid[this.x][this.y] = path;
          }
          reached = true;
        }
        else if ((this.x) == endXIndex && grid[endXIndex][endYIndex].equals(anEnemy.getName())) {
          grid[this.x][this.y] = path;
          attack(aPlayer);
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
   * method that attacks Player if an Enemy has crossed
   * @param aMap
   * @param aPlayer
   * @param anEnemy
   */
  public void attack(Player aPlayer) {
      aPlayer.takeDamage(dealtDamage);
      System.out.println("Y O U  H A V E  T A K E N  D A M A G E!!!!\n");
    }
  /**
   *  takeDamage has enemy take damage from tower.
   * @param damage
   * 
   */
  public void takeDamage(int damage) {
    this.enemyHealth -= damage;
  }
  /**
   * displays Enemy health as a string
   * 
   */
  public void displayHealth() {
    if (this.getEnemyHealth() > 0) {
      System.out.println(this.getName()+" HEALTH: "+this.getEnemyHealth()+"HP");
    }
  }
  /**
   * checks if the first enemy of the multiple enemy wave is dead.
   * @param grid
   * @param aPlayer
   */
  public void checkIfDead(String[][] grid,Player aPlayer) {
    if (this.isOnMap(grid)) {
      if(this.isDead()) {
        this.removeEnemy(grid);
        System.out.println(this.getName()+" has been killed.");
        aPlayer.gainMoney(this.moneyGained);
      }
    }
  }
  /**
   * checks if player is on map
   * @param grid
   * @return boolean
   */
  public boolean isOnMap(String[][] grid) {
    boolean onMap = false;
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[c][r].equals(this.getName())) {
          onMap = true;
        }
      }
    }
    return onMap;
  }
  /**
   * prompts to remove the enemy form the grid when enemy is dead.
   * @param grid
   */
  public void removeEnemy(String[][] grid) {
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[c][r].equals(this.getName())) {
          grid[c][r] = "_";
        }
      }
    }
  }
  /**
   * Checks if the LAST enemy of the wave is dead.
   * @return boolean
   */
  public boolean isDead() {
   return this.getEnemyHealth() <= 0;
  }
  /**
   * method that returns a boolean if an enemy has crossed the map
   * @param aMap
   * @param anEnemy
   * @return boolean
   */
  public boolean hasCrossed() {
    return this.getXCoord() > endXIndex;
  }
}


