
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GameInterface extends Application {
	
	public static final int WINDOWWIDTH = 700, WINDOWHEIGHT = 500 ;
	public static final int BoardWIDTH = 500, BoardHEIGHT = 500 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 50, OFFSETY = 50;
	public static final int TILESIZE = 50;
	public static final MainGame GAME = new MainGame();
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

	public static void main(String[] args) {
		Application.launch();
	}
	public void start(Stage primaryStage) {
		
		// The basic Layout of the Screen
		HBox root = new HBox();
		Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT);
	    primaryStage.setTitle("Demo");
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
		
	 // Setting up the place tower button in the utilityPane
		// PLACEHOLDER: add the Player stats area
	    Player playerObject = GAME.getPlayer();

		// Health
		HBox health = new HBox(); //make the Hbox so that you can set a left and right thing
		utilityPane.getChildren().add(health);
		
		/* @param BELOW IS FOR TESTING
		 * TODO make sure to make this pretty later
		 *For the health bars
		Rectangle rectangleHealth = new Rectangle(200.0, 20.0, Color.RED);
	    rectangleHealth.setX(50);
	    rectangleHealth.setY(50);
	    */
		//playerObject.setHealth(100);  works great

		//final Label playerObject.getHealthLabel() = new Label(playerObject.toStringHealth());
		
		//Rectangle rectangleCurrentHealth = new Rectangle(45.0, 20.0, Color.GREEN);
		
		
		//Rectangle rectangleLostHealth = new Rectangle(20.0, 20.0, Color.RED);

		
		
		playerObject.setHealthLabel(); //New method in player. Easier to change now

		//Stats For Health 
		//health.setStyle("-fx-background-color: #336699;");
		
		

		
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
	    		new TowerWater() ) );
		
	    Button placeWindButton = new Button("", new ImageView(defenderWind));
		placeWindButton.setPrefSize(TILESIZE*2, TILESIZE*2);
		placeWindButton.setOnAction(new PlaceTowerHandler(placeWindButton, mainboard, foreground, 
				new TowerWind() ) );
		
		Button placeIceButton = new Button("", new ImageView(defenderIce));
		placeIceButton.setPrefSize(TILESIZE*2, TILESIZE*2);
		placeIceButton.setOnAction(new PlaceTowerHandler(placeIceButton, mainboard, foreground, 
				new TowerIce() ) );
	    	    
		Button trashButton = new Button("TRASH");
		trashButton.setOnAction(new TrashTowerHandler() );
		utilityPane.getChildren().addAll(placeWaterButton, placeWindButton, placeIceButton, trashButton);
	    
		
		
	    // Main Game loop, currently has placeholder code as proof of concept
		// Please update with proper logic code
		// Might want to make a method in GameInterface to read in ArrayLists and relocate them
		GAME.createEnemyList();
			
	    AnimationTimer animator = new AnimationTimer(){
	    	int frameCounter = 0;
	    	double elapsedTime =  0.5;
	    	
            public void handle(long arg0) {
            	
            	if ( frameCounter == 0 || frameCounter % 50 == 0) {
            		Enemy spawned = GAME.spawnEnemies();
            		paintNewEnemy(spawned, foreground);
            	}
            	
	           GAME.EnemiesAdvance(elapsedTime);
	           
	           if (frameCounter % 10 == 0) {
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
            }
            
        };
        
        // starting animation and showing the screen
        animator.start();
        primaryStage.show();
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
