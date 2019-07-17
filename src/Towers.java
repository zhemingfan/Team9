
public class Towers {
	private int xCoor, yCoor;
	private int range;
	private int damage;
	
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

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
