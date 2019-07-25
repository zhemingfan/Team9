	
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GameInterface extends Application {
	
	public static final int WINDOWWIDTH = 809, WINDOWHEIGHT = 500 ;
	public static final int BoardWIDTH = 500, BoardHEIGHT = 500 ;
	
	public void start(Stage primaryStage) {
		
		HBox root = new HBox(); //whole screen
		StackPane GameArea = new StackPane();
		VBox MenuArea = new VBox();
		root.getChildren().addAll(GameArea, MenuArea);
		
		GridPane backgroundLayer = new GridPane(); 	//game area.. where the sprites are.. its the front end
		Pane foregroundLayer = new Pane(); //if loading onto map.. use switches
		GameArea.getChildren().addAll(backgroundLayer,foregroundLayer);
		
		Label Player_Stats = new Label(); 		//setting up vbox to 41... set up for the menu 
		Group Toggle_buttons = new Group();
		MenuArea.getChildren().addAll(Player_Stats, Toggle_buttons);
		
		Button PlaceButton = new Button();   //button variable to see what kinds of things need to spawn 
		EventHandler<ActionEvent> placeHandler = new PlaceTowerHandler(PlaceButton, GameArea, 
				foregroundLayer);
		PlaceButton.setOnAction(placeHandler);
		Toggle_buttons.getChildren().add(PlaceButton);
		
	    final long startNanoTime = System.nanoTime();
	    long current = new Date().getTime();
	    AnimationTimer animator = new AnimationTimer(){
	    	
            public void handle(long arg0) {
            	long elapsed = new Date().getTime() - current;
            	long current = new Date().getTime();
            	
            	
            }
        };
        animator.start();

        primaryStage.show();
        
	    }
		

}
