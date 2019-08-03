package game;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
import javafx.stage.Stage;
import parents.Enemy;
import parents.Tower;
import towers.PlaceTowerHandler;
import towers.TowerIce;
import towers.TowerWater;
import towers.TowerWind;
import towers.TrashTowerHandler;

public class GameInterface extends Application {
	
	public static final int WINDOWWIDTH = 700, WINDOWHEIGHT = 500 ;
	public static final int BoardWIDTH = 500, BoardHEIGHT = 500 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 50, OFFSETY = 50;
	public static final int TILESIZE = 50;
	
	public static double ENEMYSPEEDSCALAR = 1.0;
	public static int TOWERATTACKRATE = 10; //one per 10 frames
	public static int ENEMYSPAWNRATE = 50; //one per 50 frames
	
	public MainGame GAME = new MainGame();
	public Player playerObject = GAME.getPlayer();
	
	public Image enemyFire = new Image("/img/Enemy_Fire.PNG");
	public Image enemySpirit = new Image("/img/Enemy_Spirit.PNG");
	public Image enemyLava = new Image("/img/Enemy_Lava.PNG");
	public Image grassTile = new Image("/img/GrassTile.PNG");
  	public Image pathTile = new Image("/img/PathTile.PNG");
  	public Image rockTile = new Image("/img/RockTile.PNG");
  	public Image defenderIce = new Image("/img/Defender_Ice.PNG");
  	public Image defenderWaterSprite = new Image("/img/Defender_WaterSprite.PNG");
  	public Image defenderWind = new Image("/img/Defender_Wind.PNG");
  	public Image woodBlock = new Image("/img/woodBlock.jpeg");
  	public Image loopMap = new Image("/img/LoopyMap.png");
  	public Image zigzagMap = new Image("/img/ZigZagMap.png");
  	
  	public AudioClip fireAlarm = new AudioClip(this.getClass().getResource("/sound/fireAlarm.mp3").toString());
  	public AudioClip extinguisher = new AudioClip(this.getClass().getResource("/sound/extinguisher.mp3").toString());
  	public AudioClip waterSplash = new AudioClip(this.getClass().getResource("/sound/waterSplash.mp3").toString());
  	public AudioClip thunderStorm = new AudioClip(this.getClass().getResource("/sound/thunderStorm.mp3").toString());
  	public AudioClip iceCrack = new AudioClip(this.getClass().getResource("/sound/iceCrack.mp3").toString());



	public static void main(String[] args) {
		Application.launch();
	}
	public void start(Stage primaryStage) {
		
		// The basic Layout of the Screen
		StackPane root = new StackPane(); 
		Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT);
	    primaryStage.setTitle("Demo");
	    primaryStage.setScene(scene);
		
	    //setting up startup Menu    
		Pane startUpMenu = new Pane();
		HBox gamePlayLayer = new HBox();
		root.getChildren().addAll(gamePlayLayer, startUpMenu);
		
