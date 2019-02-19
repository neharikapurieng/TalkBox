package TalkBoxConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import TalkBoxSim.Gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;



public class GuiConfig extends Application {
	

	boolean collide = false;
	private Clip clip;
	
	//Strings
	public String profilename = "";
	private String soundname = "";
	private String filename;
	public String src = "src/Audio/";
	
	//Profile & Audio
	private TreeItem<String> root;
	public TreeView <String> Tree;
	@SuppressWarnings("rawtypes")
	private ArrayList<TreeItem> TItems;
	public ArrayList<Button> BList = new ArrayList<Button>();
	private ListView <String> ListofAudio;
	
	//Items to Pane
	private Button SetProfile;
	private TextField PN;
	public TextField numofB;
	
	//Panes
	public Pane pane;
	private GridPane sp;
	private Pane Back;
	public ScrollPane sc;
	
	//Ints
	int ctr = 480;
	int row = 0;
	int increment = 0;
	int increment2 = 0;
	private int ctr2;
	

	//Serialization
	public int numofbuttons;
	public Path pathtofile = null;

	
	
	  public void start(Stage primaryStage) throws FileNotFoundException {
		  // Create a scene and place a button in the scene
		    primaryStage.setTitle("TalkBoxConfig");   // Set title of talkbox
		    Pane pane = new Pane();
	        Scene scene = new Scene(pane,1115,600);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("TalkBox");
	        primaryStage.show();
	        scene.getStylesheets().add("application.css");       
	        Back = new Pane();
	       
	       sp = new GridPane(); // matrix 
	       sc = new ScrollPane(sp); // launch the gui, the white space (scroll)
	       sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // size of the scroll bar 
	       sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); 
		   sp.setMinSize(800, 300);
		   sp.setLayoutY(80);
		   sc.setLayoutY(80);
	       sc.setMinSize(800, 300);
	       sc.setMaxSize(800, 300);
	       pane.getChildren().addAll(Back,sc);
	       
	       Menu menu = new Menu();
	       menu.setText("File"); // file button 
	       MenuItem mi = new MenuItem("Import Audio"); // another button when we press file
	       mi.setStyle("-fx-text-fill:black");
	       menu.getItems().addAll(mi);
	       
	       mi.setOnAction(e -> {
	    	   ImportAudio ia = new ImportAudio();  
	    	   ia.open(); 
	    	   refresh(ia.name);// leads to home directory 
	       });
	       
	       // make the menu bar 
	       MenuBar mb = new MenuBar();
	       mb.getMenus().addAll(menu);
	       pane.getChildren().add(mb);
	       
