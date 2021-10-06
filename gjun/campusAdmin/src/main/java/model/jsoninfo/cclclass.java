package model.jsoninfo;

public class cclclass {
	
	String course_name, teacher, source, course_series, course_outline, course_description, subject_keyword, subject_properties, hours;

	public cclclass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public cclclass(String course_name, String teacher, String source, String course_series, String course_outline,
			String course_description, String subject_keyword, String subject_properties, String hours) {
		super();
		this.course_name = course_name;
		this.teacher = teacher;
		this.source = source;
		this.course_series = course_series;
		this.course_outline = course_outline;
		this.course_description = course_description;
		this.subject_keyword = subject_keyword;
		this.subject_properties = subject_properties;
		this.hours = hours;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCourse_series() {
		return course_series;
	}

	public void setCourse_series(String course_series) {
		this.course_series = course_series;
	}

	public String getCourse_outline() {
		return course_outline;
	}

	public void setCourse_outline(String course_outline) {
		this.course_outline = course_outline;
	}

	public String getCourse_description() {
		return course_description;
	}

	public void setCourse_description(String course_description) {
		this.course_description = course_description;
	}

	public String getSubject_keyword() {
		return subject_keyword;
	}

	public void setSubject_keyword(String subject_keyword) {
		this.subject_keyword = subject_keyword;
	}

	public String getSubject_properties() {
		return subject_properties;
	}

	public void setSubject_properties(String subject_properties) {
		this.subject_properties = subject_properties;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
	

}
