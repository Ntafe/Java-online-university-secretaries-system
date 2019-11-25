 package mainpackage;

public class Professors {

	public Professors() {
		// TODO Auto-generated constructor stub
	}
	public String showlessongrades(int id){
		String showThisStudentStatement = "SELECT COURSE.CourseTitle,COURSE_has_STUDENTS.STUDENTS_RegistrationNumber,COURSE_has_STUDENTS.GradeCourseStudent \n" + 
				"FROM COURSE_has_STUDENTS,COURSE \n" + 
				"WHERE COURSE.PROFESSORS_AFM='"+id+"' and COURSE_has_STUDENTS.COURSE_idCOURSE=COURSE.idCOURSE and COURSE_has_STUDENTS.GradeCourseStudent is not null";

		return(showThisStudentStatement);
	}
	
	public String setgrades(int id){
		String insertStudentStatement = "UPDATE COURSE_has_STUDENTS,COURSE SET GradeCourseStudent=? WHERE COURSE_idCOURSE=? and STUDENTS_RegistrationNumber=? and COURSE.idCOURSE=? and COURSE.PROFESSORS_AFM='"+id+"' and GradeCourseStudent is null" ;
		return(insertStudentStatement);
	}
	public String nogradestudents(int id){
		String showThisStudentStatement = "SELECT COURSE.CourseTitle,COURSE_has_STUDENTS.STUDENTS_RegistrationNumber,COURSE_has_STUDENTS.COURSE_idCOURSE \n" + 
				"FROM COURSE_has_STUDENTS,COURSE \n" + 
				"WHERE COURSE.PROFESSORS_AFM='"+id+"' and COURSE_has_STUDENTS.COURSE_idCOURSE=COURSE.idCOURSE and COURSE_has_STUDENTS.GradeCourseStudent is null";

		return(showThisStudentStatement);
	}
}
