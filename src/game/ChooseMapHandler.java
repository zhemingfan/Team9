package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ChooseMapHandler implements EventHandler<ActionEvent> {
	public static boolean mapWasChosen;
	String mapType = new String();
	GridPane background = new GridPane();
	private MainGame currentGame = new MainGame();
	
	public Image grassTile = new Image("/img/GrassTile.PNG");
  	public Image pathTile = new Image("/img/PathTile.PNG");
  	public Image rockTile = new Image("/img/RockTile.PNG");
  	/**
  	 * Creates an EventHandler - that checks for when players choose their desired map, with:
  	 * a background to be filled
  	 * a map type that it will create when clicked
  	 * a copy of the current game to get a 2D array as a blue print for GUI Map. 
  	 * @param background
  	 * @param mapType
  	 * @param currentGame
  	 */
	public ChooseMapHandler(GridPane background, String mapType, MainGame currentGame) {
		this.background = background;
		this.mapType = mapType;
		this.currentGame = currentGame;
	}
/**
 * Implements abstract method from EventHandler interface.
 * Fills the background grid pane with tiles corresponding to the underlying 2D array.
 * 0 => n : check points, including start and end
 * p : path
 * c : check point
 * - : tile where defense can be placed
 *@see Map 
 */
	public void handle(ActionEvent event) {
		mapWasChosen = true;
		currentGame.customizeGrid(mapType);
		Map map = currentGame.getMap();
		String[][] grid = map.generateGrid();
	  	background.getChildren().clear();
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				Rectangle rect = new Rectangle(GameInterface.TILESIZE, GameInterface.TILESIZE);
				if (grid[r][c].equals("-") ) rect.setFill(new ImagePattern(grassTile));
				else if (grid[r][c].equals("p") ) rect.setFill(new ImagePattern(pathTile));
				else if (grid[r][c].equals("r") ) rect.setFill(new ImagePattern(rockTile));
				else rect.setFill(new ImagePattern(pathTile));
				background.add(rect, c, r);
			}
		};	
	} 

}
