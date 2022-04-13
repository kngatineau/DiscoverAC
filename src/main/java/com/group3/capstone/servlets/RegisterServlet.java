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

import com.group3.capstone.dao.ApplicationDaoProxy;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationService registerDB = new ApplicationDaoProxy();
	//ApplicationDao registerDB = new ApplicationDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Updated below to use JSP approach.
//		String page = getHTMLString(request.getServletContext().getRealPath("/register.jsp"));
//		response.getWriter().write(page);
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

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

		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("ac_email");
		//changed for style purposes, if we can't trust user to input AC email we can change back
		//String email = request.getParameter("ac_email")+"@algonquinlive.com";		

		boolean userNameExists = registerDB.verifyUserName(userName);
		boolean userEmailExists = registerDB.verifyUserEmail(email);
		request.setAttribute("userNameExists", userNameExists);
		request.setAttribute("userEmailExists", userEmailExists);
		
		if (userNameExists|userEmailExists) {
			System.out.println("user already exists");
//			String page = getHTMLString(request.getServletContext().getRealPath("/register.html"));
			
			//Notify user
//			if (userNameExists) page += "<h3 style=\"margin:auto; text-align:center;color:red\">UserName already exists.</h3>";
//			if (userEmailExists) page += "<h3 style=\"margin:auto; text-align:center;color:red\">An account already exists with that email address.</h3>";			
//			page += "<h3 style=\"margin:auto; text-align:center;color:red\">Please try again!</h3>";
//			PrintWriter writer = response.getWriter();
//			writer.write(page);
			request.getRequestDispatcher("register.jsp").forward(request, response);

			
		} else {
			System.out.println("New user registration approved.");
			user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setUserName(userName);
			user.setEmail(email);
			
			// create new user
			registerDB.createUser(user);
			System.out.println("New user registered.");

			// Pass user info to session
			request.getSession().setAttribute("user", user);
			
			// send to dashboard page
			response.sendRedirect("dashboard");
			
//			// confirm new user in database
//			if (registerDB.verifyUser(user.getUserID())) {
				
//				//Start new User Session to redirect user to dashboard page
//				UserSession session = new UserSession();
//				session.setUser(user);
				
//				registerDB.createSession(session);
				
				// send to dashboard page
//				response.sendRedirect("dashboard?session="+session.getSessionId().toString());
		
//			} else {
//			
//			System.out.println("Database failure - new user not in database.");
//			String page = getHTMLString(request.getServletContext().getRealPath("/index.html"));
//			
//			//Notify user
//			page += "<h3 style=\"margin:auto; text-align:center;color:red\">System failure. Incident logged. \nAdmin will contact you at your Algonquin College email address.</h3>";
//			PrintWriter writer = response.getWriter();
//			writer.write(page);
//			}		

		}

	}


	
}
