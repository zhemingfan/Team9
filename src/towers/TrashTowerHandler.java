package towers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TrashTowerHandler implements EventHandler<ActionEvent> {
	PlaceTowerHandler placeButtons = new PlaceTowerHandler();
	
	public TrashTowerHandler() {
		
	}

	public void handle(ActionEvent event) {
		if (placeButtons.AllButtonsDisabled() ){
			for (PlaceTowerHandler ahandler: placeButtons.getAllHandlers()) {
				if (ahandler.playerIsPlacingTower()) {
					ahandler.removeInputGrid();
					ahandler.setPlayerNotPlacing();
				}
			};
			placeButtons.toggleAllButtons(false);
		}
	}

}
