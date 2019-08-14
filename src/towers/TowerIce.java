package towers;
import java.util.ArrayList;

import parents.Enemy;
import parents.Tower;

public class TowerIce extends Tower{

	public TowerIce(double x, double y) {
		super(x, y);
		this.range = 175;
		this.damage = 12;
		this.price = 50;
		this.attackRate = 95;
	}

	public TowerIce() {
		this.range = 175;
		this.damage = 12;
		this.price = 50;
		this.attackRate = 95;
	}

	/**
	 * The ice tower is special since it targets the enemies closest to the end
	 * of the path like a sort of aggressive defender
	 * @param enemiesWithinRange - Arraylist of enemies
	 * @return - The enemy closest to the end of the path
	 */
	public Enemy findEnemyClosestEnd (ArrayList<Enemy> enemiesWithinRange) {
		int largestCPIndex = 0;
		for(Enemy item: enemiesWithinRange) {
		      if (item.getCurrentCheckPointIndex() >= largestCPIndex) {
		    	  largestCPIndex = item.getCurrentCheckPointIndex();
		      }
		    }

		ArrayList<Enemy> closestEndList = new ArrayList<Enemy>();
		for(Enemy item: enemiesWithinRange) {
		      if (item.getCurrentCheckPointIndex() == largestCPIndex) {
		    	  closestEndList.add(item);
		      }
		    }
		Enemy closest = closestEndList.get(0);
		for(Enemy item: closestEndList) {
		      if (item.getDistantFromCP(largestCPIndex) <= closest.getDistantFromCP(largestCPIndex)) {
		    	  closest = item;
		      }
		    }
		return closest;
	}

	/**
	 * This method uses the above method to determine it's attacking target
	 * @return - The target enemy found.
	 */
	public Enemy findTarget(ArrayList<Enemy> currentEnemies) {
	    this.target = null;
	    ArrayList<Enemy> withinRange = new ArrayList<Enemy>();
	    for(Enemy item: currentEnemies) {
	      if (enemyIsWithinRange(item)) {
	        withinRange.add(item);
	      }
	    };
	    if (withinRange.size() > 0) {
	    	target = this.findEnemyClosestEnd(withinRange);
	    }
	    return target;
	  }

	public String toString() {
		  return "\n" + " ICE GHOUL\n" + super.toString();
	  }
}
