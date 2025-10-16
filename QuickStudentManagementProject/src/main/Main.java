package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.LoginAsMenu;

public class Main extends Application{

	public static void main(String[] args) {
		DatabaseConnection db = new DatabaseConnection();
		db.generateDB();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginAsMenu loginMenu = new LoginAsMenu(primaryStage);
		Scene scene = loginMenu.getScene();
		primaryStage.setTitle("JavaFX LMS");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
