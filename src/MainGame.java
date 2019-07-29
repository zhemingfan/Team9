import java.util.ArrayList;

public class MainGame {
	private static final double OFFSETX = 50, OFFSETY = 50;
	
	private Map map = new Map();
	private Player player = new Player(500, 500);
	private ArrayList<Defender> defenderList;
	private ArrayList<Enemy> enemyList;
	private Point[] checkPoints = map.getCheckPoints(OFFSETX, OFFSETY);
	
	
	public Map getMap() {
		return map;
	}
	
	public ArrayList<Defender> getDefenderList(){
		return defenderList;}
	
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}
	
	/**
	 * Generate a generic enemy and add it to the enemyList
	 */
	public void spawnEnemies() {
		Point start = map.getStartPoint(OFFSETX, OFFSETY);
		Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
		enemyList.add(anEnemy);
	}
	/**
	 * Update the coordinates of each enemy based on how much time has elapsed
	 * @param elapsedTime
	 */
	public void EnemiesAdvance(double elapsedTime) {
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy anEnemy = enemyList.get(i);
			anEnemy.advance(elapsedTime);
		}
	}
	
	/**
	 * Remove all enemies that have been killed
	 */
	public void removeKilledEnemies() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).isKilled() ) {
				toBeRemoved.add(enemyList.get(i));
			}
		};
		enemyList.removeAll(toBeRemoved);
	}
	
	/**
	 * Check if any enemies has reached the end. Have the enemies deal damage to the Player. Remove these enemies.
	 */
	public void EnemiesReachedEnd() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).hasReached(map.getEndPoint(OFFSETX, OFFSETY)) ) {
				enemyList.get(i).attack(player);
				toBeRemoved.add(enemyList.get(i));
			}
		};
		enemyList.removeAll(toBeRemoved);
	}
	
	public void addDefender(Defender aDefense) {
		defenderList.add(aDefense);
	}
	
	/**
	 * Have each defender find their target enemy and deal damage to the target 
	 */
	public void DefendersAttackEnemies() {
		for (int i = 0; i < defenderList.size(); i++) {
			Defender aDefender = defenderList.get(i);
			Enemy target = aDefender.findTarget(enemyList);
			if (target != null) {
				aDefender.attack(target);
			}
		}
	}
	
}
