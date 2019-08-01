import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class PlaceTowerHandler extends GameInterface implements EventHandler<ActionEvent>{
	private Button button = new Button();
	private StackPane GUIactionArea = new StackPane();
	private Pane GUIforeground = new Pane();
	private Image image;
	private Player player;
	
	Image defenderWaterCannnon = new Image("/img/Defender_WaterCannon.PNG");
  	Image defenderWaterSprite = new Image("/img/Defender_WaterSprite.PNG");
  	Image defenderWind = new Image("/img/Defender_Wind.PNG");
	Image grassTile = new Image("/img/GrassTile.PNG");
  	
  	
	public PlaceTowerHandler(Button aB, StackPane aG, Pane aSP, Image image, Player player1) {
		button = aB;
		GUIactionArea = aG;
		GUIforeground = aSP;
		this.image = image;
		this.player = player1;
	}
	
	@Override
	public void handle(ActionEvent event) {
		GridPane sudo = new GridPane();
		sudo.setPrefSize(GameInterface.BoardWIDTH, GameInterface.BoardHEIGHT);
		//sudo.setOpacity(0);
		
		GUIactionArea.getChildren().addAll(sudo);
		
		Map map = GameInterface.GAME.getMap();
		String[][] grid = map.generateGrid();
		
		
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				Rectangle rect = new Rectangle(50,50);
				if (grid[r][c].equals("-") ) {
					rect.setFill(Color.BLACK);
					rect.setOpacity(0.5);
				} else {
					rect.setOpacity(0);
				};
				sudo.add(rect, c, r);
		      }
		    }; 
		
		for(Node each: sudo.getChildren()) {
			each.addEventFilter(MouseEvent.MOUSE_CLICKED, 
					new EventHandler<MouseEvent>() {
						public void handle(final MouseEvent me) {
							int row = sudo.getRowIndex(each);
							int column = sudo.getColumnIndex(each);
							
							/*
							Media extinguish = new Media(new File("extinguisher.mp3").toUri().toString());
					        MediaPlayer mediaPlayer = new MediaPlayer(extinguish);
					        
					        
							mediaPlayer.setVolume(0.5);
							*/
							if (GAME.getMap().canPlaceDefense(row, column)) {
								
								GAME.getMap().updateDefender(row, column);
								Tower aTower = new Tower(column*GameInterface.OFFSETX, row*GameInterface.OFFSETY);
								GAME.addDefender(aTower);
								
								Rectangle rect = new Rectangle(GameInterface.OFFSETX, GameInterface.OFFSETY);
								rect.setFill(new ImagePattern(image) );
								aTower.setNode(rect);
								GUIforeground.getChildren().add(aTower.getNode());
								aTower.getNode().relocate(aTower.getXCoord(), aTower.getYCoord());
								player.buyDefense(10);
								player.setMoneyLabel();
								//mediaPlayer.play(); //music for later

								GUIactionArea.getChildren().remove(sudo);
								
								if (player.enoughFunds(10) == false) {
									button.setDisable(true);
									rect.setFill(new ImagePattern(grassTile));
									player.setMoney(0);
									//System.out.println("" + player.getMoney()); does set it to 0
									player.setMoneyLabel();

								}
								
							}
							
						}

						
					}
				);
			
		};
		
	}

}
