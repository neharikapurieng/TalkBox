package TalkBoxConfig;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Dimension2D;
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
	public static String profilename = "";
	private String soundname = "";
	private String filename;
	public String src = "src/Audio/";
	
	//Profile & Audio
	private TreeItem<String> root;
	public TreeView <String> Tree;
	@SuppressWarnings("rawtypes")
	private static ArrayList<TreeItem> TItems;
	public ArrayList<Button> BList = new ArrayList<Button>();
	private ListView <String> ListofAudio;
	
	//Items to Pane
	private Button SetProfile;
	private TextField PN;
	public TextField numofB;
	
	//Panes
	public Pane pane;
	private GridPane sp;
	public ScrollPane sc;
	
	//Ints
	int ctr = 480;
	static int row = 0;
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
	        
	        //This line applys all the css code from application.css
	        scene.getStylesheets().add("application.css");       
	        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	       sp = new GridPane(); // matrix 
	   
	       sc = new ScrollPane(sp); // launch the gui, the white space (scroll)
	       sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // size of the scroll bar 
	       sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); 
		   sp.setMinSize(800, 300);
		   sp.setLayoutY(80);
		   sc.setLayoutY(80);
	       sc.setMinSize(800, 300);
	       sc.setMaxSize(800,500);
	       sc.prefHeightProperty().bind(scene.heightProperty().divide(2));
	       sc.prefWidthProperty().bind(scene.widthProperty().divide(2));
	       
	       
	      

	 
	      pane.getChildren().add(sc);
	      
	      
		     pane.heightProperty().addListener(new ChangeListener()  {
		    	 	
					
					private ScrollPane sc;

					@Override
					public void changed(ObservableValue observable, Object oldValue, Object newValue) {
						// TODO Auto-generated method stub
						
					          double  newValue1 = (double)newValue;
					           this.sc = new ScrollPane(sp); // launch the gui, the white space (scroll)
						       sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // size of the scroll bar 
						       sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); 
							   sp.setMinSize(800, 300);
							   sp.setLayoutY(newValue1);
							   sc.setMinSize(800, 300);
						       sc.setMaxSize(800,300);
						       
					      
						      
					
					}

			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			     });
			     pane.widthProperty().addListener(new ChangeListener()  {
			    	 	
						
						private ScrollPane sc;

						@Override
						public void changed(ObservableValue observable, Object oldValue, Object newValue) {
							// TODO Auto-generated method stub
							
						           double  newValue1 = (double)newValue;
						           this.sc = new ScrollPane(sp); // launch the gui, the white space (scroll)
							       sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // size of the scroll bar 
							       sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); 
								   sp.setMinSize(800, 300);
								   sp.setLayoutX(newValue1);
								   sc.setMinSize(800, 300);
							       sc.setMaxSize(800,300);
							       
							       ListofAudio = new ListView<String>();
								      
								       ListofAudio.getItems().addAll(ListofAudio()); // addAll an arrayList ListofAudio
								       
								       ListofAudio.setLayoutX(newValue1);
								       ListofAudio.setLayoutY(300);
								       ListofAudio.setMaxSize(200, 175);
								      
							    
						      
							      
						
						}

				    	 
				    	 
				    	 
				    	 
				    	 
				    	 
				     });
			      
			      
	      
	      
	       
	       /*
	        *The menu objects are to allows users to import their own audio files
	        *MenuBar mb creates the menubar to hold menus
	        *Menu creates a menu
	        *Menuitem creates an item within a menu
	        *ie) In eclipses the menu bare has many options such as File and Edit
	        *When you click File many menuitems come up such as import or new.
	        *
	        *For our program we have a menu called File which has an item called Import Audio
	        *When Import Audio is pressed it calls the class ImportAudio 
	        *where an audio file can be chosen from the users personal directories
	        *and is copied over to src/Audio
	        *refresh is then called to update the gui
	        */
	       Menu menu = new Menu();
	       menu.setText("File");
	       MenuItem mi = new MenuItem("Import Audio"); 
	       mi.setStyle("-fx-text-fill:black");
	       menu.getItems().addAll(mi);
	       
	       mi.setOnAction(e -> {
	    	   ImportAudio ia = new ImportAudio();  
	    	   ia.open(); 
	    	   refresh(ia.name);
	       });
	       MenuBar mb = new MenuBar();
	       mb.getMenus().addAll(menu);
	      // mb.prefWidthProperty().bind(pane.widthProperty());
	      // mb.prefHeightProperty().bind(pane.heightProperty());
	       pane.getChildren().add(mb);
	       
	       
	       
	       
	      /*
	       * This shows all the audio files in src/Audio in the form of a ListView
	       * It calls ListofAudio to get and add all the names
	       * an action listener is added so that when you click on the specific sound
	       * the global variable "soundname" is changed to that sound
	       * soundname is used when sounds to profiles
	       */
	       ListofAudio = new ListView<String>();
	       ListofAudio.getItems().addAll(ListofAudio()); // addAll an arrayList ListofAudio
	       pane.getChildren().add(ListofAudio);
	       ListofAudio.setLayoutX(800);
	       ListofAudio.setLayoutY(300);
	       ListofAudio.setMaxSize(200, 175);
	       ListofAudio.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> { // when clicked 
	    	   soundname = newValue.toString(); // this is used for the profiles
	    	  
	       });
	      
	      /*
	       * root is where we add all the profiles. So just like how all the audio files
	       * are added to specified profiles. All the profiles must be added to a root in order
	       * to hold them. The "root" is set to setExpanded(true) so that the profiles show without
	       * having to expand root.
	       * If it was false then the profiles would not show unless manually expanded such as 
	       * when we expand the profiles to view the audio.
	       * 
	       * 
	       * If mans are confused about TreeView watch the javafx tutorial on youtube
	       * from a guy named thenewboston
	       */
	       root = new TreeItem<String>(); // This is used to create the profile and root and branches are added
	       root.setExpanded(true);
	       TItems = new ArrayList<>(); // creating profile   
	       TreeView <String> Tree = new TreeView<>(root); //put item in tree
	       Tree.setShowRoot(false);
	       Tree.getSelectionModel().selectedItemProperty().addListener((v,oldValue,NewValue) -> {   
	    	   if(NewValue != null) {
	    		   row = Tree.getRow(NewValue); // row is the position of the file name
	    		   profilename = NewValue.getValue(); // Gets the profile name of the clicked profile
	    	   }
	       });

	       //pane.getChildren().add(Tree);
	       Tree.prefHeightProperty().bind(pane.heightProperty());
	       Tree.prefWidthProperty().bind(pane.widthProperty());
	       Tree.setLayoutX(800);
	       Tree.setLayoutY(30);
	       Tree.setMaxSize(200, 200);
	       Tree.prefHeightProperty().bind(pane.heightProperty());
	       Tree.prefWidthProperty().bind(pane.widthProperty());
	       pane.getChildren().add(Tree);
		       
		    
	       //Remove Profile Button 
	       Button RemoveProfile = new Button("Remove Profile");
	       RemoveProfile.setLayoutX(1000);
	       RemoveProfile.setLayoutY(100);
	       pane.getChildren().add(RemoveProfile);
	       
	       // Add sound to the profiles
	       Button AddSound = new Button("Add Sound");
	       AddSound.setLayoutX(1000);
	       AddSound.setLayoutY(400);
	       
	       //AddSound.prefHeightProperty().bind(pane.heightProperty().multiply(0.5));
	       //AddSound.prefWidthProperty().bind(pane.widthProperty().multiply(0.5));
	       pane.getChildren().add(AddSound);
	       
	       
	       PN = new TextField("Enter Profile Name");
	       PN.setLayoutX(800);
	       PN.setLayoutY(230);
	       pane.getChildren().add(PN);
	       PN.setOnMouseClicked(e -> PN.clear()); // clears the textfield when mouse is clicked on set profile textfield
	       PN.setOnAction(e -> {ProfileAdder(PN.getText()); PN.clear();}); //Adds the Profile to the TreeView after pressing Enter

	       
	       SetProfile = new Button("Set Profile");
	       SetProfile.setLayoutX(1000);
	       SetProfile.setLayoutY(30);
	       pane.getChildren().add(SetProfile);
	       
	       Button Record = new Button("Record");
	       Record.setLayoutX(800);
	       Record.setLayoutY(520);
	       pane.getChildren().add(Record);

	       TextField text = new TextField("Enter Filename");
	       text.setLayoutX(875);
	       text.setLayoutY(570);
	       pane.getChildren().add(text);
	       text.setOnMouseClicked(e -> text.clear());
	       text.setOnAction(e -> filename = text.getText()); //whatever input is, it is stored in the variable so we can use it for serializer
	       
	       
	       /*
	        * The Recorder button calls the Sound class which allows us to record audio
	        * sound.temp = filename will allow them to save the audio to the desired audio name
	        * 
	        */
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
	       pane.getChildren().add(Start);
	       
	       // to stop recording
	       Button Stop = new Button("Stop");

	       Stop.setLayoutX(925);
	       Stop.setLayoutY(530);
	       Stop.setOnAction(e ->{ Sound sound = new Sound(); sound.stop();});
	       pane.getChildren().add(Stop);
	      
	       
	       
	       /*
	        * This allows users to enter how many buttons they want
	        * It parses the entered number and then calls bAdder 
	        * to add the buttons in
	        */
	       numofB = new TextField("Enter number of buttons");
	       numofB.setLayoutX(0);
	       numofB.setLayoutY(475);
	       numofB.setMinSize(200, 50);
	       pane.getChildren().add(numofB);
	       numofB.setOnMouseClicked(e -> numofB.clear());
	       numofB.setOnAction(e -> {numofbuttons = Integer.parseInt(numofB.getText()); bAdder(numofbuttons);});  //?
	       
	       /*
	        * LaunchSim serializes all the information needed and launches the simulator
	        * using the information
	        * TalkBoxConfiguration is where all the information is stores
	        * Serializer.Save saves the information and serializes into TalkBoxData.tbc
	        * 
	        * It then calls Gui g = new Gui() which is our simulator class
	        * It opens the the simulator and closes the configurator 
	        */
	       Button LaunchSim = new Button("Launch");
	       LaunchSim.setLayoutX(450);
	       LaunchSim.setLayoutY(425);
	       LaunchSim.setMinSize(150, 150);
	       pane.getChildren().add(LaunchSim);
	       LaunchSim.setOnAction(e -> {
	    	   TalkBoxConfiguration tbc = new TalkBoxConfiguration();
	    	   try {
	    	   tbc.NumOfAudioButtons = numofbuttons; //Numbder of Buttons
	    	   tbc.NumOfAudioSets = TItems.get(row).getChildren().size(); //number of audio sets in each profile
	    	   tbc.NumOfButtons = numofbuttons + 7; // all the buttons plus the buttons to set, add, etc
	    	   tbc.PathToAudioFiles = null; // cant serialize Path object
	    	   tbc.AudioName = audioFiles(); // 2-D array of audio
	    	   tbc.path = src; // path to audio
	    	   tbc.Profiles = profiles(); //array of profile anames
				Serializer.Save(tbc, "bin/TalkBoxData/"); //saves in serializer
			} catch (Exception e1) {
			e1.printStackTrace();
			}	     
	    	   Gui g = new Gui(); // Calls Simulator Class

	    	  try {
				g.start(new Stage()); //Starts simulator
				primaryStage.close();	//Close configurator
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
	       pane.getChildren().add(labelProfile);
	       
	       Label labelAudio = new Label("Audio");
	       labelAudio.setLabelFor(pane);
	       labelAudio.setLayoutX(860);
	       labelAudio.setLayoutY(270);
	       labelAudio.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
	       pane.getChildren().add(labelAudio);
	       
	       Label label = new Label("TalkBox Preview");
	       label.setLabelFor(pane);
	       label.setLayoutX(325);
	       label.setLayoutY(10);
	       label.setStyle("-fx-font-family: TRON; -fx-font-size: 25;");
	       pane.getChildren().add(label);
	       
	       Label labelRecord = new Label("Record Audio");
	       labelRecord.setLabelFor(pane);
	       labelRecord.setLayoutX(825);
	       labelRecord.setLayoutY(490);
	       labelRecord.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
	       pane.getChildren().add(labelRecord);

	       
	       SetProfile.setOnAction(e -> swapAudio()); //Set Profile by calling swapAudio
	       AddSound.setOnAction(e ->SoundAdder(soundname)); //Adds sound by calling SoundAdder
	       RemoveProfile.setOnAction(e -> ProfileRemover(row));//Removes Profile by calling ProfileRemover
	  }
	 
	  /*
	   * This method creates a TreeItem using a string and TreeItem parameter
	   * The String represents the name of either the profile or audio file
	   * Parent represents the parent node which is root for the profiles
	   * and profiles for the audio files
	   * 
	   */
	  

	

	  public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		  
		   double width = (double) arg2;
		   this.sc.setPrefHeight(width/2);
		  
		  
	  }
	  
	public TreeItem<String> branch(String title, TreeItem<String> parent){
		  TreeItem<String> item = new TreeItem<>(title);
		  item.setExpanded(false);
		  parent.getChildren().add(item);
		  return item; }

	/*
	 * Finds all the wav files in any given directory
	 * This is set to src/Audio as all our audio files are kept there
	 * It returns all the files in an array alphabetically 
	 */
	  public File[] finder(String dirName) {
				File directoryPath = new File(dirName);
				File[] files=directoryPath.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {     //?
						return name.endsWith(".wav");}});
				return files;}
	 

	  
	  /*
	   * This method adds the buttons to the scrollPane
	   * sp.getChildren.clear() allows us to refresh the numbers of buttons
	   * First for loop Adds button into an arraylist called BList
	   * Second for loop makes it so that max of 10 buttons per row
	   */
	  public void bAdder(int n) {
	      sp.getChildren().clear();
			   for(int i = ctr2; i < n; i++) {
		    	   String buttonname = String.format("Sound %d", i+1);
		    	   BList.add(new Button(buttonname));
		    	   BList.get(i).setPadding(new Insets(10,10,10,10)); // tried adding padding to the buttons
		       }
			  this.ctr2 = 0;
			 
			   int count = n;
			 for(int j = 0; j <= Math.ceil(n/10);j++) {
				 if(count >= 10) {
					 for(int k = 0; k < 10; k++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 BList.get(ctr2).setPadding(new Insets(13,10,15,17));
						 sp.setHgap(5.5);
						 sp.setVgap(5.5);
						 sp.add(BList.get(ctr2), k, j);
						 ctr2++;
						 count--;}}
				 else {
					 for(int h = 0; h < count; h++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 BList.get(ctr2).setPadding(new Insets(13,10,15,17));
						 sp.setHgap(5.5);
						 sp.setVgap(5.5);
						 sp.add(BList.get(ctr2), h, j);
						 ctr2++; }}}}

	  
	  /*
	   * This methods adds profiles by adding to the arrayList TItems
	   * which holds tree items
	   * It calls branch to make the profile TreeItem 
	   */
	  public void ProfileAdder(String title) {
		  TItems.add(branch(title,root)); }
	  
	  /*
	   * This methods removes profiles from the TreeView
	   */
	  public void ProfileRemover(int r) {
		  root.getChildren().remove(r);}

	  /*
	   * This method set the audio files to the associated buttons by numerical order
	   * This does this by getting how many audio files are in the selected profile (int size)
	   * The first for loop adds all the names of the audio to an ArrayList within the method called al
	   * The second for loop sets the audio to each button adds an action listener to play sound
	   */
	  public void swapAudio() {
		  int size = root.getChildren().get(row).getChildren().size();
		  ArrayList<String> al = new ArrayList<String>();
		  for(int k = 0; k < size; k++) {
			  al.add(root.getChildren().get(row).getChildren().get(k).getValue());}
		  for(int i = 0; i < size; i++) {
			  String name = al.get(i);
			  BList.get(i).setText(name);
			  BList.get(i).setOnAction(e -> handle(src + name + ".wav"));}}
	  
	  /*
	   * This method adds the sounds to the profiles by calling branch
	   */
	  public void SoundAdder(String s) {
		  branch(s,root.getChildren().get(row));}
	  
	  /*
	   * This method play audio files and check if it exists
	   * this.collide checks if a already file is already playing and will stop it
	   * when another audio button is clicked allowing the next sound to play
	   * 
	   */
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
	  
	  /*
	   * This method iterates through the array from the finder method
	   * Since the files are stored with the path and file extension
	   * The String builder removes all the characters that are not the name of the audio file
	   * 
	   * ie) src/Audio/Hello.wav 
	   * It will removes all characters and will be stored in the ArrayList as Hello
	   * 
	   */
	  public ArrayList<String> ListofAudio() {
		  ArrayList<String> al = new ArrayList<String>();
		  for(File temp : finder(src)){
			  StringBuilder sb = new StringBuilder();
			  sb.append(temp.getName());
			  sb.delete(sb.length()-4, sb.length()); // removes the .wav string
			  al.add(sb.toString());}
		  return al;
		  }
	  
	  /*
	   * This is for the serialization
	   * It takes all the audio files of each profiles and stores then in a 2-D array
	   * Rows represents the profiles
	   * Columns represents the Audio files
	   * 
	   * ie)Row 0 store all the audio files names from the first profile
	   */
	  public String[][] audioFiles(){
		  String[][] temp = new String[root.getChildren().size()][BList.size()];
		  for(int i = 0; i < root.getChildren().size(); i++) {
		  int numofAudio = TItems.get(i).getChildren().size();
			for(int j = 0; j < numofAudio; j++) {
				temp[i][j] = TItems.get(i).getChildren().get(j).toString();}}
		  for(String[] row : temp) {
			  System.out.println(Arrays.toString(row));}
		return temp;}
	  
	  /*
	   * This is for serialization
	   * It takes all the profiles and stores them in an array
	   */
	  public String[] profiles() {
		  String[] temp = new String[root.getChildren().size()];
		  for(int i = 0; i < temp.length; i++) {
			  temp[i] = root.getChildren().get(i).toString();
		  }
		  System.out.println(Arrays.toString(temp));
		  return temp;
	  }
	 
	  /*
	   * This allows the gui to refresh without having to restart the whole application
	   * after importing a audio file. 
	   * It also removes all characters that are not the name of the audio file
	   */
	  public void refresh(String s) {
		  StringBuilder sb = new StringBuilder();
		  sb.append(s);
		  sb.delete(sb.length()-4, sb.length());
		  ListofAudio.getItems().add(sb.toString());
	  }
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args); 
		System.out.println(row);
		System.out.println(profilename);
		System.out.println(TItems.get(row).getChildren().size());

		
	}

}
