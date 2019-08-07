package towers;
import parents.Tower;

public class TowerIce extends Tower{

	public TowerIce(double x, double y) {
		super(x, y);
		this.range = 150;
		this.damage = 15;
		this.price = 50;
	}

	public TowerIce() {
		this.range = 150;
		this.damage = 15;
		this.price = 50;
	}

}
