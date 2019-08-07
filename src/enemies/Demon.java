package enemies;
import game.Player;
import parents.Enemy;

public class Demon extends Enemy{
	public Demon(double x, double y) {
		super(x,y);
		maxHealth = 800;
		health = 800;
		damage = 200;
	    speed = 0.5;
	    bounty = 500;
	}
	
	public void attack(Player aPlayer) {
	    super.attack(aPlayer);
	  }
	
	/**
	 * Overrides slowDown() in parent class.
	 * The boss enemy Demon does not get trapped by the wind towers because that would be too easy.
	 */
	@Override
	public void slowDown(double debuff) {
		//Nothing happens
	}
}
