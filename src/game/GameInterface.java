package game;

import java.util.ArrayList;

import enemies.Demon;
import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import parents.Enemy;
import parents.Point;
import parents.Tower;
import spells.CastSpellHandler;
import spells.RainSpell;
import towers.PlaceTowerHandler;
import towers.TowerIce;
import towers.TowerSamurai;
import towers.TowerWater;
import towers.TowerWind;

public class GameInterface extends Application {


	public static final int WINDOWWIDTH = 700, WINDOWHEIGHT = 500 ;
	public static final int BoardWIDTH = 500, BoardHEIGHT = 500 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 50, OFFSETY = 50;
	public static final int TILESIZE = 50;

	public static double ENEMYSPEEDSCALAR = 0.5;
	public static int TOWERATTACKRATE = 110; //one per 110 frames
	public static int ENEMYSPAWNRATE = 250; //one per 250 frames

	public MainGame GAME = new MainGame();
	public Player playerObject = GAME.getPlayer();
	public Scene startUpScene, gamePlayScene;


	public Image enemyFire = new Image("/img/Enemy_Fire.PNG");
	public Image enemySpirit = new Image("/img/Enemy_Spirit.PNG");
	public Image enemyLava = new Image("/img/Enemy_Lava.PNG");
	public Image enemyDemon = new Image("/img/Enemy_Demon.PNG");
	public Image grassTile = new Image("/img/GrassTile.PNG");
	public Image pathTile = new Image("/img/PathTile.PNG");
	public Image rockTile = new Image("/img/RockTile.PNG");
	public Image rainSpell = new Image("/img/Spell_Rain.PNG");
	public Image defenderIce = new Image("/img/Defender_Ice.PNG");
	public Image defenderWaterSprite = new Image("/img/Defender_WaterSprite.PNG");
	public Image defenderWind = new Image("/img/Defender_Wind.PNG");
	public Image defenderSamurai = new Image("/img/Defender_Samurai.PNG");
	public Image woodBlock = new Image("/img/woodBlock.jpeg");
	public Image loopMap = new Image("/img/LoopyMap.png");
	public Image zigzagMap = new Image("/img/ZigZagMap.png");
	public Image windProj = new Image("/img/projectileWind.png");
	public Image waterProj = new Image("/img/projectileWater.png");
	public Image iceProj = new Image("/img/projectileWind.png");
	public Image utilityPaneBG = new Image("/img/utilityPaneBG.jpg");
	public Image gameStartBG = new Image("/img/gameStartBG.jpg");

