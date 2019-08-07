package game;
import javafx.scene.control.Label;
import parents.Enemy;
import parents.Point;

import javafx.scene.control.Label;

public class Player{
  private int playerHealth;
  private int money;
  Label healthLabel;
  public Label moneyLabel;

  /**
   * Creates a new Player with specified initial health and money.
   * @param aHealth
   * @param initCurrency
   */
  public Player(int aHealth, int initCurrency){
    setHealth(aHealth);
    setMoney(initCurrency);

  }

  /**
   * Sets the current health to given number if it is more than 20, and defaults to 20 otherwise.
   * @param initHealth
   */
  public void setHealth(int initHealth){
    if(initHealth >= 20) playerHealth = initHealth;
    else playerHealth = 20;
  }

  /**
   * Sets the current money to given number if it is larger than 0, and defaults to 0 otherwise.
   * @param initHealth
   */
  public void setMoney(int initMoney){
    if(initMoney >= 0) money = initMoney;
    else money = 0;
  }

  /**
   * Sets the player's health stats label on GUI.
   */
  public void setHealthLabel(){
	healthLabel = new Label(toStringHealth());
  }

  /**
   * Sets the player's money stats label on GUI.
   */
  public void setMoneyLabel(){
	 moneyLabel = new Label(toStringMoney());
  }

  /**
   * Returns the current player's health.
   * @return The current player's health
   */
  public int getHealth(){
    int healthCopy = playerHealth;
    return healthCopy;
  }

  /**
   * Returns the current player's money.
   * @return The current player's money
   */
  public int getMoney(){
    int moneyCopy = money;
    return moneyCopy;
  }

  /**
   * Returns the player's health label on GUI.
   * @return the player's health label on GUI
   */
  public Label getHealthLabel(){
    return healthLabel;
  }

  /**
   * Returns the player's money label on GUI.
   * @return the player's money label on GUI
   */
  public Label getMoneyLabel(){
    return moneyLabel;
  }

  /**
   * Checks whether player has been killed.
   * @return whether player has been killed
   */
  public boolean isKilled() {
    return playerHealth <= 0;
  }

  /**
   * Deducts the specified amount from player's health.
   * @param dealtDamage
   */
  public void takeDamage(int dealtDamage){
    if(dealtDamage >= 0) playerHealth -= dealtDamage;
  }

  /**
   * Checks if player has enough funds to spend the amount of money given.
   * @param moneyLost
   * @return if player has enough funds to spend the amount of money given
   */
  public boolean enoughFunds(int moneyLost) {
    return moneyLost <= money;
  }

  /**
   *
   * @param moneyLost - The price of the tower or spell being purchased
   */
  public void buyDefense(int moneyLost) {
    if (enoughFunds(moneyLost) && moneyLost >= 0) {
    	money -= moneyLost;
    }
  }

  /**
   * Not to be confused with getMoney; This method increases the player's funds
   * @param moneyGained - The amount to increase by
   */
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
