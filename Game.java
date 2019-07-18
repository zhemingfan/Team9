import java.util.Scanner;

public class Game{
  
  public static void openMenu() {
    System.out.println("T O W E R  D E F E N S E\n");
    pressEnter();
  }
  public static String pressEnter() {
    Scanner input = new Scanner(System.in);
    System.out.println("Press Enter");
    String enter = input.nextLine();
    return enter;
  }
  public static void main(String[] args) {
    openMenu();
    Map m = new Map();
    Enemy e = new Enemy();
    String[][] grid = new String[5][5];
    m.generateGrid(grid);
    e.generateEnemy(grid);
    m.printGrid(grid);
    e.enemyFunction(e,m,grid,pressEnter());
  
  }
    
  
}