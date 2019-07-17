
public class Towers {
	private int xCoor, yCoor;
	private int range;
	private int damage;
	
	public Towers(int X, int Y, int aRange, int aDamage) {
		xCoor = X;
		yCoor = Y;
		range = aRange;
		damage = aDamage;
	}
	
	private double getDistancefromEnemy(Enemy anEnemy) {
		int otherX = anEnemy.getXCoor();
		int otherY = anEnemy.getYCoor();
		double deltaXsquared = Math.pow((double)(xCoor - otherX), 2.0);
		double deltaYsquared = Math.pow((double)(yCoor - otherY), 2.0);
		return Math.sqrt(deltaXsquared + deltaYsquared);
	}
	
	public void attack() {
		
	}
	

}
