
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GameInterface extends Application {
	
	public static final int WINDOWWIDTH = 809, WINDOWHEIGHT = 500 ;
	public static final int BoardWIDTH = 500, BoardHEIGHT = 500 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 50, OFFSETY = 50;
	public static final int TILESIZE = 50;
	public static final MainGame GAME = new MainGame();
	
	public static void main(String[] args) {
		Application.launch();
	}
	public void start(Stage primaryStage) {
		
		// The basic Layout of the Screen
		HBox root = new HBox();
		Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT);
		
		primaryStage.setTitle("Demo2 Tower Defense");
		primaryStage.setScene(scene);

		StackPane mainboard = new StackPane();
		VBox utilityPane = new VBox();
		root.getChildren().addAll(mainboard, utilityPane);
		
		
		//Setting up the mainboard with grid background and foreground where enemies move
		GridPane background = new GridPane();
		Pane foreground = new Pane();
		mainboard.getChildren().addAll(background, foreground);
		
		background.setPrefSize(BoardWIDTH, BoardHEIGHT);
		foreground.setPrefSize(BoardWIDTH, BoardHEIGHT);
		
		// Get the Grid from MainGame to Draw Background
		Map map = GAME.getMap();
		String[][] grid = map.generateGrid();
		// Setting up the background by reading in the Grid
		Image enemyFire = new Image("/img/Enemy_Fire.PNG");
		Image enemySpirit = new Image("/img/Enemy_Spirit.PNG");
		Image enemyLava = new Image("/img/Enemy_Lava.PNG");
		Image grassTile = new Image("/img/GrassTile.PNG");
	  	Image pathTile = new Image("/img/PathTile.PNG");
	  	Image rockTile = new Image("/img/RockTile.PNG");
	  	
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				Rectangle rect = new Rectangle(50,50);
				if (grid[r][c].equals("-") ) rect.setFill(new ImagePattern(grassTile));
				else if (grid[r][c].equals("p") ) rect.setFill(new ImagePattern(pathTile));
				else if (grid[r][c].equals("r") ) rect.setFill(new ImagePattern(rockTile));
				else rect.setFill(new ImagePattern(pathTile));
				background.add(rect, c, r);
		      }
		    };
		
		 // Setting up the place tower button in the utilityPane
			// PLACEHOLDER: add the Player stats area + tower defense stuff

			Player playerObject = new Player();

			// Health

			Label stats_health = new Label("Player's Health");
			stats_health.setAlignment(Pos.CENTER);

			HBox health = new HBox(); //make the Hbox so that you can set a left and right thing
			utilityPane.getChildren().add(health);
			
			
			playerObject.setHealthLabel(); //New method in player. Easier to change now

			health.setSpacing(10);
			health.setStyle("-fx-background-color: #336699;");

			health.getChildren().add(stats_health);
			health.getChildren().add(playerObject.getHealthLabel());

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

			gold.setSpacing(10);
			gold.setStyle("-fx-background-color: #336699;");

			gold.getChildren().add(stats_gold);
			gold.getChildren().add(playerObject.getMoneyLabel());
			
			
			// add the Button Handler after you guys have worked things out on that
			Button b = new Button("click here");
			b.setPrefSize(100, 100);
			b.relocate(50, 40);
			
			
			//dont change below
		    HBox towerRow1 = new HBox();
			utilityPane.getChildren().add(towerRow1);
			//dont change above

			Image water_cannon = new Image("/img/Defender_WaterCannon.PNG");
			ImageView water_cannon1 = new ImageView(water_cannon);
			water_cannon1.setFitHeight(57);
			water_cannon1.setFitWidth(57);

			Image water_sprite = new Image("/img/Defender_WaterSprite.PNG");
			ImageView water_sprite1 = new ImageView(water_sprite);
			water_sprite1.setFitHeight(57);
			water_sprite1.setFitWidth(57);


		    Button addTower1 = new Button();
		    Button addTower2 = new Button();

		    addTower1.setPrefSize(57, 57);
		    addTower2.setPrefSize(57, 57);

		    addTower1.setGraphic(water_cannon1);
		    addTower2.setGraphic(water_sprite1);

		    towerRow1.getChildren().add(addTower1);
		    towerRow1.getChildren().add(addTower2);
			
		/////////////////////////// Making the button to do something ///////////////////////
		addTower1.setOnAction(new PlaceTowerHandler(addTower1, mainboard, foreground, water_cannon, playerObject));
		addTower2.setOnAction(new PlaceTowerHandler(addTower2, mainboard, foreground, water_sprite, playerObject));
		
	    
	    // Main Game loop, currently has placeholder code as proof of concept
		// Please update with proper logic code
		// Might want to make a method in GameInterface to read in ArrayLists and relocate them
		
			
	    AnimationTimer animator = new AnimationTimer(){
	    	int counter = 0;
	    	
	    	ArrayList<Enemy > enemyList = GAME.getEnemyList();
		    ArrayList<Tower > towerList = GAME.getTowerList();
	    	
            public void handle(long arg0) {
            	double elapsedTime =  0.25;
            	
            	if ( counter == 0 || counter % 50 == 0) {
            		Enemy spawned = GAME.spawnEnemies();
            		Rectangle rect = new Rectangle(50,50);
    				rect.setFill(new ImagePattern(enemyFire));
            		spawned.setNode(rect);
            		foreground.getChildren().add(rect);
            	}
	            
            	
	           GAME.EnemiesAdvance(elapsedTime);
	           
	           if (counter % 10 == 0) {
	        	   GAME.DefendersAttackEnemies();
	           }
	           ArrayList<Enemy> removeable = GAME.removeEnemies();


           	
	           for (int i = 0; i < enemyList.size(); i++) {
	            	Enemy anEnemy = enemyList.get(i);
	       
	 	   		    
	            	Node enemyUI = anEnemy.getNode();
	            	enemyUI.relocate(anEnemy.getXCoord(), anEnemy.getYCoord());
	            	
	            	/*
	            	double xcoor = anEnemy.getXCoord();
	            	double ycoor = anEnemy.getYCoord();
	            	Point destination = new Point(50.0, 450.0);
	            	if (new Point (xcoor, ycoor).hasReached(destination)) {
	            		playerObject.takeDamage(anEnemy.damage);
	            		System.out.println(playerObject.getHealth()+"");
	            	}*/
	   
	            }
	           /*
	           Media extinguish = new Media("/sounds/extinguisher.mp3");
	           MediaPlayer mediaPlayer = new MediaPlayer(extinguish);
	           mediaPlayer.setVolume(0.5);
	           */
	            
	           for (int i = 0; i < removeable.size(); i++) {
	            	Enemy anEnemy = removeable.get(i);
	            	Node enemyUI = anEnemy.getNode();
	            	foreground.getChildren().remove(enemyUI);
	            	
	            	if (anEnemy.isKilled() == true) {
	            		playerObject.gainMoney(anEnemy.getBounty());
	            		playerObject.setMoneyLabel();
	     	            //mediaPlayer.setAutoPlay(true);
	    
	            	}
	            	else {
	            		playerObject.takeDamage(anEnemy.damage);
	            		playerObject.setHealthLabel(); 
	            		if (playerObject.getHealth() <= 0) {
	            			// Switch scenes
	            			StackPane layout2 = new StackPane();
	            			primaryStage.setScene(new Scene(layout2, WINDOWWIDTH, WINDOWHEIGHT));
	            			VBox restartGame = new VBox();
	            			Label restartGameLabel = new Label("Restart the game?");

	            			layout2.getChildren().addAll(restartGameLabel, restartGame);
	            		
	            			
	            			//primaryStage.setScene(scene);

	            			
	            			Button restartGameButton = new Button();
	            			restartGameButton.setPrefSize(100, 100);


	            		    restartGame.getChildren().add(restartGameButton);
	            		}
	            		
	            	}
					
	            }
            	
            	counter += 1;
            }
            
        };
        
        // starting animation and showing the screen
        animator.start();
        primaryStage.show();
	    }
		

}
