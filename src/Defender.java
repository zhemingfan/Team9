import java.util.Scanner;
import java.util.ArrayList;

public class Defender extends Point{
  
  private double range;
  private int damage;
  private int price;


  public Defender(double x, double y) {
    super(x,y);
    
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
  
  public Enemy findTarget(ArrayList<Enemy> currentEnemies) {
    Enemy target = null;
    for(Enemy item: currentEnemies) {
      if (enemyIsWithinRange(item)) {
        target = item;
        break;
      }
    };
    return target;
  }
  
  public void attack(Enemy anEnemy) {
    if (anEnemy != null) anEnemy.takeDamage(damage);
  }
  
}
