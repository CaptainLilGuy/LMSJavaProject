package views;

import database.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.StudentModel;

public class AdminStudentMenu {

	private Scene scene;
	
	public AdminStudentMenu(Stage primaryStage) {
		
		GridPane gp = new GridPane();
		
		ObservableList<StudentModel> studentList = FXCollections.observableArrayList(StudentDAO.getAllStudents());
		ComboBox<StudentModel> studentSelector = new ComboBox<>(studentList);
		
		Label studentIdLabel = new Label("Student Id:");
		TextField studentIdField = new TextField();
		studentIdField.setEditable(false);
		Label studentNameLabel = new Label("Student Name:");
		TextField studentNameField = new TextField();
		studentNameField.setEditable(false);
		Label programLabel = new Label("Program: ");
		TextField programField = new TextField();
		programField.setEditable(false);
		Label yearLabel = new Label("Year: ");
		TextField yearField = new TextField();
		yearField.setEditable(false);
		Label semesterLabel = new Label("Semester: ");
		TextField semesterField = new TextField();
		semesterField.setEditable(false);
		
		Button backButton = new Button("back");
		Button classButton = new Button("Manage Student Classes");
		
		studentSelector.setOnAction(e -> {
			StudentModel selectedStudent = studentSelector.getSelectionModel().getSelectedItem();
			if (selectedStudent != null) {
				studentIdField.setText(selectedStudent.getSid());
				studentNameField.setText(selectedStudent.getStudentName());
				programField.setText(selectedStudent.getProgram());
				yearField.setText(Integer.toString(selectedStudent.getYear()));
				semesterField.setText(Integer.toString(selectedStudent.getSemester()));
			}
		});
		
		backButton.setOnAction(e -> {
			AdminHomeMenu homeMenu = new AdminHomeMenu(primaryStage);
			primaryStage.setScene(homeMenu.getScene());
		});
		
		classButton.setOnAction(e -> {
			StudentModel targetStudent = null;
			if(studentIdField.getText().isEmpty()) {
				System.out.println("Please select a student");
			} else {
				for(int i = 0; i < studentList.size(); i++) {
					if(studentList.get(i).getSid().equals(studentIdField.getText()));
					targetStudent = studentList.get(i);
				}
				AdminAddClassMenu addClass = new AdminAddClassMenu(primaryStage, targetStudent);
				primaryStage.setScene(addClass.getScene());
			}
		});
		
		gp.setPadding(new Insets(20));
		gp.setVgap(20);
		gp.setHgap(20);
		
		gp.add(studentSelector, 0, 0);
		gp.addRow(1, studentIdLabel, studentIdField);
		gp.addRow(2, studentNameLabel, studentNameField);
		gp.addRow(3, programLabel, programField);
		gp.addRow(4, yearLabel, yearField);
		gp.addRow(5, semesterLabel, semesterField);
		gp.add(backButton, 0, 6);
		gp.add(classButton, 1, 6);
		
		scene = new Scene(gp, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
