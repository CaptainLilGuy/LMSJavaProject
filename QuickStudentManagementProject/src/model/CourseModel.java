package model;

public class CourseModel {

	private String courseId;
	private String courseName;
	private int courseNum;
	private int sessions;
	private String courseDesc;
	
	public CourseModel(String courseId, String courseName, int courseNum, int sessions, String courseDesc) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseNum = courseNum;
		this.sessions = sessions;
		this.courseDesc = courseDesc;
	}

	public String getCourseId() {
		return courseId;
	}


	public String getCourseName() {
		return courseName;
	}

	public void setCoursename(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public int getSessions() {
		return sessions;
	}

	public void setSessions(int sessions) {
		this.sessions = sessions;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	
	
}
