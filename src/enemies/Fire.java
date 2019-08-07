package enemies;
import parents.Enemy;
import game.Player;
/** TEXT BASED VERSION
 * Sub Class of Enemy
 * @author Team 9
 */
public class Fire extends Enemy{
  
  /** Creates new enemy
   * @param i enemy number
   */
  public Fire(int i) {
    super(1,0);
    health = 15;
    damage = 10;
    speed = 1;   // Do not change
    bounty = 10;
    name = "F"+(i+1);
  }
  
  /** Overrides method from parent class.
   * @param grid gridMap from Map Class
   */
  @Override
  public void spawnEnemyTextBased(String[][] grid) {
    System.out.println("FIRE enemy "+getName()+" has appeared\n");
    super.spawnEnemyTextBased(grid);
  }
  
  /** Overrides method from parent class.
   * @param aPlayer Player Class Object
   */
  @Override
  public void attackTextBased(Player aPlayer) {
    System.out.println("FIRE enemy has attacked");
    super.attackTextBased(aPlayer);
  }
}
