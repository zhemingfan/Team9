public class Point{
  private int xcoord;
  private int ycoord;

  public Point(int xPos, int yPos){
    this.setXCoord(xPos);
    this.setYCoord(yPos);
  }

  public void setXCoord(int newX){
    xcoord = newX;
  }

  public void setYCoord(int newY){
    ycoord = newY;
  }
}
