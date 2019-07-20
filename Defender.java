import java.lang.Math;
import java.util.Scanner;


public class Defender extends Player{
  private int damage;
  private int price;
  private int xCoord;
  private int yCoord;
  private int range;

  public Defender(int aDamage, int aPrice, int aRange) {
      super();
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
    public void generateTower(Player aPlayer,String[][] grid) {
    Scanner input = new Scanner(System.in);
    if (defenderSelection(input).equals("A")) {
      System.out.println("You bought Archer!");
      aPlayer.buyDefense(5);
      placeArcher(grid,input);
    }

    else if (defenderSelection(input).equals("S")) {
      System.out.println("You bought Sniper!");
      aPlayer.buyDefense(10);
      placeSniper(grid,input);
    }

    }
  public String defenderSelection(Scanner input) {
    System.out.println("S E L E C T  Y O U R  D E F E N D E R:"
                 + "\n\n     Enter (S) for Sniper ($10)"
                 + "\n\n     Enter (A) for Archer ($5 )");
    String defense = input.nextLine();
    return defense;
  }
  /**
   * method that asks player if they want to place tower or skip before the next
   * iteration.
   * @param input
   * @return String
   */
  public String selection(Scanner input) {
    System.out.println("(P)lace Tower or (S)kip?");
    String enter = input.nextLine();
    return enter;
  }
  
  public void selectCoordinates(Scanner input) {
    System.out.println("Enter X coordinate:");
    xCoord = input.nextInt();
    //While loop to ensure
    while (xCoord < 0 || xCoord > 4) {
      System.out.println("Your X coordinate is Out of Range.\nPlease Enter a Value Between 0 and 4");
      xCoord = input.nextInt();
    }
    System.out.println("Enter Y coordinate:");
    yCoord = input.nextInt();
    while (yCoord < 0 || yCoord > 4) {
      System.out.println("Your Y coordinate is Out of Range.\nPlease Enter a Value Between 0 and 4");
      yCoord = input.nextInt();
     }
  }

  public void placeSniper(String[][] grid,Scanner input) {
    selectCoordinates(input);
    while (containsPath(grid,xCoord,yCoord)) {
      System.out.println("Your coordinates contain a path\nPlease enter valid X and Y coordinates.\n");
      selectCoordinates(input);
    }
      for (int r = 0; r < grid.length; r++) {
        for(int c = 0; c < grid[r].length; c++) {
          if (grid[xCoord][yCoord] == "0") {
             grid[xCoord][yCoord] = "S";
          }
        }
      }
  }

  public void placeArcher(String[][] grid,Scanner input) {
    selectCoordinates(input);
    while (containsPath(grid,xCoord,yCoord)) {
      System.out.println("Your coordinates contain a path\nPlease enter valid X and Y coordinates.\n");
      selectCoordinates(input);
    }
      for (int r = 0; r < grid.length; r++) {
        for(int c = 0; c < grid[r].length; c++) {
          if (grid[xCoord][yCoord] == "0") {
             grid[xCoord][yCoord] = "A";
          }
        }
      }
  }

  public boolean containsPath(String[][] grid,int xCoord, int yCoord) {
    boolean result = false;
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[xCoord][yCoord].equals("-") || grid[xCoord][yCoord].equals("|")) {
          result = true;
        }
      }
    }
    return result;
  }
  public int getPrice() {
    return price;
  }

  public int getDistance(Enemy anEnemy) {
    int otherX = anEnemy.getXCoord();
    int otherY = anEnemy.getYCoord();
    double deltaXsquared = Math.pow((double)(xCoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - otherY), 2.0);
    return (int)(Math.sqrt(deltaXsquared + deltaYsquared));
  }

  public boolean enemyIsWithinRange(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= range;
  }

  public void attack(Player aPlayer, Enemy anEnemy,String[][] grid) {
    if (enemyIsWithinRange(anEnemy)){
      anEnemy.takeDamage(aPlayer,grid,damage);
    }
  }

  public String toString() {
    return "Price: "+ price + "\nDamage: " + damage +"\nRange: " + range;
  }

}
