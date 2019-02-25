package TalkBoxSim;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

	  TalkBoxConfiguration tbc; // TalkBoxConfiguration Data
	   //Buttons from button class
	  Buttons button;
	  GridPane GridP = new GridPane(); //GridPane
	  ScrollPane ScrollP = new ScrollPane(GridP); //ScrollPane with GridPane Back
	  public void start(Stage primaryStage) throws Exception {
		  VBox main = new VBox();
		  //Deserializers and Loads information from TalkBoxData
		  tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
		  button = new Buttons(tbc.getNumberOfAudioButtons());
		  HBox pane = new HBox();
		  GridP.setMinSize(800, 300);
		  ScrollP.setMinSize(800, 300);
		  button.Adder(GridP,tbc.getNumberOfAudioButtons());
		  VBox v = new VBox();
		  Profiles profile = new Profiles();
		  v.getChildren().add(profile.LaunchProfileDisplay());
		  Audio audio = new Audio();
		  button.SetProfile(v);
		  button.set.setOnAction(e->{
			  try {
				  button = new Buttons(tbc.getNumberOfAudioButtons());
				  button.Adder(GridP, tbc.getNumberOfAudioButtons());
				  audio.AudioToButton(GridP, button.getButtonList(),profile.LaunchProfileDisplay(),profile.row);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  });
		  pane.getChildren().addAll(ScrollP,v);
		  Scene scene = new Scene(main,1050,400);
		  scene.getStylesheets().add("application.css");
		  Label label = new Label("TalkBox Simulator");
		  label.setCenterShape(true);
		  label.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
		  main.getChildren().addAll(label,pane);
		  primaryStage.setScene(scene);
		  primaryStage.setTitle("TalkBox Simulator");
		  primaryStage.show();

	  }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
	}



}
