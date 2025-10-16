package views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminHomeMenu {
	
	private Scene scene;
	
	public AdminHomeMenu(Stage primaryStage) {
		
		Button studentButton = new Button("Edit Student Info");
		Button coursesButton = new Button("Edit Courses");
		Button scheduleButton = new Button("Edit Schedule");
		
		double buttonHeight = 300;
		double buttonWidth = 90;
		
		studentButton.setMaxSize(buttonHeight, buttonWidth);
		coursesButton.setMaxSize(buttonHeight, buttonWidth);
		scheduleButton.setMaxSize(buttonHeight, buttonWidth);

		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setStyle("-fx-padding: 20; -fx-alignment: center;");
		
		gp.addRow(0, studentButton, coursesButton);
		gp.addRow(1, scheduleButton);

		
		studentButton.setOnAction(e -> {
			AdminStudentMenu studentAdmin = new AdminStudentMenu(primaryStage);
			primaryStage.setScene(studentAdmin.getScene());
		});
		
		scheduleButton.setOnAction(e -> {
			AdminAddScheduleMenu scheduleAdmin = new AdminAddScheduleMenu(primaryStage);
			primaryStage.setScene(scheduleAdmin.getScene());
		});
		
		coursesButton.setOnAction(e -> {
			AdminCourseMenu courseAdmin = new AdminCourseMenu(primaryStage);
			primaryStage.setScene(courseAdmin.getScene());
		});
		
		scene = new Scene(gp, 300, 250);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
