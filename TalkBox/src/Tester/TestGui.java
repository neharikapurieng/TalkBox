package Tester;

import TalkBoxConfig.GuiConfig;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestGui extends Application {
	GuiConfig test;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		test = new GuiConfig();
		test.start(new Stage());
	}
	
	public int getNumberOfButtons() {
		return test.numofbuttons;
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
