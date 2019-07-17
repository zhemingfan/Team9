public class Player{
  private int playerHealth;
  private int money;
  private int xcoord;
  private int ycoord;
  //private Point checkpoint;

  public Player(int aHealth, int initCurrency, int xPos, int yPos){
    this.setHealth(aHealth);
    this.setMoney(initCurrency);
    this.setPosition(xPos, yPos);
  }

  public void setHealth(int initHealth){
    playerHealth = initHealth;
  }

  public void setMoney(int initMoney){
    money = initMoney;
  }

  public void setPosition(int xPos, int yPos){
    xcoord = xPos;
    ycoord = yPos;
    //checkpoint = new Point(xPos, yPos);
  }
}
