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

	  TalkBoxConfiguration tbc;
	  Buttons b = new Buttons();
	  GridPane GridP = new GridPane();
	  ScrollPane ScrollP = new ScrollPane(GridP);
	  public void start(Stage primaryStage) throws Exception {
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
		  Scene scene = new Scene(pane,800,400);
		  primaryStage.setScene(scene);
		  primaryStage.show();
	  }

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
