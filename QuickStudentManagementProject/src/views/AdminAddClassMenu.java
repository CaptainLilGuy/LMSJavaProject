package views;

import java.util.ArrayList;
import java.util.List;

import database.ClassDAO;
import database.CourseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CourseModel;
import model.StudentModel;

public class AdminAddClassMenu {

	private Scene scene;
	ObservableList<CourseModel> courseList = FXCollections.observableArrayList(CourseDAO.getAllCourse());
	List<CourseModel> currStudentClass = new ArrayList<>();
	List<CheckBox> checkBoxes = new ArrayList<>();
	List<CourseModel> prevStudentClass = new ArrayList<>();
	
	public AdminAddClassMenu(Stage primaryStage, StudentModel targetStudent) {
		
		VBox container = new VBox(10);
		
		for (CourseModel course : courseList) {
			
            HBox box = new HBox(10); // each row as a box
            box.setStyle("-fx-border-color: black; -fx-padding: 10;");

            Label label = new Label(course.getCourseName());
            CheckBox checkBox = new CheckBox();
            boolean enrolledBefore = ClassDAO.classExist(targetStudent.getSid(), course.getCourseId());
            checkBox.setSelected(enrolledBefore);
            checkBoxes.add(checkBox);
            if (enrolledBefore) {
            	currStudentClass.add(course);
            	prevStudentClass.add(course);
            }
            
            checkBox.setOnAction(e -> {
            	boolean isSelected = checkBox.isSelected();
            	CourseModel selectedCourse = course;
            	if (isSelected == true) {
            		if (!currStudentClass.contains(selectedCourse)) {
            			currStudentClass.add(selectedCourse);
            			System.out.println("Added " + selectedCourse.getCourseName());
            		}
				}
            	else {
            		currStudentClass.remove(selectedCourse);
            		System.out.println("Removed " + selectedCourse.getCourseName());
            	}
            });
            
            HBox buttonBox = new HBox(10);
            buttonBox.getChildren().addAll(checkBox);
            
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            box.getChildren().addAll(label, spacer, buttonBox);

            container.getChildren().add(box);
        }
		
		ScrollPane scrollPane = new ScrollPane(container);
		scrollPane.setFitToWidth(true);
		
		Button backButton = new Button("Back");
		Region buttonSpacers = new Region();
		Button setClassButton = new Button("Set Classes");
		HBox buttonHBox = new HBox(10);
		buttonHBox.setPadding(new Insets(10));
		HBox.setHgrow(buttonSpacers, Priority.ALWAYS);
		buttonHBox.getChildren().addAll(backButton, buttonSpacers, setClassButton);
		
		backButton.setOnAction(e -> {
			AdminStudentMenu studentMenu = new AdminStudentMenu(primaryStage);
			primaryStage.setScene(studentMenu.getScene());
		});
		
		setClassButton.setOnAction(e -> {
			List<CourseModel> newClasses = new ArrayList<>();
			List<CourseModel> toDelete = new ArrayList<>();
			
			for(int i = 0; i < courseList.size(); i++) {
				CourseModel currCourse = courseList.get(i);
				CheckBox cb = checkBoxes.get(i);
				
				if(cb.isSelected() && !prevStudentClass.contains(currCourse)) {
					newClasses.add(currCourse);
				} else if (!cb.isSelected() && prevStudentClass.contains(currCourse)) {
					toDelete.add(currCourse);
				}
			}
			
			if (newClasses.isEmpty() && toDelete.isEmpty()) {
				Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
				emptyAlert.setTitle("Empty Class!");
				emptyAlert.setHeaderText("No Selected Courses");
				emptyAlert.setContentText("Please select a course.");
				emptyAlert.showAndWait();
				return;
			} 
			
			try {
				if(ClassDAO.updateClass(targetStudent.getSid(), newClasses, toDelete)) {
					 Alert success = new Alert(Alert.AlertType.INFORMATION);
		             success.setTitle("Success!");
		             success.setHeaderText("Class Update Successful");
		             success.setContentText("Selected courses have been updated.");
		             success.showAndWait();
		             AdminStudentMenu studentMenu = new AdminStudentMenu(primaryStage);
		             primaryStage.setScene(studentMenu.getScene());
				} else {
					System.out.println("Database Update Failed");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
