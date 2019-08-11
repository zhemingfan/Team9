package towers;
import parents.Enemy;
import parents.Tower;

/**
 * @author Aries
 * Trap tower. Doesn't do damage. Instead it immobilizes a single enemy.
 * Intended to combo with the damage-dealing towers.
 */
public class TowerWind extends Tower{
	private double speedDebuff;

	public TowerWind(double x, double y) {
		super(x, y);
		this.range = 100;
		this.price = 15;
		this.attackRate = 200;
	}

	public TowerWind() {
		this.range = 100;
		this.price = 15;
		this.attackRate = 200;
	}

	@Override
	/**Overrides the tower's normal attack method to freeze an enemy instead.
	 * @param anEnemy - The target enemy
	 */
	public void attack(Enemy anEnemy) {
		speedDebuff = anEnemy.getSpeed();
		if(anEnemy != null) anEnemy.slowDown(speedDebuff);
	}

	@Override
	public String toString() {
		return "\n" + " WIND SPIRIT\n" + super.toString();
	}
}
