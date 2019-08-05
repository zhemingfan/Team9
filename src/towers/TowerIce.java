package towers;
import parents.Tower;

public class TowerIce extends Tower{

	public TowerIce(double x, double y) {
		super(x, y);
		this.range = 150;
		this.damage = 15;
		this.price = 30;
	}

	public TowerIce() {
		// TODO Auto-generated constructor stub
	}

}
