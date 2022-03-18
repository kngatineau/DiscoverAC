package com.group3.capstone.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.capstone.dao.ApplicationDao;
import com.group3.capstone.dao.ApplicationDaoProxy;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = getHTMLString(request.getServletContext().getRealPath("/index.html"));
		response.getWriter().write(page);
	}

	public String getHTMLString(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		reader.close();
		String page = buffer.toString();

		return page;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user = null;
//		String firstName = request.getParameter("first_name");
//		String lastName = request.getParameter("last_name");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
//		String email = request.getParameter("ac_email");		
		user = new User();
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
		user.setPassword(password);
		user.setUserName(userName);
//		user.setUserID();
//		UUID test = user.getUserID();
//		user.setEmail(email);
		
		UserSession session;
		
		//create proxy to stand in for dao
		ApplicationService loginDB = new ApplicationDaoProxy();
		//ApplicationDao loginDB = new ApplicationDao();
		
//		AdminRole.createUser(user);
		
		boolean permissionGranted = loginDB.verifyUserNamePassword(userName, password);
		
		if (permissionGranted) {
			System.out.println("User authentication approved");
			user = loginDB.getUser(userName);
				
			//Start new User Session
			session = new UserSession();
			session.setUser(user);
			
			loginDB.createSession(session);
			

			// Redirect to Dashboard servlet.
//			response.sendRedirect("dashboard?user="+user.getUserID().toString());
			response.sendRedirect("dashboard?session="+session.getSessionId().toString());
			
		}
		else {
			System.out.println("Access Denied");
			String page = getHTMLString(request.getServletContext().getRealPath("/index.html"));
			
			//Notify user, ideally would like alert to user to try again 
			page += "<h3 style=\"margin:auto; text-align:center;color:red\">Wrong username or password. \nPlease try again!</h3>";
			PrintWriter writer = response.getWriter();
			writer.write(page);
		}

	}

}
