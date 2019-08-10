package towers;
import parents.Tower;

public class TowerWater extends Tower{

	public TowerWater(double x, double y) {
		super(x, y);
		this.range = 100;
		this.damage = 5;
		this.price = 5;
		this.attackRate = 20;
	}

	public TowerWater() {
		this.range = 100;
		this.damage = 5;
		this.price = 5;
		this.attackRate = 20;
	}
	public String toString() {
		  return "\n" + " WATER SPRITE\n" + super.toString();
	  }
}
