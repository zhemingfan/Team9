import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChooseMapHandler implements EventHandler<ActionEvent>{
	private String mapType = new String();
	
	public ChooseMapHandler(String mapType) {
		this.mapType = mapType;
	}
	public void handle(ActionEvent event) {
		//new GameInterface().chooseMap(mapType);
	}

}
