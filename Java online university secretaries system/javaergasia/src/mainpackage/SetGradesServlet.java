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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class SetGradesServlet
 */
@WebServlet("/SetGradesServlet")
public class SetGradesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetGradesServlet() {
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
		HttpSession session = request.getSession();
		String str = session.getAttribute("mitrwo").toString();
		if (requestType == null) {
			createDynPage(response, "Invalid request type");
		}
		
		
					
			try {
				if (requestType.equalsIgnoreCase("insert")) {
					
					String mitrwo = request.getParameter("mitrwo");
					String am = request.getParameter("am");
					String sem = request.getParameter("sem");
					@SuppressWarnings("unused")
					int iex1=0;
					@SuppressWarnings("unused")
					int iex2=0;
					int iex3=0;
					iex1 = Integer.parseInt(mitrwo);
					iex2 = Integer.parseInt(am);
					iex3 = Integer.parseInt(sem);
				if (iex3>=0 && iex3<=10) {
					Connection con = datasource.getConnection();
					
					PreparedStatement insertStudent = con.prepareStatement(professors.setgrades(Integer.parseInt(str)));
				    
				   
					insertStudent.setInt(3,Integer.parseInt(mitrwo));
				    insertStudent.setString(2, am);
				    insertStudent.setString(1, sem);
				    insertStudent.setString(4, am);

				    
				    int count=insertStudent.executeUpdate();
				    if(count>0) {
				    	 createDynPage(response, " Επιτυχής ανάθεση βαθμολογίας!");
				    }
				    else {
				    createDynPage(response, "Λάθος δεδομένα!Μη Επιτυχής ανάθεση βαθμολογίας!");
				    }
					insertStudent.close();
				
					con.close();
				}
				else {
					createDynPage(response, "Λάθος δεδομένα!Μη Επιτυχής ανάθεση βαθμολογίας!");
					}
				
				}
				}
			 catch(Exception SQLE) {
				createDynPage(response, "Λάθος δεδομένα!Μη Επιτυχής ανάθεση βαθμολογίας!");
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
		out.println("<a href=\"/javaergasia/prof.jsp\">Επιστροφή</a>");
		out.println("</body></html>");
	}

}
