package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.DatabaseConnection;
import model.StudentModel;

public class StudentDAO {
	
		public static StudentModel getCurrentStudent(String currSid){
			String getCurrQuery = "SELECT * FROM students WHERE sid = ?";
			try {
				Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(getCurrQuery);
				statement.setString(1, currSid);
				ResultSet rs = statement.executeQuery();
				
				if (rs.next()) {
					return new StudentModel(	
							rs.getString("sid"), 
							rs.getString("studentName"), 
							rs.getString("program"), 
							rs.getInt("year"), 
							rs.getInt("semester"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static List<StudentModel> getAllStudents(){
			List<StudentModel> studentsData = new ArrayList<>();
			String getAll = "SELECT * FROM students";
			try {
				Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(getAll);
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					StudentModel student = new StudentModel(
							rs.getString("sid"), 
							rs.getString("studentName"), 
							rs.getString("program"), 
							rs.getInt("year"), 
							rs.getInt("semester"));
					studentsData.add(student);
				}
				return studentsData;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
