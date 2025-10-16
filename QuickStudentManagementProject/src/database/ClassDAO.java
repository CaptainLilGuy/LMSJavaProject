package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.DatabaseConnection;
import model.ClassModel;
import model.CourseModel;

public class ClassDAO {
	
	public static List<ClassModel> getAllClass(){
		List<ClassModel> allClassList = new ArrayList<>();
		String query = "SELECT * FROM class";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClassModel currClass = new ClassModel(
						rs.getString("sid"),
						rs.getString("courseId"),
						rs.getInt("totalSessions"));
				allClassList.add(currClass);
			}
			return allClassList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ObservableList<String> getAssociatedCourseId(String sid){
		ObservableList<String> courseIdPair = FXCollections.observableArrayList();
		String query = "SELECT courseId FROM class WHERE sid = ?";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, sid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				courseIdPair.add(rs.getString("courseId"));
			}
			System.out.println("Pulled data based on " + sid);
			return courseIdPair;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Returned null");
		return null;
	}
	
	public static ObservableList<String> getAllSid(){
		ObservableList<String> allSid = FXCollections.observableArrayList();
		String query = "SELECT DISTINCT sid FROM class";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				allSid.add(rs.getString("sid"));
			}
			return allSid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addClass(String sid, List<CourseModel> selectedCourses) throws SQLException {
		String query = "INSERT OR IGNORE INTO class(sid, courseId, totalSessions) values(?, ?, ?)";
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = conn.prepareStatement(query);){
			conn.setAutoCommit(false);
			for (CourseModel course : selectedCourses) {
				statement.setString(1, sid);
				statement.setString(2, course.getCourseId());
				statement.setInt(3, 0);
				statement.addBatch();
			}
		statement.executeBatch();
		conn.commit();
		return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean classExist(String sid, String courseId) {
		String query = "SELECT COUNT(*) FROM class WHERE sid = ? AND courseId = ?";
		try {
			Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, sid);
			statement.setString(2, courseId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean removeClass(String sid, List<CourseModel> selectedCourse) throws SQLException {
		String query = "DELETE FROM class WHERE sid = ? AND courseId = ?";
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = conn.prepareStatement(query);){
			conn.setAutoCommit(false);
			for (CourseModel course : selectedCourse) {
				statement.setString(1, query);
				statement.setString(2, course.getCourseId());
				statement.addBatch();
			}
			statement.executeBatch();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateClass(String sid, List<CourseModel> toAdd, List<CourseModel> toRemove) {
		String insertQuery = "INSERT OR IGNORE INTO class(sid, courseId, totalSessions) VALUES(?, ?, ?)";
		String deleteQuery = "DELETE FROM class WHERE sid = ? AND courseId = ?";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
			PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
			PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);){
			
			conn.setAutoCommit(false);
			
			for(CourseModel course : toRemove) {
				deleteStatement.setString(1, sid);
				deleteStatement.setString(2, course.getCourseId());
				deleteStatement.addBatch();
			}
			deleteStatement.executeBatch();
			
			for(CourseModel course : toAdd) {
				insertStatement.setString(1, sid);
				insertStatement.setString(2, course.getCourseId());
				insertStatement.setInt(3, 0);
				insertStatement.addBatch();
			}
			insertStatement.executeBatch();
			
			conn.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
