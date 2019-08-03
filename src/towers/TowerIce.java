package towers;
import parents.Tower;

public class TowerIce extends Tower{

	public TowerIce(double x, double y) {
		super(x, y);
		this.range = 100;
		this.damage = 20;
		this.price = 20;
	}

	public TowerIce() {
		// TODO Auto-generated constructor stub
	}

}
