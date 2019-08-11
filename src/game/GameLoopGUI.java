package game;

import java.util.ArrayList;
import java.util.Random;
import enemies.Demon;
import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

	public static final int WINDOWWIDTH = 1300, WINDOWHEIGHT = 800 ;
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

	public static final Image exitBG = new Image("/img/button/exitButton.png");
	public static final Image newGameBG = new Image("/img/button/newGame.png");

	public static double ENEMYSPEEDSCALAR = 0.5;
	public static int TOWERATTACKRATE = 110; //one per 110 frames
	public int ENEMYSPAWNRATE = 220; //one per 220 frames

	public MainGame GAME = new MainGame();
	public Player playerObject = GAME.getPlayer();
	public Scene startUpScene, gamePlayScene;


	public static int frameCounter = 0;
	private GameInterface GUI = new GameInterface();
	private double elapsedTime =  ENEMYSPEEDSCALAR;
	private Stage primaryStage = new Stage();
	private Pane foreground = new Pane();
	private StackPane root = new StackPane();
	private int currentWave = 0;

	public GameLoopGUI(GameInterface GUI, Stage primaryStage, StackPane root, Pane foreground) {
		this.GUI = GUI;
		this.root  = root;
		this.primaryStage = primaryStage;
		this.foreground = foreground;
	}

    public GameLoopGUI() {

	}

	public void handle(long arg0) {
		if (GAME.getWaveNumber() != currentWave) {
			this.stop();
			declareWaveNumber();
			currentWave = GAME.getWaveNumber();
		}

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
       addEnemyEffect();
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
    	   Pane endTitle = createEndScreen(primaryStage, root);
    	   root.getChildren().add(endTitle);
       }
    }

	public Pane createEndScreen(Stage primaryStage, StackPane root) {
		Pane endScreen = new Pane();
		endScreen.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		Rectangle endBGLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);
                endBGLayer.setFill(Color.WHITE);
                endBGLayer.setOpacity(0.75);
		endScreen.setPrefSize(BoardWIDTH, BoardHEIGHT);
		Label endTitleCard = new Label( GAME.getEndingCard());
		endTitleCard.setFont(Font.font("Verdana",FontWeight.BOLD,25));
		Button restartButton = new Button("", new ImageView(newGameBG));
	        BackgroundImage bImageRestart = new BackgroundImage(newGameBG,BackgroundRepeat.NO_REPEAT, 
	        		BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
	        Background backGroundRestart = new Background(bImageRestart);
	        restartButton.setBackground(backGroundRestart);
	        restartButton.setPrefSize(10, 1);
	        restartButton.setOnAction(e -> primaryStage.close());
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					cleanUp();
					root.getChildren().clear();
					GUI.initGame(primaryStage, root, new GameLoopGUI());
				}
			}
		);
		Button exitButton = new Button("", new ImageView(exitBG));
	        BackgroundImage bImageExit = new BackgroundImage(exitBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
	        Background backGroundExit = new Background(bImageExit);
	        exitButton.setBackground(backGroundExit);
	        exitButton.setPrefSize(10, 1);
	        exitButton.setOnAction(e -> primaryStage.close());
		exitButton.setOnAction(e -> primaryStage.close());
		VBox endScreenButtons = new VBox();
		endScreenButtons.setAlignment(Pos.CENTER);
		endScreenButtons.getChildren().addAll(endTitleCard,restartButton,exitButton);
		endScreen.getChildren().addAll(endBGLayer, endScreenButtons);
		endScreenButtons.relocate(WINDOWWIDTH/3 , 
				WINDOWHEIGHT/3);
		return endScreen;
	}
	
	public void declareWaveNumber() {
		VBox waveDeclarePane = new VBox();
		waveDeclarePane.setPrefSize(BoardWIDTH, BoardHEIGHT);
		root.getChildren().add(waveDeclarePane);
		Label waveLabel = new Label("WAVE" + GAME.getWaveNumber());
		waveLabel.setFont(Font.font("Verdana",FontWeight.BOLD,50));
		waveLabel.setTextFill(Color.ORANGERED);
		waveDeclarePane.getChildren().add(waveLabel);
		waveDeclarePane.setAlignment(Pos.CENTER);
		FadeTransition ft = new FadeTransition(Duration.millis(3000), waveDeclarePane);
			ft.setFromValue(1.0);
		    ft.setToValue(0.0);
		    ft.play();
		    ft.setOnFinished((e) -> root.getChildren().remove(waveDeclarePane));
	    this.start();
		
	}
	/**
	 * Creates an entirely new game and resets the state of mode and map to unchosen to require fresh input from player.
	 */
	public void cleanUp() {
		GAME = new MainGame();
		GameLoopGUI.frameCounter = 0;
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
		Random rand = new Random();
		if ( rand.nextInt(1) == 0) {
    		rect.setOpacity(0.8);
    	}
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
    		
    		}

        }
	}
	public void addEnemyEffect() {
		if (GameLoopGUI.frameCounter % 50 == 0) {
			for (int i = 0; i < GAME.getEnemyList().size(); i++) {
        	Enemy anEnemy = GAME.getEnemyList().get(i);
        	Node enemyUI = anEnemy.getNode();
    		if (enemyUI instanceof VBox) {
    			Node sprite = ((VBox)enemyUI).getChildren().get(1);
    			if ( sprite.getOpacity() == 1.0) {
            		sprite.setOpacity(0.85);
            	} else {
            		sprite.setOpacity(1.0);
            		}
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
		if (GAME.getTowerList().size() > 0) {
			for (Tower aTower: GAME.getTowerList()) {
				aTower.getNode().setOpacity(0);Node tracker = aTower.getNode();
				Enemy target = aTower.getTarget();
				if (aTower.getTarget() != null && aTower.enemyIsWithinRange(target) && !target.isKilled()) {
					if (tracker instanceof Line) {
						tracker.setOpacity(0.5);
						((Line)tracker).setStroke(Color.WHITE);
						((Line) tracker).setEndX(target.getXCoord() + TILESIZE/2);
						((Line) tracker).setEndY(target.getYCoord() + TILESIZE/2);
					}
				} else {
					tracker.setOpacity(0);;
				}	
			}
		}
		// Visual effects when Towers attack
		if (pairList.size() > 0) {
			for (Point[] pair: pairList) {
				Point aTower = pair[0];
				Point target = pair[1];
				Node tracker = aTower.getNode();
				if (target != null ) {
					tracker.setOpacity(1.0);
					if (tracker instanceof Line) {
						((Line)tracker).setStroke(Color.DEEPSKYBLUE);
					}
				} else {
					tracker.setOpacity(0.5);
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
	 * @param aTower
	 * @param foreground
	 */
	public void paintTowerOnGUI(Tower aTower, Pane foreground) {
		Rectangle rect = new Rectangle(TILESIZE,TILESIZE);
		Line tracker = new Line();
		tracker.setStartX(aTower.getXCoord() + TILESIZE/2);
		tracker.setStartY(aTower.getYCoord() + TILESIZE/2 );
		tracker.setStrokeWidth(4.0);
		tracker.setOpacity(0.0);
		aTower.setNode(tracker);
		tracker.setStroke(Color.WHITE);
		if (aTower instanceof TowerIce) {
			rect.setFill(new ImagePattern(defenderIce));
			//tracker.setStroke(Color.ALICEBLUE);
		}
		if (aTower instanceof TowerWater) {
			rect.setFill(new ImagePattern(defenderWaterSprite));
			//tracker.setStroke(Color.DEEPSKYBLUE);
		}
		if (aTower instanceof TowerWind) {
			rect.setFill(new ImagePattern(defenderWind));
			//tracker.setStroke(Color.PALEGOLDENROD);
		}
		if (aTower instanceof TowerSamurai) {
			rect.setFill(new ImagePattern(defenderSamurai));
			//tracker.setStroke(Color.RED);
		}
		/*
		RotateTransition rt = new RotateTransition(Duration.millis(1500), rect);
	    rt.setFromAngle(-45);
		rt.setByAngle(60);
	    rt.setCycleCount(Animation.INDEFINITE);
	    rt.setAutoReverse(true);

	    rt.play();
	    */
		
		foreground.getChildren().addAll(aTower.getNode(), rect);
		rect.relocate(aTower.getXCoord(), aTower.getYCoord());

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
