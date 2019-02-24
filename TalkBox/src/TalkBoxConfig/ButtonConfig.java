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

public class ButtonConfig extends Application {
	
	

	private int ctr2;
	private ArrayList<Button> BList;
	private GridPane gridpane;

	
	public ButtonConfig() {
		
		this.BList=null;
		this.ctr2=0;
	}
/*
 * The method 
 */
	
	  public void buttonAdder(int n, GridPane pane,ScrollPane sc) throws IllegalArgumentException {
		  try {
			
			 
			  this.BList= new ArrayList<Button>();
			  pane= new GridPane();
			  
			  pane.getChildren().clear();
		  
		
			   for(int i = ctr2; i < n; i++) {
		    	   String buttonname = String.format("Sound %d", i+1);
		    	   Button button = new Button(buttonname);
		    	   BList.add( new Button(buttonname));
		    	   BList.get(i).setPadding(new Insets(10,10,10,10)); // tried adding padding to the buttons
		    	   ctr2++;
		    	   
		       }
			
			 
			   int count = n;
			 for(int j = 0; j <= Math.ceil(n/10);j++) {
				 if(count >= 10) {
					 for(int k = 0; k < 10; k++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 BList.get(ctr2).setPadding(new Insets(13,10,15,17));
						 pane.setHgap(5.5);
						 pane.setVgap(5.5);
						 pane.add(BList.get(ctr2), k, j);
						 HBox.setHgrow(BList.get(ctr2), Priority.ALWAYS);
						 VBox.setVgrow(BList.get(ctr2), Priority.ALWAYS);
						 ctr2++;
						 count--;}}
				 else {
					 for(int h = 0; h < count; h++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 BList.get(ctr2).setPadding(new Insets(13,10,15,17));
						 pane.setHgap(5.5);
						 pane.setVgap(5.5);
						 pane.add(BList.get(ctr2), h, j);
						 HBox.setHgrow(BList.get(ctr2), Priority.ALWAYS);
						 VBox.setVgrow(BList.get(ctr2), Priority.ALWAYS);
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

