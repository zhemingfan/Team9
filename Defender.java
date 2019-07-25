import java.util.Scanner;
import java.util.ArrayList;
//tower class
//target it then attack it 
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