	       //list of pre-recorded audios 
	       ListofAudio = new ListView<String>();
	       ListofAudio.getItems().addAll(ListofAudio());
	       pane.getChildren().add(ListofAudio);
	       ListofAudio.setLayoutX(800);
	       ListofAudio.setLayoutY(300);
	       ListofAudio.setMaxSize(200, 175);
	       ListofAudio.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> {
	    	   soundname = newValue.toString();
	    	  
	       });
	      
	       
	       root = new TreeItem<String>(); // This is used to create the profile and root and branches are added
	       root.setExpanded(true);
	       
	       
	       TItems = new ArrayList<>(); // creating profile
	       
	       // put tree item in tree    
	       TreeView <String> Tree = new TreeView<>(root);
	       Tree.setShowRoot(false);
	       Tree.getSelectionModel().selectedItemProperty().addListener((v,oldValue,NewValue) -> {   
	    	   if(NewValue != null) {

	    		   // row is the position of the file name

	    		   row = Tree.getRow(NewValue); 
	    		   profilename = NewValue.getValue();
	    	   }
	       });
	       
	       pane.getChildren().add(Tree);
	       Tree.setLayoutX(800);
	       Tree.setLayoutY(30);
	       Tree.setMaxSize(200, 200);
	       
	       //Remove Profile Button 
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
	       PN.setLayoutY(230);
	       Back.getChildren().add(PN);

	       PN.setOnMouseClicked(e -> PN.clear()); //clears when clicked

	       PN.setOnMouseClicked(e -> PN.clear()); // clears the textfield when mouse is clicked on set profile textfield

	       PN.setOnAction(e -> {ProfileAdder(PN.getText()); PN.clear();});

	       
	       SetProfile = new Button("Set Profile");
	       SetProfile.setLayoutX(1000);
	       SetProfile.setLayoutY(30);
	       Back.getChildren().add(SetProfile);
	       
	       Button Record = new Button("Record");
	       Record.setLayoutX(800);
	       Record.setLayoutY(520);
	       Back.getChildren().add(Record);

	       TextField text = new TextField("Enter Filename");
	       text.setLayoutX(875);
	       text.setLayoutY(570);
	       Back.getChildren().add(text);
	       text.setOnMouseClicked(e -> text.clear());
	       text.setOnAction(e -> filename = text.getText()); //whatever input is, it is stored in the variable so we can use it sor serializer
	       
	       Record.setMinSize(75, 75);
	       Record.setOnAction(e ->{ Sound sound = new Sound(); try {
	    	sound.SoundFormart();
	    	sound.temp = filename;
			sound.start();
		} catch (InterruptedException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} });
	       // to start recording
	       Button Start = new Button("Start");
	       Start.setLayoutX(880);
	       Start.setLayoutY(530);
	       Back.getChildren().add(Start);
	       
	       // to stop recording
	       Button Stop = new Button("Stop");

	       Stop.setLayoutX(925);
	       Stop.setLayoutY(530);
	       Stop.setOnAction(e ->{ Sound sound = new Sound(); sound.stop();});
	       
	       Back.getChildren().add(Stop);
	      
	       numofB = new TextField("Enter number of buttons");
	       numofB.setLayoutX(0);
	       numofB.setLayoutY(475);
	       numofB.setMinSize(200, 50);
	       Back.getChildren().add(numofB);
	       numofB.setOnMouseClicked(e -> numofB.clear());
	       numofB.setOnAction(e -> {numofbuttons = Integer.parseInt(numofB.getText()); bAdder();});  //?
	       
	       Button LaunchSim = new Button("Launch");
	       LaunchSim.setLayoutX(450);
	       LaunchSim.setLayoutY(425);
	       LaunchSim.setMinSize(150, 150);
	       Back.getChildren().add(LaunchSim);
	       LaunchSim.setOnAction(e -> {
	    	   TalkBoxConfiguration tbc = new TalkBoxConfiguration();
	    	   try {
	    	   tbc.NumOfAudioButtons = numofbuttons;
	    	   tbc.NumOfAudioSets = TItems.get(row).getChildren().size(); //?
	    	   tbc.NumOfButtons = numofbuttons + 7; //?
	    	   tbc.PathToAudioFiles = null; // 
	    	   tbc.AudioName = audioFiles();
	    	   tbc.path = src;
	    	   tbc.Profiles = profiles();
				Serializer.Save(tbc, "bin/TalkBoxData/"); //saves in serializer
			} catch (Exception e1) {
			e1.printStackTrace();
			}

	    	   Gui g = new Gui(); //gui obj is created so the things can be stored from configurator to stimulator 


	       });
	       LaunchSim.setOnAction(e -> {
	    	   Gui g = new Gui(); //?

	    	  try {
				g.start(new Stage());
				primaryStage.close();	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       });
	       
	       Label labelProfile = new Label("Profiles");
	       labelProfile.setLabelFor(pane);
	       labelProfile.setLayoutX(860);
	       labelProfile.setLayoutY(0);
	       labelProfile.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
	       Back.getChildren().add(labelProfile);
	       
	       Label labelAudio = new Label("Audio");
	       labelAudio.setLabelFor(pane);
	       labelAudio.setLayoutX(860);
	       labelAudio.setLayoutY(270);
	       labelAudio.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
	       Back.getChildren().add(labelAudio);
	       
	       Label label = new Label("TalkBox Preview");
	       label.setLabelFor(pane);
	       label.setLayoutX(325);
	       label.setLayoutY(10);
	       label.setStyle("-fx-font-family: TRON; -fx-font-size: 25;");
	       Back.getChildren().add(label);
	       
	       Label labelRecord = new Label("Record Audio");
	       labelRecord.setLabelFor(pane);
	       labelRecord.setLayoutX(825);
	       labelRecord.setLayoutY(490);
	       labelRecord.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
	       Back.getChildren().add(labelRecord);
	       
	       SetProfile.setOnAction(e -> swapAudio());
	       AddSound.setOnAction(e ->
	       SoundAdder(soundname));
	       RemoveProfile.setOnAction(e -> ProfileRemover(row));
	  }
	 
