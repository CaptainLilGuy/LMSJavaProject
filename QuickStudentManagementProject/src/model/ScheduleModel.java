package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleModel {

	private String scheduleId;
	private String courseId;
	private String sid;
	private LocalDate scheduleDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String classroom;
	
	public ScheduleModel(String scheduleId, String courseId, String sid, LocalDate scheduleDate, LocalTime startTime,
			LocalTime endTime, String classroom) {
		super();
		this.scheduleId = scheduleId;
		this.courseId = courseId;
		this.sid = sid;
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classroom = classroom;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getSid() {
		return sid;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	
}
