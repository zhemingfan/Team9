import java.util.ArrayList;

public class Enemy extends Point{
  private int damage;
  protected int bounty;
  protected int health;
  private double speed;
  private int checkPointIndex = 0;
  
  /**
   * Constructor
   * @param enemyHealth
   * @param x
   * @param y
   * 
   */
  public Enemy(double x, double y) {
     super(x, y);
     damage = 10;
     health = 50;
     bounty = 10;
  }

  public int getBounty() {
	return bounty;
}

  public int getEnemyHealth() {
    return health;
  }

  public int getDamage() {
	 return damage;
  }
  
  public double getSpeed() {
    return speed;
  }

////////////// ATTACK/TAKE DAMAGE METHODS //////////////////
  
  public boolean isKilled() {
    return health <= 0;
  }
  /**
   * method that attacks Player if an Enemy has crossed
   * @param aMap
   * @param aPlayer
   * @param anEnemy
   */
  public void attack(Player aPlayer) {
      aPlayer.takeDamage(damage);
    }
    
  /**
   * function takeDamage has enemy take damage from tower.
   * @param damage
   *
   */
  public void takeDamage(int damage) {
    this.health -= damage;
  }
  
  public void advance(Point[] checkPoints, double elapsedTime) {
    int LASTCHECKINDEX = checkPoints.length;
    
    double delta = speed * elapsedTime;
    
    Point currentCP = checkPoints[checkPointIndex];
  			
  	if (this.hasReached(currentCP) ) { ///basically you're comparing the current checkpoint and the next checkpoint.. if its higher than change y if its same height but different x, change x until you get there 
  			if (checkPointIndex < LASTCHECKINDEX) {
  				checkPointIndex += 1;
  	} else {
  		  if (getXCoord() - currentCP.getXCoord() < 0) {
  			     super.moveRight(delta);
  			};
  			if (getXCoord() - currentCP.getXCoord() > 0) {
  			     super.moveLeft(delta);
  			};
  			if (getYCoord() - currentCP.getYCoord() < 0) {
  					super.moveDown(delta);
  			};
  			if (getYCoord() - currentCP.getYCoord() > 0) {
  					super.moveUp(delta);
  			};
  		};
  	}
  }
  
  public String healthToString(Enemy anEnemy) {
    String eHealth = Integer.toString(anEnemy.getEnemyHealth());
    return eHealth;
  }
    
}
