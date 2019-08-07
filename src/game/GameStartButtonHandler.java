package game;
import javafx.animation.AnimationTimer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameStartButtonHandler implements EventHandler<ActionEvent> {
	AnimationTimer aTimer;
	StackPane root;
	Pane startUpMenu;
	AudioClip fireAlarm;
	Stage primaryStage;
	Scene gamePlayScene;
	MainGame aGame;
	String aMode;

	public GameStartButtonHandler(MainGame someGame, String someMode,
									AnimationTimer aTimer, StackPane root, Pane startUpMenu, AudioClip fireAlarm) {
		this.aTimer = aTimer;
		this.root = root;
		this.startUpMenu = startUpMenu;
		this.fireAlarm = fireAlarm;
		aGame = someGame;
		aMode = someMode;
	}

	/**
	 * Implements abstract method from EventHandler interface.
	 * Starts the animation for the game, plays the opening sound effects, and removes the start up menu to show the game play area.
	 */
	public void handle(ActionEvent event) {
		if (ChooseMapHandler.mapWasChosen) {
			aTimer.start();
			fireAlarm.play();
			root.getChildren().remove(startUpMenu);
			aGame.setGameMode(aMode);
		}
	}


}
