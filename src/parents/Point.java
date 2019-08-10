package parents;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Point{
  private double xCoord;
  private double yCoord;
  private Node UIobject;
  public Point() {
	  
  }
  /**
   * Creates new point with X-coordinate at x and Y-coordinate at y.
   * @param x
   * @param y
   */
  public Point(double x, double y){
    xCoord = x;
    yCoord = y;
  }

  /**
   * Returns the X-coordinate.
   * @return X-coordinate
   */
  public double getXCoord() {
    return xCoord;
  }

  /**
   * Returns the Y-coordinate.
   * @return Y-coordinate
   */
  public double getYCoord() {
    return yCoord;
  }

  /**
   * Sets the X-coordinate to the specified value.
   * @param xCoord
   */
  public void setXCoord(double xCoord) {
	   this.xCoord = xCoord;
  }

  /**
   * Sets the Y-coordinate to the specified value.
   * @param yCoord
   */
  public void setYCoord(double yCoord) {
	   this.yCoord = yCoord;
  }
  
  /**
   * Attaches the point object to its representation on the GUI.
   * @param aUIobject
   */
  
  public void setNode(Node aUIobject) {
	  UIobject = aUIobject;
  }
  /**
   * Returns the point object's representation on the GUI.
   * @return the point object's representation on the GUI
   */
  public Node getNode() {
	  return UIobject;
  }

  /**
   * Deducts Y-coordinate by given amount if the amount is valid.
   * @param amt
   */
  public void moveUp(double amt) {
			this.yCoord -= amt;
	}
  /**
   * Increases Y-coordinate by given amount if the amount is valid.
   * @param amt
   */
  public void moveDown(double amt) {
			this.yCoord += amt;
	}
  /**
   * Deducts X-coordinate by given amount if the amount is valid.
   * @param amt
   */
	public void moveLeft(double amt) {
			this.xCoord -= amt;		
	}
	/**
	   * Increases X-coordinate by given amount if the amount is valid.
	   * @param amt
	   */
	public void moveRight(double amt) {
			this.xCoord += amt;
		
	}
  
	/**
	 * Returns the Cartesian distance between two point object.
	 * @param other
	 * @return the distance between two point object.
	 */
  public double getDistance(Point other) {
    double deltaXsquared = Math.pow((double)(xCoord - other.getXCoord()), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - other.getYCoord()), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }
  
  /**
   * Checks if one Point or its subclass has reached another Point.
   * @param other: Point
   * @return boolean
   */
  public boolean hasReached(Point other) {
    return (this.getDistance(other) <= 0.0000001);
  }
   
  /**
   * Returns the point's coordinates in String format.
   * @return the point's coordinates in String format
   */
  public String toString() {
	  return xCoord + ", " + yCoord;
  }
}
