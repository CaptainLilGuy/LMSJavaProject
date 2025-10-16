package model;

public class CourseExamModel {
	
	private String examId;
	private String courseId;
	private String examType;
	
	public CourseExamModel(String examId, String courseId, String examType) {
		super();
		this.examId = examId;
		this.courseId = courseId;
		this.examType = examType;
	}

	public String getExamId() {
		return examId;
	}

	public String getCourseId() {
		return courseId;
	}
	
	

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	

}
