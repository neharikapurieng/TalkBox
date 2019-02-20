package Tester;

import TalkBoxConfig.GuiConfig;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestGui extends Application {


	public GuiConfig g;
	@Override
	public void start(Stage arg0) throws Exception {
		g = new GuiConfig();
		g.start(arg0);
	}

	public static void main(String [] args) {
		Application.launch(args);
	}
	
}