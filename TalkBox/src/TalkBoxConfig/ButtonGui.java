package TalkBoxConfig;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonGui extends Application {
	
	

	private int ctr2;
	public ArrayList<Button> BList;

	
	public ButtonGui() {
		
		this.BList=null;
		this.ctr2=0;
	}
	
	
	  public void buttonAdder(int n,GridPane sp) throws IllegalArgumentException {
		  try {
			  this.BList= new ArrayList<Button>();;
		  sp.getChildren().clear();
			   for(int i = ctr2; i < n; i++) {
		    	   String buttonname = String.format("Sound %d", i+1);
		    	   BList.add( new Button(buttonname));
		    	   ctr2++;
		       }
			   this.ctr2 = 0;
			   int count = n;
			 for(int j = 0; j <= Math.ceil(n/5);j++) {
				 if(count >= 5) {
					 for(int k = 0; k < 5; k++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 sp.add(BList.get(ctr2), k, j);
						 GridPane.setHgrow(BList.get(ctr2), Priority.ALWAYS);
						 GridPane.setVgrow(BList.get(ctr2), Priority.ALWAYS);
						 ctr2++;
						 count--;}}
				 else {
					 for(int h = 0; h < count; h++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 sp.add(BList.get(ctr2), h, j);
						 GridPane.setHgrow(BList.get(ctr2), Priority.ALWAYS);
						 GridPane.setVgrow(BList.get(ctr2), Priority.ALWAYS);
						 ctr2++; }}}}
		  catch(IllegalArgumentException io) {
			 System.out.println("Wrong input");
			  
		  }
		  }
	  
  public ArrayList<Button> getArray() {
		  return this.BList;
		  }
	
 public int getNumofButton() {
	 
	 return this.ctr2;
 }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}




}

