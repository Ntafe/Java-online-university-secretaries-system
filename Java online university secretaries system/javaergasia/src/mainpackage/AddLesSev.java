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
 * Servlet implementation class AddLesSev
 */
@WebServlet("/AddLesSev")
public class AddLesSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLesSev() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private Secretaries admin1=new Secretaries();
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
					String am = request.getParameter("am");
					String title = request.getParameter("title");
					String sem = request.getParameter("sem");
					@SuppressWarnings("unused")
					int iex1=0;
					@SuppressWarnings("unused")
					int iex2=0;
					@SuppressWarnings("unused")
					int iex3=0;
					iex1 = Integer.parseInt(mitrwo);
					iex2 = Integer.parseInt(am);
					iex3 = Integer.parseInt(sem);
				
				Connection con = datasource.getConnection();
				
				PreparedStatement insertStudent = con.prepareStatement(admin1.anathesi());
			    
			   
				insertStudent.setInt(1,Integer.parseInt(mitrwo));
			    insertStudent.setString(2, am);
			    insertStudent.setString(3, title);
			    insertStudent.setString(4, sem);
			    
				    
			    insertStudent.executeUpdate();
			    createDynPage(response, "Επιτυχής ανάθεση μαθήματος");
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
