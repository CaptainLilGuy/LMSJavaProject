package views;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import database.ClassDAO;
import database.ScheduleDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminAddScheduleMenu {

	private Scene scene;
	private ObservableList<String> studentIdList;
	
	public AdminAddScheduleMenu(Stage primaryStage) {
		
		List<String> classroom = new ArrayList<>();
		classroom.add("401"); classroom.add("402"); classroom.add("403"); classroom.add("404"); classroom.add("405"); classroom.add("406");
		
		GridPane gp = new GridPane();
		
		studentIdList = ClassDAO.getAllSid();
		
		ComboBox<String> studentSelector = new ComboBox<>(studentIdList);
		ComboBox<String> courseSelector = new ComboBox<>();
		courseSelector.setEditable(false);
		
		studentSelector.setOnAction(e -> {
			String selectedStudentId = studentSelector.getSelectionModel().getSelectedItem();
			if(selectedStudentId != null) {
				ObservableList<String> studentCourseList = ClassDAO.getAssociatedCourseId(selectedStudentId);
				courseSelector.setItems(studentCourseList);
			} else {
				courseSelector.getItems().clear();
				courseSelector.setEditable(false);
			}
		});
		
		Label scheduleDateLabel= new Label("Schedule Date:");
		DatePicker scheduleDatePicker = new DatePicker();
		Label scheduleStartTimeLabel = new Label("Schedule Start Time:");
		ComboBox<LocalTime> startTimeBox = new ComboBox<LocalTime>();
		Label scheduleEndTimeLabel = new Label("Schedule End Time:");
		ComboBox<LocalTime> endTimeBox = new ComboBox<LocalTime>();
		Label classroomLabel = new Label("Classroom: ");
		ComboBox<String> classroomBox = new ComboBox<String>();
		classroomBox.getItems().addAll(classroom);
		
		for(int hour = 7; hour <= 17; hour+=2) {
			startTimeBox.getItems().add(LocalTime.of(hour, 20));
			endTimeBox.getItems().add(LocalTime.of(hour + 2, 0));
		}
		
		startTimeBox.setOnAction(e -> {
			int index = startTimeBox.getSelectionModel().getSelectedIndex();
			
			if(index >= 0) {
				endTimeBox.getSelectionModel().select(index);
			}
		});
		
		endTimeBox.setOnAction(e -> {
			int index = endTimeBox.getSelectionModel().getSelectedIndex();
			
			if(index >= 0) {
				startTimeBox.getSelectionModel().select(index);
			}
		});
		
		Button backButton = new Button("back");
		Button insertButton = new Button("Insert Schedule");
		
		scheduleDatePicker.setDayCellFactory(e -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				
				if(empty || date == null) {
					return;
				}
				if(date.isBefore(LocalDate.now())) {
					setDisable(true);
					setStyle("-fx-background-color: #dddddd;");
				}
				if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
					setDisable(true);
				}
			}
		});
		
		backButton.setOnAction(e -> {
			AdminHomeMenu homeMenu = new AdminHomeMenu(primaryStage);
			primaryStage.setScene(homeMenu.getScene());
		});
		
		insertButton.setOnAction(e -> {
			if(studentSelector.getSelectionModel().isEmpty() 
					|| courseSelector.getSelectionModel().isEmpty() 
					|| scheduleDatePicker.getValue() == null
					|| startTimeBox.getSelectionModel().isEmpty()
					|| endTimeBox.getSelectionModel().isEmpty()
					|| classroomBox.getSelectionModel().isEmpty()) {
				Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
				emptyAlert.setTitle("Empty Field");
				emptyAlert.setHeaderText("Please fill all fields");
				emptyAlert.showAndWait();
			} else {
				String scheduleId = createScheduleId(studentSelector.getSelectionModel().getSelectedItem(),
				courseSelector.getSelectionModel().getSelectedItem(),
				scheduleDatePicker.getValue(), 
				startTimeBox.getSelectionModel().getSelectedItem(), 
				endTimeBox.getSelectionModel().getSelectedItem(), 
				classroomBox.getSelectionModel().getSelectedItem());
				boolean isAlready = ScheduleDAO.hasSchedule(scheduleId);
				System.out.println(isAlready);
				if(isAlready==true) {
					Alert already = new Alert(Alert.AlertType.ERROR);
					already.setTitle("Schedule Already Exist");
					already.setHeaderText("Schedule already exist.");
					already.showAndWait();
					return;
				} else {
					ScheduleDAO.addSchedule(scheduleId, 
							studentSelector.getSelectionModel().getSelectedItem(), 
							courseSelector.getSelectionModel().getSelectedItem(), 
							scheduleDatePicker.getValue(), 
							startTimeBox.getSelectionModel().getSelectedItem(), 
							endTimeBox.getSelectionModel().getSelectedItem(), 
							classroomBox.getSelectionModel().getSelectedItem());
					Alert success = new Alert(Alert.AlertType.INFORMATION);
					success.setTitle("Success!");
					success.setHeaderText("Addded new schedule!");
					success.setContentText("Successfully add schedule ID: " + scheduleId);
					AdminHomeMenu homeMenu = new AdminHomeMenu(primaryStage);
					primaryStage.setScene(homeMenu.getScene());
				}
			}
		});
		
		gp.setPadding(new Insets(20));
		gp.setVgap(20);
		gp.setHgap(20);
		
		gp.add(studentSelector, 0, 0);
		gp.add(courseSelector, 1, 0);
		gp.addRow(1, scheduleDateLabel, scheduleDatePicker);
		gp.addRow(2, scheduleStartTimeLabel, startTimeBox);
		gp.addRow(3, scheduleEndTimeLabel, endTimeBox);
		gp.addRow(4, classroomLabel, classroomBox);
		gp.add(backButton, 0, 5);
		gp.add(insertButton, 1, 5);
		
		scene = new Scene(gp, 500, 350);
	}
	
	private String createScheduleId(String sid, String courseId, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String classroom) {
		return "SU" + classroom + courseId + sid.substring(4) + startTime.format(DateTimeFormatter.ofPattern("HH")) + endTime.format(DateTimeFormatter.ofPattern("HH")) + scheduleDate.format(DateTimeFormatter.ofPattern("DD"));
	}
	
	
	public Scene getScene() {
		return this.scene;
	}
	
}
