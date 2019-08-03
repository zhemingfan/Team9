import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PlaceTowerHandler extends GameInterface implements EventHandler<ActionEvent>{
	
	private static ArrayList<Button> buttonInstances = new  ArrayList<Button>();
	private static ArrayList<PlaceTowerHandler> handlers = new ArrayList<PlaceTowerHandler>();
	
	private Button button = new Button();
	private StackPane GUIactionArea = new StackPane();
	private Pane GUIforeground = new Pane();
	private Tower toBeMade = new Tower();
	private GridPane inputGrid = new GridPane();

	
	public PlaceTowerHandler(Button aB, StackPane aG, Pane aSP, Tower toBeMade) {
		button = aB;
		GUIactionArea = aG;
		GUIforeground = aSP;
		this.toBeMade = toBeMade;
		
		buttonInstances.add(button);
		handlers.add(this);
	}
	
	public PlaceTowerHandler() {}
	
	@Override
	public void handle(ActionEvent event) {
		toggleAllButtons(true);
		
		//Set Cursor Image to the Desired Defender
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
		inputGrid.setCursor( new ImageCursor(cursorImg, 
				cursorImg.getWidth()/2, cursorImg.getHeight()/2) );
		
		makeInputGrid();
		addEventListenerToInputGrid();
		GUIactionArea.getChildren().addAll(inputGrid);

	}
	
	public void makeInputGrid() {
		GridPane updatedGrid = new GridPane();
		updatedGrid.setPrefSize(GameInterface.BoardWIDTH, GameInterface.BoardHEIGHT);

		Map map = GameInterface.GAME.getMap();
		String[][] grid = map.getGrid();
		
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				Rectangle rect = new Rectangle(50,50);
				if (grid[r][c].equals("-") ) {
					rect.setFill(Color.BLACK);
					rect.setOpacity(0.25);
				} else {
					rect.setOpacity(0);
				};
				updatedGrid.add(rect, c, r);
		      }
		 };
		 inputGrid = updatedGrid;
	}
	
	public void addEventListenerToInputGrid() {
		for(Node each: inputGrid.getChildren()) {
			
			each.addEventFilter(MouseEvent.MOUSE_CLICKED, 
				
				new EventHandler<MouseEvent>() {
					
				public void handle(final MouseEvent me) {
					int row = inputGrid.getRowIndex(each);
					int column = inputGrid.getColumnIndex(each);
					
					if (GAME.canPurchaseandPlaceTower(toBeMade, row, column) ) {
						// Add and update Player GUI
						GAME.getMap().updateDefender(row, column);
						Tower aDefender = new Tower();
							
						if (toBeMade instanceof TowerIce) {
							aDefender = new TowerIce(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
						}
						if (toBeMade instanceof TowerWater) {
							aDefender = new TowerWater(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
						}
						if (toBeMade instanceof TowerWind) {
							aDefender = new TowerWind(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
						}
							
						GAME.addDefender(aDefender);

						// Add and update Player GUI
						playerObject.buyDefense(aDefender.getPrice());
						paintTowerOnGUI(aDefender, GUIforeground); //from GameInterface
						playerObject.moneyLabel.setText(playerObject.toStringMoney()); 
						
						// Clean up
						GUIactionArea.getChildren().remove(inputGrid);
						toggleAllButtons(false);
					}
				}
			}
			);
			
		};	
	}
	
	public boolean AllButtonsDisabled() {
		boolean state = true;
		for (Button aButton: buttonInstances) {
			if (!aButton.isDisabled() ) {
				state = false;
			}
		}
		return state;
	}
	
	public void toggleAllButtons(boolean bool) {
		for (Button aButton: buttonInstances) {
			aButton.setDisable(bool);

		}
	}
	
}
