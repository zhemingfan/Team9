package parents;
import java.util.Scanner;
import java.util.ArrayList;

public class Tower extends Point{
  
  protected double range;
  protected int damage;
  protected int price;
  protected Enemy target;
  public Tower() {
	  
  }
  
  public Tower(double x, double y) {
    super(x,y);
    damage = 0;
    range = 100;
    
  }
  
  public int getPrice() {
    return price;
  }
  
  private boolean enemyIsWithinRange(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= range;
  }
  
  /**
   * Return the first enemy that is within range among all current enemies.
   * !!!NEEDS IMPROVEMENT SO THAT THE DEFENDER DOESN"T CHANGE TARGET EVERY FRAME.
   * 
   * @param currentEnemies
   * @return target: Enemy
   */
  public Enemy getTarget() {
	  return this.target;
  }
  public Enemy findTarget(ArrayList<Enemy> currentEnemies) {
    this.target = null;
    for(Enemy item: currentEnemies) {
      if (enemyIsWithinRange(item)) {
        this.target = item;
        break;
      }
    };
    return target;
  }
  
  public void attack(Enemy anEnemy) {
    if (anEnemy != null) anEnemy.takeDamage(damage);
  }
  
  public String toString() {
	  return "Range: " + range + "\n" + "Damage: " + damage + "\n" + "Price: " + price + "\n";
  }
  
}
