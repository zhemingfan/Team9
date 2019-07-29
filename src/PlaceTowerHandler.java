import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;


public class PlaceTowerHandler extends Player implements EventHandler<ActionEvent> {
	private Button b = new Button();
	private StackPane grid = new StackPane();
	private Pane sprites = new Pane();
	private Map map = new Map();
	private Player player1 = new Player();
	
	public PlaceTowerHandler(Button aB, StackPane aG, Pane aSP, Map map, Player player1) {
		b = aB;
		grid = aG;
		sprites = aSP;
		this.map = map;
		this.player1 = player1;
	}
	@Override
	public void handle(ActionEvent event) {
		GridPane sudo = new GridPane();
		sudo.setPrefSize(500, 500);
		sudo.setGridLinesVisible(true);
		sudo.setOpacity(0);
		
		grid.getChildren().addAll(sudo);
		
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
						System.out.println(x + ", " + y);
					}
				}
			);
			each.addEventFilter(MouseEvent.MOUSE_CLICKED, 
					new EventHandler<MouseEvent>() {
						public void handle(final MouseEvent me) {
							int y = sudo.getRowIndex(each);
							int x = sudo.getColumnIndex(each);
							
							Image defenderWaterCannnon = new Image("/img/Defender_WaterCannon.PNG");
							Rectangle rect = new Rectangle(50,50);
							rect.setFill(new ImagePattern(defenderWaterCannnon));

							if (map.canPlaceDefense(y, x) && enoughFunds(10)) {
		
								sprites.getChildren().add(rect);
								rect.relocate(x * 50, y * 50);
								grid.getChildren().remove(sudo);
								System.out.println(getMoney());
								buyDefense(10);
								player1.moneyLabel.setText(toStringMoney()); 
								map.updateDefender(y,x);
								map.display();
								
							}
						}
					}
				);
			
		};
		
	}

}
