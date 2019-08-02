import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/** TEXT BASED VERSION
 * This class incorporates all the logic classes to generate a wave of enemies 
 * and for the player to interact with the game and place towers.
 * @author Team 9
 *
 */
public class MainGameTextBased {
  boolean waveActive = true; //boolean operator that determines if waveActive is on.
  Scanner input = new Scanner(System.in); //scanner instance
  private ArrayList<Enemy> enemyList = new ArrayList<Enemy>(); //list of enemies generated
  private ArrayList<Enemy> aliveList = new ArrayList<Enemy>(); //list of enemies alive/on map
  private ArrayList<Tower> towerList = new ArrayList<Tower>(); //list of towers
  Random rand = new Random(); //random instance

  /** Initiates main game.
   * 
   */
  public MainGameTextBased() { 
    
  }
  
  /** Updates enemyList of randomly chosen enemies from Fire Class, Lava Class, or Spirit.
   * @param wave Wave Number
   */
  public void createEnemyList(int wave) {
    int enemyCount = wave*10;
    int i = 0;
    while (i < enemyCount) {
      int n = rand.nextInt(3);
      if (n == 0) {
        enemyList.add(new Fire(i));
      }
      else if (n == 1) {
        enemyList.add(new Lava(i));
      }
      else {
        enemyList.add(new Spirit(i));
      }
      i++;
    }
  }
  /** Takes the enemies from enemyList and spawns them on the grid.
   * @param grid gridMap from Map Class
   * @param counter a number that indicates a time step after every loop.
   */
  public void spawnEnemies(String[][] grid, int counter) {
    int interval = rand.nextInt(6)+2;     
    for (Enemy anEnemy : enemyList) {
      if (counter % interval == 0) {
        anEnemy.spawnEnemyTextBased(grid);
        enemyList.remove(anEnemy);
        aliveList.add(anEnemy);
        break;
      }
    }  
  }
  
  /** Moves the enemies through the 2D array.
   * @param grid gridMap from Map Class
   * @param coords the list of checkpoints for the enemies.
   */
  public void moveEnemies(String[][]grid, Point[] coords) {
    for (Enemy anEnemy : aliveList) {
      anEnemy.attachPathTextBased(coords);
      anEnemy.advanceTextBased();
      anEnemy.relocateTextBased(grid);        
    }   
  }
  
  /** Iterates through the list of towers that are on the map
   * and attacks the enemies that are on the map.
   */
  public void attackEnemies() {
    for (Tower aTower : towerList) {
      aTower.attack(aTower.findTarget(aliveList));        
    }
  }
  
  /** This method checks the conditions of the Map and changes the boolean waveActive
   * according to the conditions.
   * @param aPlayer Player Class object
   */
  public void checkConditions(Player aPlayer) {
    if (aPlayer.isKilled()) {
      System.out.println("YOU ARE DEAD. GAME OVER!");
      waveActive = false;
    }
    if (aliveList.size() == 0) {
      if (enemyList.size() == 0) {
        waveActive = false;
      }
    }     
  }
  /** Takes the attack methods from Enemy class and attacks Player.
   * @param aPlayer Player Class object
   * @param grid gridMap from Map Class 
   * @param lastCheckIndex last checkpoint of the map.
   */
  public void enemyAttackPlayer(Player aPlayer, String[][] grid, Point lastCheckIndex) {
    for (Enemy anEnemy : aliveList) {
      if (anEnemy.hasReached(lastCheckIndex)) {
        anEnemy.attackTextBased(aPlayer);
        aliveList.remove(anEnemy);
        anEnemy.removeEnemy(grid);
        break;
      }
      else if (anEnemy.isKilled()) {
        aPlayer.gainMoney(anEnemy.getBounty());
        aliveList.remove(anEnemy);
        anEnemy.removeEnemy(grid);
        break;
      }
    }  
  }
  /** Prompts the player to make next move
   * (P)lace Tower
   * (M)enu
   * (Press Enter) Skip
   * @param aPlayer Player Class object
   * @param grid gridMap from Map Class
   */
  public void promptNextMove(Player aPlayer, String[][] grid) {
    if (waveActive) {
      System.out.println("\n(P)lace Tower\n(M)enu\n(Press Enter) Skip");        
    }
    else {
      System.out.println("(Press Enter)");
    }
    String choice = input.nextLine().toUpperCase();
    if (choice.equals("P")) {
      towerSelection(aPlayer,grid);
    }
    else if (choice.equals("M")) {
      menu(aPlayer);
    }
  }
  /** Remove enemies from enemyList and aliveList at the end of a wave.
   */
  public void removeFromList() {
    for (int i = 0; i < enemyList.size(); i++) {
      enemyList.remove(i);
    }
    for (int i = 0; i < aliveList.size(); i++) {
      aliveList.remove(i);
    }
  }
  /** Displays the health of enemy that is on the map.
   * @param grid gridMap from Map Class
   */
  public void displayEnemyHealth(String[][] grid) {
    for (Enemy anEnemy : aliveList) {
      if (anEnemy.isOnMap(grid)) {
        anEnemy.displayHealth();
      }
    }
  }
  
