package enemies;
import game.Player;
import parents.Enemy;

public class Lava extends Enemy{
	
  public Lava(double x, double y) {
    super(x,y);
    maxHealth = 10;
    health = 10;
    damage = 10;
    speed = 5.0;
    bounty = 10;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
