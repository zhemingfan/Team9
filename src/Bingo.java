import javafx.animation.PathTransition; 
import javafx.animation.ScaleTransition; 
import javafx.animation.SequentialTransition; 
import javafx.animation.TranslateTransition; 

import javafx.application.Application; 

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.scene.shape.CubicCurveTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 

import javafx.stage.Stage;  
import javafx.util.Duration; 
        
public class Bingo extends Application { 
   @Override 
   public void start(Stage stage) {      
      //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the position of the circle 
      circle.setCenterX(150.0f); 
      circle.setCenterY(135.0f); 
      
      //Setting the radius of the circle 
      circle.setRadius(100.0f); 
      
      //Setting the color of the circle 
      circle.setFill(Color.BROWN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(20); 
      
      //Instantiating the path class 
      Path path = new Path(); 
      
      //Creating the MoveTo path element 
      MoveTo moveTo = new MoveTo(100, 150); 
      
      //Creating the Cubic curve path element 
      CubicCurveTo cubicCurveTo = new CubicCurveTo(400, 40, 175, 250, 500, 150); 
      
      //Adding the path elements to Observable list of the Path class 
      path.getElements().add(moveTo); 
      path.getElements().add(cubicCurveTo);     
       
      //Creating path Transition 
      PathTransition pathTransition = new PathTransition(); 
      
      //Setting the duration for the transition 
      pathTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      pathTransition.setNode(circle); 
      
      //Setting the path for the transition  
      pathTransition.setPath(path); 
      
      //Setting the orientation of the path 
      pathTransition.setOrientation(
         PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
      
      //Setting the cycle count for the transition 
      pathTransition.setCycleCount(5); 
      
      //Setting auto reverse value to false 
      pathTransition.setAutoReverse(false); 
      
      //Playing the animation 
      pathTransition.play();         
       
      //Creating Translate Transition 
      TranslateTransition translateTransition = new TranslateTransition(); 
      
      //Setting the duration for the transition 
      pathTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      pathTransition.setNode(circle); 
      
      //Setting the length of the transition along x axis 
      translateTransition.setByX(300); 
      
      //Setting the cycle count for the stroke 
      translateTransition.setCycleCount(5); 
      
      //Setting auto reverse value to false 
      translateTransition.setAutoReverse(false);  
       
      //Applying scale Transition to the circle 
      ScaleTransition scaleTransition = new ScaleTransition();       
      
      //Setting the duration for the transition 
      pathTransition.setDuration(Duration.millis(1000)); 
      
      //Setting the node for the transition 
      pathTransition.setNode(circle); 
      
      //Setting the dimensions for scaling 
      scaleTransition.setByY(1.5); 
      scaleTransition.setByX(1.5);  
      
      //Setting the cycle count for the translation 
      scaleTransition.setCycleCount(5); 
      
      //Setting auto reverse value to false 
      scaleTransition.setAutoReverse(false);       
       
      //Applying Sequential Translation to the circle 
      SequentialTransition sequentialTransition = new SequentialTransition(circle, 
         pathTransition, translateTransition, scaleTransition ); 
      
      //Playing the animation 
      sequentialTransition.play(); 
         
      //Creating a Group object  
      Group root = new Group(circle); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
      
      //Setting title to the Stage 
      stage.setTitle("Seqiential transition example"); 
      
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}