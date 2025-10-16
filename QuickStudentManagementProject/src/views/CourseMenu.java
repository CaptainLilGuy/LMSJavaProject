package views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseMenu {

private Scene scene;
	
	public CourseMenu(Stage primaryStage, String sid) {
		
		VBox container = new VBox(10);
		
		String[] rows = {
				"Row 1: Alice", 
				"Row 2: Bob", 
				"Row 3: Charlie",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				"Row 3: Ayam",
				};
		
		for (String row : rows) {
            HBox box = new HBox(10); // each row as a box
            box.setStyle("-fx-border-color: black; -fx-padding: 10;");

            Label label = new Label(row);
            Button descButton = new Button("Course Description");
            descButton.setOnAction(e -> {
            	CourseDescMenu courseDesc = new CourseDescMenu(primaryStage, row, sid);
            	primaryStage.setScene(courseDesc.getScene());
            });
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            box.getChildren().addAll(label, spacer, descButton);

            container.getChildren().add(box);
        }
		
		ScrollPane scrollPane = new ScrollPane(container);
		scrollPane.setFitToWidth(true);
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			HomeMenu homeMenu = new HomeMenu(primaryStage, sid);
			primaryStage.setScene(homeMenu.getScene());
		});
		
		VBox vb = new VBox(10);
		vb.getChildren().add(backButton);
		vb.getChildren().add(scrollPane);
		scene = new Scene(vb, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