// add titles for audio files 
	public TreeItem<String> branch(String title, TreeItem<String> parent){
		  TreeItem<String> item = new TreeItem<>(title);
		  item.setExpanded(false);
		  parent.getChildren().add(item);
		  return item; }

	// put all the audio files into an array   
	  public File[] finder(String dirName) {
				File directoryPath = new File(dirName);
				File[] files=directoryPath.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {     //?
						return name.endsWith(".wav");}});
				return files;}
	 

	  public void bAdder() {
	      sp.getChildren().clear();
			   for(int i = ctr2; i < numofbuttons; i++) {
		    	   String buttonname = String.format("Sound %d", i+1);
		    	   BList.add(new Button(buttonname));
		    	   BList.get(i).setPadding(new Insets(10,10,10,10)); // tried adding padding to the buttons
		       }
			  this.ctr2 = 0;
			 
			   int count = numofbuttons;
			 for(int j = 0; j <= Math.ceil(numofbuttons/10);j++) {
				 if(count >= 10) {
					 for(int k = 0; k < 10; k++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 sp.add(BList.get(ctr2), k, j);
						 ctr2++;
						 count--;}}
				 else {
					 for(int h = 0; h < count; h++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 sp.add(BList.get(ctr2), h, j);
						 ctr2++; }}}}

	  
	  public void ProfileAdder(String title) {
		  TItems.add(branch(title,root)); }
	  
	  public void ProfileRemover(int r) {
		  root.getChildren().remove(r);}

	  public void swapAudio() {
		  int size = root.getChildren().get(row).getChildren().size();
		  ArrayList<String> al = new ArrayList<String>();
		  for(int k = 0; k < size; k++) {
			  al.add(root.getChildren().get(row).getChildren().get(k).getValue());}
		  for(int i = 0; i < size; i++) {
			  String name = al.get(i);
			  BList.get(i).setText(name);
			  BList.get(i).setOnAction(e -> handle(src + name + ".wav"));}}
	  
	  public void SoundAdder(String s) {
		  branch(s,root.getChildren().get(row));}
	  
	  public void handle(String s) {	// Play Audio Files and checks if it exists
			if(this.collide == true) this.clip.stop();
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(s));
				this.clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				this.collide = true;}
			catch(Exception e) {	
				System.out.println("Can't find audio file");}}
	  
	  public ArrayList<String> ListofAudio() {
		  ArrayList<String> al = new ArrayList<String>();
		  for(File temp : finder(src)){
			  StringBuilder sb = new StringBuilder();
			  sb.append(temp.getName());
			  sb.delete(sb.length()-4, sb.length());
			  al.add(sb.toString());}
		  return al;
		  }
	  
	  public String[][] audioFiles(){
		  String[][] temp = new String[root.getChildren().size()][BList.size()];
		  for(int i = 0; i < root.getChildren().size(); i++) {
		  int numofAudio = TItems.get(i).getChildren().size();
			for(int j = 0; j < numofAudio; j++) {
				temp[i][j] = TItems.get(i).getChildren().get(j).toString();}}
		  for(String[] row : temp) {
			  System.out.println(Arrays.toString(row));}
		return temp;}
	  
	  public String[] profiles() {
		  String[] temp = new String[root.getChildren().size()];
		  for(int i = 0; i < temp.length; i++) {
			  temp[i] = root.getChildren().get(i).toString();
		  }
		  System.out.println(Arrays.toString(temp));
		  return temp;
	  }
	 
	  public void refresh(String s) {
		  StringBuilder sb = new StringBuilder();
		  sb.append(s);
		  sb.delete(sb.length()-4, sb.length());
		  ListofAudio.getItems().add(sb.toString());
	  }
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
	}

}
