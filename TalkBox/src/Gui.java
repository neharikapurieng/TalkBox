

import java.awt.Label;
import java.awt.Panel;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class Gui extends Application {
	
	
	  public void start(Stage primaryStage) {
		  // Create a scene and place a button in the scene
		
		    Pane pane = new Pane();
	        Rectangle rectangle1= new Rectangle(50, 50,200,200);
	        Rectangle rectangle2= new Rectangle(50,300,200,200);
	        Rectangle rectangle3= new Rectangle(300,50,200,200);
	        Rectangle rectangle4= new Rectangle(550,50,200,200);
	        Rectangle rectangle5= new Rectangle(300,300,200,200);
	        Rectangle rectangle6= new Rectangle(550,300,200,200);
	        
	        
	        rectangle1.setFill(Color.YELLOW);
	        rectangle2.setFill(Color.YELLOW);
	       rectangle3.setFill(Color.YELLOW);
	        rectangle4.setFill(Color.YELLOW);
	        rectangle5.setFill(Color.YELLOW);
	       rectangle6.setFill(Color.YELLOW);
	       
	       Button button1 = new Button("  Up   ");
	       Button button2 = new Button("Down");
	       Button button3 = new Button("Right");
	       Button button4 = new Button(" Left ");
	       Button button5 = new Button(" Select ");
	       button1.setLayoutX(400);
	       button1.setLayoutY(535);
	       button2.setLayoutX(400);
	       button2.setLayoutY(600);
	       button3.setLayoutX(480);
	       button3.setLayoutY(570);
	       button4.setLayoutX(320);
	       button4.setLayoutY(570);
	       button5.setLayoutX(395);
	       button5.setLayoutY(570);
	    
	       
	        pane.getChildren().add(rectangle1);
	        pane.getChildren().add(rectangle2);
	        pane.getChildren().add(rectangle3);
	        pane.getChildren().add(rectangle4);
	        pane.getChildren().add(rectangle5);
	        pane.getChildren().add(rectangle6);
	        pane.getChildren().add(button1);
	        pane.getChildren().add(button2);
	        pane.getChildren().add(button3);
	        pane.getChildren().add(button4);
	        pane.getChildren().add(button5);
	     
	        
	        Scene scene = new Scene(pane,800,700);
	        primaryStage.setScene(scene);
	        
	        primaryStage.setTitle("Rectangle");
	        primaryStage.show();
		  	
		  	
		 }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		Application.launch(args); 
	}

}
