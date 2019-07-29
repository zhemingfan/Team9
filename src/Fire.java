
public class Fire extends Enemy{
  
  public Fire(double x, double y) {
    super(x,y);
    health = 10;
    damage = 10;
    speed = 15.0;
    bounty = 10;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
