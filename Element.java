public class Element{
  //remove this class
  //finds the node and redraw it 
  private double xCoord;
  private double yCoord;
  private int size;

  public Element(double x, double y){
    xCoord = x;
    yCoord = y;
  }
  
  public double getXCoord() {
    return xCoord;
  }
  
  public double getYCoord() {
    return yCoord;
  }
  
  public void setXCoord(double x) {
    xCoord = x;
  }
  
  public void setYCoord(double y) {
    yCoord = y;
  }
  
  public void setLocation(double x, double y) {
	xCoord = x;
	yCoord = y;
  }
  
  public void moveUp(int amt) {
		if (amt <= yCoord && amt >= 0) {
			yCoord -= amt;
		}	
	}

  public void moveDown(int amt) {
    if (amt >= 0) {
      yCoord += amt;
    }  
  }
  
	public void moveLeft(int amt) {
		if (amt <= xCoord && amt >= 0) {
			xCoord -= amt;
		}
	}

	public void moveRight(int amt) {
		if (amt >= 0) {
			xCoord += amt;
		}
	}
  
  public double getDistance(Element other) {
    double otherX = other.getXCoord();
    double otherY = other.getYCoord();
    double deltaXsquared = Math.pow((double)(xCoord - otherX), 2.0);
    double deltaYsquared = Math.pow((double)(yCoord - otherY), 2.0);
    return Math.sqrt(deltaXsquared + deltaYsquared);
  }
  
  public boolean equals(Element other) {
	  return( xCoord == other.getXCoord() && yCoord == other.getYCoord());
  }
}
