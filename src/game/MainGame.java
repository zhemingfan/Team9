package game;
import java.util.ArrayList;
import java.util.Random;

import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import javafx.scene.media.AudioClip;
import parents.Enemy;
import parents.Point;
import parents.Tower;

public class MainGame {

	public enum GameMode{ SURVIVAL, STORY};
	private static final double OFFSETX = 50, OFFSETY = 50;
	private static final int MAX_WAVES_STORYMODE =  5;

	private GameMode currentMode = GameMode.SURVIVAL;
	private Map map = new Map();
	private Player player = new Player(60, 30);
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>(); //List for spawning created enemies

	public AudioClip extinguisher = new AudioClip(this.getClass().getResource("/sound/extinguisher.mp3").toString());

	private int waveCounter = 0;
	private ArrayList<Enemy> waveList = new ArrayList<Enemy>();  //List for CREATING ENEMIES

	public Player getPlayer() {
		return player;
	}

	public Map getMap() {
		return map;
	}

	/**Method used in main screen's buttons for determining which map the player wants to play
	 * @param chosenMapLayout - A string that will help determine which map the player wants to play
	 */
	public void customizeGrid(String chosenMapLayout) {
	  if (chosenMapLayout.equals("ZIGZAG") ) {
			map.makeZigZagGrid();
		}
	  if (chosenMapLayout.equals("LOOPY") ){
			map.makeLoopyGrid();
	}
	}

	/**Function for buttons that select game mode; Story for set number of waves, survival for unlimited waves
	 * @param chosenMode - String that determines which mode to use
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
	 * Method for determining when the game ends in cases such as player death or completing all story mode waves
	 * @return boolean depending on if the game has ended or not. True if it has, false if it hasn't
	 */
	public boolean isOver() {
		boolean isOver = false;
		if (player.isKilled()) {
			isOver = true;
		}

		if ( this.currentMode.equals(GameMode.STORY) ) {
			if (waveCounter == MainGame.MAX_WAVES_STORYMODE && this.waveList.size() == 0) {
				isOver = true;
			}
		}

		return isOver;
	}

	/**
	 * @return true if the player is still alive and all the story mode waves have passed
	 */
	public boolean wonStoryMode() {
		return ( !player.isKilled() ) && this.waveCounter == MainGame.MAX_WAVES_STORYMODE && this.waveList.size() == 0;
	}

	/**
	 * Method that determines what text to display at the end of the game depending on victory or defeat
	 * @return an endCard depending on if the player won or lost
	 */
	public String getEndingCard() {
		String endCard = new String();
		//This first if statement is for the story mode end cards
		if ( this.currentMode.equals(GameMode.STORY) ) {
			if (this.wonStoryMode()) {
				endCard = "YOU WON!";
			} else {
				endCard = "YOU FAILED TO SURVIVE " + (MainGame.MAX_WAVES_STORYMODE - 1)+ " WAVES! ";
			}
		}

		//This if statement is for the survival mode end cards
		//Since survival mode has infinite waves, it only needs an end card that tells you
		//how many waves you lasted
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

	/**Method that checks if the attempted tower placement is valid depending on various factors/
	 * Player must have enough money AND the tile has to be an empty, non-path tile
	 * @param aTower - The tower in question. Gets the price
	 * @param row - The row/x-coordinate of the desired placement location
	 * @param column - The column/y-coordinate of the desired placement location
	 * @return - True if the price is affordable and the location is valid, false otherwise
	 */
	public boolean canPurchaseandPlaceTower(Tower aTower, int row, int column){
		return player.enoughFunds(aTower.getPrice()) && map.canPlaceDefense(row, column);
	}

	/** Updates enemyList of randomly chosen enemies from Fire Class, Lava Class, or Spirit.
	   * @param wave Wave Number
	   */
	public void createEnemyList() {
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
	/*
	 * Generate a generic enemy and add it to the enemyList
	 */
	public Enemy spawnEnemies() {
		if (waveList.size() == 0) { //If all enemies are dead..
			waveCounter += 1;		//...advance to next wave
			this.createEnemyList(); //...and randomize an enemy list
		}
		Enemy anEnemy = this.waveList.get(waveList.size() - 1);
		anEnemy.attachPath(map.getCheckPoints(OFFSETX, OFFSETY));
		enemyList.add(anEnemy); //Adds enemy to created list
		waveList.remove(anEnemy); //Removes enemy from creation list
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
