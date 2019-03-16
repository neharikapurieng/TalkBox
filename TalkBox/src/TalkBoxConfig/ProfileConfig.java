package TalkBoxConfig;

import java.util.ArrayList;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ProfileConfig {
	
	private TreeView<String> Tree;
	private static TreeItem<String> root;
	private int row;
	private String profilename;
	private static ArrayList<TreeItem> ProfileList;
	
	
	public ProfileConfig() {
		
		this.root = new TreeItem<String>();
		this.root.setExpanded(true);
		this.row=0;
		this.profilename=null;
		this.ProfileList= new ArrayList<>();
	}
	
	
	public void setRoot(TreeItem<String> root) {
		
		this.Tree = new TreeView<String>(root);
		Tree.setShowRoot(false);
	}
	
	
	public void setProfileParameters() {
		
		Tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, NewValue) -> {

			if (NewValue != null) {

				this.row = Tree.getRow(NewValue); // row is the position of the file name
				this.profilename = NewValue.getValue(); // Gets the profile name of the clicked profile

			}

		});
		
	}
	
	public void setProfileSize(int length, int width) {
		
		this.Tree.setMinSize(length, width);
		
		
	}
	
	public void setProfileTitles(String title) {
		ProfileList.add(branch(title, root));
	}
	
	public void removeProfileTitles(int r) {
		root.getChildren().remove(r);
		ProfileList.remove(r);
	}
	
	
	public TreeItem<String> branch(String title, TreeItem<String> parent) {

		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(false);
		parent.getChildren().add(item);
		return item;
	}

	
	public void SoundAdder(String s) {
		branch(s, root.getChildren().get(row));
	}
	
	
	
	

}
