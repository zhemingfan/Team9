

 public class Enemy extends Map{
   int startindex = 0;
   int endindex = 4;
   
  public Enemy() {
  }
   
  public String[][] generateEnemy(String[][] grid) {
    System.out.println("ENEMY HAS ARRIVED");
    int startpoint = startindex;
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[r].length; c++) {
            if (grid[startpoint][2] == "-") {
              grid[startpoint][2] ="5";
            }
         }
      }
    return grid;
    }
 public void enemyFunction(Enemy anEnemy,Map aMap, String[][]grid, String enter) {
   int startpoint = startindex;
   int endpoint = endindex;
   if(enter != "no") {
     while (startpoint < endpoint) {
       anEnemy.moveEnemy(grid,startpoint,endpoint);
       printGrid(grid);
       startpoint +=1;
       pressEnter();
   }
 }
 }
 public void moveEnemy(String[][] grid,int startpoint,int endpoint) {
   for (int r = 0; r < grid.length; r++) {
     for (int c = 0; c < grid[r].length; c++) {
       if(grid[startpoint][2] == "5") {
         if((startpoint+1) < endpoint+1) {
           grid[startpoint+1][2] = "5";
           grid[startpoint][2] = "-";
         } else if ((startpoint+1) == endpoint+1) {
           grid[startpoint][2] = "-";
           System.out.println("YOU TOOK DAMAGE");
         }
       }
     }
     }
   }
 }