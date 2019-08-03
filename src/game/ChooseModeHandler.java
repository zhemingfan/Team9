package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class ChooseModeHandler implements EventHandler<ActionEvent> {
	public static boolean modeWasChosen;
	private String mode = new String();
	private MainGame currentGame = new MainGame();
  	
	public ChooseModeHandler( MainGame currentGame, String mode) {
		this.currentGame = currentGame;
		this.mode = mode;

	}

	@Override
	public void handle(ActionEvent event) {
		modeWasChosen = true;
		currentGame.setGameMode(mode);
	}
}
