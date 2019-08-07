package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;

public class ChooseModeHandler implements EventHandler<ActionEvent> {
	public static boolean modeWasChosen;
	private String mode = new String();
	private MainGame currentGame = new MainGame();
	MenuButton chooseModeButton = new MenuButton();
	/**
	 * Creates an EventHandler  - that checks for when players choose their desired mode, with:
  	 * a copy of the current game to get a 2D array as a blue print for GUI Map.
  	 * a mode  that the handler will set the game to
  	 * a button that this handler observes to get user input
	 * @param currentGame
	 * @param mode
	 * @param chooseModeButton
	 */
	public ChooseModeHandler( MainGame currentGame, String mode, MenuButton chooseModeButton) {
		this.currentGame = currentGame;
		this.mode = mode;
		this.chooseModeButton = chooseModeButton;
	}

	/**
	 * Implements abstract method from EventHandler interface.
	 * Sets the game mode of the current game to the mode variable provided.
	 */
	public void handle(ActionEvent event) {
		modeWasChosen = true;
		this.chooseModeButton.setText(this.mode);
		currentGame.setGameMode(mode);
	}
}
