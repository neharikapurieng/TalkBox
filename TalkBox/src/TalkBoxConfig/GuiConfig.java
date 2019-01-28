package TalkBoxConfig;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class GuiConfig extends Application {
	
	boolean collide = false;
	Clip clip;
	int ctr = 375 + 75;
	int number = 6;
	String profilename = "";
	String soundname = "";
	
	TreeItem<String> root, profile1, profile2;
	TreeView <String> Tree;
	public TreeItem<String> temp;
	
	int row = 0;
	Button SetProfile;
	ArrayList<Button> BList;
	ArrayList<TreeItem> TItems;
	String src = "src/Audio/";
	TextField PN;
	Pane pane;
	
	  public void start(Stage primaryStage) {
		  // Create a scene and place a button in the scene
		   primaryStage.setTitle("TalkBoxConfig");
	       pane = new Pane();
	      
	      BList = new ArrayList<>();
	       for(int i = 0; i < 6; i++) {
	    	   String buttonname = String.format("Sound %x", i);
	    	   BList.add(new Button(buttonname));
	       }
	  
	       int x = 0;
	       for(int k = 0; k < BList.size(); k++) {
	    	   int y = 50;
	    	   BList.get(k).setLayoutX(x);
	    	   BList.get(k).setLayoutY(y);
	    	   BList.get(k).setMinSize(75, 75);
	    	   pane.getChildren().add(BList.get(k));
	    	   x += 75;
	       }
	         
	       
	        Scene scene = new Scene(pane,1100,600);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("TalkBox");
	        primaryStage.show();
	        

	       ListView <File> ListofAudio = new ListView<File>();
	       ListofAudio.getItems().addAll(finder("src/Audio"));
	       pane.getChildren().add(ListofAudio);
	       ListofAudio.setLayoutX(800);
	       ListofAudio.setLayoutY(400);
	       ListofAudio.setMaxSize(200, 200);
	       ListofAudio.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> {
	    	   soundname = newValue.toString();
	       });
	      
	       
	       root = new TreeItem<String>();
	       root.setExpanded(true);
	       
	       TItems = new ArrayList<>();
	          
	       TreeView <String> Tree = new TreeView<>(root);
	       Tree.setShowRoot(false);
	       Tree.getSelectionModel().selectedItemProperty().addListener((v,oldValue,temp) -> {   
	    	   if(temp != null) {
	    		   row = Tree.getRow(temp);
	    		   profilename = temp.getValue();
	    		  // System.out.println(profilename);
	    	   }
	       });
	          
	       
	       pane.getChildren().add(Tree);
	       Tree.setLayoutX(800);
	       Tree.setLayoutY(0);
	       Tree.setMaxSize(200, 350);
	       
	       Button RemoveButton = new Button("Remove Button");
	       RemoveButton.setLayoutX(475);
	       RemoveButton.setLayoutY(525);
	       RemoveButton.setMinSize(75, 75);
	       pane.getChildren().add(RemoveButton);
	       
	       
	       Button AddButton = new Button("Add Button");
	       AddButton.setLayoutX(400);
	       AddButton.setLayoutY(525);
	       AddButton.setMinSize(75, 75);
	       pane.getChildren().add(AddButton);
	       
	       
	       Button RemoveProfile = new Button("Remove Profile");
	       RemoveProfile.setLayoutX(1000);
	       RemoveProfile.setLayoutY(100);
	       pane.getChildren().add(RemoveProfile);
	       
	       
	       Button AddSound = new Button("Add Sound");
	       AddSound.setLayoutX(1000);
	       AddSound.setLayoutY(400);
	       pane.getChildren().add(AddSound);
	       
	       
	       PN = new TextField("Enter Profile Name");
	       PN.setLayoutX(800);
	       PN.setLayoutY(350);
	       pane.getChildren().add(PN);
	       PN.setOnMouseClicked(e -> PN.clear());
	       PN.setOnAction(e -> {ProfileAdder(PN.getText()); PN.clear();});
	   
	       
	       
	       Button SetProfile= new Button("Set Profile");
	       SetProfile.setLayoutX(1000);
	       SetProfile.setLayoutY(0);
	       pane.getChildren().add(SetProfile);
	       
	       /*
	       Button EditProfile = new Button("Edit Profile");
	       EditProfile.setLayoutX(1000);
	       EditProfile.setLayoutY(50);
	       pane.getChildren().add(EditProfile);
	       */
	       
	       Button Record = new Button("Record");
	       Record.setLayoutX(0);
	       Record.setLayoutY(500);
	       pane.getChildren().add(Record);
	       Record.setMinSize(100, 80);
	       Record.setOnAction(e ->{ JavaSoundRecorder sound = new JavaSoundRecorder(); sound.start(); });
	       
	       Button Start = new Button("Start");
	       Start.setLayoutX(110);
	       Start.setLayoutY(500);
	       pane.getChildren().add(Start);
	       
	       Button Stop = new Button("Stop");
	       Stop.setLayoutX(110);
	       Stop.setLayoutY(530);
	       Stop.setOnAction(e ->{ JavaSoundRecorder sound = new JavaSoundRecorder(); sound.finish(); });
	       
	       pane.getChildren().add(Stop);
	      
	      
	       ProgressBar AudioBar = new ProgressBar();
	       AudioBar.setLayoutX(0);
	       AudioBar.setLayoutY(580);
	       pane.getChildren().add(AudioBar);
	       AudioBar.setMinSize(200, 15);
	      
	        
	       TextField text = new TextField("Enter Filename");
	       text.setLayoutX(0);
	       text.setLayoutY(470);
	       pane.getChildren().add(text);
	       text.setOnMouseClicked(e -> text.clear());
	       Stop.setOnMouseClicked(e -> {if(text.getText().isEmpty())text.insertText(0, "Enter Filename");});
	       

	       
	       Label label = new Label("TalkBox");
	       label.setLabelFor(pane);
	       label.setLayoutX(400);
	       label.setLayoutY(10);
	       label.setStyle("-fx-font-family: TRON; -fx-font-size: 25;");
	       pane.getChildren().add(label);
	       
	       AddButton.setOnAction(e -> pane.getChildren().add(BAdder()));
	       SetProfile.setOnAction(e -> swapAudio());
	       AddSound.setOnAction(e -> SoundAdder(soundname));
	       RemoveButton.setOnAction(e -> BRemover());
	       RemoveProfile.setOnAction(e -> ProfileRemover(row));
	      
	     
	  }
	 

	public TreeItem<String> branch(String title, TreeItem<String> parent){
		  TreeItem<String> item = new TreeItem<>(title);
		  item.setExpanded(false);
		  parent.getChildren().add(item);
		  return item;
	  }

	  
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
	  
	  
	  public Button BAdder() {
		  String num = String.format("Sound %d", number);
		  BList.add(new Button(num));
		  BList.get(number).setLayoutX(ctr);
		  BList.get(number).setLayoutY(50);
		  BList.get(number).setMinSize(75, 75);
		  ctr += 75;
		  number += 1;
		  return BList.get(number - 1);
	  }
	  
	  public void BRemover() {
		  pane.getChildren().remove(BList.get(BList.size() - 1));
		  BList.remove(BList.size() - 1);
		  ctr-=75;
		  number-=1;
	  }

	  public void ProfileAdder(String title) {
		  TItems.add(branch(title,root));
	  }
	  
	  
	  public void ProfileRemover(int r) {
		  root.getChildren().remove(r);
	  }
	 
	  
	  

	  public void swapAudio() {
		  int size = root.getChildren().get(row).getChildren().size();
		  ArrayList<String> al = new ArrayList<String>();
		  for(int k = 0; k < size; k++) {
			  al.add(root.getChildren().get(row).getChildren().get(k).getValue());
		  }

		  for(int i = 0; i < size; i++) {
			  String name = al.get(i);
			 // System.out.println(name);
			  BList.get(i).setOnAction(e -> handle(src + name + ".wav"));
		  }
	  }
	  
	  public void SoundAdder(String s) {
		  StringBuilder e = new StringBuilder();
		  e.append(s);
		  e.delete(0, 10);
		  e.delete(e.length()-4, e.length());
		  branch(e.toString(),root.getChildren().get(row));
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
