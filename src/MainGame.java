import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MainGame extends GameInterface {
	private static final double OFFSETX = 50, OFFSETY = 50;
	
	private Map map = new Map();
	private Player player = new Player(); // has to be new player
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private Point[] checkPoints = map.getCheckPoints(OFFSETX, OFFSETY);
	
	
	public Map getMap() {
		return map;
	}
	
	public ArrayList<Tower> getTowerList(){
		return towerList;}
	
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}
	
	/**
	 * Generate a generic enemy and add it to the enemyList
	 */
	public Enemy spawnEnemies() {
		Point start = map.getStartPoint(OFFSETX, OFFSETY);
		Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
		try {
			anEnemy.attachPath(map.getCheckPoints(OFFSETX, OFFSETY));
			enemyList.add(anEnemy);
		} catch (NullPointerException npr) {
			System.out.println(anEnemy.toString());
		}
		return anEnemy;
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
	public ArrayList<Enemy> removeEnemies() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy anEnemy = enemyList.get(i);
			if (anEnemy.isKilled() ) {
				toBeRemoved.add(anEnemy);
			
			}
			if (anEnemy.hasReached(map.getEndPoint(OFFSETX, OFFSETY)) ) {
				anEnemy.attack(player);
				toBeRemoved.add(anEnemy);
			}
			
		};
		enemyList.removeAll(toBeRemoved);
		
		return toBeRemoved;
	}
	
	/**
	 * Check if any enemies has reached the end. Have the enemies deal damage to the Player. Remove these enemies.
	 */
	/*
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
	*/
	public void addDefender(Tower aDefense) {
		towerList.add(aDefense);
	}
	
	/**
	 * Have each defender find their target enemy and deal damage to the target 
	 */
	public ArrayList<Point[] > DefendersAttackEnemies() {
		ArrayList<Point[]> pairList = new ArrayList<Point[]>();
		for (int i = 0; i < towerList.size(); i++) {
			Tower aTower = towerList.get(i);
			Enemy target = aTower.findTarget(enemyList);
			if (target != null) {
				aTower.attack(target);
				Point[] pair = new Point[] {aTower, target};
				pairList.add(pair);
			}
		}
		return pairList;
	}
	/*
	public static void main(String[] args) {
		long elapsedTime = 1;
    	int counter = 0;
    	MainGame GAME = new MainGame();
    	GAME.spawnEnemies();
    	while (1 == 1) {
	    	if ( counter == 0 || counter % 300 == 0) {
	    		Enemy spawned = GAME.spawnEnemies();
	    		System.out.println(spawned.toString());
				
	    	}
	        
	    	if ( counter % 60 == 0) {
	    		for (int i = 0; i < GAME.enemyList.size(); i++) {
	    			Enemy anEnemy = GAME.enemyList.get(i);
	    			System.out.println(anEnemy.toString());
	    		}
	        	GAME.EnemiesAdvance(elapsedTime);
	        	GAME.removeKilledEnemies();
	        	GAME.EnemiesReachedEnd();

	    	}
	    	counter += 1;
    	}
	}*/
}
