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
 * Servlet implementation class TotalGrade
 */
@WebServlet("/TotalGrade")
public class TotalGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private Students students=new Students();
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
		out.println("<head><title>LessonGrade</title></head>");
		out.println("<body bgcolor=black>");
		HttpSession session = request.getSession();
		String str = session.getAttribute("mitrwo").toString();
		try {
			Connection con = datasource.getConnection();
			out.println("<table align=center bgcolor=goldenrod border=\"10\"bordercolor=darkred >");
			out.println("<tr>");
			out.println("<th style=color:#505050;><u>Αριθμός Μητρώου</u></th>");
			out.println("<th style=color:#505050;><u>Συνολικός Μέσος Όρος</u></th>");
			out.println("</tr>");  
			PreparedStatement showStudent = con.prepareStatement(students.totalgrade(Integer.parseInt(str)));
			ResultSet rs = showStudent.executeQuery();
			while(rs.next()) {
				
				String GradeCourseStudent = rs.getString("COURSE_has_STUDENTS.STUDENTS_RegistrationNumber");
				String CourseSemester2 = rs.getString("kati");
				String htmlRow = createHTMLRow(GradeCourseStudent,CourseSemester2);
				out.println(htmlRow);
			}
			
			rs.close();
			out.println("<a href=\"/javaergasia/foithtes.jsp\">Επιστροφή</a>");

			con.close();
		} catch(Exception e) {
			out.println("Database connection problem");
		}
	}
	
private String createHTMLRow(String GradeCourseStudent,String CourseSemester2) {
		
		String row = "<tr>";
		
		row  += "<td style=color:black; >" + GradeCourseStudent + " "+ "</td>";
		row  += "<td style=color:black; >" + CourseSemester2+ " " + "</td>";
		
		row +="</tr>";
		
		return row;
		
		

	}

}
