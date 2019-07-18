import java.util.Scanner;

public class MapTest{

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Press Enter");
    String enter = input.nextLine();
    Map m = new Map();
    String[][] grid = new String[5][5];
    m.generateGrid(grid);
    m.createPath(grid);
    m.printGrid(grid);
  }
}