
public class Defender {
  private int xCoord, yCoord;
  private int damage;
  private int price;
  private double range;
  
  public Defender(int x, int y, ) {
    
  }
  
  
  public double getDistance() {
    int otherX = other.getXCoord();
    int otherY = other.getYCoord();
    double deltaXsquared = Math.pow((double)(xcoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(ycoord - otherY), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }
  
  public attack() {
    
  }
  
}