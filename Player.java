public class Player{
  private int playerHealth;
  private int money;
  private int xcoord;
  private int ycoord;
  //private Point checkpoint;

  public Player(int aHealth, int initCurrency, int xPos, int yPos, String[][] grid){
    this.setHealth(aHealth);
    this.setMoney(initCurrency);
    this.setPosition(xPos, yPos);
    grid[xPos][yPos] = "X";
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
  public int getHealth(){
    int healthCopy = playerHealth;
    return healthCopy;
  }

  public int getMoney(){
    int moneyCopy = money;
    return moneyCopy;
  }

  public Point getLocation(){
    Point location = new Point(xcoord, ycoord);
    return location;
  }

  public void takeDamage(int dealtDamage){
    playerHealth -= dealtDamage;
  }

  public void gainMoney(int moneyGained){
    money += moneyGained;
  }
}
