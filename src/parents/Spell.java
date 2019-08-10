package parents;
import java.util.ArrayList;

/**
 * Spells function as another form of defense aside from the towers
 */
public class Spell {
	protected int damage;
	protected int price;
	protected ArrayList<Enemy> targetList = new ArrayList<Enemy>();
	
	public int getDamage() {
		int copyDmg = damage;
		return copyDmg;
	}
	
	public int getPrice() {
		int copyPrice = price;
		return copyPrice;
	}
	
	public void setDamage(int someDamage) {
		if (someDamage >= 0) damage = someDamage;
	}
	
	public void setPrice(int somePrice) {
		if (somePrice >= 0) price = somePrice;
	}
	
	/**
	 * Attacks a targetted enemy if it exists
	 * @param someTarget - Attacks this enemy
	 */
	public void attack(Enemy someTarget) {
		if(someTarget != null) someTarget.takeDamage(damage);
	}
	
	/**
	 * Has some effect on all enemies on the game board. Until more spells are added, it just damages them all
	 * @param someTargets - The targeted list of enemies
	 */
	public void castSpell(ArrayList<Enemy> someTargets) {
		for (Enemy target: someTargets) {
			attack(target);
		}
	}
	
	public String toString() {
		  return "Damage: " + damage + "\n" + "Price: " + price + "\n";
	  }
}
