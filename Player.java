public class Player{
  private int playerHealth;
  private int money;


  public Player() {

  }
  public Player(int aHealth, int initCurrency){
    playerHealth = aHealth;
    money = initCurrency;

  }

  public void setHealth(int initHealth){
    playerHealth = initHealth;
  }

  public void setMoney(int initMoney){
    money = initMoney;
  }


  public int getHealth(){
    int healthCopy = playerHealth;
    return healthCopy;
  }

  public int getMoney(){
    return money;
  }

  public int getXCoord(){
    return 0;
  }
  public boolean isKilled() {
    return playerHealth == 0;
  }
  public void takeDamage(int dealtDamage){
    playerHealth -= dealtDamage;
  }
  public boolean enoughFunds(int moneyLost) {
    return moneyLost < money;
  }
  public void buyDefense(int moneyLost) {
    if (money < moneyLost) {
      System.out.println("You have insufficient funds.");
    }
    money -= moneyLost;
    System.out.println("\nCASH: $"+money);
  }
  public void gainMoney(int moneyGained){
    money += moneyGained;
    System.out.println("You gained $"+moneyGained+"\nCASH: $"+money);
  }
}
