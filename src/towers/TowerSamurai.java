package towers;
import parents.Tower;

public class TowerSamurai extends Tower{

	public TowerSamurai(double x, double y) {
		super(x,y);
		damage = 50;
		range = 55;
		price = 100;
	}
	
	public TowerSamurai() {
		damage = 50;
		range = 55;
		price = 100;
	}
}
