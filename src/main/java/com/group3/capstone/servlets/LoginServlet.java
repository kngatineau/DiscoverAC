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
import javax.servlet.http.HttpSession;

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
		// Old method below, deprecated.
//		String page = getHTMLString(request.getServletContext().getRealPath("/index.jsp"));
//		response.getWriter().write(page);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

		// Below method no longer necessary with JSP approach.
//	public String getHTMLString(String filePath) throws IOException {
//		BufferedReader reader = new BufferedReader(new FileReader(filePath));
//		String line = "";
//		StringBuffer buffer = new StringBuffer();
//		while ((line = reader.readLine()) != null) {
//			buffer.append(line);
//		}
//
//		reader.close();
//		String page = buffer.toString();
//
//		return page;
//	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user = null;
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		user = new User();
		user.setPassword(password);
		user.setUserName(userName);
		
//		UserSession session;
		
		//create proxy to stand in for dao
		ApplicationService loginDB = new ApplicationDaoProxy();

		
		boolean permissionGranted = loginDB.verifyUserNamePassword(userName, password);
		
		if (permissionGranted) {
			System.out.println("User authentication approved");
			user = loginDB.getUser(userName);
				
			// No need to rely on DB to track sessions if passing sessions directly.
//			session = new UserSession();
//			session.setUser(user);
//			
//			loginDB.createSession(session);
			
			//Start new User Session
			HttpSession session = request.getSession();
			

			// Redirect to Dashboard servlet.
//			response.sendRedirect("dashboard?session="+session.getSessionId().toString());
			request.setAttribute("logInError", false);
			session.setAttribute("user", user);
			response.sendRedirect("dashboard");
		}
		else {
			System.out.println("Access Denied");
//			String page = getHTMLString(request.getServletContext().getRealPath("/index.jsp"));
			
			//Notify user, ideally would like alert to user to try again 
//			page += "<h3 style=\"margin:auto; text-align:center;color:red\">Wrong username or password. \nPlease try again!</h3>";
//			PrintWriter writer = response.getWriter();
//			writer.write(page);
			request.setAttribute("logInError", true);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

}
