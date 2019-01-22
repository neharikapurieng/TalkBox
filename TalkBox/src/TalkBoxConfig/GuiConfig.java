package TalkBoxConfig;
import java.io.File;
import java.io.FilenameFilter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
public class GuiConfig extends Application {
	
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
	       button1.setLayoutY(200);
	       button2.setLayoutX(80);
	       button2.setLayoutY(200);
	       button3.setLayoutX(160);
	       button3.setLayoutY(200);
	       button4.setLayoutX(240);
	       button4.setLayoutY(200);
	       button5.setLayoutX(320);
	       button5.setLayoutY(200);
	       button6.setLayoutX(400);
	       button6.setLayoutY(200);
	    
	       button1.setMinWidth(75);
	       button1.setMinHeight(75);
	       button2.setMinWidth(75);
	       button2.setMinHeight(75);
	       button3.setMinWidth(75);
	       button3.setMinHeight(75);
	       button4.setMinWidth(75);
	       button4.setMinHeight(75);
	       button5.setMinWidth(75);
	       button5.setMinHeight(75);
	       button6.setMinWidth(75);
	       button6.setMinHeight(75);

	        pane.getChildren().add(button1);
	        pane.getChildren().add(button2);
	        pane.getChildren().add(button3);
	        pane.getChildren().add(button4);
	        pane.getChildren().add(button5);
	        pane.getChildren().add(button6);
	     
	        
	        Scene scene = new Scene(pane,900,600);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("TalkBox");
	        primaryStage.show();
	        
	       ListView <File> ListofAudio = new ListView<File>();
	       ListofAudio.getItems().addAll(finder("bin/Audio"));
	       pane.getChildren().add(ListofAudio);
	       ListofAudio.setLayoutX(600);
	       ListofAudio.setLayoutY(400);
	       ListofAudio.setMaxSize(200, 200);
	       
	       Button confirm = new Button("Confirm");
	       confirm.setLayoutX(800);
	       confirm.setLayoutY(400);
	       pane.getChildren().add(confirm);
	    
	       button1.setOnAction(e -> handle("bin/Audio/Hello.wav"));
	       button2.setOnAction(e -> handle("bin/Audio/Bye.wav"));
	       button3.setOnAction(e -> handle("bin/Audio/Yes.wav"));
	       button4.setOnAction(e -> handle("bin/Audio/Laugh.wav"));
	       button5.setOnAction(e -> handle("bin/Audio/Good Morning.wav"));
	       button6.setOnAction(e -> handle("bin/Audio/Clap.wav"));
	  
	       TreeItem<String> root, profile1, profile2;
	       root = new TreeItem<String>();
	       root.setExpanded(true);
	       
	       profile1 = branch("Greetings",root);
	       branch("Hello",profile1);
	       branch("Good Morning",profile1);
	       
	       profile2 = branch("Noises",root);
	       branch("Laugh",profile2);
	       branch("Clap",profile2);
	       
	       TreeView <String> Tree = new TreeView<>(root);
	       Tree.setShowRoot(false);
	       
	       pane.getChildren().add(Tree);
	       Tree.setLayoutX(600);
	       Tree.setLayoutY(0);
	       Tree.setMaxSize(200, 350);
	       
	       Button AddProfile = new Button("Add Profile");
	       AddProfile.setLayoutX(800);
	       AddProfile.setLayoutY(50);
	       pane.getChildren().add(AddProfile);
	       
	       Button RemoveProfile = new Button("Remove Profile");
	       RemoveProfile.setLayoutX(800);
	       RemoveProfile.setLayoutY(150);
	       pane.getChildren().add(RemoveProfile);
	       
	       Button AddSound = new Button("Add Sound");
	       AddSound.setLayoutX(800);
	       AddSound.setLayoutY(200);
	       pane.getChildren().add(AddSound);
	       
	       Button SetProfile= new Button("Set Profile");
	       SetProfile.setLayoutX(800);
	       SetProfile.setLayoutY(0);
	       pane.getChildren().add(SetProfile);
	       
	       Button EditProfile = new Button("Edit Profile");
	       EditProfile.setLayoutX(800);
	       EditProfile.setLayoutY(100);
	       pane.getChildren().add(EditProfile);
	       
	       
	       
	        
	  }
	  
	  public TreeItem<String> branch(String title, TreeItem<String> parent){
		  TreeItem<String> item = new TreeItem<>(title);
		  item.setExpanded(false);
		  parent.getChildren().add(item);
		  return item;
		  
		  
	  }
	  /*
	  public void File() {
		  File list = new File(this.getClass().getResource("/").getFile());
		  System.out.println(list.getAbsolutePath());
			}
			*/
	  
	  public File[] finder(String dirName) {
				File directoryPath = new File(dirName);
				File[] files=directoryPath.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".wav");
					}
				});
				return files;
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
			}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
	}

}
