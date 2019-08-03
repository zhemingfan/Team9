import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameStartButtonHandler implements EventHandler<ActionEvent> {
	AnimationTimer aTimer;
	StackPane root;
	Pane startUpMenu;
	
	public GameStartButtonHandler(AnimationTimer aTimer, StackPane root, Pane startUpMenu) {
		this.aTimer = aTimer;
		this.root = root;
		this.startUpMenu = startUpMenu;
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		aTimer.start();
		root.getChildren().remove(startUpMenu);
		
	}
	

}
