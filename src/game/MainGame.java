package game;
import java.util.ArrayList;
import java.util.Random;

import enemies.Demon;
import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import game.MainGame.GameMode;
import javafx.scene.media.AudioClip;
import parents.Enemy;
import parents.Point;
import parents.Tower;

public class MainGame {

	public enum GameMode{SURVIVAL, STORY};
	private static final double OFFSETX = 50, OFFSETY = 50;
	private static final int MAX_WAVES_STORYMODE =  5;

	private GameMode currentMode;
	private Map map = new Map();
	private Player player = new Player(50, 0);
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	public AudioClip extinguisher = new AudioClip(this.getClass().getResource("/sound/extinguisher.mp3").toString());

	private int waveCounter = 0;
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
		}
	  if (chosenMapLayout.equals("LOOPY") ){
			map.makeLoopyGrid();
	}
	}
	
	public String getGameMode() {
		return "" + currentMode;
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
			if (this.wonStoryMode()) {
				isOver = true;
			}
		}

		return isOver;
	}

	public boolean wonStoryMode() {
		return ( !player.isKilled() ) && this.waveCounter == MainGame.MAX_WAVES_STORYMODE 
				&& this.enemyList.size() == 0 && this.waveList.size() == 0;
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
		if(currentMode == GameMode.SURVIVAL) {
			//For Survival
			//There will be 10 times as many enemies as the wave number.
			//For example, there will be 20 enemies on wave 2 and 30 on wave 3
		    int enemyCount = waveCounter*10;
		    int i = 0;
		    Point start = map.getStartPoint(OFFSETX, OFFSETY);
			Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
		    while (i < enemyCount) {
		    	//The random number will determine what TYPE of enemy to spawn/add to the enemy list.
		    	//0 For the fire sprite, 1 for the lava sprite, and 2 for the spirit/blue fire sprite
		      int n = new Random().nextInt(4);
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
		/////FOR STORY MODE
		else {
			if(waveCounter == 1) {
				int i = 0;
				Point start = map.getStartPoint(OFFSETX, OFFSETY);
				Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
				while (i < 10) {
					anEnemy = new Fire(start.getXCoord(), start.getYCoord());
					i++;
					waveList.add(anEnemy);
				}
			}
			else if(waveCounter == 2) {
				int i = 0;
				Point start = map.getStartPoint(OFFSETX, OFFSETY);
				Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
				while (i < 20) {
					if(i < 10) anEnemy = new Spirit(start.getXCoord(), start.getYCoord());
					else anEnemy = new Fire(start.getXCoord(), start.getYCoord());
					i++;
					waveList.add(anEnemy);
				}
			}
			else if(waveCounter == 3) {
				int i = 0;
				Point start = map.getStartPoint(OFFSETX, OFFSETY);
				Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
				while (i < 25) {
					if(i < 10) anEnemy = new Spirit(start.getXCoord(), start.getYCoord());
					else if (i < 15) anEnemy = new Fire(start.getXCoord(), start.getYCoord());
					else anEnemy = new Lava(start.getXCoord(), start.getYCoord());
					i++;
					waveList.add(anEnemy);
				}
			}
			else if(waveCounter == 4) {
				int i = 0;
				Point start = map.getStartPoint(OFFSETX, OFFSETY);
				Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
				while (i < 25) {
					if(i < 10) anEnemy = new Lava(start.getXCoord(), start.getYCoord());
					else anEnemy = new Spirit(start.getXCoord(), start.getYCoord());
					i++;
					waveList.add(anEnemy);
				}
			}
			else if(waveCounter == 5) {
				int i = 0;
				Point start = map.getStartPoint(OFFSETX, OFFSETY);
				Enemy anEnemy = new Enemy(start.getXCoord(), start.getYCoord());
				while (i < 30) {
					if(i < 1) anEnemy = new Demon(start.getXCoord(), start.getYCoord());
					else if (i < 10) anEnemy = new Fire(start.getXCoord(), start.getYCoord());
					else if (i < 20) anEnemy = new Lava(start.getXCoord(), start.getYCoord());
					else anEnemy = new Spirit(start.getXCoord(), start.getYCoord());
					i++;
					waveList.add(anEnemy);
				}
			}
		}
  	}
	/*
	 * Generate a generic enemy and add it to the enemyList
	 */
	public Enemy spawnEnemies() {
		if (waveList.size() == 0) {
			if (!(this.currentMode.equals(GameMode.STORY) && this.waveCounter == MainGame.MAX_WAVES_STORYMODE)) {
				waveCounter++;
				this.createEnemyList();
				System.out.println(waveCounter);
				player.gainMoney(20);
			}
		}
		Enemy anEnemy = null;
		if (waveList.size() != 0) {
			anEnemy = this.waveList.get(waveList.size() - 1);
			anEnemy.attachPath(map.getCheckPoints(OFFSETX, OFFSETY));
			enemyList.add(anEnemy);
			waveList.remove(anEnemy);
		}
		
		System.out.println("Enemies remaining: " + waveList.size());
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
				extinguisher.play();
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
}
