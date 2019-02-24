package TalkBoxSim;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Gui extends Application {

	  TalkBoxConfiguration tbc; // TalkBoxConfiguration Data
	  Buttons b = new Buttons(); //Buttons from button class
	  GridPane GridP = new GridPane(); //GridPane
	  ScrollPane ScrollP = new ScrollPane(GridP); //ScrollPane with GridPane Back
	  public void start(Stage primaryStage) throws Exception {
		  //Deserializers and Loads information from TalkBoxData
		  tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
		  Buttons button = new Buttons();
		  Pane pane = new Pane();
		  GridP.setLayoutX(0);
		  GridP.setLayoutY(100);
		  ScrollP.setLayoutX(0);
		  ScrollP.setLayoutY(100);
		  GridP.setMinSize(800, 300);
		  ScrollP.setMinSize(800, 300);
		  button.Adder(GridP);
		  pane.getChildren().add(ScrollP);
		  Profiles profile = new Profiles();
		  pane.getChildren().add(profile.LaunchProfileDisplay());
		  Audio audio = new Audio();
		  audio.AudioToButton(GridP);
		  Scene scene = new Scene(pane,1200,600);
		  Label label = new Label("TalkBox Simulator");
		  label.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
		  label.setLayoutX(500);
		  label.setLayoutY(0);
		  pane.getChildren().add(label);
		  primaryStage.setScene(scene);
		  primaryStage.setTitle("TalkBox Simulator");
		  primaryStage.show();
	  }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
	}



}
