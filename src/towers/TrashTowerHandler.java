package towers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class TrashTowerHandler implements EventHandler<ActionEvent> {
	PlaceTowerHandler placeButtons = new PlaceTowerHandler();
	private StackPane GUIactionArea = new StackPane();
	
	public TrashTowerHandler(StackPane aG) {
		this.GUIactionArea = aG;
	}

	public void handle(ActionEvent event) {
		if (placeButtons.AllButtonsDisabled() ){
			this.GUIactionArea.getChildren().remove(this.GUIactionArea.getChildren().size() - 1);
			placeButtons.toggleAllButtons(false);
		}
	}

}
