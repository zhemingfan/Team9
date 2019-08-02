/** TEXT BASED VERSION
 * Sub Class of Tower
 * @author Team 9
 */
public class TowerWater extends Tower{
  
  /** Once created prompts user to place tower on map.
   * @param grid gridMap from Map Class
   */
  public TowerWater(String[][] grid) {
    super();
    damage = 5;
    range = 2;
    price = 10;
    name = "W";
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
    System.out.println("Place your Water Tower");
    super.placeTower(grid);
  }
}