	//Sounds from soundbible
	public AudioClip fireAlarm = new AudioClip(this.getClass().getResource("/sound/fireAlarm.mp3").toString());

	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initGame(primaryStage);
	}

	public void initGame(Stage primaryStage) {

		// The basic Layout of the Screen
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT);
	    primaryStage.setTitle("FireForce No9");
	    primaryStage.setScene(scene);

	    //setting up startup Menu
		Pane startUpMenu = new Pane();
		HBox gamePlayLayer = new HBox();
		root.getChildren().addAll(gamePlayLayer, startUpMenu);

		gamePlayLayer.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		startUpMenu.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);


	    StackPane mainboard = new StackPane();
	    Rectangle containerBG = new Rectangle(WINDOWWIDTH-BoardWIDTH,BoardHEIGHT);
	    containerBG.setFill(new ImagePattern(utilityPaneBG));
	    StackPane utilityPaneContainer = new StackPane();
	    VBox utilityPane = new VBox();
	    utilityPaneContainer.getChildren().addAll(containerBG, utilityPane);
		gamePlayLayer.getChildren().addAll(mainboard, utilityPaneContainer);

		//Setting up the mainboard with grid background and foreground where enemies move
		GridPane background = new GridPane();
		Pane foreground = new Pane();
		mainboard.getChildren().addAll(background, foreground);

		background.setPrefSize(BoardWIDTH, BoardHEIGHT);
		foreground.setPrefSize(BoardWIDTH, BoardHEIGHT);

		// Setting up the place tower button in the utilityPane
		// PLACEHOLDER: add the Player stats area
		utilityPane.setAlignment(Pos.CENTER);
	    Player playerObject = GAME.getPlayer();

		// Health
		HBox health = new HBox(); //make the Hbox so that you can set a left and right thing
		utilityPane.getChildren().add(health);
		playerObject.setHealthLabel();
		Label stats_health = new Label("Player's Health   ");
		stats_health.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		stats_health.setTextFill(Color.BLACK);
		stats_health.setAlignment(Pos.BASELINE_RIGHT);
		playerObject.getHealthLabel().setFont(Font.font("Verdana",FontWeight.BOLD,12));
		playerObject.getHealthLabel().setTextFill(Color.BLACK);

		health.getChildren().addAll(stats_health, playerObject.getHealthLabel());

		//Gold
		Label stats_gold = new Label("Player's Gold");
		stats_gold.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		stats_gold.setTextFill(Color.BLACK);
		playerObject.setMoneyLabel();
		//Label playerObject.getMoneyLabel() = new Label(playerObject.toStringMoney());

		playerObject.getMoneyLabel().setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(playerObject.getMoneyLabel(), 0.0);
		AnchorPane.setRightAnchor(playerObject.getMoneyLabel(), 0.0);
		playerObject.getMoneyLabel().setAlignment(Pos.CENTER);
		playerObject.getMoneyLabel().setFont(Font.font("Verdana",FontWeight.BOLD,12));
		playerObject.getMoneyLabel().setTextFill(Color.BLACK);




		HBox gold = new HBox(); //make the Hbox so that you can set a left and right thing
		utilityPane.getChildren().add(gold);

		gold.setSpacing(23);
		//gold.setStyle("-fx-background-color: #336699;");

		gold.getChildren().add(stats_gold);
		gold.getChildren().add(playerObject.getMoneyLabel());

		// add the Button Handler after you guys have worked things out on that
		HBox waterLabel = new HBox();
		Label waterDescription = new Label(new TowerWater().toString()); //The text describing cost, damage, etc
		waterDescription.setFont(Font.font("Verdana",FontWeight.BOLD,12)); //Styling the text described above
		waterDescription.setTextFill(Color.BLACK);
		Button placeWaterButton = new Button("", new ImageView(defenderWaterSprite));
	    placeWaterButton.setPrefSize(TILESIZE*1.75, TILESIZE*1.75);
	    placeWaterButton.setOnAction(new PlaceTowerHandler(placeWaterButton, mainboard, foreground,
	    		new TowerWater(), GAME) );
	   waterLabel.getChildren().addAll(placeWaterButton, waterDescription);

		HBox windLabel = new HBox();
		Label windDescription = new Label(new TowerWind().toString());
		windDescription.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		windDescription.setTextFill(Color.BLACK);
	    Button placeWindButton = new Button("", new ImageView(defenderWind));
		placeWindButton.setPrefSize(TILESIZE*1.75, TILESIZE*1.75);
		placeWindButton.setOnAction(new PlaceTowerHandler(placeWindButton, mainboard, foreground,
				new TowerWind(), GAME ) );
		windLabel.getChildren().addAll(placeWindButton, windDescription);

		HBox iceLabel = new HBox();
		Label iceDescription = new Label(new TowerIce().toString());
		iceDescription.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		iceDescription.setTextFill(Color.BLACK);
		Button placeIceButton = new Button("", new ImageView(defenderIce));
		placeIceButton.setPrefSize(TILESIZE*1.75, TILESIZE*1.75);
		placeIceButton.setOnAction(new PlaceTowerHandler(placeIceButton, mainboard, foreground,
				new TowerIce(), GAME) );
		iceLabel.getChildren().addAll(placeIceButton, iceDescription);

		HBox samuraiLabel = new HBox();
		Label samuraiDescription = new Label(new TowerSamurai().toString());
		samuraiDescription.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		samuraiDescription.setTextFill(Color.BLACK);
		Button placeSamuraiButton = new Button("", new ImageView(defenderSamurai));
		placeSamuraiButton.setPrefSize(TILESIZE*1.75, TILESIZE*1.75);
		placeSamuraiButton.setOnAction(new PlaceTowerHandler(placeSamuraiButton, mainboard, foreground,
				new TowerSamurai(), GAME));
		samuraiLabel.getChildren().addAll(placeSamuraiButton, samuraiDescription);

		HBox rainSpellLabel = new HBox();
		Label rainSpellDescription = new Label(new RainSpell().toString());
		rainSpellDescription.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		rainSpellDescription.setTextFill(Color.BLACK);
		Button placeRainButton = new Button("", new ImageView(rainSpell));
		placeRainButton.setPrefSize(TILESIZE*1.5, TILESIZE*1.5); //Spells are slightly smaller than the tower buttons
		placeRainButton.setOnAction(new CastSpellHandler(GAME));
		rainSpellLabel.getChildren().addAll(placeRainButton, rainSpellDescription);

		utilityPane.getChildren().addAll(waterLabel, iceLabel, windLabel, samuraiLabel, rainSpellLabel);

	    AnimationTimer animator = new AnimationTimer(){
	    	int frameCounter = 0;
	    	double elapsedTime =  ENEMYSPEEDSCALAR;

            public void handle(long arg0) {
            	if ( frameCounter == 0 || frameCounter % ENEMYSPAWNRATE == 0) {
            		Enemy spawned = GAME.spawnEnemies();
            		if (spawned != null) {
            			paintNewEnemy(spawned, foreground);
            		}
            	}

	           GAME.EnemiesAdvance(elapsedTime);

	           if (frameCounter % TOWERATTACKRATE == 0) {
	        	   GAME.DefendersAttackEnemies();
	           }
	           paintEnemyTrackers(foreground, GAME.getTowerList());

	           ArrayList<Enemy> KilledEnemies = GAME.removeKilledEnemies();
	           ArrayList<Enemy> EnemiesReachedEnd = GAME.removeEnemiesReachedEnd();

	           moveEnemiesOnGUI(foreground);
	           cleanRemovedEnemiesfromGUI(KilledEnemies, foreground);
	           cleanRemovedEnemiesfromGUI(EnemiesReachedEnd, foreground);

	           playerObject.moneyLabel.setText(playerObject.toStringMoney());
	           playerObject.getHealthLabel().setText(playerObject.toStringHealth());
	           frameCounter += 1;

	           if(GAME.getGameMode().equals("STORY")) {
	        	   if(frameCounter % 1000 == 0) ENEMYSPAWNRATE -= 10;
	           }
	           else if (frameCounter % 2000 == 0) ENEMYSPAWNRATE -= 10;

	           if (GAME.isOver()) {
	        	   this.stop();
	        	   Pane endTitle = createEndScreen(primaryStage);
	        	   root.getChildren().add(endTitle);
	           }
            }
        };


        Button pauseButton = new Button("PAUSE");
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			int clickCounter = 0;
			public void handle(ActionEvent event) {
				clickCounter += 1;
				if (clickCounter%2 != 0 ) {
					animator.stop();
					pauseButton.setText("RESUME");

				} else {
					animator.start();
					pauseButton.setText("PAUSE");
				}

			}
        });

        Button quitButton = new Button("QUIT");
        quitButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				animator.stop();
	        	Pane endTitle = createEndScreen(primaryStage);
	        	root.getChildren().add(endTitle);
			}
        });

        utilityPane.getChildren().addAll(pauseButton, quitButton);

        Rectangle startButtonLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);
        startButtonLayer.setFill(new ImagePattern(gameStartBG));

        Button startStoryButton = new Button("Start Story");
        startStoryButton.setOnAction(new GameStartButtonHandler(GAME, "STORY", animator, root, startUpMenu, fireAlarm));
        Button startSurvivalButton = new Button("Start Survival");
        startSurvivalButton.setOnAction(new GameStartButtonHandler(GAME, "SURVIVAL",
        													animator, root, startUpMenu, fireAlarm));

		//Button startButton = new Button("Start");
		//startButton.setOnAction(new GameStartButtonHandler(animator, root, startUpMenu, fireAlarm));

		VBox initGameButtons = new VBox();
		initGameButtons.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		initGameButtons.setAlignment(Pos.CENTER);


		//MenuButton chooseModeButton = new MenuButton("Choose Play Mode:");

		/*
		MenuItem storyButton = new MenuItem("STORY MODE");
		storyButton.setOnAction(new ChooseModeHandler(GAME , "STORY", chooseModeButton));
		MenuItem survivalButton = new MenuItem("SURVIVAL MODE");
		survivalButton.setOnAction(new ChooseModeHandler(GAME, "SURVIVAL", chooseModeButton));
		*/

		HBox chooseMapButtons = new HBox();
		chooseMapButtons.setAlignment(Pos.CENTER);
		ToggleGroup mapGroup = new ToggleGroup();
		ToggleButton loopMapButton = new ToggleButton("", new ImageView(loopMap));
		loopMapButton.setOnAction(new ChooseMapHandler(background, "LOOPY", GAME ));
		ToggleButton zigzagMapButton = new ToggleButton("", new ImageView(zigzagMap));
		zigzagMapButton.setOnAction(new ChooseMapHandler(background, "ZIGZAG", GAME ));
		loopMapButton.setToggleGroup(mapGroup);
		zigzagMapButton.setToggleGroup(mapGroup);

		chooseMapButtons.getChildren().addAll(zigzagMapButton, loopMapButton);

		initGameButtons.getChildren().addAll(chooseMapButtons, startStoryButton, startSurvivalButton);
		startUpMenu.getChildren().addAll(startButtonLayer, initGameButtons);

        primaryStage.show();

	}

	/**
	 * Sets up an ending screen with the player's results and a restart button that will initiate a new game if pressed.
	 * @param primaryStage
	 * @return the ending screen if the game is over or if the player chooses to quit.
	 */
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
					initGame(primaryStage);
				}
			}
		);
		endScreen.getChildren().addAll(endBGLayer, endTitleCard, restartButton);
		restartButton.relocate(WINDOWWIDTH/2, WINDOWHEIGHT/2);
		return endScreen;
	}

	/**
	 * Creates an entirely new game and resets the state of mode and map to unchosen to require fresh input from player.
	 */
	public void cleanUp() {
		GAME = new MainGame();
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
    		}

        }
	}

	/**
	 * Creates a line on GUI connecting a tower to the enemy it is targeting.
	 * @param foreground
	 * @param towerList
	 */
	public void paintEnemyTrackers(Pane foreground, ArrayList<Tower> towerList) {
		for (Tower aTower: towerList) {
			Enemy target = aTower.getTarget();
			Node tracker = aTower.getNode();
			if (target != null && !target.isKilled()) {
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
		foreground.getChildren().addAll(aDefender.getNode(), rect);
		rect.relocate(aDefender.getXCoord(), aDefender.getYCoord());

	}

}