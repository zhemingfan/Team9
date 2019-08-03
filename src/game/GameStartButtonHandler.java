package game;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class GameStartButtonHandler implements EventHandler<ActionEvent> {
	AnimationTimer aTimer;
	StackPane root;
	Pane startUpMenu;
	AudioClip fireAlarm;
	
	public GameStartButtonHandler(AnimationTimer aTimer, StackPane root, Pane startUpMenu, AudioClip fireAlarm) {
		this.aTimer = aTimer;
		this.root = root;
		this.startUpMenu = startUpMenu;
		this.fireAlarm = fireAlarm;
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (ChooseMapHandler.mapWasChosen && ChooseModeHandler.modeWasChosen) {
		aTimer.start();
		fireAlarm.play();
		root.getChildren().remove(startUpMenu);
		}
	}
	

}
