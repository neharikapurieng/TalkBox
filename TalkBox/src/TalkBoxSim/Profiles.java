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
	private TreeItem<String> root;
	
	
	public Profiles() throws Exception {
		//TalkBoxConfiguration tbc;
		this.tbc=(TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
	}

	//TalkBoxConfiguration tbc;
	
	/*
	 * This method basically launches a display panel of the profile names that were used on the configurator
	 * 
	 */
	
	public TreeView LaunchProfileDisplay() {

		this.root = new TreeItem<String>(); // This is used to create the profile and root and branches are
														// added
		root.setExpanded(true);
		ArrayList<TreeItem> TItems = new ArrayList<>(); // creating profile
		TreeView<String> Tree = new TreeView<>(root); // put item in tree
		Tree.setShowRoot(false);
		Tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, NewValue) -> {
			if (NewValue != null) {
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
		for (int i = 0; i <= profile.length - 1; i++) {

		}
		for (int i = 0; i <= profile.length - 1; i++) {

			int column = audioname[i].length;
			System.out.println(column + "length");
			String profilename = profile[i].substring(17, profile[i].length() - 1);

			this.SetProfile(profilename, root);

			for (int j = 0; j <= column - 1; j++) {

				TreeItem<String> item = new TreeItem<>(profilename);

				if (audioname[i][j] != null) {
					this.SetProfile(audioname[i][j].substring(17, audioname[i][j].length() - 1),
							root.getChildren().get(i));

				}
				System.out.println(audioname[i][j] + "Yes");

			}

		}

		return Tree;

	}
	
	
	
	public TreeItem<String> getRoot() {
		
		return this.root;
	}
	
	
	public int getRow() {
		
		return this.row;
	} 
	
	public String getProfilename() {
		
		return this.profilename;
	}
	/*
	 * This method allows many profiles to concantanate into the profile display panel
	 */

	public void SetProfile(String title, TreeItem<String> parent) {
	
			  TreeItem<String> item = new TreeItem<>(title);
			  item.setExpanded(false);
			  parent.getChildren().add(item);
			
		
		
	}
	
	
	
	
	
	

}
