package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CourseDescMenu {

	private Scene scene;
	
	public CourseDescMenu(Stage primaryStage, String courseName, String currSid) {
		
		GridPane gp = new GridPane();
		
		Label courseId = new Label("Course Name:");
		TextField courseIdField = new TextField();
		courseIdField.setEditable(false);
		Label courseNumberLabel = new Label("Course Number: ");
		TextField courseNumberField = new TextField();
		courseNumberField.setEditable(false);
		Label sessionsLabel = new Label("Number of Sessions: ");
		TextField sessionsField = new TextField();
		sessionsField.setEditable(false);
		Label descriptionLabel = new Label("Semester: ");
		TextField descriptionField = new TextField();
		descriptionField.setEditable(false);
		Label cardName = new Label(courseName);
		
		Button backButton = new Button("back");
		
		gp.setPadding(new Insets(20));
		gp.setVgap(20);
		gp.setHgap(20);
		
		gp.addRow(0, courseId, courseIdField);
		gp.addRow(1, courseNumberLabel, courseNumberField);
		gp.addRow(2, sessionsLabel, sessionsField);
		gp.addRow(3, descriptionLabel, descriptionField);
		gp.add(backButton, 0, 4);
		gp.add(cardName, 0, 5);
		
		backButton.setOnAction(e -> {
			CourseMenu courseMenu = new CourseMenu(primaryStage, currSid);
			primaryStage.setScene(courseMenu.getScene());
		});
		
		scene = new Scene(gp, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
