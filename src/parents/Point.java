package parents;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Point{
  private double xCoord;
  private double yCoord;
  private Image sprite;
  private VBox UIobject;
  public Point() {
	  
  }
  public Point(double x, double y){
    xCoord = x;
    yCoord = y;
  }

  public double getXCoord() {
    return xCoord;
  }

  public double getYCoord() {
    return yCoord;
  }

  public void setXCoord(double xCoord) {
	   this.xCoord = xCoord;
  }


  public void setYCoord(double yCoord) {
	   this.yCoord = yCoord;
  }
  /**
   * Attaches the point object to its manifestation on the UI
   * @param aUIobject
   */
  
  public void setNode(VBox aUIobject) {
	  UIobject = aUIobject;
  }
  
  public VBox getNode() {
	  return UIobject;
  }
  
  public Image getSprite() {
    return sprite;
  }

  public void moveUp(double amt) {
			this.yCoord -= amt;
	}
  
  public void moveDown(double amt) {
			this.yCoord += amt;
	}
  
	public void moveLeft(double amt) {
			this.xCoord -= amt;		
	}

	public void moveRight(double amt) {
			this.xCoord += amt;
		
	}
  
  public double getDistance(Point other) {
    double deltaXsquared = Math.pow((double)(xCoord - other.getXCoord()), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - other.getYCoord()), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }
  /**
   * Check if one Point or its subclass has reached another Point.
   * @param other: Point
   * @return boolean
   */
  public boolean hasReached(Point other) {
    return (this.getDistance(other) <= 0.0000001);
  }
  
  
  public String toString() {
	  return xCoord + ", " + yCoord;
  }
}
