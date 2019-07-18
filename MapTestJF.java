
public class MapTestJF{

  public static void main(String[] args) {
    Map3JF m = new Map3JF();
    String[][] grid = new String[5][5];
    m.createInitialGrid(grid); //combined function
    //insert the buy tower function here
    m.placeEnemies(grid, 2, 0, "5");
    m.printGrid(grid);
    System.out.println();
    m.runEnemies(grid);
    
  }
}