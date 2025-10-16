package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.DatabaseConnection;
import model.CourseModel;

public class CourseDAO {

	public static List<CourseModel> getAllCourse(){
		List<CourseModel> allCourseList = new ArrayList<>();
		String query = "SELECT * FROM course";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				CourseModel allCourses = new CourseModel(
						rs.getString("courseId"),
						rs.getString("courseName"),
						rs.getInt("sectionNum"),
						rs.getInt("sessions"),
						rs.getString("courseDesc"));
				allCourseList.add(allCourses);
			}
			return allCourseList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updateCourse(String courseId, String courseName, int courseSessions, String courseDesc) {
		String query = "UPDATE course SET courseName = ?, sessions = ?, courseDesc = ? WHERE courseId = ?";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, courseName);
			statement.setInt(2, courseSessions);
			statement.setString(3, courseDesc);
			statement.setString(4, courseId);
			statement.executeUpdate();
			System.out.println("Updated data in table course");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addCourse(String courseId, String courseName, int sectionNum, int sessions, String courseDesc) {
		String query = "INSERT INTO course(courseId, courseName, sectionNum, sessions, courseDesc) values(?, ?, ?, ?, ?)";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, courseId);
			statement.setString(2, courseName);
			statement.setInt(3, sectionNum);
			statement.setInt(4, sessions);
			statement.setString(5, courseDesc);
			statement.executeUpdate();
			System.out.println("Inserted new course");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteCourse(String courseId) throws SQLException{
		String query = "DELETE FROM course WHERE courseId = ?";
		Connection conn = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, courseId);
		statement.executeUpdate();
		System.out.println("Deleted a record in course");
	}
	
	
}
