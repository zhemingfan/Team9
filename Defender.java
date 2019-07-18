
public class Defender {
  private int xCoord, yCoord;
  private int damage;
  private int price;
  private double range;
  
  public Defender(int x, int y, int aDamage, int aPrice, double aRange) {
      xCoord = x;
      yCoord = y;
      damage = aDamage;
      range = aRange;
      price = aPrice;
  }
  
  
  
  public double getDistance(Enemy anEnemy) {
    int otherX = anEnemy.getXCoord();
    int otherY = anEnemy.getYCoord();
    double deltaXsquared = Math.pow((double)(xcoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(ycoord - otherY), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }

  
  
  public void attack(Enemy anEnemy) {
    if (this.getDistance(anEnemy) <= range){
      anEnemy.takeDamage(damage);
    }
    
  }
  
}