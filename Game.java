import java.util.Scanner;
/**
 * Main class that calls all other classes and their methods.
 * 
 *
 */
public class Game{
  /**
   * main
   * @param args
   */

  public static void main(String[] args) { 
    
    
    ////////////Opening Title////////////
    System.out.println("T O W E R  D E F E N S E\n");
    
    
    //////////// Press Enter////////////
    Scanner input = new Scanner(System.in);
    System.out.println("Press Enter");
    
    
    ////////////Generate Map////////////
    String enter = input.nextLine();
    Map gameMap = new Map();
    
    
    //////////// Generate Enemy Wave ////////////
    Wave enemyWave = new Wave();
    
    //////////// Generate Player ////////////
    Player player = new Player(10,10);
    
    ////////////Generate Defense////////////
    Defender defense = new Defender(10,100,1);
    
    //////////// Generate Map ////////////
    String[][] grid = gameMap.generateGrid();
    gameMap.printGrid(grid);
    
    //////////// Select Tower ////////////
    defense.generateTower(grid);
    //p.selectTower(grid,p);
    
    //////////// ENEMY WAVE 1 ////////////
    enemyWave.wave1(player,gameMap,grid,defense,input);
    
    //////////// ENEMY WAVE 2 ////////////
    //enemyHoard.wave2(player,gameMap,grid,defense,input);
    
    //////////// ENEMY WAVE 3 ////////////
    //enemyHoard.wave3(player, gameMap, grid,defense, input);
    
  
  }
    
  
}
