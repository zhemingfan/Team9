
public class Player{
  private int playerHealth;
  private int money;


  public Player() {
    
  }
  public Player(int aHealth, int initCurrency){
    this.setHealth(aHealth);
    this.setMoney(initCurrency);
    
    //this.setPosition(xPos, yPos);
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
    int moneyCopy = money;
    return moneyCopy;
  }

  public int getXCoord(){
    return 0;
  }
  public void checkIfPlayerKilled() {
    if (playerHealth == 0) {
      System.out.println("YOU'RE DEAD. GAME OVER!");
    }
  }
  public void takeDamage(int dealtDamage){
    playerHealth -= dealtDamage;
  }

  public void gainMoney(int moneyGained){
    money += moneyGained;
    System.out.println("You gained $"+moneyGained+"\nCASH: $"+money);
  }
}
