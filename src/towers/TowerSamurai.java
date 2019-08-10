package towers;
import parents.Tower;

public class TowerSamurai extends Tower{

	public TowerSamurai(double x, double y) {
		super(x,y);
		damage = 100;
		range = 85;
		price = 100;
		this.attackRate = 200;
	}

	public TowerSamurai() {
		damage = 100;
		range = 85;
		price = 100;
		this.attackRate = 200;
	}

	public String toString() {
		  return "\n" + " SAMURAI TOWER\n" + super.toString();
	  }
}
