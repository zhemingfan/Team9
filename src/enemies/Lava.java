package enemies;
import parents.Enemy;
import game.Player;
/** TEXT BASED VERSION
 * Sub Class of Enemy
 * @author Team 9
 */
public class Lava extends Enemy{
  
  /** Creates new enemy
   * @param i enemy number
   */
  public Lava(int i) {
    super(1,0);
    health = 25;
    damage = 20;
    speed = 1;    // Do not change this value
    bounty = 20;
    name = "L"+(i+1);
  }
  
  /** Overrides method from parent class.
   * @param grid gridMap from Map Class
   */
  @Override
  public void spawnEnemyTextBased(String[][] grid) {
    System.out.println("LAVA enemy "+getName()+" has appeared\n");
    super.spawnEnemyTextBased(grid);
  }
  
  /** Overrides method from parent class.
   * @param aPlayer Player Class Object
   */
  @Override
  public void attackTextBased(Player aPlayer) {
    System.out.println("LAVA enemy has attacked");
    super.attackTextBased(aPlayer);
  }
}
