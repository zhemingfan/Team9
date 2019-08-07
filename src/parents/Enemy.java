package parents;

import java.util.ArrayList;
import java.util.Date;

import game.Player;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

public class Enemy extends Point{
  protected int damage;
  protected int bounty;
  protected int health;
  protected double speed;
  private Point[] path;
  private int checkPointIndex = 0;
  private Rectangle enemyRect = new Rectangle(50, 50);
  private String name;
  protected int maxHealth;

  /**
   * Constructor
   * @param enemyHealth
   * @param x
   * @param y
   *
   */

//This constructor is currently unused
  public Enemy(double x, double y) {
     super(x, y);
     this.maxHealth = 10;
     damage = 10;
     health = 40;
     bounty = 10;
     speed = 10;
  }

//Constructor used for test enemies. The speed value is used in the runPath function.
//The smaller the value of speed, the faster it moves
  public Enemy(int someDamage, double aSpeed, int someHealth){
    damage = someDamage;
    speed = aSpeed;
    health = someHealth;
  }
  /**
   * Returns the enemy's name.
   * @return the enemy's name
   */
  public String getName() {
	    return name;
  }
  
  /**
   * Returns the enemy's bounty (how much the player gets for killing the enemy).
   * @return
   */
  public int getBounty() {
	return bounty;
}

  /**
   * Return the enemy's current health.
   * @return the enemy's current health
   */
  public int getEnemyHealth() {
    return health;
  }

  /**
   * Return the enemy's damage to the player.
   * @return the enemy's damage to the player
   */
  public int getDamage() {
	 return damage;
  }

  
  public Rectangle getRect(){
    return enemyRect;
  }

  /**
   * Return the enemy's speed.
   * @return the enemy's speed
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Checks if the enemy is dead.
   * @return whether the enemy is dead
   */
  public boolean isKilled() {
    return health <= 0;
  }
  /**
   * Deals damage to the player if an Enemy has got the final check point.
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
  
  /**
   * Function specifically for the wind-trap tower. Freezes a single enemy by reducing it's speed.
   * @param debuff
   */
  public void slowDown(double debuff) {
	  speed -= debuff;
  }

  /**
   * Update the enemy's location. If an enemy has reached a check point, this increases the checkPointIndex variable by 1.
   * After this, the enemy will start heading towards the next check point.
   * @param checkPoints
   * @param elapsedTime
   */

  public void attachPath(Point[] checkPoints) {
	  path = checkPoints;
  }

  public void advance(double elapsedTime) {
	  if ( !this.isKilled() ) {
		int LASTCHECKINDEX = path.length - 1;
	    double delta = speed * elapsedTime;

	    if (checkPointIndex <= LASTCHECKINDEX) {
	    	Point currentCP = path[checkPointIndex];
	    	if (!this.hasReached(currentCP) ) {
	  			if (this.getXCoord() - currentCP.getXCoord() < 0) {
	  			     super.moveRight(delta);
	  			};
	  			if (this.getXCoord() - currentCP.getXCoord() > 0) {
	  			     super.moveLeft(delta);
	  			};
	  			if (this.getYCoord() - currentCP.getYCoord() < 0) {
	  					super.moveDown(delta);
	  			};
	  			if (this.getYCoord() - currentCP.getYCoord() > 0) {
	  					super.moveUp(delta);
	  			};
	    	} else {
				checkPointIndex += 1;
	    	}
	    }
	  }
  }

  /**
   * Returns the enemy's maximum health.
   * @return the enemy's maximum health
   */
  public double getMaxHealth() {
	  return maxHealth;
  }
  
  /**
   * Sets the enemy's maximum health to given value
   * @param value
   */
  public void setMaxHealth(int value) {
	 if (value >= 0) this.maxHealth = value;
  }
  
  /**
   * Returns the specified enemy's health in String format.
   * @param anEnemy
   * @return the specified enemy's health in String format.
   */
  public String healthToString(Enemy anEnemy) {
    String eHealth = Integer.toString(anEnemy.getEnemyHealth());
    return eHealth;
  }

  }

