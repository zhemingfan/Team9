import java.util.Scanner;


public class Wave extends Map{
  boolean waveActive = true;
  Scanner input = new Scanner(System.in);
  
  
  public Wave() {
  }
  
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
        if (grid[c][r].equals("A")) {
          defenderX = c;
          defenderY = r;
          range = 1;
          damage = 1;
        }
        else if (grid[c][r].equals("S")) {
          defenderX = c;
          defenderY = r;
          range = 2;
          damage = 2;
        }
        if (grid[c][r].equals(anEnemy.getName())) {
          enemyX = c;
          enemyY = r;
          
        }
      }
    }
    aPlayer.attack(grid,anEnemy,damage,defenderX,defenderY,enemyX,enemyY,range);
  }

//////////////ENEMY WAVE METHODS //////////////////
  /**
   * function that generates a single wave of enemies.
   * @param anEnemy
   * @param anEnemy2
   * @param anEnemy3
   * @param aPlayer
   * @param grid
   * @param WaveNumber
   */
  public void enemyWave(Player aPlayer, String[][]grid, int WaveNumber) {
    Enemy e1 = new Enemy("E1",10,5,10,0,2);
    Enemy e2 = new Enemy("E2",1,5,10,0,2);
    Enemy e3 = new Enemy("E3",10,5,10,0,2);
    e1.generateEnemy(grid, e1);
    e2.generateEnemy(grid, e2);
    e3.generateEnemy(grid, e3);
    System.out.println("\n\nW A V E  "+WaveNumber+"\n\n");
    printGrid(grid);
    System.out.println("Press Enter");
    input.nextLine();
    System.out.println("ENEMY HAS ARRIVED");
    
    waveActive = true;
    //////////// WHILE WAVE IS ACTIVE ////////////////
    while (waveActive) {
      
      
      printGrid(grid); // displays map
      
      /*
       * Switch case method that determines which wave to generate.
       */
      switch (WaveNumber) {
      case 1: 
        ///// WAVE 1 /////
        e1.displayHealth(); 
        findEnemyAlgorithm(aPlayer,grid,e1); // defender attack method
        e1.moveEnemy(grid,e1,aPlayer);
        if (e1.isDead()){
          e1.checkIfDead(grid,aPlayer);
          waveActive = false;
        }
        if (e1.hasCrossed()) {
          waveActive = false;
        }
      break;
      
      case 2:
        ///// WAVE 2 /////
        e1.displayHealth();
        findEnemyAlgorithm(aPlayer,grid,e1);
        e1.moveEnemy(grid,e1,aPlayer);
        //// ADD 2ND ENEMY ////
        if (e1.getXCoord() > 3) {
          e2.displayHealth();
          e2.moveEnemy(grid, e2, aPlayer);
          findEnemyAlgorithm(aPlayer,grid,e2);
      }
        e1.checkIfDead(grid,aPlayer);
        if (e2.isDead()) {
          e2.removeEnemy(grid);
          System.out.println(e2.getName()+" has been killed.");
          aPlayer.gainMoney(e2.moneyGained);
          waveActive = false;
        }
        else if (e2.hasCrossed()) {
          waveActive = false;
        }        
      break;
      
      case 3:
        ///// WAVE 3 /////
        e1.displayHealth();
        findEnemyAlgorithm(aPlayer,grid,e1);
        e1.moveEnemy(grid,e1,aPlayer);
        ///// ADD 2ND ENEMY /////
        if (e1.getXCoord() > 3) {
          e2.displayHealth();
          e2.moveEnemy(grid, e2, aPlayer);
          findEnemyAlgorithm(aPlayer,grid,e2);
         }
        e1.checkIfDead(grid, aPlayer);
        
        if (e2.getXCoord() > 3) {
        ///// ADD 3RD ENEMY /////
          e3.displayHealth();
          e3.moveEnemy(grid, e3, aPlayer);
          findEnemyAlgorithm(aPlayer,grid,e3);
        } 
        e2.checkIfDead(grid,aPlayer);
        
        if (e3.isDead()) {
          e3.removeEnemy(grid);
          System.out.println(e3.getName()+" has been killed.");
          aPlayer.gainMoney(e3.moneyGained);
          waveActive = false;
        }
        else if (e3.hasCrossed()) {
          waveActive = false;
        }   
        break;
      }
      
      /////// CHECKS IF PLAYER IS KILLED /////////
      if (aPlayer.isKilled()) {
        waveActive = false;
        break;
      }
      aPlayer.displayHealth();
      ///////// PROMPTS USER TO SELECT ////////////
      if (waveActive) {
      System.out.println("(P)lace Tower\n"
        + "(Press Enter) Skip");
        String choice = input.nextLine();
        if (choice.equals("P")) {
          aPlayer.defenderSelection(grid);
        }
      }
      else {
        System.out.println("(Press Enter)");
      }
      String choice = input.nextLine();
      if (choice.equals("P")) {
        aPlayer.defenderSelection(grid);
      }
    }

    System.out.println("\nE N D  O F  W A V E  "+WaveNumber+"\n\n");
  }
  /**
   * this function generates the amount of enemies for this demo
   * and will facilitate each wave of enemies.
   * 
   * @param aPlayer
   * @param grid
   * @param WaveNumber
   */
  public void generateWave(Player aPlayer, String grid[][],int WaveNumber) {

    if (WaveNumber == 1) {      

      enemyWave(aPlayer,grid,1);
    }
    else if (WaveNumber == 2) {      

      enemyWave(aPlayer,grid,2);
    }
    else if (WaveNumber == 3) {

      enemyWave(aPlayer,grid,3);
    }     
  }


}