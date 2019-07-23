import java.util.Scanner;

public class Game{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    ////////////Opening Title////////////
    System.out.println("T O W E R  D E F E N S E\n"); 
    
    //////////// Press Enter////////////
    System.out.println("     Press Enter"); 
    input.nextLine();
    
    //////////// Generate Enemy Wave ////////////
    Wave enemyWave = new Wave();   
    int i = 1;
    boolean gameon = true;
    
    ////////////Generate Map////////////
    Map gameMap = new Map();
    String[][] grid = gameMap.generateGrid();
    
    //////////// Display Map ////////////
    gameMap.printGrid(grid); 
    
    //////////// Generate Player ////////////
    Player player = new Player(100,100,grid);
    
////////////////////////GAME ON////////////////////////
    while (gameon) {
      enemyWave.towerDefense(player,enemyWave,grid,gameon,i);
      i++;
      if (player.isKilled()) {
        System.out.println("YOU ARE DEAD. GAME OVER!");
        System.out.println("Want to play again?\n(Y) or (N)");
        if (input.nextLine().equals("N")) {
          gameon = false;
        }
        else {
          gameon = true;
          main(args); //call main method recursively
        }
      }
      if (i == 4) {
        gameon = false;
      }
    }
    input.close();
  }
///////////////////////////////////////////////////////
}