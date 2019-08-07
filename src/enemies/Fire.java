package enemies;
import game.Player;
import parents.Enemy;

public class Fire extends Enemy{

  public Fire(double x, double y) {
    super(x,y);
    maxHealth = 50;
    health = 50;
    damage = 10;
    speed = 1.25;
    bounty = 3;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
