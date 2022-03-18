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

import com.group3.capstone.dao.ApplicationDao;
import com.group3.capstone.user.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationDao dao = new ApplicationDao();
	User user;
	UUID sessionId = null;
	boolean isEdit = false;
	
       
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
		// TODO Auto-generated method stub

		if (request.getParameter("name") != null && request.getParameter("name").equals("\"edit\"")) {
			isEdit = true;
		} else {
			isEdit = false;
		}
		sessionId = UUID.fromString(request.getParameter("session"));
		String page = getHTMLString(request.getServletContext().getRealPath("/profile.html"));
		response.getWriter().write(page);
		response.getWriter().write(printUserInfo(sessionId));
		System.out.println("SESSION ID: " + sessionId);
		

		
	
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

	protected String printUserInfo(UUID sessionID) {
		User user = dao.getSession(sessionId).getUser();
		String userInfo;
		
		if (!isEdit) {
		userInfo = "<div style='margin: auto; width: 300px; text-align: center'>"
				+ "<div style='display: inline-block; list-style-type: none;'"
				+ "<ul style='margin: auto;' >\r\n"
				+ "<li>" + user.getFirstName() + " " + user.getLastName() + "</li>\r\n"
				+ "<li>" + user.getUserName() + "</li>\r\n"
				+ "<li>" + user.getEmail() + "</li>\r\n"
				+ "</ul>"
				+ "<a href='profile?session=" + sessionID + "&name=\"edit\"'>Edit</a></div></div>";
		} else { 
			
		
		userInfo =  "<form>\r\n"
					+ "<ul style='list-style-type: none; margin: auto; text-align: center;'>\r\n"
					+ " <li><label for=\"fname\">First Name: </label><input type=\"text\" id=\"fname\" name=\"fname\" value='" 
					+ user.getFirstName() + "'></li>\r\n"
					+ " <li><label for=\"fname\">Last Name: </label><input type=\"text\" id=\"lname\" name=\"lname\" value='" 
					+ user.getLastName() + "'></li>\r\n"
					+ "   <li><label for=\"uname\">Username: </label><input type=\"text\" id=\"uname\" name=\"uname\" value='" 
					+ user.getUserName() + "'><li>\r\n"
							+ "<li>" + user.getEmail() + "</li>\r\n"
					+ "   <li><input type=\"submit\" value=\"Submit\"></li>\r\n"
					+ "   </ul>\r\n"
					+ "</form>"; 
		}
		return userInfo;
		
	} 
}
