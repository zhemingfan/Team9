package game;
import javafx.scene.control.Label;
import parents.Enemy;
import parents.Point;

import javafx.scene.control.Label;

public class Player /*extends Point*/{
  private int playerHealth;
  private int money;
  Label healthLabel;
  public Label moneyLabel;

  public Player(int aHealth, int initCurrency){
    setHealth(aHealth);
    setMoney(initCurrency);

  }

  public void setHealth(int initHealth){
    if(initHealth >= 20) playerHealth = initHealth;
    else playerHealth = 20;
  }

  public void setMoney(int initMoney){
    if(initMoney >= 20) money = initMoney;
    else money = 20;
  }

  public void setHealthLabel(){
	healthLabel = new Label(toStringHealth());
  }

  public void setMoneyLabel(){
	 moneyLabel = new Label(toStringMoney());
  }

  public int getHealth(){
    int healthCopy = playerHealth;
    return healthCopy;
  }

  public int getMoney(){
    int moneyCopy = money;
    return moneyCopy;
  }

  public Label getHealthLabel(){
    return healthLabel;
  }

  public Label getMoneyLabel(){
    return moneyLabel;
  }

  public boolean isKilled() {
    return playerHealth <= 0;
  }

  public void takeDamage(int dealtDamage){
    if(dealtDamage >= 0) playerHealth -= dealtDamage;
  }

  public boolean enoughFunds(int moneyLost) {
    return moneyLost <= money;
  }

  public void buyDefense(int moneyLost) {
    if (enoughFunds(moneyLost) && moneyLost >= 0) {
    	money -= moneyLost;
    }
  }

  public void gainMoney(int moneyGained){
    if(moneyGained >= 0) money += moneyGained;
    //System.out.println("You gained $"+moneyGained+"\nCASH: $"+money);
  }

  public String toStringMoney() {
	  return  " " + getMoney();
  }

  public String toStringHealth() {
	  return  " " + getHealth();
  }

  //CODE FOR TEXT-BASED BELOW
  public void attack(String[][] grid,Enemy anEnemy,int damage,int xD, int yD,int eX, int eY,int range) {
	    if (enemyIsWithinRange(xD,yD,eX,eY,range)){
	      anEnemy.takeDamage(damage);
	    }
	    else {
	      System.out.println("You have no defenders in range to attack "+anEnemy.getName());
	    }
	  }

  public int TextgetDistance(int otherX, int otherY,int defenderX, int defenderY) {
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
	    return this.TextgetDistance(eX,eY,defenderX,defenderY) <= range;
	  }


}

