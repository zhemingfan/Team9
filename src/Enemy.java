import java.util.ArrayList;
import java.util.Date;
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
 
  public String getName() {
	    return name;
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

  public Rectangle getRect(){
    return enemyRect;
  }

  public double getSpeed() {
    return speed;
  }

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

  public double getMaxHealth() {
	  return maxHealth;
  }
  
  public void setMaxHealth(int value) {
	  this.maxHealth = value;
  }
  
  public String healthToString(Enemy anEnemy) {
    String eHealth = Integer.toString(anEnemy.getEnemyHealth());
    return eHealth;
  }
  
  

/*
  public static void main(String[] args) {
	  ArrayList<Enemy> list = new ArrayList<Enemy>(0);
	  long start = new Date().getTime();
	  int counter = 0;
	  Map map = new Map();
	  map.generateGrid();
	  Point[] checkPoints = map.getCheckPoints(50, 50);
	  while(true) {
		  long current = new Date().getTime();
		  Enemy testEnemy = new Enemy(50,0);
		  if ( (current - start) % 1000 == 0) {
			  counter += 1;
			  testEnemy = new Enemy(50,0);
			  testEnemy.attachPath(checkPoints);
			  list.add(testEnemy);
			  System.out.println("/n " +counter + " e spawned");

			  //for (int i = 0; i < list.size(); i ++) {
				  list.get(0).advance(1);
				  System.out.println( " : "+list.get(0).toString() + " " + list.get(0).checkPointIndex);
			  //}
		  }

		  if ( current - start == 20000) {
			  break;
		  }

	  }
	  */
  }

