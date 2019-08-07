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
	/**
	 * Creates a new EventHandler that starts shows the game play area on the screen and starts the animation for the game if the player has chosen a mode and a map.
	 * @param aTimer
	 * @param root
	 * @param startUpMenu
	 * @param fireAlarm
	 */
	public GameStartButtonHandler(AnimationTimer aTimer, StackPane root, Pane startUpMenu, AudioClip fireAlarm) {
		this.aTimer = aTimer;
		this.root = root;
		this.startUpMenu = startUpMenu;
		this.fireAlarm = fireAlarm;	
	}
	
	/**
	 * Implements abstract method from EventHandler interface.
	 * Starts the animation for the game, plays the opening sound effects, and removes the start up menu to show the game play area.
	 */
	public void handle(ActionEvent event) {
		if (ChooseMapHandler.mapWasChosen && ChooseModeHandler.modeWasChosen) {
			aTimer.start();
			fireAlarm.play();
			root.getChildren().remove(startUpMenu);
		}
	}
	

}
