package TalkBoxConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/*
 * This class allows user to import their own audio files
 * it opens up their personal file explorer where the user can navigate
 * and add any wav file they want
 * 
 * 
 */
public class ImportAudio {

	private Stage stage;
	private Path to;
	private Path from;
	private String src = "src/Audio/";
	public String name;
	
	
	/*
	 * This acts as the visible stage of their file explorer
	 */
	public void init(Stage stage) {
		this.stage = stage;
	}
	
	/*
	 * This opens their file explorer and copies the chosen audio
	 * to src/Audio
	 */
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
	
	/*
	 * Closes file explorer
	 */
	public void close() {
		Platform.exit();
	}

}
