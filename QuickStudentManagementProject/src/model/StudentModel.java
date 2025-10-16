package model;

public class StudentModel {

	private String sid;
	private String studentName;
	private String program;
	private int year;
	private int semester;
	
	public StudentModel(String sid, String studentName, String program, int year, int semester) {
		super();
		this.sid = sid;
		this.studentName = studentName;
		this.program = program;
		this.year = year;
		this.semester = semester;
	}

	public String getSid() {
		return sid;
	}


	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	@Override
	public String toString() {
		return this.studentName;
	}
	
}
