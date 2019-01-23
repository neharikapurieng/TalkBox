package TalkBoxSim;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class Gui extends Application {
	
	boolean collide = false;
	Clip clip;
	
	  public void start(Stage primaryStage) {
		  // Create a scene and place a button in the scene
		
	       Pane pane = new Pane();
		    
	       Button button1 = new Button("Sound 1");
	       Button button2 = new Button("Sound 2");
	       Button button3 = new Button("Sound 3");
	       Button button4 = new Button("Sound 4");
	       Button button5 = new Button("Sound 5");
	       Button button6 = new Button("Sound 6");
	       
	       button1.setLayoutX(0);
	       button1.setLayoutY(150);
	       button2.setLayoutX(200);
	       button2.setLayoutY(150);
	       button3.setLayoutX(400);
	       button3.setLayoutY(150);
	       button4.setLayoutX(600);
	       button4.setLayoutY(150);
	       button5.setLayoutX(800);
	       button5.setLayoutY(150);
	       button6.setLayoutX(1000);
	       button6.setLayoutY(150);
	    
	      button1.setMinSize(100,100);
	      button2.setMinSize(100,100);
	      button3.setMinSize(100,100);
	      button4.setMinSize(100,100);
	      button5.setMinSize(100,100);
	      button6.setMinSize(100,100);


	        pane.getChildren().add(button1);
	        pane.getChildren().add(button2);
	        pane.getChildren().add(button3);
	        pane.getChildren().add(button4);
	        pane.getChildren().add(button5);
	        pane.getChildren().add(button6);
	     
	        
	        Scene scene = new Scene(pane,1150,400);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("TalkBox");
	        primaryStage.show();

	        
	        button1.setOnAction(e -> handle("src/Audio/Hello.wav"));
	        button2.setOnAction(e -> handle("src/Audio//Bye.wav"));
	        button3.setOnAction(e -> handle("src/Audio/Yes.wav"));
	        button4.setOnAction(e -> handle("src/Audio/Laugh.wav"));
	        button5.setOnAction(e -> handle("src/Audio/Good Morning.wav"));
	        button6.setOnAction(e -> handle("src/Audio/Clap.wav"));
	        
	        pane.setStyle("-fx-background-color: #B0E0E6;");
	        button1.setTextFill(Color.BLACK);
	        button2.setTextFill(Color.BLACK);
	        button3.setTextFill(Color.BLACK);
	        button4.setTextFill(Color.BLACK);
	        button5.setTextFill(Color.BLACK);
	        button6.setTextFill(Color.BLACK);
	        
	        Label label = new Label("TalkBox");
		       label.setLabelFor(pane);
		       label.setLayoutX(500);
		       label.setLayoutY(35);
		       label.setStyle("-fx-font-family: TRON; -fx-font-size: 40;");
		       pane.getChildren().add(label);
	        
	        
	        
		 }
	  
	  public void handle(String s) {	
			if(this.collide == true) this.clip.stop();
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(s));
				this.clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				this.collide = true;
					}
			catch(Exception e) {
				System.out.println("Can't find audio file");
			}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
	}

}
