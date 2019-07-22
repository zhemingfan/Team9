import java.util.Scanner;
/**
 * Main class that calls all other classes and their methods.
 * 
 *
 */
public class Game{
  Scanner input = new Scanner(System.in);
  
  /**
   * main tower defense function
   * @param aPlayer
   * @param enemyWave
   * @param grid
   * @param gameon
   * @param i
   */
  public void towerDefense(Player aPlayer,Wave enemyWave,String[][] grid,boolean gameon,int i) { 

        ////////////ENEMY WAVE ////////////
        enemyWave.generateWave(aPlayer,grid,i);
        if(aPlayer.hasSurvived()) {
          if (i < 3) {
          System.out.println("Proceed to Wave "+(i+1));
          }
        input.nextLine();
        }
        if (i == 3) {
          System.out.println("You have completed all 3 waves.\nThis concludes Demo 1.");
          gameon = false;
        }
      }
  
    
  /**
   * The main method for tower defense game.
   * @param args
   */
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
    Game towerDefense = new Game();
    while (gameon) {
      towerDefense.towerDefense(player,enemyWave,grid,gameon,i);
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

}