package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {

	private static String url = "jdbc:sqlite:student.db";
	private static DatabaseConnection instance;
	private Connection conn;
	
	public DatabaseConnection() {
		try {
            // Path: student.db in project root
            
            conn = DriverManager.getConnection(url);

            if (conn != null) {
//                System.out.println("Connection Established");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void generateDB() {
		try (Statement stmt = conn.createStatement()) {
        	String createStudentTable = "CREATE TABLE IF NOT EXISTS students ("
                    +"sid TEXT PRIMARY KEY NOT NULL, " 
                    +"studentName TEXT NOT NULL, "
                    +"program TEXT NOT NULL,"
                    + "year INTEGER NOT NULL,"
                    + "semester INTEGER NOT NULL"
                    + ")";
        	stmt.execute(createStudentTable);
            String createClassTable = "CREATE TABLE IF NOT EXISTS class ("
            		+ "sid TEXT NOT NULL,"
            		+ "courseId TEXT NOT NULL,"
            		+ "totalSessions INTEGER NOT NULL,"
            		+ "PRIMARY KEY (sid, courseId),"
            		+ "FOREIGN KEY (sid) REFERENCES students (sid) ON UPDATE CASCADE ON DELETE CASCADE,"
            		+ "FOREIGN KEY (courseId) REFERENCES course (courseId) ON UPDATE CASCADE ON DELETE CASCADE"
            		+ ")";
            stmt.execute(createClassTable);
            String createCourseTable = "CREATE TABLE IF NOT EXISTS course ("
            		+ "courseId TEXT PRIMARY KEY NOT NULL,"
            		+ "courseName TEXT NOT NULL,"
            		+ "sectionNum INTEGER NOT NULL,"
            		+ "sessions INTEGER NOT NULL,"
            		+ "courseDesc TEXT NOT NULL"
            		+ ")";
            stmt.execute(createCourseTable);
            String createScheduleTable = "CREATE TABLE IF NOT EXISTS schedule ("
            		+ "scheduleId TEXT PRIMARY KEY NOT NULL,"
            		+ "sid TEXT NOT NULL,"
            		+ "courseId TEXT NOT NULL,"
            		+ "scheduleDate TEXT NOT NULL,"
            		+ "startTime TEXT NOT NULL,"
            		+ "endTime TEXT NOT NULL,"
            		+ "classroom TEXT NOT NULL,"
            		+ "FOREIGN KEY (sid, courseId) REFERENCES class (sid, courseId) ON UPDATE CASCADE ON DELETE CASCADE"
            		+ ")";
            stmt.execute(createScheduleTable);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	public static synchronized DatabaseConnection getInstance() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	public synchronized  Connection getConnection() throws SQLException{
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url);
		}
		return conn;
	}
	
}
