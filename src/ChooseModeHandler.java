import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChooseModeHandler implements EventHandler<ActionEvent>{
	private String mode = new String();
	private MainGame newGame = new MainGame();
	
	public ChooseModeHandler(MainGame newGame, String mode) {
		this.mode = mode;
		this.newGame = newGame;
	} 
	public void handle(ActionEvent event) {
		newGame.setGameMode(mode);
	}

}