		gamePlayLayer.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		startUpMenu.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);	
		
	    
	    StackPane mainboard = new StackPane();
	    VBox utilityPane = new VBox();
		gamePlayLayer.getChildren().addAll(mainboard, utilityPane);
		
		//Setting up the mainboard with grid background and foreground where enemies move
		GridPane background = new GridPane();
		Pane foreground = new Pane();
		mainboard.getChildren().addAll(background, foreground);
		
		background.setPrefSize(BoardWIDTH, BoardHEIGHT);
		foreground.setPrefSize(BoardWIDTH, BoardHEIGHT);
		/*
		// Get the Grid from MainGame to Draw Background
		Map map = GAME.getMap();
		String[][] grid = map.generateGrid();
		// Setting up the background by reading in the Grid
		
	  	
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				Rectangle rect = new Rectangle(TILESIZE,TILESIZE);
				if (grid[r][c].equals("-") ) rect.setFill(new ImagePattern(grassTile));
				else if (grid[r][c].equals("p") ) rect.setFill(new ImagePattern(pathTile));
				else if (grid[r][c].equals("r") ) rect.setFill(new ImagePattern(rockTile));
				else rect.setFill(new ImagePattern(pathTile));
				background.add(rect, c, r);
		      }
		    };
		*/
		
		// Setting up the place tower button in the utilityPane
		// PLACEHOLDER: add the Player stats area
	    Player playerObject = GAME.getPlayer();

		// Health
		HBox health = new HBox(); //make the Hbox so that you can set a left and right thing
		utilityPane.getChildren().add(health);
		
		playerObject.setHealthLabel();
		Label stats_health = new Label("Player's Health   ");
		stats_health.setAlignment(Pos.BASELINE_RIGHT);
		
		health.getChildren().addAll(stats_health, playerObject.getHealthLabel());

		//Gold
		Label stats_gold = new Label("Player's Gold");
		playerObject.setMoneyLabel();
		//Label playerObject.getMoneyLabel() = new Label(playerObject.toStringMoney());

		playerObject.getMoneyLabel().setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(playerObject.getMoneyLabel(), 0.0);
		AnchorPane.setRightAnchor(playerObject.getMoneyLabel(), 0.0);
		playerObject.getMoneyLabel().setAlignment(Pos.CENTER);

		HBox gold = new HBox(); //make the Hbox so that you can set a left and right thing
		utilityPane.getChildren().add(gold);

		gold.setSpacing(23);
		//gold.setStyle("-fx-background-color: #336699;");

		gold.getChildren().add(stats_gold);
		gold.getChildren().add(playerObject.getMoneyLabel());
		
		// add the Button Handler after you guys have worked things out on that
	
		Button placeWaterButton = new Button("", new ImageView(defenderWaterSprite));
	    placeWaterButton.setPrefSize(TILESIZE*2, TILESIZE*2);
	    placeWaterButton.setOnAction(new PlaceTowerHandler(placeWaterButton, mainboard, foreground, 
	    		new TowerWater(), GAME) );
		
	    Button placeWindButton = new Button("", new ImageView(defenderWind));
		placeWindButton.setPrefSize(TILESIZE*2, TILESIZE*2);
		placeWindButton.setOnAction(new PlaceTowerHandler(placeWindButton, mainboard, foreground, 
				new TowerWind(), GAME ) );
		
		Button placeIceButton = new Button("", new ImageView(defenderIce));
		placeIceButton.setPrefSize(TILESIZE*2, TILESIZE*2);
		placeIceButton.setOnAction(new PlaceTowerHandler(placeIceButton, mainboard, foreground, 
				new TowerIce(), GAME) );
	    	    
		Button trashButton = new Button("TRASH");
		trashButton.setOnAction(new TrashTowerHandler(mainboard) );
		utilityPane.getChildren().addAll(placeWaterButton, placeWindButton, placeIceButton, trashButton);
	    
	    AnimationTimer animator = new AnimationTimer(){
	    	int frameCounter = 0;
	    	double elapsedTime =  ENEMYSPEEDSCALAR;
	    	
            public void handle(long arg0) {	
            	if ( frameCounter == 0 || frameCounter % ENEMYSPAWNRATE == 0) {
            		Enemy spawned = GAME.spawnEnemies();
            		paintNewEnemy(spawned, foreground);
            	}
            	
	           GAME.EnemiesAdvance(elapsedTime);
	           
	           if (frameCounter % TOWERATTACKRATE == 0) {
	        	   GAME.DefendersAttackEnemies();
	        	   
	           }
	           ArrayList<Enemy> KilledEnemies = GAME.removeKilledEnemies();
	           ArrayList<Enemy> EnemiesReachedEnd = GAME.removeEnemiesReachedEnd();
	          
	           moveEnemiesOnGUI(foreground);
	           cleanRemovedEnemiesfromGUI(KilledEnemies, foreground);
	           cleanRemovedEnemiesfromGUI(EnemiesReachedEnd, foreground);
	           
	           playerObject.moneyLabel.setText(playerObject.toStringMoney());
	           playerObject.getHealthLabel().setText(playerObject.toStringHealth());
	           frameCounter += 1;
	           
	           if (GAME.isOver()) {
	        	   this.stop();
	        	   Pane endTitle = createEndScreen();
	        	   root.getChildren().add(endTitle);
	           }  
            } 
        };
        
        Rectangle startButtonLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);	
        startButtonLayer.setFill(Color.WHITE);
		Button startButton = new Button("Start");
		startButton.setOnAction(new GameStartButtonHandler(animator, root, startUpMenu, fireAlarm));
		
		VBox initGameButtons = new VBox();
		
		HBox chooseModeButtons = new HBox();
		Button storyButton = new Button("STORY MODE");
		storyButton.setOnAction(new ChooseModeHandler(GAME , "STORY"));
		Button survivalButton = new Button("SURVIVAL MODE");
		survivalButton.setOnAction(new ChooseModeHandler(GAME, "SURVIVAL"));
		chooseModeButtons.getChildren().addAll(storyButton, survivalButton);
		
		HBox chooseMapButtons = new HBox();
		Button loopMapButton = new Button("", new ImageView(loopMap));
		loopMapButton.setOnAction(new ChooseMapHandler(background, "LOOPY", GAME ));
		Button zigzagMapButton = new Button("", new ImageView(zigzagMap));
		zigzagMapButton.setOnAction(new ChooseMapHandler(background, "ZIGZAG", GAME ));
		chooseMapButtons.getChildren().addAll(zigzagMapButton, loopMapButton);
		
		initGameButtons.getChildren().addAll(chooseModeButtons, chooseMapButtons, startButton);
		startUpMenu.getChildren().addAll(startButtonLayer, initGameButtons);
		
        primaryStage.show();
        
	}
	
	public Pane createEndScreen() {
		Pane endScreen = new Pane();
		endScreen.setPrefSize(BoardWIDTH, BoardHEIGHT);
		Rectangle endBGLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);	
        endBGLayer.setFill(Color.WHITE);
        endBGLayer.setOpacity(0.75);
		endScreen.setPrefSize(BoardWIDTH, BoardHEIGHT);
		Label endTitleCard = new Label( GAME.getEndingCard());
		
		endScreen.getChildren().addAll(endBGLayer, endTitleCard);
		endTitleCard.relocate( WINDOWWIDTH/2 - endTitleCard.getWidth(),
				WINDOWHEIGHT/2 + endTitleCard.getHeight());
		return endScreen;
	}
	
	public void paintNewEnemy(Enemy anEnemy, Pane foreground) {
		VBox container = new VBox();
		anEnemy.setNode(container);
		Rectangle enemyHealthbar = updateHealthBars(anEnemy);

		Rectangle rect = new Rectangle(TILESIZE,TILESIZE);
		if (anEnemy instanceof Fire) rect.setFill(new ImagePattern(enemyFire));
		if (anEnemy instanceof Lava) rect.setFill(new ImagePattern(enemyLava));
		if (anEnemy instanceof Spirit) rect.setFill(new ImagePattern(enemySpirit));
		
		container.getChildren().addAll(enemyHealthbar, rect);
		
		foreground.getChildren().add(anEnemy.getNode());
	}
	
	
	public void moveEnemiesOnGUI(Pane foreground) {
		for (int i = 0; i < GAME.getEnemyList().size(); i++) {
        	Enemy anEnemy = GAME.getEnemyList().get(i);
        	VBox enemyUI = anEnemy.getNode();
        	enemyUI.relocate(anEnemy.getXCoord(), anEnemy.getYCoord());
        	enemyUI.getParent();
    		Rectangle enemyHealthbar = updateHealthBars(anEnemy);
    		enemyUI.getChildren().set(0, enemyHealthbar);
        }
	}
	
	public Rectangle updateHealthBars(Enemy anEnemy) {
		double enemyPercentageHealth = (anEnemy.getEnemyHealth()/anEnemy.getMaxHealth()) * 1;
		Rectangle enemyHealthbar = new Rectangle(TILESIZE*enemyPercentageHealth,3, Color.RED);
		return enemyHealthbar;
	}
	
	public void cleanRemovedEnemiesfromGUI(ArrayList<Enemy> removeable, Pane foreground) {
		for (int i = 0; i < removeable.size(); i++) {
        	Enemy anEnemy = removeable.get(i);
        	Node enemyUI = anEnemy.getNode();
        	foreground.getChildren().remove(enemyUI);
        	extinguisher.play();
        }

	}
	
	public void paintTowerOnGUI(Tower aDefender, Pane foreground) {
		Rectangle rect = new Rectangle(TILESIZE,TILESIZE);
		VBox container = new VBox();
		container.getChildren().add(rect);
		aDefender.setNode(container);
		if (aDefender instanceof TowerIce) rect.setFill(new ImagePattern(defenderIce));
		if (aDefender instanceof TowerWater) rect.setFill(new ImagePattern(defenderWaterSprite));
		if (aDefender instanceof TowerWind) rect.setFill(new ImagePattern(defenderWind));
		foreground.getChildren().add(aDefender.getNode());
		aDefender.getNode().relocate(aDefender.getXCoord(), aDefender.getYCoord());
	}
}
