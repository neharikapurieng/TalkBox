package TalkBoxConfig;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;



public class GuiConfig extends Application implements Serializable {
	
	private static final long serialVersionUID = 1L;
	boolean collide = false;
	Clip clip;
	String profilename = "";
	String soundname = "";
	
	TreeItem<String> root, profile1, profile2;
	TreeView <String> Tree;
	
	int row = 0;
	Button SetProfile;
	public ArrayList<Button> BList;
	@SuppressWarnings("rawtypes")
	ArrayList<TreeItem> TItems;
	String src = "bin/TalkBoxData/Audio/";
	TextField PN;
	public Pane pane;
	GridPane sp;
	Pane Back;
	public static ScrollPane sc;
	int ctr = 480;
	int increment = 0;
	int increment2 = 0;

	
	public int numofbuttons;
	public Path pathtofile = null;
	
	
	public GuiConfig() {
		SetProfile = new Button("Set Profile");
		BList = new ArrayList<>();
	}
	
	
	  public void start(Stage primaryStage) {
		  // Create a scene and place a button in the scene
		   primaryStage.setTitle("TalkBoxConfig");
		    Pane pane = new Pane();
	        Scene scene = new Scene(pane,1100,600);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("TalkBox");
	        primaryStage.show();
	        
	       Back = new Pane();
	       
	       sp = new GridPane();
	       sc = new ScrollPane(sp);
		   sp.setMinSize(800, 400);
	       sc.setMinSize(800, 400);
	       sc.setMaxSize(800, 400);
	       pane.getChildren().addAll(Back,sc);
		
	       
	       
	       ListView <File> ListofAudio = new ListView<File>();
	       ListofAudio.getItems().addAll(finder(src));
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
	       Tree.getSelectionModel().selectedItemProperty().addListener((v,oldValue,NewValue) -> {   
	    	   if(NewValue != null) {
	    		   row = Tree.getRow(NewValue);
	    		   profilename = NewValue.getValue();
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
	 
	       Back.getChildren().add(RemoveButton);
	       
	       
	       Button AddButton = new Button("Add Button");
	       AddButton.setLayoutX(400);
	       AddButton.setLayoutY(525);
	       AddButton.setMinSize(75, 75);
	       Back.getChildren().add(AddButton);
	       
	       
	       Button RemoveProfile = new Button("Remove Profile");
	       RemoveProfile.setLayoutX(1000);
	       RemoveProfile.setLayoutY(100);
	       Back.getChildren().add(RemoveProfile);
	       
	       
	       Button AddSound = new Button("Add Sound");
	       AddSound.setLayoutX(1000);
	       AddSound.setLayoutY(400);
	       Back.getChildren().add(AddSound);
	       
	       
	       PN = new TextField("Enter Profile Name");
	       PN.setLayoutX(800);
	       PN.setLayoutY(350);
	       Back.getChildren().add(PN);
	       PN.setOnMouseClicked(e -> PN.clear());
	       PN.setOnAction(e -> {ProfileAdder(PN.getText()); PN.clear();});
	   
	       
	       
	       SetProfile.setLayoutX(1000);
	       SetProfile.setLayoutY(0);
	       Back.getChildren().add(SetProfile);
	       
	       
	       Button Record = new Button("Record");
	       Record.setLayoutX(0);
	       Record.setLayoutY(500);
	       Back.getChildren().add(Record);
	       Record.setMinSize(100, 80);
	       Record.setOnAction(e ->{ JavaSoundRecorder sound = new JavaSoundRecorder(); sound.start(); });
	       
	       Button Start = new Button("Start");
	       Start.setLayoutX(110);
	       Start.setLayoutY(500);
	       Back.getChildren().add(Start);
	       
	       Button Stop = new Button("Stop");
	       Stop.setLayoutX(110);
	       Stop.setLayoutY(530);
	       Stop.setOnAction(e ->{ JavaSoundRecorder sound = new JavaSoundRecorder(); sound.finish();});
	       
	       Back.getChildren().add(Stop);
	      
	      
	       ProgressBar AudioBar = new ProgressBar();
	       AudioBar.setLayoutX(0);
	       AudioBar.setLayoutY(580);
	       Back.getChildren().add(AudioBar);
	       AudioBar.setMinSize(200, 15);
	      
	        
	       TextField text = new TextField("Enter Filename");
	       text.setLayoutX(0);
	       text.setLayoutY(470);
	       Back.getChildren().add(text);
	       text.setOnMouseClicked(e -> text.clear());
	       Stop.setOnMouseClicked(e -> {if(text.getText().isEmpty())text.insertText(0, "Enter Filename");});
	       
	       TextField numofB = new TextField("Enter number of buttons");
	       numofB.setLayoutX(400);
	       numofB.setLayoutY(500);
	       Back.getChildren().add(numofB);
	       numofB.setOnMouseClicked(e -> numofB.clear());
	       numofB.setOnAction(e -> {numofbuttons = Integer.parseInt(numofB.getText()); bAdder();});
	       
	       Button SerializeButton = new Button("Serialize");
	       SerializeButton.setLayoutX(500);
	       SerializeButton.setLayoutY(500);
	       Back.getChildren().add(SerializeButton);
	       SerializeButton.setOnAction(e ->{
	    	   
	    	   TalkBoxConfiguration tbc = new TalkBoxConfiguration();
	    	   try {
	    	   tbc.NumOfAudioButtons = numofbuttons;
	    	   tbc.NumOfAudioSets = TItems.get(row).getChildren().size();
	    	   tbc.NumOfButtons = numofbuttons + 7;
	    	   tbc.PathToAudioFiles = null;
	    	   tbc.AudioName = null;
				Serializer.Save(tbc, "bin/TalkBoxData/TalkBoxData.tbc");
			} catch (Exception e1) {
				System.out.println("Exception Caught Cannot Serialize");
			}
	    	   
	       });
	       
	       
	       Label label = new Label("TalkBox");
	       label.setLabelFor(pane);
	       label.setLayoutX(400);
	       label.setLayoutY(10);
	       label.setStyle("-fx-font-family: TRON; -fx-font-size: 25;");
	       Back.getChildren().add(label);
	       
	      // AddButton.setOnAction(e -> pane.getChildren().add(BAdder()));
	       SetProfile.setOnAction(e -> swapAudio());
	       AddSound.setOnAction(e -> SoundAdder(soundname));
	       
	      // RemoveButton.setOnAction(e -> BRemover());
	       
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
	 

	  public void bAdder() {
		   for(int i = 0; i < numofbuttons; i++) {
	    	   String buttonname = String.format("Sound %d", i);
	    	   BList.add(new Button(buttonname));
	       }
		   int ctr = 0;
		   for(Button e : BList) {
			   e.setMinSize(75, 75);
			   if(ctr <= 9) {
				  sp.add(e,ctr,0);
				   ctr++;
			   }
			   else if(ctr > 9 && ctr <= 19) {
				   sp.add(e, ctr - 10, 1);
				   ctr++;
			   }
			   else if(ctr > 19 && ctr <= 29) {
				   sp.add(e, ctr - 20, 2);
				   ctr++;
			   }
		   }
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
			  BList.get(i).setOnAction(e -> handle(src + name + ".wav"));
		  }
	  }
	  
	  public void SoundAdder(String s) {
		  StringBuilder e = new StringBuilder();
		  e.append(s);
		  e.delete(0, 22);
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
