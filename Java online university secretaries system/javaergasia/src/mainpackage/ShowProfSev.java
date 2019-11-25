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
import javax.sql.DataSource;

/**
 * Servlet implementation class ShowProfSev
 */
@WebServlet("/ShowProfSev")
public class ShowProfSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Secretaries secretaries=new Secretaries();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProfSev() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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
		out.println("<head><title>Εμφάνιση καθηγητών</title></head>");
		out.println("<body bgcolor=black>");

		try {
			Connection con = datasource.getConnection();
			out.println("<table align=center bgcolor=goldenrod border=\"10\"bordercolor=darkred >");
			out.println("<tr>");
			out.println("<th style=color:#505050;><u>Όνομα</u></th>");
			out.println("<th style=color:#505050;><u>Επώνυμο</u></th>");
			out.println("<th style=color:#505050;><u>Μάθημα</u></th>");
			out.println("<th style=color:#505050;><u>Εξάμηνο</u></th>");
			out.println("</tr>");  
			PreparedStatement showStudent = con.prepareStatement(secretaries.provmath());
			ResultSet rs = showStudent.executeQuery();
			while(rs.next()) {
				String name = rs.getString("Name");
				String surName = rs.getString("Surname");
				String CourseTitle = rs.getString("CourseTitle");
				String CourseSemester = rs.getString("CourseSemester");
				String htmlRow = createHTMLRow(name,surName,CourseTitle,CourseSemester);
				out.println(htmlRow);
			}
			
			rs.close();
			out.println("<a href=\"/javaergasia/Show.jsp\">Επιστροφή</a>");

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
		doGet(request, response);
	}
	private String createHTMLRow(String name, String surName, String courseTitle,String CourseSemester) {
		
		String row = "<tr>";
		
		row  += "<td style=color:black; >" + name + " "+ "</td>";
		row  += "<td style=color:black; >" + surName +" "+ "</td>";
		row  += "<td style=color:black; >" + courseTitle+ " " + "</td>";
		row  += "<td style=color:black; >" + CourseSemester+ " " + "</td>";
		
		row +="</tr>";
		
		return row;
		
		

	}
}
