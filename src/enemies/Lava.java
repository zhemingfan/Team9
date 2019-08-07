package enemies;
import game.Player;
import parents.Enemy;

public class Lava extends Enemy{
  public Lava(double x, double y) {
    super(x,y);
    maxHealth = 100;
    health = 100;
    damage = 20;
    speed = 0.5;
    bounty = 5;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
