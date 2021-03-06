package game;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.animation.TranslateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.TextAlignment;
import spells.CastSpellHandler;
import spells.RainSpell;
import towers.PlaceTowerHandler;
import towers.TowerIce;
import towers.TowerSamurai;
import towers.TowerWater;
import towers.TowerWind;

public class GameInterface extends Application {
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	public static final int WINDOWWIDTH = 1300, WINDOWHEIGHT = 800 ;
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

	public static final Image woodBlock = new Image("/img/WoodBlock.png");

	//public static final Image utilityPaneBG = new Image("/img/utilityPaneBG.jpg");
	public static final Image bigGameBG = new Image("/img/gamePlayBG.png");
	public static final Image gameStartBG = new Image("/img/StartBG.png");
	public static final Image loadingScreenBG = new Image("/img/LoadingScreen.png");
	public static final Image screenTitle = new Image("/img/OpeningTitle.png");
	public static final Image creditsRollBG = new Image("/img/creditsRoll.png");

	public static final Image buttonLegendBG = new Image("/img/button/buttonLegend.png");
	public static final Image startButtonBG = new Image("/img/button/startButton.png");
	public static final Image skipButtonBG = new Image("/img/button/skipButton.png");
	public static final Image creditsButtonBG = new Image("/img/button/creditsButton.png");
	public static final Image startStoryBG = new Image("/img/button/startStoryButton.png");
	public static final Image startSurvivalBG = new Image("/img/button/startSurvivalButton.png");
	public static final Image exitBG = new Image("/img/button/exitButton.png");

	public static final Image chooseMapBG = new Image("/img/maps/chooseMap.png");
	public static final Image selectLoopBG = new Image("/img/maps/selectLoop.png");
	public static final Image selectZigZagBG = new Image("/img/maps/selectZigZag.png");

	public static final Image pauseButtonPicture = new Image("/img/pauseButton.png");
	public static final Image resumeButtonPicture = new Image("/img/resumeButton.png");
	public static final Image quitButtonPicture = new Image("/img/quitButton.png");
	
	private boolean qPressed;

