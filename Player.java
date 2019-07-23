import java.util.Scanner;


public class Player extends Wave{
  int playerHealth;
  private int money;
  int archerPrice = 5;
  int sniperPrice = 10;
  Scanner input = new Scanner(System.in);
  

  public Player() {
  }
  
  public Player(int aHealth, int initCurrency, String[][] grid){
    playerHealth = aHealth;
    money = initCurrency;
    defenderSelection(grid);
    displayHealth();
  }
  
/////////////////////////////VARIABLE METHODS///////////////////////////////////
  /**
   * Displays Player health as a string and as a health bar
   * |------------|
   */
  public void displayHealth() {
    StringBuilder r = new StringBuilder();
    for (int i = 0; i < playerHealth/10; i++) {
        r.append("-");
    }
    System.out.println("HEALTH: "+playerHealth+"HP |"+r+"|");
  }
  
  public int getHealth(){
    return playerHealth;
  }

  public int getMoney(){
    return money;
  }

  public boolean isKilled() {
    return playerHealth == 0;
  }
  
  public void takeDamage(int dealtDamage){
    this.playerHealth -= dealtDamage;
  }
  
  public boolean hasEnoughFunds(int price) {
    boolean sufficient = false;
    if (money >= price) {
      sufficient = true;
    }
    return sufficient;
  }
  public boolean hasSurvived() {
    boolean survived = false;
    if (this.getHealth() != 0){
      survived = true;
    }
    return survived;
  }

  public void buyDefense(int price) {
    money -= price;
    System.out.println("\nCASH: $"+money);
  }
  public void gainMoney(int moneyGained){
    money += moneyGained;
    System.out.println("You gained $"+moneyGained+"\nCASH: $"+money);
  }
/////////////////////////////////////////////////////////////////////////////////////

///////////////////////////DEFENSE ATTACK METHOD////////////////////////////////////
  /**
   * 
   * @param grid
   * @param anEnemy
   * @param damage
   * @param xD
   * @param yD
   * @param eX
   * @param eY
   * @param range
   */
  public void attack(String[][] grid,Enemy anEnemy,int damage,int xD, int yD,int eX, int eY,int range) {
    if (enemyIsWithinRange(xD,yD,eX,eY,range)){
      anEnemy.takeDamage(damage);
    }
    else {
      System.out.println("You have no defenders in range to attack "+anEnemy.getName());
    }
  }
  /**
   * 
   * @param otherX
   * @param otherY
   * @param defenderX
   * @param defenderY
   * @return
   */
  public int getDistance(int otherX, int otherY,int defenderX, int defenderY) {
    double deltaXsquared = Math.pow((double)(defenderX - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(defenderY - otherY), 2.0);
    return (int)(Math.sqrt(deltaXsquared + deltaYsquared));
  }
  /**
   * 
   * @param defenderX
   * @param defenderY
   * @param eX
   * @param eY
   * @param range
   * @return
   */
  public boolean enemyIsWithinRange(int defenderX, int defenderY,int eX, int eY,int range) {
    return this.getDistance(eX,eY,defenderX,defenderY) <= range;
  }
///////////////////////////////////////////////////////////////////////////////////
  
////////////////////////MAIN TOWER GENERATION METHOD///////////////////////////////
  /**
  *method that generates tower
  *@param grid
  *
  *
  */
  public void defenderSelection(String[][] grid) {
    System.out.println("SELECT YOUR DEFENDER:"
                 + "\nCASH: $"+this.getMoney()
                 + "\n\nEnter (S) for Sniper ($10)"
                 + "\n\nEnter (A) for Archer ($5)"
                 + "\n\nor press enter.");
    String choice = input.nextLine();
    if(choice.equals("S") && hasEnoughFunds(sniperPrice)) {
      System.out.println("You bought a Sniper!\n$"+this.getMoney()+" - $"+sniperPrice);
      buyDefense(sniperPrice);
      new Defender("S",grid,2,5);
    }
    else if (choice.equals("A") && hasEnoughFunds(archerPrice)) {
      System.out.println("You bought an Archer!\n$"+this.getMoney()+" - $"+archerPrice);
      buyDefense(archerPrice);
      new Defender("A",grid,1,5);
    }
    else if (!hasEnoughFunds(archerPrice) && !hasEnoughFunds(sniperPrice)){
      System.out.print("You have insufficient funds\nPress Enter.");
      input.nextLine();
    }
  }
/////////////////////////////////////////////////////////////////////////////////
}
