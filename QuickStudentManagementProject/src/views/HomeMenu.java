package views;

import database.StudentDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.StudentModel;

public class HomeMenu {
	
	private Scene scene;
	
	
	public HomeMenu(Stage primaryStage, String sid){
		
		StudentModel currStudent = StudentDAO.getCurrentStudent(sid);
		
		Label studentNameLabel = new Label("Welcome, " + currStudent.getStudentName());
		Button studentButton = new Button("Student Info");
		Button coursesButton = new Button("Courses");
		Button scheduleButton = new Button("Schedule");
		
		double buttonHeight = 300;
		double buttonWidth = 90;
		
		studentButton.setMaxSize(buttonHeight, buttonWidth);
		coursesButton.setMaxSize(buttonHeight, buttonWidth);
		scheduleButton.setMaxSize(buttonHeight, buttonWidth);

		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setStyle("-fx-padding: 20; -fx-alignment: center;");
		
		gp.add(studentNameLabel, 0, 0, 3, 1);
		gp.addRow(1, studentButton, coursesButton, scheduleButton);

		
		studentButton.setOnAction(e -> {
			StudentMenu studentMenu = new StudentMenu(primaryStage, currStudent, sid);
			primaryStage.setScene(studentMenu.getScene());
		});
		
		scheduleButton.setOnAction(e -> {
			ScheduleMenu scheduleMenu = new ScheduleMenu(primaryStage, sid);
			primaryStage.setScene(scheduleMenu.getScene());
		});
		
		coursesButton.setOnAction(e -> {
			CourseMenu courseMenu = new CourseMenu(primaryStage, sid);
			primaryStage.setScene(courseMenu.getScene());
		});
		
		scene = new Scene(gp, 300, 250);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
