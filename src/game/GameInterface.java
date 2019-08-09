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
	
	public static final Image rainSpell = new Image("/img/RainSpell.png");
	public static final Image defenderIce = new Image("/img/TowerIce.png");
	public static final Image defenderWaterSprite = new Image("/img/TowerWater.png");
	public static final Image defenderWind = new Image("/img/TowerWind.png");
	public static final Image defenderSamurai = new Image("/img/TowerSamurai.png");
	
	public static final Image woodBlock = new Image("/img/WoodBlock.png");
	public static final Image loopMap = new Image("/img/LoopyMap.png");
	public static final Image zigzagMap = new Image("/img/ZigZagMap.png");
	
	public static final Image utilityPaneBG = new Image("/img/utilityPaneBG.jpg");
	public static final Image gameStartBG = new Image("/img/StartBG.png");
	
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
		Image heart = new Image("/img/PlayerHeart.png");
		Rectangle heartRect = new Rectangle(50, 50, new ImagePattern(heart));
		utilityPane.getChildren().add(health);
		playerObject.setHealthLabel();
		Label stats_health = new Label("Player's Health   ");
		stats_health.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		stats_health.setTextFill(Color.BLACK);
		stats_health.setAlignment(Pos.BASELINE_RIGHT);
		playerObject.getHealthLabel().setFont(Font.font("Verdana",FontWeight.BOLD,12));
		playerObject.getHealthLabel().setTextFill(Color.BLACK);

		health.getChildren().addAll(heartRect,stats_health, playerObject.getHealthLabel());

		//Gold
		Image goldimg = new Image("/img/PlayerHeart.png");
		Rectangle goldRect = new Rectangle(50, 50, new ImagePattern(goldimg));
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

		gold.getChildren().addAll(goldRect ,stats_gold, playerObject.getMoneyLabel());

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
		
		animator.setForeground(foreground);
		animator.setPrimaryStage(primaryStage);
		animator.setRoot(root);
		
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
