package TalkBoxConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import TalkBoxSim.Gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiConfig extends Application {

	boolean collide = false;

	private Clip clip;

	// Strings

	public static String profilename = "";
	private String soundname = "";
	private String filename;
	public String src = "src/Audio/";
	// Profile & Audio
	private static TreeItem<String> root;
	public TreeView<String> Tree;
	private int time;

	@SuppressWarnings("rawtypes")

	private static ArrayList<TreeItem> TItems;
	public ButtonGui b;
	//public ArrayList<Button> BList = new ArrayList<Button>();
	private ListView<String> ListofAudio;
	private TextField wrongInput;

	// Items to Pane

	private Button SetProfile;
	private TextField PN;
	public TextField numofB;
	private TextField switch1;
	private TextField switch2;
	
	//private int count = 0;
	
	// Panes
	public GridPane sp;
	public ScrollPane sc;

	// Ints

	int ctr = 480;
	public static int row = 0;
	int increment = 0;
	int increment2 = 0;
	private int ctr2;
	private int numofAudioAdded;
	private int numofButtons;
	private int position1;
	private int position2;
	//private int counter;

	// Serialization

	public int numofbuttons;
	public Path pathtofile = null;
	
	
	
	ProfileConfig profile;
	
	
	public void start(Stage primaryStage) throws IOException {

		// Create a scene and place a button in the scene

		primaryStage.setTitle("TalkBoxConfig"); // Set title of talkbo
		HBox temp = new HBox();
		Scene scene = new Scene(temp, 1200, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TalkBox");
		primaryStage.show();

		// This line applys all the css code from application.css

		scene.getStylesheets().add("application.css");
		sp = new GridPane(); // matrix
		sc = new ScrollPane(sp); // launch the gui, the white space (scroll)
		sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // size of the scroll bar
		sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		sp.setMinSize(800, 300);
		sc.setMinSize(800, 300);

		// ----------------------------------------------------------------------------------------------------------------------//

		VBox msbal = new VBox(25);

		/*
		 * 
		 * The menu objects are to allows users to import their own audio files
		 *  MenuBar mb creates the menubar to hold menus
		 *	Menu creates a menu
		 * Menuitem creates an item within a menu
		 * ie) In eclipses the menu bare has many options such as File and Edit
		 * 
		 * When you click File many menuitems come up such as import or new.
		 *
		 * 
		 * 
		 * For our program we have a menu called File which has an item called Import
		 * Audio
		 * 
		 * When Import Audio is pressed it calls the class ImportAudio
		 * 
		 * where an audio file can be chosen from the users personal directories
		 * 
		 * and is copied over to src/Audio
		 * 
		 * refresh is then called to update the gui
		 * 
		 */

		Menu menu = new Menu();
		menu.setText("File");
		MenuItem mi = new MenuItem("Import Audio");
		MenuItem li = new MenuItem("Load Profiles");
		mi.setStyle("-fx-text-fill:black");
		menu.getItems().addAll(mi,li);
		li.setOnAction(e -> {
			try {
				Load();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		mi.setOnAction(e -> {

			ImportAudio ia = new ImportAudio();
			ia.open();
			refresh(ia.name);

		});

		MenuBar mb = new MenuBar();
		mb.setMaxWidth(65);
		mb.getMenus().addAll(menu);
		Label label = new Label("TalkBox Preview");
		label.setStyle("-fx-font-family: TRON; -fx-font-size: 25;");
		sp = new GridPane(); // matrix
		sc = new ScrollPane(sp); // launch the gui, the white space (scroll)
		sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // size of the scroll bar
		sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		sp.setMinSize(800, 300);
		sc.setMinSize(800, 300);
		HBox ButtonsandLaunch = new HBox(300);
		VBox ButtonsandError = new VBox(10);

		/*
		 * 
		 * This allows users to enter how many buttons they want
		 *  It parses the entered number and then calls bAdder
		 *	to add the buttons in
		 * 
		 */

		/*
		 * 
		 * This allows users to enter how many buttons they want
		 *	It parses the entered number and then calls bAdder
		 *	to add the buttons in
		 * 
		 */

		numofB = new TextField("Enter number of buttons");
		numofB.setMinSize(200, 50);
		wrongInput = new TextField();
		wrongInput.setLayoutX(0);
		wrongInput.setLayoutY(550);
		numofB.setOnMouseClicked(e -> numofB.clear());
		numofB.setOnAction(e -> {

			try {
				numofbuttons = Integer.parseInt(numofB.getText());

				wrongInput.clear();
			}

			catch (NumberFormatException e1) {

				wrongInput.setText("Wrong Input");

			}
			b = new ButtonGui();
			b.buttonAdder(numofbuttons,sp);
			
			
		}); 


		ButtonsandError.getChildren().addAll(numofB, wrongInput);

		/*
		 * 
		 * LaunchSim serializes all the information needed and launches the simulator
		 *	using the information
		 * 	TalkBoxConfiguration is where all the information is stores
		 * 	Serializer.Save saves the information and serializes into TalkBoxData.tbc
		 * 	It then calls Gui g = new Gui() which is our simulator class
		 * 	It opens the the simulator and closes the configurator
		 * 
		 */

		Button LaunchSim = new Button("Launch");
		LaunchSim.setMinSize(100, 100);

		// pane.getchildren().add(LaunchSim);

		LaunchSim.setOnAction(e -> {

			TalkBoxConfiguration tbc = new TalkBoxConfiguration();

			try {

				tbc.NumOfAudioButtons = numofbuttons; // Numbder of Buttons
				// tbc.NumOfAudioSets = TItems.get(row).getChildren().size(); //number of audio
				// sets in each profile
				tbc.NumOfAudioSets = numofAudioFiles(); // Total number of audio files
				tbc.NumOfButtons = numofbuttons + 7; // all the buttons plus the buttons to set, add, etc

				tbc.PathToAudioFiles = null; // cant serialize Path object
				tbc.AudioName = audioFiles(); // 2-D array of audio
				tbc.path = src; // path to audio
				tbc.Profiles = profiles(); // array of profile anames
				Serializer.Save(tbc, "bin/TalkBoxData/"); // saves in serializer

			} catch (Exception e1) {

				e1.printStackTrace();

			}

			Gui g = new Gui(); // Calls Simulator Class

			try {

				g.start(new Stage()); // Starts simulator
				primaryStage.close(); // Close configurator

			} catch (Exception e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}

		});

		Button Reorder = new Button("Switch");
		Reorder.setMinSize(60,60);
		Reorder.setOnAction(e -> {
			
		  if(time==0) {
			b.getFirstSound(position1);
			b.getSecondSound(position2);
			time++;
		  }
		  else {
			  
			  b.Switch(sp, position1,position2);
			  this.switchAudio(position1, position2);
			  
		  }
			
		});
		
		switch1 = new TextField("Enter the 1st Sound Number");
		switch1.setMinSize(20, 20);
		switch1.setOnMouseClicked(e -> switch1.clear());
		
		switch1.setOnAction(e -> {

		
				position1 = Integer.parseInt(switch1.getText());
				System.out.println(position1);
		});

		
		switch2 = new TextField("Enter the 2nd Sound Number");
		switch2.setMinSize(20, 20);
		switch2.setOnMouseClicked(e -> switch2.clear());
		
		switch2.setOnAction(e -> {

		
				position2 = Integer.parseInt(switch2.getText());
				System.out.println(position2);
		});

		
		
		ButtonsandLaunch.getChildren().addAll(ButtonsandError, LaunchSim, Reorder);

		// ----------------------------------------------------------------------------------------------------------------------//

		HBox ListandAddAudio = new HBox();

		/*
		 * 
		 * This shows all the audio files in src/Audio in the form of a ListView
		 * 
		 * It calls ListofAudio to get and add all the names
		 * 
		 * an action listener is added so that when you click on the specific sound
		 * 
		 * the global variable "soundname" is changed to that sound
		 * 
		 * soundname is used when sounds to profiles
		 * 
		 */

		ListofAudio = new ListView<String>();
		ListofAudio.getItems().addAll(ListofAudio());

		// //pane.getchildren().add(ListofAudio);
		ListofAudio.setMinSize(200, 175);
		ListofAudio.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			soundname = newValue.toString();

		});

		Button AddSound = new Button("Add Sound");
		ListandAddAudio.getChildren().addAll(ListofAudio, AddSound);

		// ----------------------------------------------------------------------------------------------------------------------//

		HBox TreeandButton = new HBox();
		VBox RemoveandSet = new VBox();

		/*
		 * 
		 * root is where we add all the profiles. So just like how all the audio files
		 * 
		 * are added to specified profiles. All the profiles must be added to a root in
		 * order
		 * 
		 * to hold them. The "root" is set to setExpanded(true) so that the profiles
		 * show without
		 * 
		 * having to expand root.
		 * 
		 * If it was false then the profiles would not show unless manually expanded
		 * such as
		 * 
		 * when we expand the profiles to view the audio.
		 * 
		 */


		
		
		this.profile = new ProfileConfig();
		this.profile.setRoot();
		this.profile.setProfileParameters();
		this.profile.setProfileSize(200, 200);
		
		
		
		

		//Tree.setMinSize(200, 200);
		Button RemoveProfile = new Button("Remove Profile");
		SetProfile = new Button("Set Profile");
		RemoveandSet.setSpacing(50);
		RemoveandSet.getChildren().addAll(SetProfile, RemoveProfile,switch1,switch2);
		TreeandButton.getChildren().addAll(this.profile.getTree(), RemoveandSet);

		// ----------------------------------------------------------------------------------------------------------------------//

		HBox RecordingArea = new HBox();
		VBox StopandFile = new VBox();
		Button Record = new Button("Record");
		TextField text = new TextField("Enter Filename");
		text.setOnMouseClicked(e -> text.clear());
		text.setOnAction(e -> filename = text.getText()); // whatever input is, it is stored in the variable so we can
															// use it for serializer

		/*
		 * 
		 * The Recorder button calls the Sound class which allows us to record audio
		 * 
		 * sound.temp = filename will allow them to save the audio to the desired audio
		 * name
		 * 
		 * 
		 * 
		 */

		Record.setMinSize(75, 75);
		Sound sound = new Sound();
		Record.setOnAction(e -> {
			
			try {

				sound.soundFormat();
				sound.start(this.filename);

			} catch (InterruptedException | LineUnavailableException e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}
		});

		// to start recording

		// to stop recording

		Button Stop = new Button("Stop");
		Stop.setOnAction(e -> {
			sound.stop();
			refresh(text.getText());
			});

		StopandFile.getChildren().addAll(Stop, text);
		RecordingArea.getChildren().addAll(Record, StopandFile);

		// ----------------------------------------------------------------------------------------------------------------------//

		VBox ProfileandAudio = new VBox();
		PN = new TextField("Enter Profile Name");
		PN.setMaxWidth(247);
		PN.setOnMouseClicked(e -> PN.clear()); // clears the textfield when mouse is clicked on set profile textfield
		PN.setOnAction(e -> {
			this.profile.setProfileTitles(PN.getText());
			PN.clear();
		}); // Adds the Profile to the TreeView after pressing Enter

		SetProfile.setOnAction(e -> {
			try {
				configureAudioToButton();
			} catch (Exception ie) {
				ie.printStackTrace();
				wrongInput.setText("Fix profiles");
			}
		}); // Set Profile by calling configureAudioToButton

		AddSound.setOnAction(e -> this.profile.addSoundToProfile(soundname)); // Adds sound by calling SoundAdder
		RemoveProfile.setOnAction(e -> this.profile.removeProfileTitles(this.profile.getRow()));// Removes Profile by calling ProfileRemover
		Label labelProfile = new Label("		Profiles");
		labelProfile.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
		Label labelAudio = new Label("		Audio");
		labelAudio.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
		Label labelRecord = new Label("Record Audio");
		labelRecord.setStyle("-fx-font-family: TRON; -fx-font-size: 20;");
		VBox labelandsc = new VBox(5);
		labelandsc.getChildren().addAll(label, sc);
		VBox lsbl = new VBox(100);
		lsbl.getChildren().addAll(labelandsc, ButtonsandLaunch);
		ProfileandAudio.getChildren().addAll(labelProfile, TreeandButton, PN, labelAudio, ListandAddAudio, labelRecord,
				RecordingArea);

		// ----------------------------------------------------------------------------------------------------------------------//

		msbal.getChildren().addAll(mb, lsbl);
		temp.getChildren().addAll(msbal, ProfileandAudio);
		VBox.setVgrow(temp, Priority.ALWAYS);
		HBox.setHgrow(msbal, Priority.ALWAYS);
		VBox.setVgrow(lsbl, Priority.ALWAYS);
		VBox.setVgrow(labelandsc, Priority.ALWAYS);
		VBox.setVgrow(sc, Priority.ALWAYS);
		VBox.setVgrow(sp, Priority.ALWAYS);

	}










	/*
	 * 
	 * This method set the audio files to the associated buttons by numerical order
	 * 
	 * This does this by getting how many audio files are in the selected profile
	 * (int size)
	 * 
	 * The first for loop adds all the names of the audio to an ArrayList within the
	 * method called al
	 * 
	 * The second for loop sets the audio to each button adds an action listener to
	 * play sound
	 * 
	 */

	public void configureAudioToButton() {
		int size = this.profile.getRoot().getChildren().get(this.profile.getRow()).getChildren().size();
		ArrayList<String> al = new ArrayList<String>();
			for (int k = 0; k < size; k++) {
			al.add(this.profile.getRoot().getChildren().get(this.profile.getRow()).getChildren().get(k).getValue());
		
		}
		int n = this.numofbuttons;
	
		
		for (int i = 0; i < size; i++) {
			String name = al.get(i);
			b.getArray().get(i).setText(name);
	        AudioHandler<ActionEvent> handler = new AudioHandler<ActionEvent>(src + name + ".wav");
	        b.getArray().get(i).setOnAction(handler);
		}
	 		}
	
	
     public void switchAudio(int switch1, int switch2) {
    	 
    	int size = this.profile.getRoot().getChildren().get(this.profile.getRow()).getChildren().size();
    	ArrayList<String> profilenames = new ArrayList<String>();
    	for (int k = 0; k < size; k++) {
    			profilenames.add(this.profile.getRoot().getChildren().get(this.profile.getRow()).getChildren().get(k).getValue());
    		} 
    	 
    	 
    	 
    	 
    	 String name1 = profilenames.get(switch2-1);
    	 AudioHandler<ActionEvent> handler1 = new AudioHandler<ActionEvent>(src + name1 + ".wav");
    	 b.getArray().get(switch1-1).setOnAction(handler1);
    	 String name2 = profilenames.get(switch1-1);
     	 AudioHandler<ActionEvent> handler2 = new AudioHandler<ActionEvent>(src + name2 + ".wav");
     	 b.getArray().get(switch2-1).setOnAction(handler2);
    
    	 
     }
		


	public void Load() throws Exception {
		Load l = new Load();
		l.Loader(sp,this.profile.getRoot());
		for(int i = 0; i < l.size; i++) {
			TItems.add(l.getTreeItem(i, root));
		}		
	}
	
	

	/*
	 * 
	 * This method play audio files and check if it exists
	 * 
	 * this.collide checks if a already file is already playing and will stop it
	 * 
	 * when another audio button is clicked allowing the next sound to play
	 * 
	 * 
	 * 
	 */
/*

	
*/
	/*
	 * 
	 * This method iterates through the array from the finder method
	 * 
	 * Since the files are stored with the path and file extension
	 * 
	 * The String builder removes all the characters that are not the name of the
	 * audio file
	 * 
	 * 
	 * 
	 * ie) src/Audio/Hello.wav
	 * 
	 * It will removes all characters and will be stored in the ArrayList as Hello
	 * 
	 * 
	 * 
	 */

	public ArrayList<String> ListofAudio() throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		FileInputOutput file = new FileInputOutput();
		for (File temp : file.finder(src)) {
			StringBuilder sb = new StringBuilder();
			sb.append(temp.getName());
			sb.delete(sb.length() - 4, sb.length()); // removes the .wav string
			al.add(sb.toString());
		}
		return al;

	}

	/*
	 * 
	 * This is for the serialization It takes all the audio files of each profiles
	 * and stores then in a 2-D array Rows represents the profiles Columns
	 * represents the Audio files
	 * 
	 * ie)Row 0 store all the audio files names from the first profile
	 * 
	 */
	public String[][] audioFiles() {
		String[][] temp = new String[this.profile.getRoot().getChildren().size()][b.getArray().size()];
		for (int i = 0; i < this.profile.getRoot().getChildren().size(); i++) {
			int numofAudio = this.profile.getProfileList().get(i).getChildren().size();
			System.out.println(this.profile.getProfileList().get(i));
			System.out.println(this.profile.getProfileList().get(i).getChildren());
			for (int j = 0; j < numofAudio; j++) {
				temp[i][j] = this.profile.getProfileList().get(i).getChildren().get(j).toString();
			}
		}
		// for(String[] row : temp) {
		// System.out.println(Arrays.toString(row));}
		return temp;
	}

	/*
	 * 
	 * This is for serialization
	 * 
	 * It takes all the profiles and stores them in an array
	 * 
	 */

	public String[] profiles() {

		String[] temp = new String[this.profile.getRoot().getChildren().size()];

		for (int i = 0; i < temp.length; i++) {

				temp[i] = this.profile.getRoot().getChildren().get(i).toString();

		}
		// System.out.println(Arrays.toString(temp));
		return temp;

	}
	


	/*
	 * 
	 * This allows the gui to refresh without having to restart the whole
	 * application
	 * 
	 * after importing a audio file.
	 * 
	 * It also removes all characters that are not the name of the audio file
	 * 
	 */

	public void refresh(String s) {
		if(s.contains(".wav")) {
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		sb.delete(sb.length() - 4, sb.length());
		ListofAudio.getItems().add(sb.toString());}
		else {
			ListofAudio.getItems().add(s);
		}

	}

	public int numofAudioFiles() {
		int ctr = 0;
		String[][] temp = audioFiles();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j] != null) {
					ctr++;
				}
			}
		}
		return ctr;
	}
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Application.launch(args);
		
	
		
	}
}