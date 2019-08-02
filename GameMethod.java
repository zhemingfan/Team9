import java.util.Scanner;
/** TEXT BASED VERSION
 * GAME METHOD
 * @author Team 9
 */
public class GameMethod {
    private static Scanner input = new Scanner(System.in); 
  /**
   * This class incorporates WaveTextBased class that implements all the logic classes
   * and is called in this method.
   */
  public void fireForceNo9() {
    ////////////Opening Title/////////
    System.out.println("\n\n\n"+
        "       ___ ___ ___ ___       \n" + 
        "      | __|_ _| _ \\ __|     \n" + 
        "      | _| | ||   / _|       \n" + 
        "    __|_|_|___|_|_\\___|__   \n" + 
        "   | __/ _ \\| _ \\/ __| __| \n" + 
        "   | _| (_) |   / (__| _|    \n" + 
        "   |_|_\\___/|_|_\\\\___|___|\n" + 
        "     | \\| |/ _ \\   / _ \\  \n" + 
        "     | .` | (_) |  \\_, /    \n" + 
        "     |_|\\_|\\___(_)  /_/    \n\n"+
        "     text based edition\n\n"); 
    //////////// Press Enter //////////
    System.out.println("        Press Enter");
    input.nextLine();
    System.out.println("        HOW TO PLAY:\n\n"
        + "  You are Fire Force No. 9,\n"
        + " the heroic firefighting unit\n"
        + " of Foo-Ville responsible of\n"
        + "   extinguishing the nasty\n"
        + "fire, lava and spirit enemies\n "
        + "sent from the malicious fire\n"
        + "       demon, Snarol.\n\n"
        + "       (Press Enter)\n");
    input.nextLine();
    System.out.println(""
        + " You must protect the town from\n"
        + "  a scortching frenzy by using\n "
        + "  everything at your defense:\n "
        + "     water and ice towers.\n\n "
        + "  Foo-Ville Depends on you.\n\n"
        + "       (Press Enter)\n\n");
    input.nextLine();
    
    
    
    ////// Generate Enemy Wave ///////
    WaveTextBased game = new WaveTextBased();   
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
          fireForceNo9(); //call main game method recursively
        }
      }
    }
  }
}
