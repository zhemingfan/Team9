package game;

import java.util.ArrayList;

import enemies.Demon;
import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import parents.Enemy;
import parents.Point;
import parents.Tower;
import towers.TowerIce;
import towers.TowerSamurai;
import towers.TowerWater;
import towers.TowerWind;

public class GameLoopGUI extends AnimationTimer{
	
	public static final int WINDOWWIDTH = 1200, WINDOWHEIGHT = 800 ;
	public static final int BoardWIDTH = 800, BoardHEIGHT = 800 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 80, OFFSETY = 80;
	public static final int TILESIZE = 80;
	
	public static final Image enemyFire = new Image("/img/EnemyFire.png");
	public static final Image enemySpirit = new Image("/img/EnemySpirit.png");
	public static final Image enemyLava = new Image("/img/EnemyLava.png");
	public static final Image enemyDemon = new Image("/img/EnemyDemon.png");
	
	public static final Image rainSpell = new Image("/img/RainSpell.png");
	public static final Image defenderIce = new Image("/img/TowerIce.png");
	public static final Image defenderWaterSprite = new Image("/img/TowerWater.png");
	public static final Image defenderWind = new Image("/img/TowerWind.png");
	public static final Image defenderSamurai = new Image("/img/TowerSamurai.png");
	
	
	public static double ENEMYSPEEDSCALAR = 0.5;
	public static int TOWERATTACKRATE = 110; //one per 110 frames
	public int ENEMYSPAWNRATE = 100; //one per 250 frames

	public MainGame GAME = new MainGame();
	public Player playerObject = GAME.getPlayer();
	public Scene startUpScene, gamePlayScene;
	

	public static int frameCounter = 0;
	private GameInterface GUI = new GameInterface();
	private double elapsedTime =  ENEMYSPEEDSCALAR;
	private Stage primaryStage = new Stage();
	private Pane foreground = new Pane();
	private StackPane root = new StackPane();
	
	public GameLoopGUI(GameInterface GUI, Stage primaryStage, StackPane root, Pane foreground) {
		this.GUI = GUI;
		this.root  = root;
		this.primaryStage = primaryStage;
		this.foreground = foreground;
	}
	
    public GameLoopGUI() {
		
	}
	
	
    
	public void handle(long arg0) {
    	if ( frameCounter == 0 || frameCounter % ENEMYSPAWNRATE == 0) {
    		Enemy spawned = GAME.spawnEnemies();
    		if (spawned != null) {
    			paintNewEnemy(spawned, foreground);
    		}
    	}

       GAME.EnemiesAdvance(elapsedTime);
       
       ArrayList<Point[]> pairList = new ArrayList<Point[]>();
       
       pairList = GAME.DefendersAttackEnemies(frameCounter);

       paintEnemyTrackers(foreground, pairList);

       ArrayList<Enemy> KilledEnemies = GAME.removeKilledEnemies();
       ArrayList<Enemy> EnemiesReachedEnd = GAME.removeEnemiesReachedEnd();

       moveEnemiesOnGUI(foreground);
       cleanRemovedEnemiesfromGUI(KilledEnemies, foreground);
       cleanRemovedEnemiesfromGUI(EnemiesReachedEnd, foreground);

       playerObject.moneyLabel.setText(playerObject.toStringMoney());
       playerObject.getHealthLabel().setText(playerObject.toStringHealth());
       frameCounter += 1;
       
       
       if(GAME.getGameMode().equals("STORY")) {
    	   if(frameCounter % 1000 == 0 &&  ENEMYSPAWNRATE > 50) ENEMYSPAWNRATE -= 10;
       }
       else if (frameCounter % 2000 == 0 &&  ENEMYSPAWNRATE > 50) ENEMYSPAWNRATE -= 10;
       
       if (GAME.isOver()) {
    	   this.stop();
    	   Pane endTitle = createEndScreen(primaryStage);
    	   root.getChildren().add(endTitle);
       }
    }
	
