package com.group3.capstone.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationService dao = new ApplicationDaoProxy();
	UserSession session;
	User user;
	UUID sessionId = null;
	String message = "";
       
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
		
		if (request.getParameter("uname") != null) {
			updateUserInfo(request, response);
			session = dao.getSession(sessionId);
			response.sendRedirect("profile?session="+ session.getSessionId().toString());
		}
		
		if (request.getParameter("session") != null) {
			sessionId = UUID.fromString(request.getParameter("session"));
			// SID = sessionId.toString();
		}
		String page = getHTMLString(request.getServletContext().getRealPath("/profile.html"));
		page = MessageFormat.format(page, message);
		response.getWriter().write(page);
		response.getWriter().write(printUserInfo(request, response, sessionId));
		System.out.println("SESSION ID: " + sessionId);	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = dao.getSession(sessionId).getUser();
		String page = getHTMLString(request.getServletContext().getRealPath("/profile.html"));
		String userName = user.getUserName();
		String currentPassword = request.getParameter("currentPass");
		String newPassword = request.getParameter("newPass");
		String confirmPassword = request.getParameter("confirmPass");
		
		boolean permissionGranted = dao.verifyUserNamePassword(userName, currentPassword);
		
		if (permissionGranted) {
			session = dao.getSession(sessionId);
			if (newPassword.equals(confirmPassword)) {
			dao.updateUserPassword(user, newPassword);
			message = "<h3 style=\"margin:auto; text-align:center;color:green\">"
					+ "Password changed successfully.</h3>";
			response.sendRedirect("profile?session="+ session.getSessionId().toString());
			}
			else {
				message = "<h3 style=\"margin:auto; text-align:center;color:red\">"
						+ "Passwords do not match.</h3>";
				response.sendRedirect("profile?session="+ session.getSessionId().toString() + "&name=changePass");
			}
		} else {
			message = "<h3 style=\"margin:auto; text-align:center;color:red\">"
					+ "Incorrect password.</h3>";
			response.sendRedirect("profile?session="+ session.getSessionId().toString() + "&name=changePass");
		}
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

	protected String printUserInfo(HttpServletRequest request, HttpServletResponse response, UUID sessionID) {
		session = dao.getSession(sessionId);
		User user = dao.getSession(sessionId).getUser();
		String userInfo;
		message = "";
		if (request.getParameter("name") != null && request.getParameter("name").equals("edit")) {
			userInfo =  "<div style='margin: auto; text-align: center;'><form method=\"get\"><table style='margin: auto; text-align: center;'>"
					+ "<tr><td><label for=\"fname\">First Name: </label></td><td><input type=\"text\" id=\"fname\" name=\"fname\" value='" 
					+ user.getFirstName() + "'></td></tr>"
					+ "<tr><td><label for=\"fname\">Last Name: </label></td><td><input type=\"text\" id=\"lname\" name=\"lname\" value='" 
					+ user.getLastName() + "'></td></tr>"
					+ "<tr><td><label for=\"uname\">Username: </label></td><td><input type=\"text\" id=\"uname\" name=\"uname\" value='" 
					+ user.getUserName() + "'></td></tr><td></table>" 
					+ user.getEmail() 
					+ "</br><a href='profile?session=" + sessionID + "&name=changePass'>Change Password</a></br>"
					+ "</br><input type=\"submit\" value=\"Submit\">"
					+ "</form>"
					+ "<form method=\"get\" action='profile?session=" + sessionID + "'>"
					+ "<input type=\"submit\" value=\"Back to Profile\"></form></div>\r\n"; 

		} else if (request.getParameter("name") != null && request.getParameter("name").equals("changePass")) { 
			userInfo =  "<div style='margin: auto; text-align: center;'><form method=\"post\">"
					+ "<table style='margin: auto; text-align: center;'>"
					+ "<tr><td><label for=\"currentPass\">Current Password: </label></td><td><input type=\"text\" id=\"currentPass\" name=\"currentPass\"><td></tr>"
					+ "<tr><td><label for=\"newPass\">New Password: </label></td><td><input type=\"text\" id=\"newPass\" name=\"newPass\"><td></tr>"
					+ "<tr><td><label for=\"confirmPass\">Confirm Password: </label></td><td><input type=\"text\" id=\"confirmPass\" name=\"confirmPass\"><td></tr>"
					+ "</table></br><input type=\"submit\" value=\"Submit\">"
					+ "</form>"
					+ "<form method=\"get\" action='profile?session=" + sessionID + "'>"
					+ "<input type=\"submit\" value=\"Back to Profile\"></form></div>\r\n"; 
		} else {

		
		userInfo = "<div style='margin: auto; width: 300px; text-align: center'>"
				+ user.getFirstName() + " " + user.getLastName()
				+ "</br>" +user.getUserName()
				+ "</br>" + user.getEmail()
				+ "</br><a href='profile?session=" + sessionID + "&name=edit'>Edit</a>"		
				+ "<form method=\"get\" action='dashboard?session=" + sessionID + "'>"
						+ "</br><input type=\"submit\" value=\"Back to Bulletin Board\"></form>\r\n"
				+ "</div>";
		}
		return userInfo;
		
	} 
	protected void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = dao.getSession(sessionId).getUser();
		// ApplicationDao dao = new ApplicationDao();
		user.setUserName(request.getParameter("uname"));
		user.setFirstName(request.getParameter("fname"));
		user.setLastName(request.getParameter("lname"));
		dao.updateUser(user);
	}
	
}
