package TalkBoxConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportAudio {

	private Stage stage;
	private Path to;
	private Path from;
	private String src = "src/Audio/";
	public String name;
	
	public void init(Stage stage) {
		this.stage = stage;
	}
	
	public void open() {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(stage);
		name = file.getName();
		to = Paths.get(src + file.getName());
		from = file.toPath();
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		if(file != null) {
		try {
			Files.copy(from, to);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void close() {
		Platform.exit();
	}

}