	public Pane createEndScreen(Stage primaryStage) {
		Pane endScreen = new Pane();
		endScreen.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		Rectangle endBGLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);
        endBGLayer.setFill(Color.WHITE);
        endBGLayer.setOpacity(0.75);
		endScreen.setPrefSize(BoardWIDTH, BoardHEIGHT);
		Label endTitleCard = new Label( GAME.getEndingCard());
		Button restartButton = new Button("NEW GAME?");
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					cleanUp();
					GUI.initGame(primaryStage, new GameLoopGUI());
				}
			}
		);
		Button exitButton = new Button("EXIT");
		exitButton.setOnAction(e -> primaryStage.close());
		endScreen.getChildren().addAll(endBGLayer, endTitleCard, restartButton, exitButton);
		restartButton.relocate(WINDOWWIDTH/2, WINDOWHEIGHT/2);
		return endScreen;
	}
	
	/**
	 * Creates an entirely new game and resets the state of mode and map to unchosen to require fresh input from player.
	 */
	public void cleanUp() {
		GAME = new MainGame();
		GameLoopGUI.frameCounter = 0;
		ChooseModeHandler.modeWasChosen = false;
		ChooseMapHandler.mapWasChosen = false;
	}

	/**
	 * Paints a new Enemy on the foreground when an enemy is spawned.
	 * @param anEnemy
	 * @param foreground
	 */
	public void paintNewEnemy(Enemy anEnemy, Pane foreground) {
		VBox container = new VBox();
		anEnemy.setNode(container);
		Rectangle enemyHealthbar = updateHealthBars(anEnemy);

		Rectangle rect = new Rectangle(TILESIZE,TILESIZE);
		if (anEnemy instanceof Fire) rect.setFill(new ImagePattern(enemyFire));
		if (anEnemy instanceof Lava) rect.setFill(new ImagePattern(enemyLava));
		if (anEnemy instanceof Spirit) rect.setFill(new ImagePattern(enemySpirit));
		if (anEnemy instanceof Demon) rect.setFill(new ImagePattern(enemyDemon));

		container.getChildren().addAll(enemyHealthbar, rect);

		foreground.getChildren().add(anEnemy.getNode());
	}

	/**
	 * Moves current enemies'sprites on the foreground.
	 * @param foreground
	 */
	public void moveEnemiesOnGUI(Pane foreground) {
		for (int i = 0; i < GAME.getEnemyList().size(); i++) {
        	Enemy anEnemy = GAME.getEnemyList().get(i);
        	Node enemyUI = anEnemy.getNode();
        	enemyUI.relocate(anEnemy.getXCoord(), anEnemy.getYCoord());
        	enemyUI.getParent();
    		Rectangle enemyHealthbar = updateHealthBars(anEnemy);
    		if (enemyUI instanceof VBox) {
    			((VBox)enemyUI).getChildren().set(0, enemyHealthbar);
    			Node rect = ((VBox)enemyUI).getChildren().get(1);
    			if (rect.getOpacity() == 1.0) {
    				rect.setOpacity(rect.getOpacity() - 0.5);
    			} else if ( rect.getOpacity() == 0.5){
    				rect.setOpacity(rect.getOpacity() + 0.5);
    			}
    		}

        }
	}

	/**
	 * Creates a line on GUI connecting a tower to the enemy it is targeting.
	 * @param foreground
	 * @param towerList
	 */
	public void paintEnemyTrackers(Pane foreground, ArrayList<Point[]> pairList) {
		if (pairList.size() > 0) {
			for (Point[] pair: pairList) {
				Point aTower = pair[0];
				Point target = pair[1];
				Node tracker = aTower.getNode();
				if (target != null ) {
					if (tracker instanceof Line) {
						tracker.setOpacity(1.0);
						((Line) tracker).setStartX(aTower.getXCoord() + TILESIZE/2);
						((Line) tracker).setStartY(aTower.getYCoord() + TILESIZE/2 );
						((Line) tracker).setEndX(target.getXCoord() + TILESIZE/2);
						((Line) tracker).setEndY(target.getYCoord() + TILESIZE/2);
					}
				} else {
					tracker.setOpacity(0);;
				}
			}
		} else {
			if (GAME.getTowerList().size() > 0) {
				for (Tower aTower: GAME.getTowerList()) {
					aTower.getNode().setOpacity(0);
				}
			}
		}
	}

	/**
	 * Creates a rectangle that represents the enemies' current health.
	 * @param anEnemy
	 * @return the health bar for the enemy
	 */
	public Rectangle updateHealthBars(Enemy anEnemy) {
		double enemyPercentageHealth = (anEnemy.getEnemyHealth()/anEnemy.getMaxHealth()) * 1;
		Rectangle enemyHealthbar = new Rectangle(TILESIZE*enemyPercentageHealth,3, Color.RED);
		return enemyHealthbar;
	}

	/**
	 * Removes the sprite of dead enemies and enemies which have reached the end from the GUI.
	 * @param removeable
	 * @param foreground
	 */

	public void cleanRemovedEnemiesfromGUI(ArrayList<Enemy> removeable, Pane foreground) {
		for (int i = 0; i < removeable.size(); i++) {
        	Enemy anEnemy = removeable.get(i);
        	Node enemyUI = anEnemy.getNode();
        	foreground.getChildren().remove(enemyUI);
        }

	}
	/**
	 * Creates sprite for a tower on the foreground, including the picture that represents the tower and a line which will connect with an enemy when the tower finds a target.
	 * @param aDefender
	 * @param foreground
	 */
	public void paintTowerOnGUI(Tower aDefender, Pane foreground) {
		Rectangle rect = new Rectangle(TILESIZE,TILESIZE);
		Line tracker = new Line();
		tracker.setStrokeWidth(2.0);
		tracker.setOpacity(0.0);
		aDefender.setNode(tracker);
		if (aDefender instanceof TowerIce) {
			rect.setFill(new ImagePattern(defenderIce));
			tracker.setStroke(Color.ALICEBLUE);
		}
		if (aDefender instanceof TowerWater) {
			rect.setFill(new ImagePattern(defenderWaterSprite));
			tracker.setStroke(Color.DEEPSKYBLUE);
		}
		if (aDefender instanceof TowerWind) {
			rect.setFill(new ImagePattern(defenderWind));
			tracker.setStroke(Color.PALEGOLDENROD);
		}
		if (aDefender instanceof TowerSamurai) {
			rect.setFill(new ImagePattern(defenderSamurai));
			tracker.setStroke(Color.RED);
		}
		
		RotateTransition rt = new RotateTransition(Duration.millis(1500), rect);
	    rt.setFromAngle(-45); 
		rt.setByAngle(60);
	    rt.setCycleCount(Animation.INDEFINITE);
	    rt.setAutoReverse(true);
	 
	    rt.play();
	     
		foreground.getChildren().addAll(aDefender.getNode(), rect);
		rect.relocate(aDefender.getXCoord(), aDefender.getYCoord());

	}
	
	public MainGame getGAME() {
		return GAME;
	}
	public void setGAME(MainGame gAME) {
		GAME = gAME;
	}
	public Player getPlayerObject() {
		return playerObject;
	}
	public void setPlayerObject(Player playerObject) {
		this.playerObject = playerObject;
	}
	
	
	public void setGUI(GameInterface gUI) {
		GUI = gUI;
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void setForeground(Pane foreground) {
		this.foreground = foreground;
	}
	
	public void setRoot(StackPane root) {
		this.root = root;
	}
}
