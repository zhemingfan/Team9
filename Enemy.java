

 public class Enemy extends Map{
   int col;
   int row;
   int startindex = 0;
   int endindex = 4;
   int enemyHealth = 5;
   
  public Enemy(int enemyHealth) {
     this.enemyHealth = enemyHealth;
  }
  public int getEnemyXCoord(String[][]grid) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; r++) {
        if (grid[c][r] == ""+enemyHealth) {
          col = c;
        }
      }
    }
    return col;
  }
  public int getEnemyYCoord(String[][]grid) {
    for (int r = 0; r < grid.length; r++) {
      for(int c = 0; c < grid[r].length; r++) {
        if (grid[c][r] == ""+enemyHealth) {
          row = r;
        }
      }
    }
    return row;
  }
  public void takeDamage() {
    //enemyHealth -= attackDamage;
  }
  public int getEnemyHealth() {
    return enemyHealth;
  }
  public String[][] generateEnemy(String[][] grid) {
    System.out.println("ENEMY HAS ARRIVED");
    int startpoint = startindex;
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[r].length; c++) {
            if (grid[startpoint][2] == "-") {
              grid[startpoint][2] = "5";
            }
         }
      }
    return grid;
    }
 public void enemyFunction(Enemy anEnemy,Map aMap, String[][]grid, String enter) {
   int startpoint = startindex;
   int endpoint = endindex;
   if(pressEnter() != "no") {
     while (startpoint < endpoint+1) {
       anEnemy.moveEnemy(grid,startpoint,endpoint);
       printGrid(grid);
       startpoint +=1;
       pressEnter();
   }
 }
 }
 public String toString() {
   return ""+enemyHealth;
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
