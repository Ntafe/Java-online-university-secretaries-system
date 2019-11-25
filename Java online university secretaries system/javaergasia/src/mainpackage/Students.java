package mainpackage;

public class Students {

	public Students() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String gradelesson(int id){
		String showThisStudentStatement = "SELECT COURSE.CourseTitle,COURSE_has_STUDENTS.STUDENTS_RegistrationNumber,COURSE_has_STUDENTS.GradeCourseStudent FROM COURSE_has_STUDENTS,Course WHERE COURSE_has_STUDENTS.COURSE_idCOURSE=COURSE.idCOURSE and COURSE_has_STUDENTS.GradeCourseStudent is not null and STUDENTS_RegistrationNumber="+id;
		return(showThisStudentStatement);
	}
	
	public String gradesemester(int id){
		String insertStudentStatement = "SELECT COURSE_has_STUDENTS.STUDENTS_RegistrationNumber,COURSE.CourseSemester,avg(COURSE_has_STUDENTS.GradeCourseStudent) as kati\n" + 
				" FROM COURSE_has_STUDENTS,COURSE \n" + 
				" WHERE COURSE_has_STUDENTS.COURSE_idCOURSE=COURSE.idCOURSE  and STUDENTS_RegistrationNumber='"+id+"'\n" + 
				" GROUP BY COURSE.CourseSemester\n" + 
				" having kati is not null \n" + 
				" ORDER BY COURSE.CourseSemester";
		return(insertStudentStatement);
	}
	
	public String totalgrade(int id){
		String insertStudentStatement = "SELECT STUDENTS_RegistrationNumber,avg(GradeCourseStudent) as kati\n" + 
				" FROM COURSE_has_STUDENTS\n" + 
				" WHERE STUDENTS_RegistrationNumber="+id;
		return(insertStudentStatement);
	}

}
