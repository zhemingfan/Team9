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
  /**
   * Returns the tower's price.
   * @return The tower's price
   */
  public int getPrice() {
    return price;
  }

  /**
   * Checks if an enemy is within the tower's attack range.
   * @param anEnemy
   * @return if an enemy is within the tower's attack range
   */
  private boolean enemyIsWithinRange(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= range;
  }

  /**
   * Returns the tower's current enemy.
   * @return the tower's current enemy
   */
  public Enemy getTarget() {
	  return this.target;
  }

  /**
   * Return the first enemy that is within range among all current enemies.
   * @param currentEnemies
   * @return target: Enemy
   */
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

  /**
   * Attacks the given enemy.
   * @param anEnemy
   */
  public void attack(Enemy anEnemy) {
    if (anEnemy != null) anEnemy.takeDamage(damage);
  }

  public String toString() {
	  return "Range: " + range + "\n" + "Damage: " + damage + "\n" + "Price: " + price + "\n";
  }

}
