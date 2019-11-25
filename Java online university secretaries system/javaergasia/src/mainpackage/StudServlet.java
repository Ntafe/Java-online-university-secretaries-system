package mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Servlet implementation class StudServlet
 */
@WebServlet("/StudServlet")
public class StudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SALT = "my-salt-text";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DataSource datasource = null;
	private egrafes egr=new egrafes();

	public void init(ServletConfig config) throws ServletException {
		
		try {
			
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}
	}
    public StudServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String str = request.getParameter("mitrwo");
		HttpSession session = request.getSession();
		session.setAttribute("mitrwo",str);
		
		String requestType= request.getParameter("requestType");
		if (requestType == null) {
			createDynPage(response, "Invalid request type");
		}
		
		if (requestType.equalsIgnoreCase("insert")) {
			int flag=0;
			String mitrwo = request.getParameter("mitrwo");
			
			String password = request.getParameter("password");
			
			
			
			
			try {
				Connection con = datasource.getConnection();
				
			    Statement stmt = con.createStatement();
			   
			    ResultSet rs = stmt.executeQuery("SELECT RegistrationNumber,password FROM STUDENTS");
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    while(rs.next()) {
			    	String saltedPassword = SALT + password;
					String hashedPassword = egr.generateHash(saltedPassword);
					String am = rs.getString("RegistrationNumber");
					String pass = rs.getString("password");
					if (mitrwo.equals(am)  & hashedPassword.equals(pass)) {
							flag=1;
					}

				}
				rs.close();
			    
			    
			    if (flag==1) {
			    	response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					response.sendRedirect("/javaergasia/foithtes.jsp"); 
				
			    }
			    if (flag==0) {
			    	createDynPage(response, "Αποτυχής Σύνδεση!");
			    }
			    
				stmt.close();
			
				con.close();
				
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			
		} else {
			createDynPage(response, "The request type parameter must be insert");
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
		out.println("<a href=\"/javaergasia/index.jsp\">Επιστροφή στην αρχική σελίδα</a>");
		out.println("</body></html>");
	}


}
