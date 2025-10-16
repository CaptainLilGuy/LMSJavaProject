package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.StudentModel;

public class StudentMenu {

	private Scene scene;
	
	public StudentMenu(Stage primaryStage, StudentModel currStudent, String currSid) {
		
		GridPane gp = new GridPane();
		
		Label studentLabel = new Label("Student Name:");
		TextField studentField = new TextField();
		studentField.setText(currStudent.getStudentName());
		studentField.setEditable(false);
		Label programLabel = new Label("Program: ");
		TextField programField = new TextField();
		programField.setText(currStudent.getProgram());
		programField.setEditable(false);
		Label yearLabel = new Label("Year: ");
		TextField yearField = new TextField();
		yearField.setText(Integer.toString(currStudent.getYear()));
		yearField.setEditable(false);
		Label semesterLabel = new Label("Semester: ");
		TextField semesterField = new TextField();
		semesterField.setText(Integer.toString(currStudent.getSemester()));
		semesterField.setEditable(false);
		
		Button backButton = new Button("back");
		
		gp.setPadding(new Insets(20));
		gp.setVgap(20);
		gp.setHgap(20);
		
		gp.addRow(0, studentLabel, studentField);
		gp.addRow(1, programLabel, programField);
		gp.addRow(2, yearLabel, yearField);
		gp.addRow(3, semesterLabel, semesterField);
		gp.add(backButton, 0, 4);
		
		backButton.setOnAction(e -> {
			HomeMenu homeMenu = new HomeMenu(primaryStage, currSid);
			primaryStage.setScene(homeMenu.getScene());
		});
		
		scene = new Scene(gp, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
