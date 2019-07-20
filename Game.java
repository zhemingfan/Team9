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
    // Open Title
    System.out.println("T O W E R  D E F E N S E\n");
    // Press Enter
    Scanner input = new Scanner(System.in);
    System.out.println("Press Enter");
    String enter = input.nextLine();
    // Generate Map
    Map m = new Map();
    // Generate Enemy
    Enemy e = new Enemy(5,1);
    // Generate Player
    Player p = new Player(10,10);
    // Generate Defender Class
    Defender d = new Defender(10,100,1);
    // Generate 2D Array
    String[][] grid = m.generateGrid();
    m.printGrid(grid);
    //d.generateTower(grid);
    //p.selectTower(grid,p);
    e.generateEnemy(grid,e);
    m.printGrid(grid);
    //1st enemy wave
    e.enemyWave1(e,p,m,grid,input,d);
  
  }
    
  
}
}
