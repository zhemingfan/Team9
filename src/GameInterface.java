import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.animation.*;
//import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
//import javafx.animation.TranslateTransition;
//import javafx.animation.SequentialTransition;
import javafx.scene.text.*;
import javafx.scene.Cursor;


public class GameInterface extends Application{
	public static void main(String[] args) {
		Application.launch();

	}

	public static final int WINDOWWIDTH = 800, WINDOWHEIGHT = 500 ;
	public static final int BoardWIDTH = 500, BoardHEIGHT = 500 ;
	public static final int COLUMN = 10, ROW = 10;
	public static final int OFFSETX = 50, OFFSETY = 50;
	public static final int TILESIZE = 50;
	public static final MainGame GAME = new MainGame();
	public static final int MAXVALUE = 50;

/**	Sends a given enemy along a path
*		@param anEnemy - A specified enemy object. Will take the rectangle instance variable
*										 from within this enemy object and move it along the path
*		@param aPath - A given specified path. Pre-made.
*		@param aPlayer - The instance of the player. Needed for receiving damage when
*										 enemies successfully complete the path
*/
	public void runPath(Enemy anEnemy, Path aPath, Player aPlayer){
		PathTransition transition = new PathTransition();
		transition.setNode(anEnemy.getRect());	//Setting the rectangle representing the enemy to the path
		transition.setDuration(Duration.seconds(anEnemy.speed)); //How long it takes to travel the entire path. Smaller = faster
		transition.setPath(aPath); //Setting which Path object will be travelled
		transition.setOnFinished(event -> { //A method that activates specified event(s) upon path completion
			aPlayer.takeDamage(anEnemy.damage);	//One such event is damaging the player by the value of the enemy's damage variable
			System.out.println(aPlayer.getHealth());  //Debugging print statement
			updateLabel(aPlayer.healthLabel, aPlayer.toStringHealth()); //Updates the player's HP in the GUI
			if(aPlayer.getHealth() <= 0) System.out.println("YOU LOSE"); //Prints when player drops to 0 or less HP. Hopefully replace

		});	//Uses the curly braces to define MULTIPLE events for setOnFinished
		transition.play(); //Play the transition
	}

	
	
//The code inside this function is using the "Frogger" example made by AlmasB
//Repo at: https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/frogger/FroggerApp.java
//Took the Win condition of his game and modified it to be a Lose condition
//Idk how to implement this yet :(
//NOT CURRENTLY IN USE, DON'T WORRY ABOUT THIS
	public void gameOver(){
            String lose = "YOU LOSE";
            HBox hBox = new HBox();

            hBox.setTranslateX(300);
            hBox.setTranslateY(250);
            //aPane.getChildren().add(hBox);

            for (int i = 0; i < lose.toCharArray().length; i++) {
                char letter = lose.charAt(i);

                Text text = new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);

                hBox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();


            }
	}

	//This is literally only here to update the labels at the side.
	/** Use to change labels at the side menu easily
	*	@param someLabel - The label you want to change
	*	@param newLabelText - The new text you want to update with
	*/
	public void updateLabel(Label someLabel, String newLabelText){
		someLabel.setText(newLabelText);
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

		// Setting up the place tower button in the utilityPane
		// PLACEHOLDER: add the Player stats area + tower defense stuff

		Player playerObject = new Player();

		// Health

		Label stats_health = new Label("Player's Health");
		stats_health.setAlignment(Pos.CENTER);

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

	    //////////////////////////////// Now the tower icon ////////////////////////////////

		//Image imageDecline = new Image(getClass().getResourceAsStream("Defender_WaterCannon.PNG"));
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

	    /////////////////////////// Setting up the mainboard with grid background and foreground where enemies move

	    Image enemyFire = new Image("/img/Enemy_Fire.PNG");
	  	Image enemySpirit = new Image("/img/Enemy_Spirit.PNG");
	  	Image enemyLava = new Image("/img/Enemy_Lava.PNG");
	  	Image grassTile = new Image("/img/GrassTile.PNG");
	  	Image pathTile = new Image("/img/PathTile.PNG");
	  	Image rockTile = new Image("/img/RockTile.PNG");
	  	Image lavaTile = new Image("/img/LavaTile.PNG");
	  	Image defenderWaterCannnon = new Image("/img/Defender_WaterCannon.PNG");
	  	Image defenderWaterSprite = new Image("/img/Defender_WaterSprite.PNG");
	  	Image defenderWind = new Image("/img/Defender_Wind.PNG");


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
				Rectangle rect = new Rectangle(50,50);
				if (grid[r][c].equals("-") ) rect.setFill(new ImagePattern(grassTile));
				else if (grid[r][c].equals("p") ) rect.setFill(new ImagePattern(pathTile));
				else if (grid[r][c].equals("r") ) rect.setFill(new ImagePattern(rockTile));
				else rect.setFill(new ImagePattern(pathTile));
				background.add(rect, c, r);
		      }
		    };


		 /////////////////////////// Making the button to do something ///////////////////////
		  
		 addTower1.setOnAction(new PlaceTowerHandler(addTower1, mainboard, foreground, map, playerObject));
		 
	
		


		/////////////////////////// Making the animation of enemy movement ///////////////////////


		Enemy testEnemy = new Enemy(10, 10); //little bit to the right but straight at the top for y
		testEnemy.attachPath(map.getCheckPoints(OFFSETX, OFFSETY));
		testEnemy.getRect().setFill(new ImagePattern(enemyFire));	//Assigning enemy sprite

		foreground.getChildren().add(testEnemy.getRect());
		//testEnemy.setNode(testEnemy.getRect()); Not too sure what this does, commenting didn't change anything so yeah
    	ArrayList<Enemy> list = new ArrayList<Enemy>();

		list.add(testEnemy);
		Enemy anEnemy = list.get(0);

		//The "Path" object is literally that: The exact path the enemy will take.
		//However, the path needs to be created by indicating which direction
		//the object will move when it travels this path.
		//First line, "MoveTo" is the starting position.
		//VLineTo is a vertical translation.
		//HLineTo is a horizontal translation
		Path path = new Path();
		path.getElements().add(new MoveTo(70, 0));
		path.getElements().add(new VLineTo(125));
		path.getElements().add(new HLineTo(425));
		path.getElements().add(new VLineTo(225));
		path.getElements().add(new HLineTo(70));
		path.getElements().add(new VLineTo(325));
		path.getElements().add(new HLineTo(425));
		path.getElements().add(new VLineTo(425));
		path.getElements().add(new HLineTo(70));
		path.getElements().add(new VLineTo(530));

		//Making a bunch of test enemies
		//This uses a new (potentially temporary) Enemy constructor. See Enemy class for more details
		Enemy fastEnemyTest1 = new Enemy(5, 3, 1); //Construct enemy
		fastEnemyTest1.getRect().setFill(new ImagePattern(enemySpirit)); //Set image
		foreground.getChildren().add(fastEnemyTest1.getRect()); //Makes image visible. Without this you wouldn't see it

		Enemy fastEnemyTest2 = new Enemy(5, 5, 1);
		fastEnemyTest2.getRect().setFill(new ImagePattern(enemySpirit));
		foreground.getChildren().add(fastEnemyTest2.getRect());

		Enemy fastEnemyTest3 = new Enemy(5, 7, 1);
		fastEnemyTest3.getRect().setFill(new ImagePattern(enemySpirit));
		foreground.getChildren().add(fastEnemyTest3.getRect());

		Enemy slowEnemyTest1 = new Enemy(15, 13, 1);
		slowEnemyTest1.getRect().setFill(new ImagePattern(enemyLava));
		foreground.getChildren().add(slowEnemyTest1.getRect());

		Enemy slowEnemyTest2 = new Enemy(15, 15, 1);
		slowEnemyTest2.getRect().setFill(new ImagePattern(enemyLava));
		foreground.getChildren().add(slowEnemyTest2.getRect());

		Enemy slowEnemyTest3 = new Enemy(15, 17, 1);
		slowEnemyTest3.getRect().setFill(new ImagePattern(enemyLava));
		foreground.getChildren().add(slowEnemyTest3.getRect());


		//A bunch of test Path runs
		/*
		runPath(testEnemy, path, playerObject);
		runPath(fastEnemyTest1, path, playerObject);
		runPath(slowEnemyTest1, path, playerObject);
		runPath(fastEnemyTest2, path, playerObject);
		runPath(slowEnemyTest2, path, playerObject);
		runPath(fastEnemyTest3, path, playerObject);
		runPath(slowEnemyTest3, path, playerObject);
		*/


        primaryStage.show();
	    }


}
