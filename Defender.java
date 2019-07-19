import java.lang.Math;
import java.util.Scanner;


public class Defender extends Point {
  private int damage;
  private int price;
  private int xCoord;
  private int yCoord;
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
    xCoord = input.nextInt();
    //While loop to ensure
    while (xCoord < 0 || xCoord > 4) {
      System.out.println("Your X Value is Out of Range.\nPlease Enter a Value Between 0 and 4");
      xCoord = input.nextInt();
    }
    System.out.println("Enter a Y Value");
    yCoord = input.nextInt();
    while (yCoord < 0 || yCoord > 4) {
      System.out.println("Your Y Value is Out of Range.\nPlease Enter a Value Between 0 and 4");
      yCoord = input.nextInt();
     }
      for (int r = 0; r < grid.length; r++) {
        for(int c = 0; c < grid[r].length; c++) {
          if (grid[xCoord][yCoord] == "0") {
             grid[xCoord][yCoord] = "T";
          }
        }
      }
    }
  public int getPrice() {
    return price;
  }

  public double getDistance(Enemy anEnemy) {
    int otherX = anEnemy.x;
    int otherY = anEnemy.y;
    double deltaXsquared = Math.pow((double)(xCoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - otherY), 2.0);
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
