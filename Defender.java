import java.util.Scanner;
public class Defender{
  int archerPrice;
  int archerRange;
  int archerDamage;
  int sniperPrice;
  int sniperRange;
  int sniperDamage;
  int xCoord;
  int yCoord;


  public Defender() {
    archerPrice = 5;
    archerRange = 1;
    archerDamage = 2;
    sniperPrice = 10;
    sniperRange = 2;
    sniperDamage = 4;

  }
    /**
    *method that generates tower
    *@param grid
    *
    *
    */
    public void generateTower(Player aPlayer,String[][] grid,Scanner input) {
      defenderSelection(aPlayer, input);
      if (input.nextLine().equals("A")) {
        placeArcher(grid,input);
      }
      else if (input.nextLine().equals("S")) {
        placeSniper(grid,input);
      }
    }

  public void defenderSelection(Player aPlayer, Scanner input) {
    System.out.println("SELECT YOUR DEFENDER:"
                 + "\nCASH: $"+aPlayer.getMoney()
                 + "\n\nEnter (S) for Sniper ($10)"
                 + "\n\nEnter (A) for Archer ($5)"
                 + "\n\nor press enter.");
  }
  /**
   * method that asks player if they want to place tower or skip before the next
   * iteration.
   * @param input
   * @return String
   */
  public String selection(Scanner input) {
    System.out.println("P to place a tower, Enter to continue");
    String enter = input.nextLine();
    if(input.nextLine() == "exit"){
      System.exit(0); //Exits the game without having to close the console
    }
    return enter;
  }
  public void selectCoordinates(Scanner input) {
    System.out.println("Enter X coordinate:");
    xCoord = input.nextInt();
    //While loop to ensure
    while (xCoord < 0 || xCoord > 9) {
      System.out.println("Your X coordinate is Out of Range.\nPlease Enter a Value Between 0 and 9");
      xCoord = input.nextInt();
    }
    System.out.println("Enter Y coordinate:");
    yCoord = input.nextInt();
    while (yCoord < 0 || yCoord > 9) {
      System.out.println("Your Y coordinate is Out of Range.\nPlease Enter a Value Between 0 and 9");
      yCoord = input.nextInt();
     }
  }
  /**
   * method that places sniper defender on grid.
   * @param grid
   * @param input
   */
  public void placeSniper(String[][] grid,Scanner input) {
    System.out.println("You bought Sniper!");
    selectCoordinates(input);
    while (containsPath(grid,xCoord,yCoord)) {
      System.out.println("Your coordinates contain a path\nPlease enter valid X and Y coordinates.\n");
      selectCoordinates(input);
    }
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[xCoord][yCoord] == "/") {
          grid[xCoord][yCoord] = "S";
        }
      }
    }
  }
  /**
   * method that places archer defender on grid.
   * @param grid
   * @param input
   */
  public void placeArcher(String[][] grid,Scanner input) {
    System.out.println("You bought Archer!");
    selectCoordinates(input);
    while (containsPath(grid,xCoord,yCoord)) {
      System.out.println("Your coordinates contain a path\nPlease enter valid X and Y coordinates.\n");
      selectCoordinates(input);
    }
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[xCoord][yCoord] == "/") {
          grid[xCoord][yCoord] = "A";
        }
      }
    }
  }
  /**
   * method that checks if x and y coordinates contain "-" path.
   * @param grid
   * @param xCoord
   * @param yCoord
   * @return boolean
   */
  public boolean containsPath(String[][] grid,int xCoord, int yCoord) {
    boolean result = false;
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; c++) {
        if (grid[xCoord][yCoord].equals("-")) {
          result = true;
        }
      }
    }
    return result;
  }

  public int getDistance(Enemy anEnemy) {
    int otherX = anEnemy.getXCoord();
    int otherY = anEnemy.getYCoord();
    double deltaXsquared = Math.pow((double)(xCoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - otherY), 2.0);
    return (int)(Math.sqrt(deltaXsquared + deltaYsquared));
  }

  public boolean enemyIsWithinRangeOfArcher(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= this.archerRange;
  }
  public boolean enemyIsWithinRangeOfSniper(Enemy anEnemy) {
    return this.getDistance(anEnemy) <= this.sniperRange;
  }

  public void attack(Enemy anEnemy) {
    if (enemyIsWithinRangeOfArcher(anEnemy)){
      anEnemy.takeDamage(this.archerDamage);
    }
    else if (enemyIsWithinRangeOfSniper(anEnemy)) {
      anEnemy.takeDamage(this.sniperDamage);
    }
  }
}
