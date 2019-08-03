package towers;
import parents.Tower;

public class TowerWind extends Tower{

	public TowerWind(double x, double y) {
		super(x, y);
		this.range = 75;
		this.price = 15;
	}

	public TowerWind() {
		this.range = 75;
		this.price = 15;
	}
}
