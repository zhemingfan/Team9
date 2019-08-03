/** TEXT BASED VERSION
 * Main player class.
 * @author Team 9
 */
public class Player{
  private int playerHealth;
  private int money;

  /** Player object that takes in health and currency.
   * @param aHealth player health
   * @param initCurrency money
   */
  public Player(int aHealth, int initCurrency){
    setHealth(aHealth);
    setMoney(initCurrency);
  }

  /** Displays Player health as a string and as a health bar
   * |------------|
   */
  public void displayHealth() {
    StringBuilder r = new StringBuilder();
    for (int i = 0; i < playerHealth/10; i++) {
        r.append("-");
    }
    System.out.println("HEALTH: "+playerHealth+"HP |"+r+"|");
  }

  /**
   * @return int playerHealth
   */
  public int getHealth(){
	  int healthCopy = playerHealth;
    return healthCopy;
  }

  /**
   * @return int money
   */
  public int getMoney(){
    int moneyCopy = money;
    return moneyCopy;
  }

  public void setHealth(int someHealth) {
	  if (someHealth >= 20) playerHealth = someHealth;
	  else playerHealth = 20;
  }

  public void setMoney(int someMoney) {
	  if (someMoney >= 20) money = someMoney;
	  else money = 20;
  }
  /**
   * @return boolean if player health is zero
   */
  public boolean isKilled() {
    return playerHealth == 0;
  }

  /** Player takes damage from enemy.
   * @param dealtDamage damage dealt from enemy
   */
  public void takeDamage(int dealtDamage){
    if(dealtDamage >= 0) playerHealth -= dealtDamage;
  }

  /** If the player has enough money to buy a tower.
   * @param price Tower price
   * @return boolean if player has enough money
   */
  public boolean hasEnoughFunds(int price) {
    boolean sufficient = false;
    if (money >= price) {
      sufficient = true;
    }
    return sufficient;
  }

  /** Updates the Players money after buying a tower.
   * @param price Tower price
   */
  public void buyTower(int price) {
    if(price > 0 && hasEnoughFunds(price)) money -= price;
    System.out.println("\nCASH: $"+money);
  }

  /** Updates the Players money after Tower has killed an enemy.
   * @param moneyGained Bounty from Enemy Class
   */
  public void gainMoney(int moneyGained){
    if(moneyGained >= 0) money += moneyGained;
    System.out.println("You gained $"+moneyGained+"\nCASH: $"+money);
  }


}
