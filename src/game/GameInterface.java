package game;

import enemies.Demon;
import enemies.Fire;
import enemies.Lava;
import enemies.Spirit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.text.TextAlignment;
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

	public static final int WINDOWWIDTH = 1200, WINDOWHEIGHT = 800 ;
	public static final int BoardWIDTH = 800, BoardHEIGHT = 800 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 80, OFFSETY = 80;
	public static final int TILESIZE = 80;
	public static final double buttonMultiplier = 1.75;

	
	public static final Image rainSpell = new Image("/img/RainSpell.png");
	public static final Image defenderIce = new Image("/img/TowerIce.png");
	public static final Image defenderWaterSprite = new Image("/img/TowerWater.png");
	public static final Image defenderWind = new Image("/img/TowerWind.png");
	public static final Image defenderSamurai = new Image("/img/TowerSamurai.png");
	
	public static final Image loopMap = new Image("/img/LoopyMap.png");
	public static final Image zigzagMap = new Image("/img/ZigZagMap.png");
	
	public static final Image utilityPaneBG = new Image("/img/utilityPaneBG.jpg");
	public static final Image gameStartBG = new Image("/img/StartBG.png");
	public static final Image woodBlock = new Image("/img/woodBlock.png");
	
	public static final Image pauseButtonPicture = new Image("/img/pauseButton.png");
	public static final Image resumeButtonPicture = new Image("/img/resumeButton.png");
	public static final Image quitButtonPicture = new Image("/img/quitButton.png");
	
	
	public AudioClip fireAlarm = new AudioClip(this.getClass().getResource("/sound/fireAlarm.mp3").toString());
	
	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameLoopGUI loopGUI = new GameLoopGUI();
		initGame(primaryStage, loopGUI);
	}

	public void initGame(Stage primaryStage, GameLoopGUI animator) {
		MainGame GAME = animator.getGAME();
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
	    Rectangle containerBG = new Rectangle(WINDOWWIDTH-BoardWIDTH+100,BoardHEIGHT);
	    containerBG.setFill(new ImagePattern(utilityPaneBG));
	    containerBG.setOpacity(0.9);
	    
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

		/* The health component of the utility box. 
		 * Health updates when enemies reach the end of the checkpoint.
		 */
		HBox health = new HBox(); //make the Hbox so that you can set a left and right thing
		Image heart = new Image("/img/PlayerHeart.png");
		Rectangle heartRect = new Rectangle(75, 75, new ImagePattern(heart));
		playerObject.setHealthLabel();
		Label stats_health = new Label("Player's Health   ");
		stats_health.setFont(Font.font("Verdana",FontWeight.BOLD,25));
		stats_health.setTextFill(Color.DARKRED);
		stats_health.setAlignment(Pos.BASELINE_RIGHT);
		playerObject.getHealthLabel().setFont(Font.font("Verdana",FontWeight.BOLD,25));
		playerObject.getHealthLabel().setTextFill(Color.DARKRED);

		health.getChildren().addAll(heartRect,stats_health, playerObject.getHealthLabel());
		utilityPane.getChildren().add(health);

		
		/* The gold component of the utility box.
		 * Gold updates when enemies are killed, granting players respective the monster's respective bounty. Gold
		 * is spent whenever the player purchases a spell or a tower.
		 */
		Image goldimg = new Image("/img/goldCoins.png");
		Rectangle goldRect = new Rectangle(50, 50, new ImagePattern(goldimg));
		Label stats_gold = new Label("Player's Gold");
		stats_gold.setFont(Font.font("Verdana",FontWeight.BOLD,25));
		playerObject.setMoneyLabel();

		playerObject.getMoneyLabel().setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(playerObject.getMoneyLabel(), 20.0);
		AnchorPane.setRightAnchor(playerObject.getMoneyLabel(), 0.0);
		playerObject.getMoneyLabel().setAlignment(Pos.CENTER);
		playerObject.getMoneyLabel().setFont(Font.font("Verdana",FontWeight.BOLD,25));
		playerObject.getMoneyLabel().setTextFill(Color.GOLD);




		HBox gold = new HBox(); //make the Hbox so that you can set a left and right thing
		utilityPane.getChildren().add(gold);

		gold.setSpacing(23);

		gold.getChildren().addAll(goldRect ,stats_gold, playerObject.getMoneyLabel());
		
		/* Creating the buttons to place towers
		 */
		
		/*
		 * LABELS FOR ALL THE BUTTONS
		 * 
		 */
	

		HBox waterLabel = new HBox();
		Label waterDescription = new Label(new TowerWater().toString()); //The text describing cost, damage, etc
		waterDescription.setFont(Font.font("Verdana",FontWeight.BOLD,12)); //Styling the text described above
		waterDescription.setTextFill(Color.BLACK);
		
		
		
		
		
		// Water button
		Button placeWaterButton = new Button();
	    placeWaterButton.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
		Button placeWaterButton1 = new Button("", new ImageView(defenderWaterSprite));
	    BackgroundImage bImageWater = new BackgroundImage(woodBlock, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(placeWaterButton.getWidth(), placeWaterButton.getHeight(), true, true, true, false));
	    Background backGroundWater = new Background(bImageWater);
	    placeWaterButton1.setBackground(backGroundWater);
	    placeWaterButton1.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
	    placeWaterButton1.setOnAction(new PlaceTowerHandler(placeWaterButton1, mainboard, foreground,
	    		new TowerWater(), GAME) );
	    waterLabel.getChildren().addAll(placeWaterButton1, waterDescription);

	    
	    // Wind button
	    
	    HBox windLabel = new HBox();
	  	Label windDescription = new Label(new TowerWind().toString());
	  	windDescription.setFont(Font.font("Verdana",FontWeight.BOLD,12));
	  	windDescription.setTextFill(Color.BLACK);
	   
		Button placeWindButton = new Button();
	    placeWindButton.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
		Button placeWindButton1 = new Button("", new ImageView(defenderWind));
	    BackgroundImage bImageWind = new BackgroundImage(woodBlock, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(placeWindButton.getWidth(), placeWindButton.getHeight(), true, true, true, false));
	    Background backGroundWind = new Background(bImageWind);
	    placeWindButton1.setBackground(backGroundWind);
	    placeWindButton1.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
	    placeWindButton1.setOnAction(new PlaceTowerHandler(placeWindButton1, mainboard, foreground,
	    		new TowerWind(), GAME) );
	    waterLabel.getChildren().addAll(placeWindButton1, windDescription);
	   
	    
	    // Ice button
		

		HBox iceLabel = new HBox();
		Label iceDescription = new Label(new TowerIce().toString());
		iceDescription.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		iceDescription.setTextFill(Color.BLACK);
		
		
		Button placeIceButton = new Button();
		placeIceButton.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
		Button placeIceButton1 = new Button("", new ImageView(defenderIce));
	    BackgroundImage bImageIce = new BackgroundImage(woodBlock, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(placeIceButton.getWidth(), placeIceButton.getHeight(), true, true, true, false));
	    Background backGroundIce = new Background(bImageIce);
	    placeIceButton1.setBackground(backGroundIce);
	    placeIceButton1.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
	    placeIceButton1.setOnAction(new PlaceTowerHandler(placeIceButton1, mainboard, foreground,
	    		new TowerIce(), GAME) );
	    iceLabel.getChildren().addAll(placeIceButton1, iceDescription);
		
	    // Samurai button

		HBox samuraiLabel = new HBox();
		Label samuraiDescription = new Label(new TowerSamurai().toString());
		samuraiDescription.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		samuraiDescription.setTextFill(Color.BLACK);
		samuraiDescription.setTextAlignment(TextAlignment.LEFT);
		
		Button placeSamuraiButton = new Button();
		placeSamuraiButton.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
		Button placeSamuraiButton1 = new Button("", new ImageView(defenderSamurai));
	    BackgroundImage bImageSamurai = new BackgroundImage(woodBlock, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(placeSamuraiButton.getWidth(), placeSamuraiButton.getHeight(), true, true, true, false));
	    Background backGroundSamurai = new Background(bImageSamurai);
	    placeSamuraiButton1.setBackground(backGroundSamurai);
	    placeSamuraiButton1.setPrefSize(TILESIZE*buttonMultiplier, TILESIZE*buttonMultiplier);
	    placeSamuraiButton1.setOnAction(new PlaceTowerHandler(placeSamuraiButton1, mainboard, foreground,
	    		new TowerSamurai(), GAME) );
	    iceLabel.getChildren().addAll(placeSamuraiButton1, samuraiDescription);
		
	    // Rain spell

		HBox rainSpellLabel = new HBox();
		Label rainSpellDescription = new Label(new RainSpell().toString());
		rainSpellDescription.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		rainSpellDescription.setTextFill(Color.BLACK);
		rainSpellDescription.setTextAlignment(TextAlignment.LEFT);
		
		Button placeRainButton = new Button();
		placeRainButton.setPrefSize(TILESIZE*2.5, TILESIZE*1.5);
		Button placeRainButton1 = new Button("", new ImageView(rainSpell));
	    BackgroundImage bImageRain = new BackgroundImage(woodBlock, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(placeRainButton.getWidth(), placeRainButton.getHeight(), true, true, true, false));
	    Background backGroundRain = new Background(bImageRain);
	    placeRainButton1.setBackground(backGroundRain);
	    placeRainButton1.setPrefSize(TILESIZE*2.5, TILESIZE*1.5);
	    placeRainButton1.setOnAction(new CastSpellHandler(GAME));
	    rainSpellLabel.setAlignment(Pos.CENTER);
	    
	    rainSpellLabel.getChildren().addAll(placeRainButton1, rainSpellDescription);
		utilityPane.getChildren().addAll(waterLabel, iceLabel, windLabel, samuraiLabel, rainSpellLabel);
		
		animator.setForeground(foreground);
		animator.setPrimaryStage(primaryStage);
		animator.setRoot(root);
		
		Button pauseButton = new Button();
		pauseButton.setPrefSize(TILESIZE*2.5, TILESIZE*1.5);
	    BackgroundImage bImagePauseButton = new BackgroundImage(pauseButtonPicture, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(pauseButton.getWidth(), pauseButton.getHeight(), true, true, true, false));
	    Background backGroundPauseButton = new Background(bImagePauseButton);
	    pauseButton.setBackground(backGroundPauseButton);

		Button resumeButton = new Button();
		resumeButton.setPrefSize(TILESIZE*2.5, TILESIZE*1.5);
	    BackgroundImage bImageResumeButton = new BackgroundImage(resumeButtonPicture, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(resumeButton.getWidth(), resumeButton.getHeight(), true, true, true, false));
	    Background backGroundResumeButton = new Background(bImageResumeButton);
	    resumeButton.setBackground(backGroundResumeButton);
		
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			int clickCounter = 0;
			public void handle(ActionEvent event) {
				clickCounter += 1;
				if (clickCounter%2 != 0 ) {
					animator.stop();
					pauseButton.setBackground(backGroundResumeButton);

					
				} else {
					animator.start();
					pauseButton.setBackground(backGroundPauseButton);

				}

			}
        });
        
		Button quitButton = new Button();
		quitButton.setPrefSize(TILESIZE*2.5, TILESIZE*1.5);
	    BackgroundImage bImageQuitButton = new BackgroundImage(quitButtonPicture, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(quitButton.getWidth(), quitButton.getHeight(), true, true, true, false));
	    Background backGroundQuitButton = new Background(bImageQuitButton);
	    quitButton.setBackground(backGroundQuitButton);


        quitButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				animator.stop();
	        	Pane endTitle = animator.createEndScreen(primaryStage);
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

		
		VBox initGameButtons = new VBox();
		initGameButtons.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		initGameButtons.setAlignment(Pos.CENTER);

		HBox chooseMapButtons = new HBox();
		chooseMapButtons.setAlignment(Pos.CENTER);
		ToggleGroup mapGroup = new ToggleGroup();
		ToggleButton loopMapButton = new ToggleButton("", new ImageView(loopMap));
		loopMapButton.setOnAction(new ChooseMapHandler(background, "LOOPY", GAME ));
		loopMapButton.setPadding(Insets.EMPTY);
		
		ToggleButton zigzagMapButton = new ToggleButton("", new ImageView(zigzagMap));
		zigzagMapButton.setOnAction(new ChooseMapHandler(background, "ZIGZAG", GAME ));
		loopMapButton.setToggleGroup(mapGroup);
		zigzagMapButton.setToggleGroup(mapGroup);
		zigzagMapButton.setPadding(Insets.EMPTY);
		chooseMapButtons.getChildren().addAll(zigzagMapButton, loopMapButton);

		Button exitButton = new Button("EXIT");
		exitButton.setOnAction(e -> primaryStage.close());
		initGameButtons.getChildren().addAll(chooseMapButtons, startStoryButton, startSurvivalButton,
				exitButton);
		startUpMenu.getChildren().addAll(startButtonLayer, initGameButtons);

        primaryStage.show();

	}

}
