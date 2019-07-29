
public class Spirit extends Enemy{
  
  public Spirit(double x, double y) {
    super(x,y);
    health = 10;
    damage = 10;
    speed = 5.0;
    bounty = 10;
  }
  public void attack(Player aPlayer) {
    super.attack(aPlayer);
  }
}
