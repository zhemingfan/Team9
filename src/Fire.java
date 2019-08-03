
public class Fire extends Enemy{
	
  public Fire(double x, double y) {
    super(x,y);
    maxHealth = 50;
    health = 50;
    damage = 10;
    speed = 10.0;
    bounty = 10;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
