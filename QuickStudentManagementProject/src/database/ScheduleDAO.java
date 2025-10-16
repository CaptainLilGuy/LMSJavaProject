package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import main.DatabaseConnection;
import model.ScheduleModel;

public class ScheduleDAO {
	
	public static List<ScheduleModel> getStudentSchedules (String sid){
		List<ScheduleModel> studentSchedules = new ArrayList<>();
		String query = "SELECT * FROM schedule WHERE sid = ?";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, sid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ScheduleModel currSchedule = new ScheduleModel(
						rs.getString("scheduleId"), 
						rs.getString("sid"), 
						rs.getString("courseId"), 
						LocalDate.parse(rs.getString("scheduleDate")), 
						LocalTime.parse(rs.getString("startTime")), 
						LocalTime.parse(rs.getString("endTime")), 
						rs.getString("classroom"));
				studentSchedules.add(currSchedule);
			}
			return studentSchedules;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addSchedule(String scheduleId, String sid, String courseId, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String classroom) {
		try {
			String query = "INSERT INTO schedule(scheduleId, sid, courseId, scheduleDate, startTime, endTime, classroom) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, scheduleId);
			statement.setString(2, sid);
			statement.setString(3, courseId);
			statement.setString(4, scheduleDate.toString());
			statement.setString(5, startTime.toString());
			statement.setString(6, endTime.toString());
			statement.setString(7, classroom);
			statement.executeUpdate();
			System.out.println("Added new schedule");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasSchedule(String scheduleId) {
		try {
			String query = "SELECT COUNT(*) FROM schedule WHERE scheduleId = ?";
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, scheduleId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				System.out.println("ID already exist");
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