  /** Is called in promptNextMove method when (M)enu option is chosen.
   */
  public void menu(Player aPlayer) {
    if (waveActive) {
      System.out.println("\n\nMain Menu\n\nSelect:\nResume (R)");
      String choice = input.nextLine().toUpperCase();
      if (choice.equals("R")){
        aPlayer.takeDamage(aPlayer);
        waveActive = true;
      }
    }
  }
  
  /** HUD (Head Up Display): displays map, enemy health, and player health.
   * @param grid gridMap from Map Class
   * @param aPlayer Player Class Object
   * @param aMap Map Class Object
   */
  public void showHUD(String[][] grid, Player aPlayer, Map aMap) {
    aMap.display();
    displayEnemyHealth(grid);
    aPlayer.displayHealth();
  }
  
  /** Is called by promptNextMove method when (P)lace Tower is selected.
   * This method will prompt the player to select which tower to spend, and the players money
   * will be reflected upon purchase of tower.
   * @param aPlayer Player Class Object
   * @param grid gridMap from Map Class
   */
  public void towerSelection(Player aPlayer, String[][] grid) {
    System.out.println("SELECT YOUR DEFENDER:"
                     + "\nCASH: $"+aPlayer.getMoney()
                     + "\n\nEnter (W) for Water Tower ($5)"
                     + "\n\nEnter (I) for Ice Tower ($10)"
                     + "\n\nor press enter.");
    String choice = input.nextLine().toUpperCase();
    if(choice.equals("W") && aPlayer.hasEnoughFunds(5)) {
      System.out.println("You bought a Water Tower!\n$"+aPlayer.getMoney()+" - $"+5);
      aPlayer.buyTower(5);
      towerList.add(new TowerWater(grid));
    }
    else if (choice.equals("I") && aPlayer.hasEnoughFunds(10)) {
      System.out.println("You bought an Ice Tower!\n$"+aPlayer.getMoney()+" - $"+10);
      aPlayer.buyTower(10);
      towerList.add(new TowerIce(grid));
    }
    else if (!aPlayer.hasEnoughFunds(10) && !aPlayer.hasEnoughFunds(5)){
      System.out.print("You have insufficient funds\nPress Enter.");
      input.nextLine();
    }
  }
  
  /** Main enemy wave function. This method will be called in the Game Class.
   * Functions called:
   * createEnemyList, showHUD, spawnEnemies, moveEnemies, 
   * attackEnemies, checkConditions, promptNextMove, removeFromList.
   * @param aPlayer Player Class Object
   * @param grid gridMap from Map Class
   * @param coords list of checkpoints for the enemies
   * @param aMap Map Class Object
   * @param gameon boolean from the main method in Game Class.
   * @param wave Wave Number, is updated after every loop in the Game Class main method.
   */
  public void enemyWave(Player aPlayer, String[][]grid,Point[] coords,Map aMap,boolean gameon, int wave) {
    int counter = 0;
    createEnemyList(wave);
    System.out.println("\n\nW A V E  "+wave+"\n\n");
    System.out.println("Press Enter");
    input.nextLine();
    showHUD(grid,aPlayer,aMap);  
    System.out.println("\n\nWAVE COMMENCING\n\n");
    waveActive = true;
    Point lastCheckIndex = coords[coords.length-1]; 
    while (waveActive) {    
      spawnEnemies(grid,counter);
      moveEnemies(grid,coords);
      attackEnemies();
      showHUD(grid,aPlayer,aMap);
      checkConditions(aPlayer);
      enemyAttackPlayer(aPlayer,grid,lastCheckIndex);
      promptNextMove(aPlayer,grid);
      counter++;
    }
    removeFromList();
    if(!aPlayer.isKilled()) {
          System.out.println("\nE N D  O F  W A V E  "+wave+"\n\n");
      System.out.println("Proceed to Wave "+(wave+1));          
    }
    else {
      gameon = false;
    }
  }
}