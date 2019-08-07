package parents;
import game.Player;
/** TEXT BASED VERSION
 * Enemy Super Class
 * Moves through the Map and attacks Player object.
 * Sub Classes: Fire, Spirit, Lava
 * Extends Point Class for xCoord, yCoord.
 * @author Team 9
 */
public class Enemy extends Point{
   protected int damage; // Enemy damage inflicted on Player
   protected int health;
   protected int bounty;
   protected int speed;
   protected String name;
   private Point[] path;
   private int checkPointIndex = 0;

  /** Enemy constructor takes in x,y coordinates and sets the xCoord and yCoord.
   * @param x xCoord from Point Class
   * @param y yCoord from Point Class
   */
  public Enemy(int x, int y) {
    super(x,y);
  }
  
  /** 
   * @return name Name of Enemy
   */
  public String getName() {
    return name;
  }
  
  /**
   * @return speed Speed of Enemy
   */
  public int getSpeed() {
    return speed;
  }
  
  /**
   * @return damage Damage amount of Enemy
   */
  public int getDamage() {
    return damage;
  }

  /**
   * @return health Health of Enemy
   */
  public int getEnemyHealth() {
    return health;
  }
  /**
   * @return bounty Player Reward if Enemy is Killed
   */
  public int getBounty() {
    return bounty;
  }
  
  /** Takes the list of checkpoints from Map Class and creates an instance
   * for the specified enemy.
   * @param checkPoints List of checkpoints from Map Class
   */
  public void attachPathTextBased(Point[] checkPoints) {
    path = checkPoints;
  }
  
  /** Spawns the enemy on the Map.
   * @param grid gridMap from Map Class
   */
  public void spawnEnemyTextBased(String[][] grid) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        grid[getYCoord()][getXCoord()] = getName();
      }
    }   
  }
    
  /**
   * Update the enemy's location. If an enemy has reached a check point, this increases the checkPointIndex variable by 1.
   * After this, the enemy will start heading towards the next check point.
   */
  public void advanceTextBased() {
    if ( !this.isKilled() ) {
      int LASTCHECKINDEX = path.length - 1;
      int speed = getSpeed();     
      
      if (checkPointIndex <= LASTCHECKINDEX) {
        Point currentCP = path[checkPointIndex];
        if (!this.hasReached(currentCP) ) {
          if (this.getXCoord() - currentCP.getXCoord() < 0) {
               this.moveRight(speed);
          };
          if (this.getXCoord() - currentCP.getXCoord() > 0) {
               this.moveLeft(speed);
          };
          if (this.getYCoord() - currentCP.getYCoord() < 0) {
               this.moveDown(speed);
          };
          if (this.getYCoord() - currentCP.getYCoord() > 0) {
               this.moveUp(speed);
          };
        } 
        else {
          checkPointIndex += 1;
        }
      }
    }
  }
  
  /** Relocates the enemy after advance is called.
   * @param grid gridMap from Map Class
   */
  public void relocateTextBased(String[][] grid) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[r][c].equals(getName())) {
          grid[r][c] = "-";
          grid[getYCoord()][getXCoord()]=getName();
        }
      }
    }
  }
  
  /** Attacks the player once the enemy has reached the end checkpoint.
   * @param aPlayer Player Class Object
   */
  public void attackTextBased(Player aPlayer) {
      aPlayer.takeDamage(getDamage());

  }
  
  /** Enemy takes damage from tower.
   * @param damage Damage from Tower
   */
  public void takeDamage(int damage) {
    this.health -= damage;
  }
  
  /** Displays enemy health as a string.
   */
  public void displayHealth() {
    if (this.getEnemyHealth() > 0) {
      System.out.println(this.getName()+" HEALTH: "+this.getEnemyHealth()+"HP");
    }
  }
  
  /** Boolean that checks if enemy is on the map.
   * @param grid gridMap from Map Class
   * @return boolean If enemy is on map
   */
  public boolean isOnMap(String[][] grid) {
    boolean exists = false;
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[r][c].equals(getName())) {
          exists = true;
        }
      }
    }
    return exists;
  }
  
  /** Removes the enemy from Map if enemy has reached end checkpoint or is killed by tower.
   * @param grid gridMap from Map Class
   */
  public void removeEnemy(String[][] grid) {
    for(int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        grid[getYCoord()][getXCoord()] = "-";
      }
    }
  }
  
  /** Boolean that checks if enemy health is zero.
   * @return boolean If enemy health is zero.
   */
  public boolean isKilled() {
   return health <= 0;
  }

}


