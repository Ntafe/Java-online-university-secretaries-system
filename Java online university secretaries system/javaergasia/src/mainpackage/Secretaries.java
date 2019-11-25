package mainpackage;



public class Secretaries  {
	
	
	public Secretaries() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String sunmath(){
		String showThisStudentStatement = "SELECT * FROM COURSE";
		return(showThisStudentStatement);
	}
	
	public String provmath(){
		String insertStudentStatement = "SELECT Name, Surname,CourseTitle,CourseSemester  FROM COURSE INNER JOIN PROFESSORS ON COURSE.PROFESSORS_AFM = PROFESSORS.AFM order by CourseSemester";
		return(insertStudentStatement);
	}
	
	public String anathesi(){
		String insertStudentStatement = "INSERT INTO COURSE (PROFESSORS_AFM, idCOURSE,  CourseTitle, CourseSemester) VALUES (?,?,?,?)";
		return(insertStudentStatement);
	}
	
}
