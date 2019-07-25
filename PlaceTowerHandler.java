import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PlaceTowerHandler implements EventHandler<ActionEvent>{
	private Button b = new Button();
	private StackPane grid = new StackPane(); //background
	private Pane sprites = new Pane(); //pane where you draw this
	
	public PlaceTowerHandler(Button aB, StackPane aG, Pane aSP) {
		b = aB;
		grid = aG;
		sprites = aSP;
		
	}
	@Override
	public void handle(ActionEvent event) {
		GridPane sudo = new GridPane();
		sudo.setPrefSize(500, 500);
		sudo.setGridLinesVisible(true); //mouse over gives you the col and row and check using the map function whether you can place it
		sudo.setOpacity(0);
		
		grid.getChildren().addAll(sudo); //sudo is called transparent layer... create sudo layer so you can get rows and columns. only use it to get input.. if its valid you create a new tower
		//on the map.. main loop should check if  
		
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				Rectangle rect = new Rectangle(50,50);
				rect.setOpacity(0);
				sudo.add(rect, c, r);
		      }
		    };
		    
		for(Node each: sudo.getChildren()) {
			each.addEventFilter(MouseEvent.MOUSE_ENTERED, 
				new EventHandler<MouseEvent>() {
					public void handle(final MouseEvent me) {
						int y = sudo.getRowIndex(each);
						int x = sudo.getColumnIndex(each);
					}
				}
			);
			each.addEventFilter(MouseEvent.MOUSE_CLICKED, 
					new EventHandler<MouseEvent>() {
						public void handle(final MouseEvent me) {
							int y = sudo.getRowIndex(each);
							int x = sudo.getColumnIndex(each);
							Rectangle rect = new Rectangle(50,50);
							rect.setFill(Color.DODGERBLUE);
							sprites.getChildren().add(rect);
							rect.relocate(x * 50, y * 50);
							grid.getChildren().remove(sudo);
						}
					}
				);
			
		};
		
	}

}
