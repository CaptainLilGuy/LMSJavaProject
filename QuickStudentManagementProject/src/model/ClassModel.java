package model;

public class ClassModel {
	
	private String sid;
	private String courseId;
	private int totalSessions;
	
	public ClassModel(String sid, String courseId, int totalSessions) {
		super();
		this.sid = sid;
		this.courseId = courseId;
		this.totalSessions = totalSessions;
	}

	public String getSid() {
		return sid;
	}

	public String getCourseId() {
		return courseId;
	}

	public int getTotalSessions() {
		return totalSessions;
	}

	public void setTotalSessions(int totalSessions) {
		this.totalSessions = totalSessions;
	}

	

}