	public AudioClip fireAlarm = new AudioClip(this.getClass().getResource("/sound/fireAlarm.mp3").toString());

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setMaxHeight(WINDOWHEIGHT);
		primaryStage.setMaxWidth(WINDOWWIDTH);
		StackPane root = new StackPane();
        Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT);
        primaryStage.setTitle("Fire Force No. 9");
        primaryStage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("/img/EnemyFire.png"));
		primaryStage.getIcons().add(icon);
		
		GameLoopGUI loopGUI = new GameLoopGUI();
		
		//Loading Screen
        Rectangle loadingScreen = new Rectangle(WINDOWWIDTH,WINDOWHEIGHT);
        loadingScreen.setFill(new ImagePattern(loadingScreenBG));
        //Loading Screen Transition
        FadeTransition ft = new FadeTransition(Duration.millis(2000), loadingScreen);
             ft.setFromValue(0);
             ft.setToValue(1.0);
             ft.setCycleCount(2);
             ft.setAutoReverse(true);
             ft.play();
        root.getChildren().add(loadingScreen);
        primaryStage.show();
        
        ft.setOnFinished((e)-> initGame(primaryStage, root, loopGUI) );
	}

	public void initGame(Stage primaryStage, StackPane root, GameLoopGUI animator) {
		
        MainGame GAME = animator.getGAME();
        // The basic Layout of the Screen
        

        //setting up startup Menu
        Pane startUpMenu = new Pane();
        
        StackPane gamePlayLayer = new StackPane();
        VBox mapSelectLayer = new VBox();

        startUpMenu.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
        gamePlayLayer.setPrefSize(WINDOWHEIGHT, WINDOWWIDTH);
        mapSelectLayer.setPrefSize(WINDOWWIDTH,WINDOWHEIGHT);
        mapSelectLayer.setAlignment(Pos.TOP_CENTER);
        Rectangle chooseMapSign = new Rectangle(200,200);
        chooseMapSign.setFill(new ImagePattern(chooseMapBG));


        //Credits
        Rectangle creditsScreen = new Rectangle(WINDOWWIDTH,WINDOWHEIGHT);
        creditsScreen.setFill(new ImagePattern(creditsRollBG));
        
        //Opening screen
        Rectangle startButtonLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);
        startButtonLayer.setFill(new ImagePattern(gameStartBG));
        Rectangle openingTitle = new Rectangle(200,200);
        openingTitle.setFill(new ImagePattern(screenTitle));
       
        //Opening Screen Transition
		ScaleTransition st = new ScaleTransition(Duration.millis(2000), openingTitle);
		     st.setByX(1.5f);
		     st.setByY(1.5f);
		     st.setCycleCount(3);
		     st.setAutoReverse(true);
		     st.play();

		//Credits Transition
		TranslateTransition tt = new TranslateTransition(Duration.seconds(30), creditsScreen);
		     tt.setFromY(700);
		     tt.setByY(-1800);
		     tt.setCycleCount(1);



	        // Start Menu Buttons
	        Button startGameButton = new Button("", new ImageView(startButtonBG));
	        BackgroundImage bImageStartGame = new BackgroundImage(startButtonBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
	        Background backGroundStart = new Background(bImageStartGame);
	        startGameButton.setBackground(backGroundStart);
	        startGameButton.setPrefSize(10, 1);

	        // Credits Button
                Button creditsButton = new Button("", new ImageView(creditsButtonBG));
                BackgroundImage bImageCredits = new BackgroundImage(creditsButtonBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
                Background backGroundCredits = new Background(bImageCredits);
                creditsButton.setBackground(backGroundCredits);
                creditsButton.setPrefSize(10, 1);

                // Skip Button
                Button skipButton = new Button("", new ImageView(skipButtonBG));
                BackgroundImage bImageSkip = new BackgroundImage(skipButtonBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
                Background backGroundSkip = new Background(bImageSkip);
                skipButton.setBackground(backGroundSkip);
                skipButton.setPrefSize(10, 1);

                HBox creditsScreenButton = new HBox();
                creditsScreenButton.getChildren().add(skipButton);
                creditsScreenButton.setAlignment(Pos.CENTER);
                creditsScreenButton.setTranslateX(400);
                creditsScreenButton.setTranslateY(300);

	        HBox openingScreenButtons = new HBox();
	        openingScreenButtons.getChildren().addAll(startGameButton,creditsButton);
	        openingScreenButtons.setAlignment(Pos.CENTER);
	        openingScreenButtons.setTranslateY(300);
	    root.getChildren().addAll(gamePlayLayer,startButtonLayer,
	        		openingTitle,openingScreenButtons);
		startGameButton.setOnAction(new EventHandler<ActionEvent>() {
		  public void handle(ActionEvent event) {
		  root.getChildren().removeAll(gamePlayLayer, openingTitle,openingScreenButtons);
		  root.getChildren().addAll(gamePlayLayer, startUpMenu);
		  }
		});
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
                  public void handle(ActionEvent event) {
                  root.getChildren().removeAll(gamePlayLayer, startButtonLayer,openingTitle,openingScreenButtons);
                  root.getChildren().addAll(startButtonLayer, creditsScreen,creditsScreenButton);
                  tt.play();
                  }
                });
                skipButton.setOnAction(new EventHandler<ActionEvent>() {
                  public void handle(ActionEvent event) {
                  root.getChildren().removeAll(startButtonLayer, creditsScreen,creditsScreenButton);
                  root.getChildren().addAll(gamePlayLayer, startButtonLayer,openingTitle,openingScreenButtons);
                  tt.stop();
                  }
                });
        
		//Game Play Layer
		Rectangle gamePlayBG = new Rectangle(WINDOWWIDTH,WINDOWHEIGHT);
        gamePlayBG.setFill(new ImagePattern(bigGameBG));
        HBox gamePlayFG = new HBox();
        gamePlayLayer.getChildren().addAll(gamePlayBG, gamePlayFG);
        StackPane mainboard = new StackPane();
        VBox utilityPane = new VBox();
        gamePlayFG.getChildren().addAll(mainboard, utilityPane);

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
			Rectangle heartRect = new Rectangle(50, 50, new ImagePattern(heart));
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
		Image goldimg = new Image("/img/PlayerGold.png");
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
			gold.setSpacing(23);
			gold.getChildren().addAll(goldRect ,stats_gold, playerObject.getMoneyLabel());
			utilityPane.getChildren().add(gold);
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
			Rectangle buttonLegend = new Rectangle(150,120);
			buttonLegend.setFill(new ImagePattern(buttonLegendBG));
			buttonLegend.setTranslateX(100);
			buttonLegend.setTranslateY(-100);


			utilityPane.getChildren().addAll(waterLabel, iceLabel, windLabel, samuraiLabel, rainSpellLabel);

			
			//Pause/resume and quit buttons responds to both clicks AND keypresses
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
		        	Pane endTitle = animator.createEndScreen(primaryStage, root);
		        	root.getChildren().add(endTitle);
				}
	        });

	        utilityPane.getChildren().addAll(pauseButton, quitButton);
			
		animator.setForeground(foreground);
		animator.setPrimaryStage(primaryStage);
		animator.setRoot(root);

        primaryStage.getScene().setOnKeyPressed(event -> {
        	switch(event.getCode())
        	{
        	case P:
        		pauseButton.setBackground(backGroundResumeButton);
        	    animator.stop();
        	    break;
            case R:
            	pauseButton.setBackground(backGroundPauseButton);
        	    animator.start();
        	    break;
        	default:
        	    break;

        	  }
        	});
                
                gamePlayLayer.setOnKeyPressed(event -> {
        	if(qPressed) {
        	   event.consume();
        	   return;
        	}
        	    switch (event.getCode()) {
                case Q:
        	  qPressed = true;
          	  animator.stop();
          	  Pane endTitle = animator.createEndScreen(primaryStage,root);
          	  root.getChildren().add(endTitle);
          	  event.consume();
          	  break;

        	  default:
        	  break;

        	   }
        	});
        //Finish setting up StartScreen
        Rectangle startScreenLayer = new Rectangle(WINDOWWIDTH, WINDOWHEIGHT);
        startScreenLayer.setFill(new ImagePattern(gameStartBG));

        HBox initGameButtons = new HBox();
        initGameButtons.setAlignment(Pos.CENTER);

	        // Story Button
	        Button startStoryButton = new Button("", new ImageView(startStoryBG));
	        BackgroundImage bImageStartStory = new BackgroundImage(startStoryBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
	        Background backGroundStartStory = new Background(bImageStartStory);
	        startStoryButton.setBackground(backGroundStartStory);
	        startStoryButton.setPrefSize(10, 1);
	        startStoryButton.setOnAction(new GameStartButtonHandler(GAME, "STORY", animator, root, startUpMenu, fireAlarm));
	        // Survival Button
	        Button startSurvivalButton = new Button("", new ImageView(startSurvivalBG));
	        BackgroundImage bImageStartSurvival = new BackgroundImage(startSurvivalBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
	        Background backGroundStartSurvival = new Background(bImageStartSurvival);
	        startSurvivalButton.setBackground(backGroundStartSurvival);
	        startSurvivalButton.setPrefSize(10, 1);
	        startSurvivalButton.setOnAction(new GameStartButtonHandler(GAME, "SURVIVAL", animator, root, startUpMenu, fireAlarm));
	
	        // Exit Button
	        Button exitButton = new Button("", new ImageView(exitBG));
	        BackgroundImage bImageExit = new BackgroundImage(exitBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(10, 1, true, true, true, false));
	        Background backGroundExit = new Background(bImageExit);
	        exitButton.setBackground(backGroundExit);
	        exitButton.setPrefSize(10, 1);
	        exitButton.setOnAction(e -> primaryStage.close());
	        
	        //Visual Effect for starting Game
	        EventHandler<MouseEvent> mapNotChosenHandler = new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if (ChooseMapHandler.mapWasChosen == false) {
						ScaleTransition st = new ScaleTransition(Duration.millis(1500), chooseMapSign);
					     st.setByX(0.25);
					     st.setByY(0.25);
					     st.setCycleCount(2);
					     st.setAutoReverse(true);
					     st.play();
					}
				}
				
	        };
	        startSurvivalButton.addEventFilter(MouseEvent.MOUSE_CLICKED, mapNotChosenHandler);
	        startStoryButton.addEventFilter(MouseEvent.MOUSE_CLICKED, mapNotChosenHandler);
        HBox chooseMapButtons = new HBox();
        chooseMapButtons.setAlignment(Pos.CENTER);

		ToggleGroup mapGroup = new ToggleGroup();
			//Loop Map BUtton
			ToggleButton loopMapButton = new ToggleButton("", new ImageView(selectLoopBG));
            BackgroundImage bImageLoop = new BackgroundImage(selectLoopBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
            Background backGroundLoop = new Background(bImageLoop);
            loopMapButton.setBackground(backGroundLoop);
            loopMapButton.setPrefSize(100, 100);
            loopMapButton.setOnAction(new ChooseMapHandler(background, "LOOPY", GAME ));
            loopMapButton.setPadding(Insets.EMPTY);
            
            //ZigZagMapButton
            ToggleButton zigzagMapButton = new ToggleButton("", new ImageView(selectZigZagBG));
            BackgroundImage bImageZigZag = new BackgroundImage(selectZigZagBG,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
            Background backGroundZigZag = new Background(bImageZigZag);
            zigzagMapButton.setBackground(backGroundZigZag);
            zigzagMapButton.setPrefSize(100, 100);
            zigzagMapButton.setOnAction(new ChooseMapHandler(background, "ZIGZAG", GAME ));
            loopMapButton.setToggleGroup(mapGroup);
            zigzagMapButton.setToggleGroup(mapGroup);
            zigzagMapButton.setPadding(Insets.EMPTY);
            chooseMapButtons.getChildren().addAll(zigzagMapButton, loopMapButton);
            
            //Visual Effect for Map Buttons
            mapGroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle toggle, Toggle new_toggle) {
                    if (new_toggle == null) {}
                    else {
                    	for (Node aButton: chooseMapButtons.getChildren()) {
                        	if ( (aButton instanceof ToggleButton )
                        			&& ((ToggleButton)aButton).isSelected()) {
                        		aButton.setEffect(new SepiaTone());
                        	} else {
                        		if (aButton.getEffect() != null) {
                        			aButton.setEffect(null);
                        		}
                        	}
                        }
                    };
                }//closing new changelistener object
            });
            
        mapSelectLayer.getChildren().addAll(chooseMapSign,chooseMapButtons,initGameButtons);
        initGameButtons.getChildren().addAll(startSurvivalButton, startStoryButton, exitButton);
                

		startUpMenu.getChildren().addAll(startScreenLayer,mapSelectLayer);

	}
}
