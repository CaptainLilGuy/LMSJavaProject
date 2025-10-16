package views;

import database.ScheduleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ScheduleModel;

public class ScheduleMenu {

	private Scene scene;
	
	public ScheduleMenu(Stage primaryStage, String sid) {
		
		VBox container = new VBox(10);
		
		ObservableList<ScheduleModel> scheduleList = FXCollections.observableArrayList(ScheduleDAO.getStudentSchedules(sid));
		
		for (ScheduleModel currSchedule : scheduleList) {
            HBox box = new HBox(10); // each row as a box
            box.setStyle("-fx-border-color: black; -fx-padding: 10;");
            VBox leftBox = new VBox(10);
            Label classroomLabel = new Label(currSchedule.getScheduleId());
            Label dateLabel = new Label(currSchedule.getScheduleDate().toString());
            Label timeLabel = new Label("Time: " + currSchedule.getStartTime().toString() + "-" + currSchedule.getEndTime().toString());
            leftBox.getChildren().addAll(classroomLabel, dateLabel);
            box.getChildren().add(leftBox);
            box.getChildren().add(timeLabel);

            container.getChildren().add(box);
        }
		
		ScrollPane scrollPane = new ScrollPane(container);
		scrollPane.setFitToWidth(true);
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			HomeMenu homeMenu = new HomeMenu(primaryStage, sid);
			primaryStage.setScene(homeMenu.getScene());
		});
		
		VBox vb = new VBox(10);
		vb.getChildren().add(backButton);
		vb.getChildren().add(scrollPane);
		scene = new Scene(vb, 500, 350);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
