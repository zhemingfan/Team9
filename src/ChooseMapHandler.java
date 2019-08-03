import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChooseMapHandler implements EventHandler<ActionEvent>{
	private String mapType = new String();
	private MainGame newGame = new MainGame();
	
	public ChooseMapHandler(MainGame newGame, String mapType) {
		this.mapType = mapType;
		this.newGame = newGame;
	} 
	public void handle(ActionEvent event) {
		newGame.generateGrid(mapType);
	}

}
