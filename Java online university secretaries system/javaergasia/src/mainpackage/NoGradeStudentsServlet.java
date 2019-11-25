package mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class NoGradeStudentsServlet
 */
@WebServlet("/NoGradeStudentsServlet")
public class NoGradeStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoGradeStudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private Professors professors=new Professors();
    private DataSource datasource = null;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Students With No Grades</title></head>");
		out.println("<body bgcolor=black>");
		HttpSession session = request.getSession();
		String str = session.getAttribute("mitrwo").toString();
		try {
			Connection con = datasource.getConnection();
			
			PreparedStatement showStudent = con.prepareStatement(professors.nogradestudents(Integer.parseInt(str)));
			ResultSet rs = showStudent.executeQuery();
			int count=0;
			while(rs.next()) {
				count=count +1;
				if (count==1) {
					out.println("<table align=center bgcolor=goldenrod border=\"10\"bordercolor=darkred >");
					out.println("<tr>");
					out.println("<th style=color:#505050;><u>Αριθμός Μητρώου Μαθήματος</u></th>");
					out.println("<th style=color:#505050;><u>Μάθημα</u></th>");
					out.println("<th style=color:#505050;><u>Αριθμός Μητρώου Φοιτητη</u></th>");
					out.println("</tr>");  
				}
				String courseid = rs.getString("COURSE_has_STUDENTS.COURSE_idCOURSE");
				String STUDENTS_RegistrationNumber = rs.getString("COURSE_has_STUDENTS.STUDENTS_RegistrationNumber");
				String CourseTitle = rs.getString("COURSE.CourseTitle");
				String htmlRow = createHTMLRow(CourseTitle,STUDENTS_RegistrationNumber,courseid);
				out.println(htmlRow);
			}
			if (count==0) 
			{
				out.println("<p style=color:goldenrod;>Δεν υπάρχουν μαθητές χωρίς βαθμολογία</p>");

			}
			rs.close();
			out.println("<a href=\"/javaergasia/prof.jsp\">Επιστροφή</a>");

			con.close();
		} catch(Exception e) {
			out.println("Database connection problem");
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
private String createHTMLRow(String CourseTitle,String STUDENTS_RegistrationNumber,String courseid) {
		
		String row = "<tr>";
		
		row  += "<td style=color:black; >" + courseid+ " " + "</td>";
		row  += "<td style=color:black; >" + CourseTitle + " "+ "</td>";
		row  += "<td style=color:black; >" + STUDENTS_RegistrationNumber+ " " + "</td>";
		
		row +="</tr>";
		
		return row;
		
		

	}
	
}
