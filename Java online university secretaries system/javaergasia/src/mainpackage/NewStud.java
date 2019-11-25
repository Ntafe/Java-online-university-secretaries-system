package mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class NewStud
 */
@WebServlet("/NewStud")
public class NewStud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewStud() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private egrafes eg=new egrafes();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String requestType= request.getParameter("requestType");
		if (requestType == null) {
			createDynPage(response, "Invalid request type");
		}
		
		
					
			try {
				if (requestType.equalsIgnoreCase("insert")) {
					
					String mitrwo = request.getParameter("mitrwo");
					String name = request.getParameter("name");
					String surname = request.getParameter("surname");
					String department = request.getParameter("department");
					String pass = request.getParameter("password");
					
				
				Connection con = datasource.getConnection();
				
				PreparedStatement insertStudent = con.prepareStatement(eg.egfoit(mitrwo,pass));
			    
			    insertStudent.setString(1, name);
			    insertStudent.setString(2, surname);
			    insertStudent.setString(3, department);
				    
			    insertStudent.executeUpdate();
			    createDynPage(response, "Επιτυχής εγγραφή φοιτητή");
				insertStudent.close();
			
				con.close();
			    
				}
				}
			 catch(Exception SQLE) {
				createDynPage(response, "Λάθος δεδομένα!");
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
