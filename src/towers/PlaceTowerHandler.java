package towers;
import java.util.ArrayList;
import game.GameLoopGUI;
import game.Player;
import game.GameInterface;
import game.MainGame;
import game.Map;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import parents.Tower;

public class PlaceTowerHandler implements EventHandler<ActionEvent>{
	private static ArrayList<Button> buttonInstances = new  ArrayList<Button>();

	private Button button = new Button();
	private StackPane GUIactionArea = new StackPane();
	private Pane GUIforeground = new Pane();
	private Tower toBeMade = new Tower();
	private GridPane inputGrid = new GridPane();
	private MainGame currentGame = new MainGame();
	private int clicksCounter = 0;

	public static final Image rainSpell = new Image("/img/RainSpell.png");
	public static final Image defenderIce = new Image("/img/TowerIce.png");
	public static final Image defenderWaterSprite = new Image("/img/TowerWater.png");
	public static final Image defenderWind = new Image("/img/TowerWind.png");
	public static final Image defenderSamurai = new Image("/img/TowerSamurai.png");

  	//Sound effects are from soundbible.com
  	public AudioClip waterSplash = new AudioClip(this.getClass().getResource("/sound/waterSplash.mp3").toString());
  	//public AudioClip thunderStorm = new AudioClip(this.getClass().getResource("/sound/thunderStorm.mp3").toString());
  	public AudioClip iceCrack = new AudioClip(this.getClass().getResource("/sound/iceCrack.mp3").toString()); //Except this one, from zapsplat.com
  	public AudioClip swordSwish = new AudioClip(this.getClass().getResource("/sound/Swish.mp3").toString());
  	public AudioClip windWoosh = new AudioClip(this.getClass().getResource("/sound/wind.mp3").toString());

	public PlaceTowerHandler(Button aB, StackPane aG, Pane aSP, Tower toBeMade, MainGame currentGame) {
		button = aB;
		GUIactionArea = aG;
		GUIforeground = aSP;
		this.toBeMade = toBeMade;
		this.currentGame = currentGame;
		buttonInstances.add(button);
	}

	public PlaceTowerHandler() {}

	@Override
	public void handle(ActionEvent event) {
		clicksCounter += 1;
		if ( clicksCounter % 2 != 0) {
			if (currentGame.getPlayer().enoughFunds(toBeMade.getPrice())) {
				toggleAllButtons();
				currentGame.getPlayer().getMoneyLabel().setTextFill(Color.BLACK);
				makeInputGrid();
				Image cursorImg = defenderWaterSprite;
				if (toBeMade instanceof TowerIce) {
					cursorImg = defenderIce;
				}
				if (toBeMade instanceof TowerWater) {
					cursorImg = defenderWaterSprite;
				}
				if (toBeMade instanceof TowerWind) {
					cursorImg = defenderWind;
				}
				if (toBeMade instanceof TowerSamurai) {
					cursorImg = defenderSamurai;
				}
				inputGrid.setCursor( new ImageCursor(cursorImg,
						cursorImg.getWidth()/2, cursorImg.getHeight()/2) );

				addEventListenerToInputGrid();
				GUIactionArea.getChildren().addAll(inputGrid);
			} else {
				currentGame.getPlayer().getMoneyLabel().setTextFill(Color.RED);
			}
			
		} 
		else {
			if ( GUIactionArea.getChildren().contains(this.inputGrid) ) {
				GUIactionArea.getChildren().remove(this.inputGrid);
				toggleAllButtons();
			}
		}

	}

	/**
	 * Creates a grid on top of the game play's foreground to get input from the player on where to place towers.
	 */
	public void makeInputGrid() {
		inputGrid = new GridPane();
		inputGrid.setPrefSize(GameInterface.BoardWIDTH, GameInterface.BoardHEIGHT);

		Map map = this.currentGame.getMap();
		String[][] grid = map.generateGrid();

		for(int r = 0; r < GameInterface.ROW; r++) {
			for(int c = 0; c < GameInterface.COLUMN; c++) {
				Rectangle rect = new Rectangle(GameInterface.TILESIZE, GameInterface.TILESIZE);
				if (grid[r][c].equals("-") ) {
					rect.setFill(Color.BLACK);
					rect.setOpacity(0.25);
				} else {
					rect.setOpacity(0);
				};
				inputGrid.add(rect, c, r);
		      }
		 };
	}

	/**
	 * Adds event listener to individual nodes in input grid to handle input from player.
	 * Creates a new tower and paints that tower on GUI if the chosen spot is valid.
	 */
	public void addEventListenerToInputGrid() {
		for(Node each: inputGrid.getChildren()) {

			each.addEventFilter(MouseEvent.MOUSE_CLICKED,

				new EventHandler<MouseEvent>() {

				public void handle(final MouseEvent me) {
					int row = inputGrid.getRowIndex(each);
					int column = inputGrid.getColumnIndex(each);

					if (currentGame.canPurchaseandPlaceTower(toBeMade, row, column) ) {
						// Add and update Player GUI
						clicksCounter += 1;
						currentGame.getMap().updateDefender(row, column);
						Tower aDefender = new Tower();

						if (toBeMade instanceof TowerIce) {
							aDefender = new TowerIce(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
							iceCrack.play();

						}
						if (toBeMade instanceof TowerWater) {
							aDefender = new TowerWater(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
							waterSplash.play();

						}
						if (toBeMade instanceof TowerWind) {
							aDefender = new TowerWind(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
							windWoosh.play();
						}
						if (toBeMade instanceof TowerSamurai) {
							aDefender = new TowerSamurai(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
							swordSwish.play();
						}
						aDefender.setframeCreated(GameLoopGUI.frameCounter);
						currentGame.addDefender(aDefender);
						Player playerObject = currentGame.getPlayer();
						// Add and update Player GUI
						playerObject.buyDefense(aDefender.getPrice());
						new GameLoopGUI().paintTowerOnGUI(aDefender, GUIforeground); //from GameInterface
						playerObject.moneyLabel.setText(playerObject.toStringMoney());

						// Clean up
						GUIactionArea.getChildren().remove(inputGrid);
						toggleAllButtons();
					} else {
						if (each instanceof Rectangle) {
							((Rectangle) each).setOpacity(0.5);
							((Rectangle) each).setFill(Color.RED);
							FadeTransition ft = new FadeTransition(Duration.millis(1000), (Rectangle) each);
							ft.setFromValue(0.5);
						    ft.setToValue(0.0);
						    ft.play();
						}
					}
				}
			}
			);

		};
	}

	/**
	 * Disables other place tower buttons if player if this handler's button was chosen and turns thse buttons back on otherwise.
	 */
	public void toggleAllButtons() {
		for (Button aButton: PlaceTowerHandler.buttonInstances) {
			if( !aButton.equals(this.button) ) {
				aButton.setDisable(!aButton.isDisabled());
			}
		}
	}

}
