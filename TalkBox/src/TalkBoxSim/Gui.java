package TalkBoxSim;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;

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
		  Pane pane = new Pane();
		  GridP.setLayoutX(0);
		  GridP.setLayoutY(100);
		  ScrollP.setLayoutX(0);
		  ScrollP.setLayoutY(100);
		  GridP.setMinSize(800, 300);
		  ScrollP.setMinSize(800, 300);
		  Adder(GridP);
		  pane.getChildren().add(ScrollP);
		  Profiles profile = new Profiles();
		  profile.profiles();
		  //profile.LaunchProfileDisplay().setLayoutX(200);
		  //profile.LaunchProfileDisplay().setLayoutY(100);
		  pane.getChildren().add(profile.LaunchProfileDisplay());
		  Scene scene = new Scene(pane,1200,600);
		  primaryStage.setScene(scene);
		  primaryStage.show();
	  }

	  /*
	   * Adds all the buttons onto the GridPane which is also attached to the ScrollPane
	   * Calls the Button Class & the TalkBoxData
	   * Max 10 buttons per line
	   */
	  public void Adder(GridPane p) {
		  int ctr = 0;
		  int count = tbc.NumOfAudioButtons;
		  for(int j = 0; j <= Math.ceil(tbc.NumOfAudioButtons/10);j++) {
				 if(count >= 10) {
					 for(int k = 0; k < 10; k++) {
						 b.Buttons.get(ctr).setMinSize(75, 75);
						 GridP.add(b.Buttons.get(ctr), k, j);
						 ctr++;
						 count--;
					 }
				 }
				 else {
					 for(int h = 0; h < count; h++) {
						 b.Buttons.get(ctr).setMinSize(75, 75);
						 GridP.add(b.Buttons.get(ctr), h, j);
						 ctr++;
				 }
			 }
			 }
	  }
	  
	  


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
	}



}
