import java.lang.Math;

public class Defender extends Point {
  private int damage;
  private int price;
  private double range;
  
  public Defender(int x, int y, int aDamage, int aPrice, double aRange) {
      super(x, y);
      damage = aDamage;
      range = aRange;
      price = aPrice;
  }
    /**
    *method that generates tower
    *@param grid
    *
    *
    */
    public void generateTower(String[][] grid) {
    Scanner input = new Scanner(System.in);
    System.out.println("Place your tower");
    System.out.println("Enter an X Value");
    XCoord = input.nextInt();
    //While loop to ensure
    while (XCoord < 0 || XCoord > 4) {
      System.out.println("Your X Value is Out of Range.\nPlease Enter a Value Between 0 and 4");
      XCoord = input.nextInt();
    }
    System.out.println("Enter a Y Value");
    YCoord = input.nextInt();
    while (YCoord < 0 || YCoord > 4) {
      System.out.println("Your Y Value is Out of Range.\nPlease Enter a Value Between 0 and 4");
      YCoord = input.nextInt();
     }
      for (int r = 0; r < grid.length; r++) {
        for(int c = 0; c < grid[r].length; c++) {
          if (grid[XCoord][YCoord] == "0") {
             grid[XCoord][YCoord] = "T";        
          } 
        } 
      }
    }
  public int getPrice() {
    return price;
  }
  
  public double getDistance(Enemy anEnemy) {
    int otherX = anEnemy.getXCoord();
    int otherY = anEnemy.getYCoord();
    double deltaXsquared = Math.pow((double)(xcoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(ycoord - otherY), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }

  public boolean isWithinRange(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= range;
  }
  
  public void attack(Enemy anEnemy) {
    if (isWithinRange(anEnemy)){
      anEnemy.takeDamage(damage);
    }
  }
  
  public String toString() {
    return "Price: "+ price + "\nDamage: " + damage +"\nRange: " + range; 
  }
  
}