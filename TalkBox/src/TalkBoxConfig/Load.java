package TalkBoxConfig;

import java.awt.Button;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;

public class Load {
	TalkBoxConfiguration tbc;
	ButtonGui b = new ButtonGui();
	public int size;
	String[] profile;
	
	Load() throws Exception{
		tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
		profile = tbc.Profiles;
	}
	
	public void Loader(GridPane sp,TreeItem<String> root) throws Exception {
	String[][] audioname = tbc.AudioName;
	TreeItem<String> parent = new TreeItem<String>();
	for (int i = 0; i <= profile.length - 1; i++) {
		int column = audioname[i].length;
		String profilename = profile[i].substring(17, profile[i].length() - 1);
		this.branch(profilename, root);
		for (int j = 0; j <= column - 1; j++) {
			TreeItem<String> item = new TreeItem<>(profilename);
			if (audioname[i][j] != null) {
				this.branch(audioname[i][j].substring(18, audioname[i][j].length() - 2),
						root.getChildren().get(i));
			}
		}
		size = root.getChildren().size();
}
	}

	
	public TreeItem getTreeItem(int i,TreeItem<String> root) {
		return root.getChildren().get(i);
	}
	
	
	public TreeItem<String> branch(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(false);
		parent.getChildren().add(item);
		return item;
	}
}

