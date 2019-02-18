package TalkBoxSim;

	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

// Application is part of the f(x) that gives us all the functionalities of an application we can use 
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		 
		}
	}
	
	public static void main(String[] args) {
		// a method inside Application, whenever called it will launch the app as a Java Fx (app)
		launch(args);
	}
}
