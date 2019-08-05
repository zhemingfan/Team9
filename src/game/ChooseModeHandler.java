package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;

public class ChooseModeHandler implements EventHandler<ActionEvent> {
	public static boolean modeWasChosen;
	private String mode = new String();
	private MainGame currentGame = new MainGame();
	MenuButton chooseModeButton = new MenuButton();
  	
	public ChooseModeHandler( MainGame currentGame, String mode, MenuButton chooseModeButton) {
		this.currentGame = currentGame;
		this.mode = mode;
		this.chooseModeButton = chooseModeButton;
	}
	
	@Override
	public void handle(ActionEvent event) {
		modeWasChosen = true;
		this.chooseModeButton.setText(this.mode);
		currentGame.setGameMode(mode);
	}
}
