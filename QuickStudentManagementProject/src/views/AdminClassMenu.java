package views;

import java.sql.SQLException;

import database.CourseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CourseModel;

public class AdminClassMenu {

private Scene scene;
	
	public AdminClassMenu(Stage primaryStage) {
		
		VBox container = new VBox(10);
		
		ObservableList<CourseModel> courseList = FXCollections.observableArrayList(CourseDAO.getAllCourse());
		
		
		for (CourseModel course : courseList) {
			
            HBox box = new HBox(10); // each row as a box
            box.setStyle("-fx-border-color: black; -fx-padding: 10;");

            Label label = new Label(course.getCourseName());
            Button descButton = new Button("Edit Course");
            Button deleteButton = new Button("Delete Course");
            
            HBox buttonBox = new HBox(10);
            buttonBox.getChildren().addAll(descButton, deleteButton);
            
            descButton.setOnAction(e -> {
            	AdminCourseDescMenu courseDesc = new AdminCourseDescMenu(primaryStage, course);
            	primaryStage.setScene(courseDesc.getScene());
            });
            
            deleteButton.setOnAction(e -> {
            	try {
					CourseDAO.deleteCourse(course.getCourseId());
					container.getChildren().remove(box);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            });
            
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            box.getChildren().addAll(label, spacer, buttonBox);

            container.getChildren().add(box);
        }
		
		ScrollPane scrollPane = new ScrollPane(container);
		scrollPane.setFitToWidth(true);
		
		Button backButton = new Button("Back");
		Region buttonSpacers = new Region();
		Button newCourseButton = new Button("Create New Course");
		HBox buttonHBox = new HBox(10);
		buttonHBox.setPadding(new Insets(10));
		HBox.setHgrow(buttonSpacers, Priority.ALWAYS);
		buttonHBox.getChildren().addAll(backButton, buttonSpacers, newCourseButton);
		
		backButton.setOnAction(e -> {
			AdminHomeMenu homeMenu = new AdminHomeMenu(primaryStage);
			primaryStage.setScene(homeMenu.getScene());
		});
		
		newCourseButton.setOnAction(e -> {
			AdminAddCourseMenu addCourse = new AdminAddCourseMenu(primaryStage);
			primaryStage.setScene(addCourse.getScene());
		});
		
		VBox vb = new VBox(10);
		vb.getChildren().add(buttonHBox);
		vb.getChildren().add(scrollPane);
		scene = new Scene(vb, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
