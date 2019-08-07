package enemies;
import game.Player;
import parents.Enemy;

public class Lava extends Enemy{
  public Lava(double x, double y) {
    super(x,y);
    maxHealth = 80;
    health = 80;
    damage = 20;
    speed = 0.5;
    bounty = 3;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
