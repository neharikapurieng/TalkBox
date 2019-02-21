package TalkBoxSim;

import java.util.ArrayList;
import java.util.Arrays;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.application.Application;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Profiles {
	
	private TalkBoxConfiguration tbc;
	private int row;
	private String profilename;
	
	
	public Profiles() {
		//TalkBoxConfiguration tbc;
		this.tbc=null;
	}

	//TalkBoxConfiguration tbc;
	public void profiles() throws Exception {
		tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
		
		/*  public String[][] audioFiles(){
			  String[][] temp = new String[root.getChildren().size()][BList.size()];
			  for(int i = 0; i < root.getChildren().size(); i++) {
			  int numofAudio = TItems.get(i).getChildren().size();
				for(int j = 0; j < numofAudio; j++) {
					temp[i][j] = TItems.get(i).getChildren().get(j).toString();}}
			  for(String[] row : temp) {
				  System.out.println(Arrays.toString(row));}
			return temp;}
		  */
		  
		 String[][] temp = tbc.getAudioFileNames();
		 
		 for(int i=0; i<=temp.length-1; i++) {
			 
			  int profileLength = temp[i].length; 
			 
			 for(int j=0; j<=profileLength-1; j++) {
				 
				 System.out.println(temp[i][j] + "Hello");
				 
			 }
			 
			 
		 }
	
	
		
		
		
		
		
	}
	
	public TreeView LaunchProfileDisplay() {
		
		
		 TreeItem<String> root = new TreeItem<String>(); // This is used to create the profile and root and branches are added
	       root.setExpanded(true);
	       ArrayList<TreeItem> TItems = new ArrayList<>(); // creating profile   
	       TreeView <String> Tree = new TreeView<>(root); //put item in tree
	       Tree.setShowRoot(false);
	       Tree.getSelectionModel().selectedItemProperty().addListener((v,oldValue,NewValue) -> {   
	    	   if(NewValue != null) {
	    		   row = Tree.getRow(NewValue); // row is the position of the file name
	    		   profilename = NewValue.getValue(); // Gets the profile name of the clicked profile
	    	   }
	       });
	       Tree.setMinSize(300, 300);
	       Tree.setMaxSize(300, 300);
	       Tree.setLayoutX(900);
	       Tree.setLayoutY(100);
	       
	       
	       String[] profile = tbc.Profiles;
	       String[][] audioname = tbc.AudioName;
	       TreeItem<String> parent = new TreeItem<String>();
	       for(int i=0; i<=profile.length-1; i++) {
	    	   
	    	   //root.getChildren().add(profile[i]);
	    	   
	       }
	       for(int i=0; i<=profile.length-1; i++) {

			int column = audioname[i].length;
			String profilename = profile[i];
			this.SetProfile(profilename, root);

			for (int j = 0; j <= column; j++) {

				//this.SetProfile(audioname[i][j], profilename);

			}

		}

		return Tree;

	}

	public void SetProfile(String title, TreeItem<String> parent) {
	
			  TreeItem<String> item = new TreeItem<>(title);
			  item.setExpanded(false);
			  parent.getChildren().add(item);
			
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		
	    Profiles profile = new Profiles();
	    profile.profiles();

		
	}

}
