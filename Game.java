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
    // Generate 2D Array
    String[][] grid = new String[5][5];
    // Generate Enemy
    Enemy e = new Enemy(5,0,2);
    // Generate Player
    Player p = new Player(20, 200, 4, 2, grid);
    // Generate Defender Class
    Defender d = new Defender(2, 50, 1); //Change first parameter if you want to adjust the damage

    //Generate Grid
    m.generateGrid(grid);
    m.printGrid(grid);
    d.generateTower(grid);
    e.generateEnemy(grid,e);
    m.printGrid(grid);
    //1st enemy wave
    e.enemyWave1(e,m,grid,input, d);

  }


}
