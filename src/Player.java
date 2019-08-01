import javafx.scene.control.Label;

public class Player extends Point{
  private int playerHealth = 70;
  private int money = 50;
  Label healthLabel = new Label("70"); //make this is initialized to something
  Label moneyLabel = new Label("50"); //make this is initialized to something 


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
    return playerHealth;
  }

  public int getMoney(){
    return money;
  }

  public Label getHealthLabel(){
    return healthLabel;
  }

  public Label getMoneyLabel(){
    return moneyLabel;
  }
  
  public void setMoneyLabel() {
	  moneyLabel.setText(" " + getMoney());
  }
  
  public void setHealthLabel() {
	  healthLabel.setText(" " + getHealth());
  }

  public boolean isKilled() {
    return playerHealth == 0;
  }

  public void takeDamage(int dealtDamage){
    playerHealth -= dealtDamage;
  }

  public boolean enoughFunds(int moneyLost) {
    return moneyLost <= money;
  }

  public void buyDefense(int price) {
    if (enoughFunds(price) == false) {
      money = 0;
      System.out.println("You have insufficient funds.\nCASH: $" + getMoney());
    }
    else {
    	money -= price;
    	System.out.println("\nCASH: $"+getMoney());
    }
  }

  public void gainMoney(int moneyGained){
    money += moneyGained;
    System.out.println("You gained $"+moneyGained+"\nCASH: $"+money);
  }

  public void attack(String[][] grid,Enemy anEnemy,int damage,int xD, int yD,int eX, int eY,int range) {
	    if (enemyIsWithinRange(xD,yD,eX,eY,range)){
	      anEnemy.takeDamage(damage);
	    }
	    else {
	      System.out.println("You have no defenders in range to attack "+anEnemy.getName());
	    }
	  }

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
  
  
}
