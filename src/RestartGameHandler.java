import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class RestartGameHandler implements EventHandler<ActionEvent>{
	Pane rootStartUp;
	Scene primaryScene = new Scene(rootStartUp);
	
	public RestartGameHandler(Scene primaryScene, Pane rootStartUP) {
		this.rootStartUp = rootStartUP;
		this.primaryScene = primaryScene;
	}
	@Override
	public void handle(ActionEvent event) {
		primaryScene.setRoot(rootStartUp);
	}

}
