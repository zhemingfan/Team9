package parents;
/** TEXT BASED VERSION
 * Super Class of Enemy and Tower.
 * @author Team 9
 */
public class Point {  
  private int xCoord;
  private int yCoord;
  
  /** Constructor for Enemy Class
   * @param xPos
   * @param yPos
   */
  public Point(int xPos, int yPos){
    xCoord = xPos;
    yCoord = yPos;
  }
  
  /** Constructor for Tower Class
   */
  public Point() {
  }
 
  /** Sets the x coordinate
   * @param x
   */
  public void setXCoord(int x){
    this.xCoord = x;
  }
  
  /** Sets the y coordinate
   * @param y 
   */
  public void setYCoord(int y) {
    this.yCoord = y;
  }
 
  /**
   * @return xCoord
   */
  public int getXCoord() {
    return xCoord;
  }

  /**
   * @return yCoord
   */
  public int getYCoord() {
    return yCoord;
  }

  /** Moves point up
   * @param amt
   */
  public void moveUp(int amt) {
    this.yCoord -= amt;
  }
  
  /** Moves point down
   * @param amt
   */
  public void moveDown(int amt) {
    this.yCoord += amt;
  }
  
  /** Moves point left
   * @param amt
   */
  public void moveLeft(int amt) {
    this.xCoord -= amt;             
  }
  
  /** Moves point right
   * @param amt
   */
  public void moveRight(int amt) {
    this.xCoord += amt; 
  }

  /** Takes two points and takes the euclidean distance between those points.
   * @param other
   * @return euclidean distance
   */
  public double getDistance(Point other) {
    double deltaXsquared = Math.pow((double)(xCoord - other.getXCoord()), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - other.getYCoord()), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }

  /** If point reaches another point.
   * @param other
   * @return boolean
   */
  public boolean hasReached(Point other) {
    return (this.getDistance(other) == 0);
  }

  /** Point to string of format (<xCoord>,<yCoord>)
   */
  public String toString() {
          return "("+xCoord + ", " + yCoord+")";
  }
}
