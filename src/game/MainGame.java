package game;
import java.util.ArrayList;
import java.util.Random;

import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import parents.Enemy;
import parents.Point;
import parents.Tower;

public class MainGame {
	
	public enum GameMode{ SURVIVAL, STORY};
	private static final double OFFSETX = 50, OFFSETY = 50;
	private static final int MAX_WAVES_STORYMODE =  5;
	
	private GameMode currentMode = GameMode.SURVIVAL;
	private Map map = new Map();
	private Player player = new Player();
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private Point[] checkPoints = map.getCheckPoints(OFFSETX, OFFSETY);
	
	private int waveCounter = 1;
	private ArrayList<Enemy> waveList = new ArrayList<Enemy>();
	
	public Player getPlayer() {
		return player;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void customizeGrid(String chosenMapLayout) {
	  if (chosenMapLayout.equals("ZIGZAG") ) {
			map.makeZigZagGrid();
		} else if (chosenMapLayout.equals("LOOPY") ){
			map.makeLoopyGrid();
		}
	}
	
	public void setGameMode(String chosenMode) {
		if (chosenMode.equals("STORY") ) {
			this.currentMode = GameMode.STORY;
		}
		if (chosenMode.equals("SURVIVAL") ){
			this.currentMode = GameMode.SURVIVAL;
		}
	}
	
	
	
	public boolean isOver() {
		boolean isOver = false;
		if (player.isKilled()) {
			isOver = true;
		}
		if ( this.currentMode.equals(GameMode.STORY) ) {
			if (waveCounter == MainGame.MAX_WAVES_STORYMODE) {
				isOver = true;
			}
		}
		
		return isOver;
	}
	
	public boolean wonStoryMode() {
		return ( !player.isKilled() ) && this.waveCounter == MainGame.MAX_WAVES_STORYMODE;
	}
	
	public String getEndingCard() {
		String endCard = new String();
		if ( this.currentMode.equals(GameMode.STORY) ) {
			if (this.wonStoryMode()) {
				endCard = "YOU WON!";
			} else {
				endCard = "YOU FAILED TO SURVIVE " + (MainGame.MAX_WAVES_STORYMODE - 1)+ " WAVES! ";
			}
		}
		
		if ( this.currentMode.equals(GameMode.SURVIVAL) ) {
			endCard = "YOU LASTED " + this.waveCounter + " WAVES! ";
		}
		
		return endCard;
	}

	public int getWaveNumber() {
		return waveCounter;
	}
	public ArrayList<Tower> getTowerList(){
		return towerList;}
	
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}
	
	public boolean canPurchaseandPlaceTower(Tower aTower, int row, int column){
		return player.enoughFunds(aTower.getPrice()) && map.canPlaceDefense(row, column);
	}
	
	/** Updates enemyList of randomly chosen enemies from Fire Class, Lava Class, or Spirit.
	   * @param wave Wave Number
	   */
	public void createEnemyList() {
	    int enemyCount = waveCounter*10;
	    int i = 0;
	    Point start = map.getStartPoint(OFFSETX, OFFSETY);
		Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
	    while (i < enemyCount) {
	      int n = new Random().nextInt(3);
	      if (n == 0) {
	    	  anEnemy = new Fire(start.getXCoord(), start.getYCoord());
	      }
	      else if (n == 1) {
	    	  anEnemy = new Lava(start.getXCoord(), start.getYCoord());
	      }
	      else {
	    	  anEnemy = new Spirit(start.getXCoord(), start.getYCoord());
	      }
	      i++;
	      waveList.add(anEnemy);
	    }
  	}
	/*
	 * Generate a generic enemy and add it to the enemyList
	 */
	public Enemy spawnEnemies() {
		if (waveList.size() == 0) {
			waveCounter += 1;
			this.createEnemyList();
			System.out.println(waveCounter);
		}
		Enemy anEnemy = this.waveList.get(waveList.size() - 1);
		anEnemy.attachPath(map.getCheckPoints(OFFSETX, OFFSETY));
		enemyList.add(anEnemy);
		waveList.remove(anEnemy);
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
	public ArrayList<Enemy> removeKilledEnemies() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy anEnemy = enemyList.get(i);
			if (anEnemy.isKilled() ) {
				toBeRemoved.add(anEnemy);
				this.getPlayer().gainMoney(anEnemy.getBounty());
			}
			
		};
		enemyList.removeAll(toBeRemoved);
		
		return toBeRemoved;
	}
	
	/**
	 * Check if any enemies has reached the end. Have the enemies deal damage to the Player. Remove these enemies.
	 */
	
	public ArrayList<Enemy> removeEnemiesReachedEnd() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).hasReached(map.getEndPoint(OFFSETX, OFFSETY)) ) {
				enemyList.get(i).attack(player);
				toBeRemoved.add(enemyList.get(i));
			}
		};
		enemyList.removeAll(toBeRemoved);
		return toBeRemoved;
	}
	
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
