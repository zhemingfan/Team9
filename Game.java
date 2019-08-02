import java.util.Scanner;
/** TEXT BASED VERSION
 * MAIN GAME METHOD
 * @author Team 9
 */
public class Game{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); 
    ////////////Opening Title/////////
    System.out.println("\n\n\nF I R E  F O R C E  NO. 9\n\n\n"); 
    //////////// Press Enter//////////
    System.out.println("     Press Enter"); 
    input.nextLine();
    
    
    ////// Generate Enemy Wave ///////
    MainGameTextBased game = new MainGameTextBased();   
    int i = 1; // Wave Count
    boolean gameon = true; // set game to true

    
    ////////////Generate Map//////////
    Map gameMap = new Map();
    String[][] grid = gameMap.getGridMap();
    Point[] coords = gameMap.getCheckPoints();
    
    
    ////////// Generate Player ///////
    Player player = new Player(100,100);
    
    
    ///////////// GAME ON ///////////
    while (gameon) {
      game.enemyWave(player,grid,coords,gameMap,gameon,i);
      i++;
      if (player.isKilled()) {
        System.out.println("Want to play again?\n(Y) or (N)");
        if (input.nextLine().toUpperCase().equals("N")) {
          gameon = false;
        }
        else {
          gameon = true;
          main(args); //call main method recursively
        }
      }
    }
    input.close();
  }
}