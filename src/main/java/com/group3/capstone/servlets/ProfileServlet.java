package com.group3.capstone.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationService dao = new ApplicationDaoProxy();
//	UserSession session;
	HttpSession session = null;
	User user;
	UUID sessionId = null;
	String message = "";
	String SID;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// if the Submit button is pressed under the 'Edit' form
		if (request.getParameter("uname") != null) {
			updateUserInfo(request, response);
//			session = dao.getSession(sessionId);
//			response.sendRedirect("profile?session="+ session.getSessionId().toString());
//			response.sendRedirect("profile");
			request.getRequestDispatcher("/profile.jsp").forward(request, response);
		}
		session = request.getSession();
//		// sets sessionId to equal the session parameter if not null
//		if (request.getParameter("session") != null) {
//			sessionId = UUID.fromString(request.getParameter("session"));
//			SID = sessionId.toString();
//		}
		
//		User user = dao.getSession(sessionId).getUser();
		User user = (User) session.getAttribute("user");
		
		session.setAttribute("user", user);
//		request.setAttribute("session", SID);
						
		request.getRequestDispatcher("/profile.jsp").forward(request, response);
		
		// No longer necessary, converted to JSP
//		String page = getHTMLString(request.getServletContext().getRealPath("/profile.jsp"));
//		page = MessageFormat.format(page, message);
//		response.getWriter().write(page);
//		response.getWriter().write(printUserInfo(request, response, sessionId));
//	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		sessionId = UUID.fromString(request.getParameter("session"));
//		session = dao.getSession(sessionId);
		session = request.getSession();
		
//		User user = dao.getSession(sessionId).getUser();
		User user = (User) session.getAttribute("user");
		
		String userName = user.getUserName();
		String currentPassword = request.getParameter("currentPass");
		String newPassword = request.getParameter("newPass");
		String confirmPassword = request.getParameter("confirmPass");
		
		boolean permissionGranted = dao.verifyUserNamePassword(userName, currentPassword);
		
		
		// if the 'current password' entry matches the password on file
		if (permissionGranted) {

			// if the two 'new password' entries match
			if (newPassword.equals(confirmPassword)) {
			dao.updateUserPassword(user, newPassword);
			message = "<h3 style=\"margin:auto; text-align:center;color:green\">"
					+ "Password changed successfully.</h3>";
			
//			response.sendRedirect("profile?session="+ session.getSessionId().toString());
			}
			else { // error for non-matching 'new password' entries
				message = "<h3 style=\"margin:auto; text-align:center;color:red\">"
						+ "Passwords do not match.</h3>";
				request.setAttribute("name", "changePass");
//				response.sendRedirect("profile?session="+ session.getSessionId().toString() + "&name=changePass");
			}
		} else { // error for incorrect 'current password'
			message = "<h3 style=\"margin:auto; text-align:center;color:red\">"
					+ "Incorrect password.</h3>";

			request.setAttribute("name", "changePass");
//			response.sendRedirect("profile?session="+ session.getSessionId().toString() + "&name=changePass");
		}
//		request.setAttribute("session", session.getSessionId().toString());
		request.setAttribute("message", message);
		request.getRequestDispatcher("/profile.jsp").forward(request, response);
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

//	protected String printUserInfo(HttpServletRequest request, HttpServletResponse response, UUID sessionId) {
//		session = dao.getSession(sessionId);
//		User user = dao.getSession(sessionId).getUser();
//		String userInfo;
//		message = "";
//		// prints if the 'Edit' link is clicked
//		if (request.getParameter("name") != null && request.getParameter("name").equals("edit")) {
//			userInfo =  "<div style='margin: auto; text-align: center;'><form method=\"get\"><table style='margin: auto; text-align: center;'>"
//					+ "<tr><td><label for=\"fname\">First Name: </label></td><td><input type=\"text\" id=\"fname\" name=\"fname\" value='" 
//					+ user.getFirstName() + "'></td></tr>"
//					+ "<tr><td><label for=\"fname\">Last Name: </label></td><td><input type=\"text\" id=\"lname\" name=\"lname\" value='" 
//					+ user.getLastName() + "'></td></tr>"
//					+ "<tr><td><label for=\"uname\">Username: </label></td><td><input type=\"text\" id=\"uname\" name=\"uname\" value='" 
//					+ user.getUserName() + "'></td></tr><td></table>" 
//					+ user.getEmail() 
//					+ "</br><a href='profile?session=" + SID + "&name=changePass'>Change Password</a></br>"
//					+ "</br><input type=\"submit\" value=\"Submit\">"
//					+ "</form>"
//					+ "<form action='profile?session=" + SID + "'>"
//					+ "<input type=\"submit\" value=\"Back to Profile\"></form></div>\r\n"; 
//		// prints if the 'change password' link is clicked
//		} else if (request.getParameter("name") != null && request.getParameter("name").equals("changePass")) { 
//			userInfo =  "<div style='margin: auto; text-align: center;'><form method=\"post\">"
//					+ "<table style='margin: auto; text-align: center;'>"
//					+ "<tr><td><label for=\"currentPass\">Current Password: </label></td><td><input type=\"text\" id=\"currentPass\" name=\"currentPass\"><td></tr>"
//					+ "<tr><td><label for=\"newPass\">New Password: </label></td><td><input type=\"text\" id=\"newPass\" name=\"newPass\"><td></tr>"
//					+ "<tr><td><label for=\"confirmPass\">Confirm Password: </label></td><td><input type=\"text\" id=\"confirmPass\" name=\"confirmPass\"><td></tr>"
//					+ "</table></br><input type=\"submit\" value=\"Submit\">"
//					+ "</form>"
//					+ "<form action='profile?session=" + SID + "'>"
//					+ "<input type=\"submit\" value=\"Back to Profile\"></form></div>\r\n"; 
//			
//			// prints profile information otherwise
//		} else {
//		userInfo = "<div style='margin: auto; width: 300px; text-align: center'>"
//				+ user.getFirstName() + " " + user.getLastName()
//				+ "</br>" +user.getUserName()
//				+ "</br>" + user.getEmail()
//				+ "</br><a href='profile?session=" + SID + "&name=edit'>Edit</a>"		
//				+ "<form method='get' action=\"dashboard?session=" + SID + "\">"
//						+ "</br><input type=\"submit\" value=\"Back to Bulletin Board\"></form>\r\n"
//				+ "</div>";
//		}
//		return userInfo;
//		
//	} 
	protected void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// method called when 'Submit' is pressed using the 'Edit' form

		// User retrieval now from session object rather than DB.
//		User user = dao.getSession(sessionId).getUser();
		User user = (User) request.getSession().getAttribute("user");
		user.setUserName(request.getParameter("uname"));
		user.setFirstName(request.getParameter("fname"));
		user.setLastName(request.getParameter("lname"));
		dao.updateUser(user);
	}
	
}
