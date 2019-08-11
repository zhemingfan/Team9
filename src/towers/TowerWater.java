package towers;
import parents.Tower;

public class TowerWater extends Tower{

	public TowerWater(double x, double y) {
		super(x, y);
		this.range = 120;
		this.damage = 5;
		this.price = 5;
		this.attackRate = 110;
	}

	public TowerWater() {
		this.range = 120;
		this.damage = 5;
		this.price = 5;
		this.attackRate = 110;
	}
	public String toString() {
		  return "\n" + " WATER SPRITE\n" + super.toString();
	  }
}
