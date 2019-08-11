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
import spells.RainSpell;

public class MainGame {

	public enum GameMode{SURVIVAL, STORY};
	private static final double OFFSETX = GameInterface.OFFSETX, OFFSETY = GameInterface.OFFSETY;
	private static final int MAX_WAVES_STORYMODE =  5;

	private GameMode currentMode;
	private Map map = new Map();
	private Player player = new Player(50, 0);
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	public AudioClip enemyDeath = new AudioClip(this.getClass().getResource("/sound/plop.mp3").toString());
	public AudioClip rainCasted = new AudioClip(this.getClass().getResource("/sound/thunderStorm.mp3").toString());

	private int waveCounter = 0;
	private ArrayList<Enemy> waveList = new ArrayList<Enemy>();

	/**
	 * Returns the player instance for the current game.
	 * @return the player instance for current game
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the map instance for the current game.
	 * @return the map instance for the current game.
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Creates a new 2D array to represent the background map according to player's input
	 * @param chosenMapLayout
	 */
	public void customizeGrid(String chosenMapLayout) {
	  if (chosenMapLayout.equals("ZIGZAG") ) {
			map.makeZigZagGrid();
		}
	  if (chosenMapLayout.equals("LOOPY") ){
			map.makeLoopyGrid();
	}
	}
	/**
	 * Returns a string that corresponds to current game mode
	 * @return A string that corresponds to current game mode
	 */
	public String getGameMode() {
		return "" + currentMode;
	}

	/**
	 * Sets the current game mode to the one chosen by the player.
	 * @param chosenMode
	 */
	public void setGameMode(String chosenMode) {
		if (chosenMode.equals("STORY") ) {
			this.currentMode = GameMode.STORY;
		}
		if (chosenMode.equals("SURVIVAL") ){
			this.currentMode = GameMode.SURVIVAL;
		}
	}

	/**
	 * Checks if the game has ended either because the player has been killed or because has won the Story mode.
	 * @return whether the game is over
	 */
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
	/**
	 * Checks if player has won story mode if the mode is Story
	 * @return whether the player has won story mode if the mode is Story
	 */
	public boolean wonStoryMode() {
		return ( !player.isKilled() ) && this.waveCounter == MainGame.MAX_WAVES_STORYMODE
				&& this.enemyList.size() == 0 && this.waveList.size() == 0;
	}

	/**
	 * Returns the ending message to tell the player their result.
	 * @return the ending message
	 */
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

	/**
	 * Returns the current wave number.
	 * @return the current wave number
	 */
	public int getWaveNumber() {
		return waveCounter;
	}

	/**
	 * Returns the list of towers made by player
	 * @return the list of towers made by player
	 */
	public ArrayList<Tower> getTowerList(){
		return towerList;}

	/**
	 * Returns the list of alive enemies
	 * @return the list of alive enemies
	 */
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	/**
	 * Checks if player has enough funds to buy a new tower and if a tower can be placed on the tile at the row and column given.
	 * @param aTower
	 * @param row
	 * @param column
	 * @return whether player can buy and place new tower
	 */
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
				while (i < 20) {
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
				while (i < 35) {
					if(i < 10) anEnemy = new Lava(start.getXCoord(), start.getYCoord());
					else if(i < 25) anEnemy = new Fire(start.getXCoord(), start.getYCoord());
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
					if (i < 10) anEnemy = new Fire(start.getXCoord(), start.getYCoord());
					else if (i < 20) anEnemy = new Lava(start.getXCoord(), start.getYCoord());
					else if (i < 29) anEnemy = new Spirit(start.getXCoord(), start.getYCoord());
					else anEnemy = new Demon(start.getXCoord(), start.getYCoord());
					i++;
					waveList.add(anEnemy);
				}
			}
		}
  	}
	/**
	 * Creates and returns a new enemy by taking an enemy from the current wave list.
	 * This method also updates the alive enemies list and generates new wave list if needed.
	 * @return A new enemy
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
	 * Removes killed enemies from current alive enemies list and returns a list of enemies that also needs to be removed from GUI.
	 * @return List of dead enemies to be removed from GUI
	 */
	public ArrayList<Enemy> removeKilledEnemies() {
		ArrayList<Enemy> toBeRemoved = new ArrayList<Enemy>() ;
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy anEnemy = enemyList.get(i);
			if (anEnemy.isKilled() ) {
				toBeRemoved.add(anEnemy);
				this.getPlayer().gainMoney(anEnemy.getBounty());
				enemyDeath.play();
			}

		};
		enemyList.removeAll(toBeRemoved);
		return toBeRemoved;
	}

	/**
	 * Removes enemies that reached the from current alive enemies list and returns a list of enemies that also needs to be removed from GUI.
	 * This method also calls on the enemies that reached the end to deal damage to player.
	 * @return List of enemies that reached the end to be removed from GUI
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
	 * Commands all towers that have been added to target and attack an enemy in the current alive enemies list.
	 * Returns a list of pairs containing a tower and its targeted enemy.
	 * @return the list of pairs containing a tower and its targeted enemy.
	 */
	public ArrayList<Point[] > DefendersAttackEnemies(int frameCounter) {
		ArrayList<Point[]> pairList = new ArrayList<Point[]>();
		for (int i = 0; i < towerList.size(); i++) {
			Tower aTower = towerList.get(i);
			if ( (frameCounter - aTower.getframeCreated()) % aTower.getAttackRate() == 0) {
				Enemy target = aTower.findTarget(enemyList);
				if (target != null) {
					aTower.attack(target);
					Point[] pair = new Point[] {aTower, target};
					pairList.add(pair);
				}
			}
		}
		return pairList;
	}

	public void spellAttackEnemies() {
		RainSpell rainAttack = new RainSpell();
		if(player.enoughFunds(rainAttack.getPrice())) {
			player.buyDefense(rainAttack.getPrice());
			rainAttack.castSpell(enemyList);
			rainCasted.play();
		}
	}
}
