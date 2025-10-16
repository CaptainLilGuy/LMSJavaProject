package views;

import database.CourseDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AdminAddCourseMenu {

	private Scene scene;
	
	public AdminAddCourseMenu(Stage primaryStage) {
		
		GridPane gp = new GridPane();
		
		Label courseId = new Label("Course ID:");
		TextField courseIdField = new TextField();
		Label courseNameLabel = new Label("Course Name:");
		TextField courseNameField = new TextField();
		Label courseNumberLabel = new Label("Course Number: ");
		TextField courseNumberField = new TextField();
		Label sessionsLabel = new Label("Number of Sessions: ");
		TextField sessionsField = new TextField();
		Label descriptionLabel = new Label("Course Description: ");
		TextField descriptionField = new TextField();
		
		Region spacer = new Region();
		Button backButton = new Button("back");
		Button saveButton = new Button("Save Data");
		
		
		gp.setPadding(new Insets(20));
		gp.setVgap(20);
		gp.setHgap(20);
		
		gp.addRow(0, courseId, courseIdField);
		gp.addRow(1, courseNameLabel, courseNameField);
		gp.addRow(2, courseNumberLabel, courseNumberField);
		gp.addRow(3, sessionsLabel, sessionsField);
		gp.addRow(4, descriptionLabel, descriptionField);
		gp.add(backButton, 0, 5);
		gp.add(spacer, 1, 5);
		gp.add(saveButton, 2, 5);
		
		backButton.setOnAction(e -> {
			AdminCourseMenu courseMenu = new AdminCourseMenu(primaryStage);
			primaryStage.setScene(courseMenu.getScene());
		});
		
		saveButton.setOnAction(e -> {
			Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
			emptyAlert.setTitle("Error");
			emptyAlert.setHeaderText("Empty field!");
			
			String insertedCourseId = courseIdField.getText().trim();
			String insertedCourseName = courseNameField.getText().trim();
			String insertedCourseNumber = courseNumberField.getText().trim();
			String insertedCourseSessions = sessionsField.getText().trim();
			String insertedCourseDesc = descriptionField.getText().trim();
			
			if (insertedCourseId.isEmpty() ||
				insertedCourseName.isEmpty() ||
				insertedCourseNumber.isEmpty() ||
				insertedCourseSessions.isEmpty() ||
				insertedCourseDesc.isEmpty()) {
				emptyAlert.setContentText("Please insert all fields.");
				emptyAlert.showAndWait();
				return;
			}
			
			try {
					CourseDAO.addCourse(
							insertedCourseId, 
							insertedCourseName, 
							Integer.parseInt(insertedCourseNumber), 
							Integer.parseInt(insertedCourseSessions), 
							insertedCourseDesc);
					
					Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
					successAlert.setTitle("Success!");
					successAlert.setHeaderText("Course insert success");
					successAlert.showAndWait();
					
			} catch (Exception e2) {
					e2.printStackTrace();
			}
		});
		
		scene = new Scene(gp, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
