package ZD2;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader L1 = new FXMLLoader(getClass().getResource("Sample.fxml"));
			BorderPane root = (BorderPane)L1.load();
			Scene scene = new Scene(root,400,400);
			SampleController Controller1 = L1.getController();
			
			FXMLLoader L2 = new FXMLLoader(getClass().getResource("Root2.fxml"));
			BorderPane root2 = (BorderPane)L2.load();
			SampleController Controller2 = L2.getController();
			
			Controller1.setS(scene);
			Controller1.setRoot1(root);
			Controller2.setS(scene);
			Controller2.setRoot1(root2);
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
