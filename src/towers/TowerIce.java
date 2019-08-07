package towers;
import parents.Tower;
import parents.Enemy;
/** TEXT BASED VERSION
 * Sub Class of Tower
 * @author Team 9
 */
public class TowerIce extends Tower{
  
  /** Once created prompts user to place tower on map.
   * @param grid gridMap from Map Class
   */
  public TowerIce(String[][] grid) {
    super();
    damage = 2;
    range = 2;
    price = 10;
    name = "I";
    placeTower(grid);
  }
  
  /** Overrides method from parent class.
   * @param anEnemy Enemy Class Object
   */
  @Override
  public void attack(Enemy anEnemy) {
    super.attack(anEnemy);
  }
  
  /** Overrides method from parent class.
   * @param grid gridMap from Map Class
   */
  @Override
  public void placeTower(String[][] grid) {
    System.out.println("Place your Ice Tower");
    super.placeTower(grid);
  }
}
