package TalkBoxConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import TalkBoxSim.Gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;



public class GuiConfig2 extends Application {
	

	boolean collide = false;
	private Clip clip;
	public String profilename = "";
	private String soundname = "";
	private String filename;
	public String src = "src/Audio/";
	private TreeItem<String> root;
	public TreeView <String> Tree;
	@SuppressWarnings("rawtypes")
	private ArrayList<TreeItem> TItems;
	public ArrayList<Button> BList;
	private ListView <String> ListofAudio;
	private Button SetProfile;
	private TextField PN;
	public Pane pane;
	private GridPane sp;
	private Pane Back;
	public ScrollPane sc;
	int ctr = 480;
	int row = 0;
	int increment = 0;
	int increment2 = 0;
	private int  repeat=0;
	private int ctr2;
	public int numofbuttons;
	public Path pathtofile = null;
	private Menu menu;
	private MenuItem mi;
	private MenuBar mb;

	
	
	public GuiConfig2() {
		SetProfile = new Button("Set Profile");
		BList = new ArrayList<>();
	
	}
	
	public void setPane() {
		
		this.pane = new Pane();
		this.Back= new Pane();
	}
	
	
	public void setGridPane() {
		
		this.sp= new GridPane();                                   // Created a gridpane
		this.sp.setMinSize(800, 300);                            
		this.sp.setLayoutY(80);
		}
	
	public GridPane getGridPane() {
		
		return this.sp;                                        // returns gridpane
	}
	
	public void setScrollPane() {
		
		 this.sc = new ScrollPane(sp);                                        // Sets the scrollpane
		 this.sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	     this.sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	     this.sc.setLayoutY(80);
	     this.sc.setMinSize(800, 300);
	     this.sc.setMaxSize(800, 300);
	}
	
	public ScrollPane getScrollPane() {
		
		 
		return this.sc;                                                          // returns scrollpane
	} 
	
	
	public void setMenu() {
		
		 this.menu = new Menu();
	     this.menu.setText("File");
	     this.mi = new MenuItem("Import Audio");
	     mi.setStyle("-fx-text-fill:black");
	     this.menu.getItems().addAll(mi);
		
		
	}
	
	
	public void ClickMenuButton() {
		
		  this.mi.setOnAction(e -> {
	    	   ImportAudio ia = new ImportAudio();  
	    	   ia.open();
	    	   refresh(ia.name);
	       });
	       
	}
	
