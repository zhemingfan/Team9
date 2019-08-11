package enemies;
import game.Player;
import parents.Enemy;

public class Spirit extends Enemy{

  public Spirit(double x, double y) {
    super(x,y);
    maxHealth = 20;
    health = 20;
    damage = 5;
    speed = 2;
    bounty = 1;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
