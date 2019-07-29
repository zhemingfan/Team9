

public class Wave extends Map{
  boolean waveActive = true; //boolean operator that determines if waveActive is on.
  
  
  public Wave() {
  }

//////////////////////////////////////////////////////////////////////////////  
  /**
   * this algorithm will iterate through the 2D array to find enemy/defender coordinates
   * and will find the distance through the attack method.
   * @param aPlayer
   * @param grid
   * @param anEnemy
   */
  public void findEnemyAlgorithm(Player aPlayer,String[][] grid,Enemy anEnemy) {
    ///// DEFENDER COORDINATES & STATS ////
    int defenderX = 0; int defenderY = 0;
    int range = 0; int damage = 0;
    
    //// ENEMY COORDINATES ////
    int enemyX = 0; int enemyY = 0; 
    
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[c][r].equals("D")) {
          defenderX = c;
          defenderY = r;
          range = 1;
          damage = 1;
        }

        if (grid[c][r].equals(anEnemy.getName())) {
          enemyX = c;
          enemyY = r;
          
        }
      }
    }
    aPlayer.attack(grid,anEnemy,damage,defenderX,defenderY,enemyX,enemyY,range);
  }

}