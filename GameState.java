import java.util.ArrayList;

public class GameState {
	private static final double OFFSETX = 50, OFFSETY = 50;
	
	private Map map = new Map();
	private Player player = new Player(500, 500);
	private ArrayList<Defender> defenderList;
	private ArrayList<Enemy> enemyList;
	private Point[] checkPoints = map.getCheckPoints(OFFSETX, OFFSETY);
	
	
	public GameState() {
	
	}
	
	public void spawnEnemies() {
		Point start = map.getStartPoint(OFFSETX, OFFSETY);
		Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
		enemyList.add(anEnemy);
	}
	
	public void EnemiesAdvance(double elapsedTime) {
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy anEnemy = enemyList.get(i);
			anEnemy.advance(checkPoints, elapsedTime); //takes checkpoint... calculates speed*elapsed time.. if you have reached most current checkpoint, checkpoint++
		}
	}
	
	public void removeKilledEnemies() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).isKilled() ) {
				toBeRemoved.add(enemyList.get(i)); //add enemy to a dead enemy list... don't change size of list 
			}
		};
		enemyList.removeAll(toBeRemoved);
	}
	
	public void EnemiesReachedEnd() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).hasReached(map.getEndPoint(OFFSETX, OFFSETY)) ) {
				enemyList.get(i).attack(player);
				toBeRemoved.add(enemyList.get(i));
			}
		};
		enemyList.removeAll(toBeRemoved); //add the enemy to the arraylist 
	}
	
	public void addDefender(Defender aDefense) {
		defenderList.add(aDefense);
	}
	
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
