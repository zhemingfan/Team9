import java.util.Scanner;


public class Wave extends Map{
  boolean waveActive = true; //boolean operator that determines if waveActive is on.
  Scanner input = new Scanner(System.in);
  
  
  public Wave() {
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
/////////////////////////////// "MAIN GAME" METHOD ////////////////////////////
  /**
   * main tower defense function
   * @param aPlayer
   * @param enemyWave
   * @param grid
   * @param gameon
   * @param i
   */
  public void towerDefense(Player aPlayer,Wave enemyWave,String[][] grid,boolean gameon,int i) { 
        ////////////ENEMY WAVE ////////////
        enemyWave.generateWave(aPlayer,grid,i);
        if(aPlayer.hasSurvived()) {
          if (i < 3) {
          System.out.println("Proceed to Wave "+(i+1));
          }
        input.nextLine();
        }
        if (i == 3) {
          System.out.println("You have completed all 3 waves.\nThis concludes Demo 1.");
          gameon = false;
        }
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
    Enemy e2 = new Enemy("E2",10,5,10,0,2);
    Enemy e3 = new Enemy("E3",10,5,10,0,2);
    Enemy e4 = new Enemy("E4",10,5,10,0,2);
    Enemy e5 = new Enemy("E5",10,5,10,0,2);
    Enemy e6 = new Enemy("E6",10,5,10,0,2);
    e1.generateEnemy(grid, e1);
    e2.generateEnemy(grid, e2);
    e3.generateEnemy(grid, e3);
    e4.generateEnemy(grid, e4);
    e5.generateEnemy(grid, e5);
    e6.generateEnemy(grid, e6);
    System.out.println("\n\nW A V E  "+WaveNumber+"\n\n");
    printGrid(grid);
    System.out.println("Press Enter");
    input.nextLine();
    System.out.println("ENEMY HAS ARRIVED");
    waveActive = true;
    
////////////////////// WHILE WAVE IS ACTIVE //////////////////////
    while (waveActive) {
      printGrid(grid); // displays map
      
/////////////////////////////////////////////////////////////////      
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
/////////////////////////////////////////////////////////////////      
      case 2:
        ///// WAVE 2 /////
        e2.displayHealth();
        findEnemyAlgorithm(aPlayer,grid,e2);
        e2.moveEnemy(grid,e2,aPlayer);
        e2.checkIfDead(grid,aPlayer);
        //// ADD 2ND ENEMY ////
        if (e2.getXCoord() > 3) {
          e3.displayHealth();
          e3.moveEnemy(grid, e3, aPlayer);
          findEnemyAlgorithm(aPlayer,grid,e3);
      }
        
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
/////////////////////////////////////////////////////////////////      
      case 3:
        ///// WAVE 3 /////
        e4.displayHealth();
        findEnemyAlgorithm(aPlayer,grid,e4);
        e4.moveEnemy(grid,e4,aPlayer);
        e4.checkIfDead(grid, aPlayer);
        ///// ADD 2ND ENEMY /////
        if (e4.getXCoord() > 3) {
          e5.displayHealth();
          e5.moveEnemy(grid, e5, aPlayer);
          findEnemyAlgorithm(aPlayer,grid,e5);
          e5.checkIfDead(grid,aPlayer);
         }       
        if (e5.getXCoord() > 3) {
        ///// ADD 3RD ENEMY /////
          e6.displayHealth();
          e6.moveEnemy(grid, e6, aPlayer);
          findEnemyAlgorithm(aPlayer,grid,e6);
        }     
        if (e6.isDead()) {
          e6.removeEnemy(grid);
          System.out.println(e6.getName()+" has been killed.");
          aPlayer.gainMoney(e6.moneyGained);
          waveActive = false;
        }
        else if (e6.hasCrossed()) {
          waveActive = false;
        }   
        break;
      }
/////////////////////////////////////////////////////////////////    
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
}