package views;

import database.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.StudentModel;

public class LoginAsMenu {

	private Scene scene;
	
	ObservableList<StudentModel> studentList = FXCollections.observableArrayList(StudentDAO.getAllStudents());
	
	public LoginAsMenu(Stage primaryStage) {
		Label loginAsLabel = new Label("Login As:");
		Button studentButton = new Button("Student");
		Button adminButton = new Button("Admin");
		
		Label selectStudentLabel = new Label("Select Student: ");
		ComboBox<StudentModel> studentNameBox = new ComboBox<>(studentList);
		
		studentButton.setOnAction(e -> {
			if(!studentNameBox.getSelectionModel().isEmpty()) {
				HomeMenu home = new HomeMenu(primaryStage, studentNameBox.getSelectionModel().getSelectedItem().getSid());
				primaryStage.setScene(home.getScene());
			}
		});
		
		adminButton.setOnAction(e -> {
			AdminHomeMenu adminMenu = new AdminHomeMenu(primaryStage);
			primaryStage.setScene(adminMenu.getScene());
		});
		
		VBox vb = new VBox();
		vb.setStyle("-fx-padding: 20; -fx-alignment: center;");
		vb.getChildren().addAll(selectStudentLabel, studentNameBox);
		
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setStyle("-fx-padding: 20; -fx-alignment: center;");
		
		gp.add(loginAsLabel, 0, 0, 2, 1);
		gp.add(adminButton, 0, 1, 1, 1);
		gp.add(studentButton, 1, 1, 1, 1);
		gp.add(vb, 0, 2, 2, 1);
		
		scene = new Scene(gp, 500, 500);
		
	}
	
	public Scene getScene() {
		return this.scene;	
		}
	
}