	public void setMenuBar() {
		
		this.mb = new MenuBar();
		this.mb.getMenus().addAll(this.menu);
		this.pane.getChildren().add(this.mb);
		
	}
	
	
	public void setLisOfAudio() {
		
	       this.ListofAudio = new ListView<String>();
	       this.ListofAudio.getItems().addAll(ListofAudio());
	       this.pane.getChildren().add(this.ListofAudio);
	       this.ListofAudio.setLayoutX(800);
	       this.ListofAudio.setLayoutY(300);
	       this.ListofAudio.setMaxSize(200, 175);
	       this.ListofAudio.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> {
	       this.soundname = newValue.toString();
	    	  
	       });
		
	}
	
	public void setProfile() {
		
		  this.root = new TreeItem<String>(); // This is used to create the profile and root and branches are added
	      this.root.setExpanded(true);
	       
	       this.TItems = new ArrayList<>();
	          
	       TreeView <String> Tree = new TreeView<>(this.root);
	       Tree.setShowRoot(false);
	       Tree.getSelectionModel().selectedItemProperty().addListener((v,oldValue,NewValue) -> {   
	    	   if(NewValue != null) {
	    		   this.row = Tree.getRow(NewValue); 
	    		   this.profilename = NewValue.getValue();
	    	   }
	       });
	       
	       this.pane.getChildren().add(Tree);
	       Tree.setLayoutX(800);
	       Tree.setLayoutY(30);
	       Tree.setMaxSize(200, 200);
		
	}
	
	public void addRemoveButton() {
		
		   Button RemoveProfile = new Button("Remove Profile");
	       RemoveProfile.setLayoutX(1000);
	       RemoveProfile.setLayoutY(100);
	       this.Back.getChildren().add(RemoveProfile);
		
		
	}
	
	
	public void addSoundButton() {
		
		   Button AddSound = new Button("Add Sound");
	       AddSound.setLayoutX(1000);
	       AddSound.setLayoutY(400);
	      this.Back.getChildren().add(AddSound);
	       
	}
	
	public void addProfileText() {
		
		   this.PN = new TextField("Enter Profile Name");                    
	       this.PN.setLayoutX(800);
	       this.PN.setLayoutY(230);
	       this.Back.getChildren().add(PN);
		
	}
	
	public void clickProfileText() {
		
		this.PN.setOnMouseClicked(e -> PN.clear()); // clears the textfield when mouse is clicked on set profile textfield
	    this.PN.setOnAction(e -> {ProfileAdder(PN.getText()); PN.clear();});
		
	}
	
	public void addSetProfileButton() {
		
		 this.SetProfile.setLayoutX(1000);
	     this.SetProfile.setLayoutY(30);
	     this.Back.getChildren().add(SetProfile);
		
	}
	
	
	
	
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
	       
	       sp = new GridPane();
	       sc = new ScrollPane(sp);
	       sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	       sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		   sp.setMinSize(800, 300);
		   sp.setLayoutY(80);
		   sc.setLayoutY(80);
	       sc.setMinSize(800, 300);
	       sc.setMaxSize(800, 300);
	       pane.getChildren().addAll(Back,sc);
	       
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
	       
	       GuiConfig2 gui = new GuiConfig2();
	       gui.ClickMenuButton();

	       MenuBar mb = new MenuBar();
	       mb.getMenus().addAll(menu);
	       pane.getChildren().add(mb);
	       
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
	       Tree.setLayoutY(30);
	       Tree.setMaxSize(200, 200);
	       
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
	       PN.setOnMouseClicked(e -> PN.clear()); // clears the textfield when mouse is clicked on set profile textfield
	       PN.setOnAction(e -> {ProfileAdder(PN.getText()); PN.clear();});

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
	       text.setOnAction(e -> filename = text.getText());
	       
	       Record.setMinSize(75, 75);
	       Record.setOnAction(e ->{ Sound sound = new Sound(); try {
	    	sound.soundFormat();
	    	sound.temp = filename;
			sound.start(filename);
		} catch (InterruptedException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} });
	       
	       Button Start = new Button("Start");
	       Start.setLayoutX(880);
	       Start.setLayoutY(530);
	       Back.getChildren().add(Start);
	       
	       Button Stop = new Button("Stop");

	       Stop.setLayoutX(925);
	       Stop.setLayoutY(530);
	       Stop.setOnAction(e ->{ Sound sound = new Sound(); sound.stop();});
	       
	       Back.getChildren().add(Stop);
	      
	       TextField numofB = new TextField("Enter number of buttons");
	       numofB.setLayoutX(0);
	       numofB.setLayoutY(475);
	       numofB.setMinSize(200, 50);
	       Back.getChildren().add(numofB);
	       numofB.setOnMouseClicked(e -> numofB.clear());
	       numofB.setOnAction(e -> {numofbuttons = Integer.parseInt(numofB.getText()); bAdder();
	       this.repeat++;});  //?
	       
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
				Serializer.Save(tbc, "bin/TalkBoxData/");
			} catch (Exception e1) {
			e1.printStackTrace();
			}

	       });
	       
	       Button LaunchSim1 = new Button("Launch");
	       LaunchSim1.setLayoutX(600);
	       LaunchSim1.setLayoutY(450);
	       LaunchSim1.setMinSize(100, 100);
	       Back.getChildren().add(LaunchSim1);
	       LaunchSim1.setOnAction(e -> {
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
	 

	public TreeItem<String> branch(String title, TreeItem<String> parent){
		  TreeItem<String> item = new TreeItem<>(title);
		  item.setExpanded(false);
		  parent.getChildren().add(item);
		  return item; }

	  
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
