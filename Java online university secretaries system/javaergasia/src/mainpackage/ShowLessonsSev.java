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
 * Servlet implementation class ShowLessonsSev
 */
@WebServlet("/ShowLessonsSev")
public class ShowLessonsSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Secretaries secretaries=new Secretaries();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLessonsSev() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Σύνολο μαθημάτων</title></head>");
		out.println("<body>");

		try {
			Connection con = datasource.getConnection();
	    
		    int count=0;
		    PreparedStatement showStudent = con.prepareStatement(secretaries.sunmath());
			ResultSet rs = showStudent.executeQuery();
			while(rs.next()) {
				count+=1;
			}
			createDynPage(response, "Το σύνολο των μαθημάτων είναι: "+count);
			rs.close();
        	con.close();
		} catch(Exception e) {
			out.println("Database connection problem");
		}
		  
	}
	private void createDynPage(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Εισαγωγή στοιχείων φοιτητή</title></head>");
		out.println("<body bgcolor=#00000");
		out.println("<p style=color:gold;>" + message + "</p>");
		out.println("<a href=\"/javaergasia/Show.jsp\">Επιστροφή</a>");
		out.println("</body></html>");
	}
}
