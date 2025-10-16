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
import model.CourseModel;

public class AdminCourseDescMenu {

	private Scene scene;
	
	public AdminCourseDescMenu(Stage primaryStage, CourseModel course) {
		
		GridPane gp = new GridPane();
		
		Label courseId = new Label("Course ID:");
		TextField courseIdField = new TextField();
		courseIdField.setText(course.getCourseId());
		courseIdField.setEditable(false);
		Label courseNameLabel = new Label("Course Name:");
		TextField courseNameField = new TextField();
		courseNameField.setText(course.getCourseName());
		Label courseNumberLabel = new Label("Course Number: ");
		TextField courseNumberField = new TextField();
		courseNumberField.setText(Integer.toString(course.getCourseNum()));
		courseNumberField.setEditable(false);
		Label sessionsLabel = new Label("Number of Sessions: ");
		TextField sessionsField = new TextField();
		sessionsField.setText(Integer.toString(course.getSessions()));
		Label descriptionLabel = new Label("Course Description: ");
		TextField descriptionField = new TextField();
		descriptionField.setText(course.getCourseDesc());
		
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
			String newCourseName = courseNameField.getText();
			int newCourseSessions = Integer.parseInt(sessionsField.getText());
			String newCourseDescription = descriptionField.getText();
			
			if (!(newCourseDescription.equals(course.getCourseDesc())) ||
				newCourseSessions != course.getSessions() || 
				!(newCourseName.equals(course.getCourseName()))) 
			{	
				CourseDAO.updateCourse(course.getCourseId(), newCourseName, newCourseSessions, newCourseDescription);
				AdminCourseMenu courseMenu = new AdminCourseMenu(primaryStage);
				primaryStage.setScene(courseMenu.getScene());
			}
			else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Info");
				alert.setHeaderText("No changes");
				alert.setContentText("The data has not been changed");
				alert.showAndWait();
			}
		});
		
		scene = new Scene(gp, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
