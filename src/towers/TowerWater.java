package towers;
import parents.Tower;

public class TowerWater extends Tower{

	public TowerWater(double x, double y) {
		super(x, y);
		this.range = 75;
		this.damage = 10;
		this.price = 5;
	}

	public TowerWater() {
		this.range = 50;
		this.damage = 10;
		this.price = 10;
	}
}
