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
	
	
	@Override
	public void handle(ActionEvent event) {
		if (ChooseMapHandler.mapWasChosen) {
			aTimer.start();
			fireAlarm.play();
			root.getChildren().remove(startUpMenu);
			aGame.setGameMode(aMode);
		}
	}
	

}
