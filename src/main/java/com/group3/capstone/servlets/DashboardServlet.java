package com.group3.capstone.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.dao.ApplicationDaoProxy;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//    UserSession session = null;
//    UUID sessionId = null;
	HttpSession session = null;
	User user = null;
	String page = null;
	ApplicationService appDB = new ApplicationDaoProxy();
	
	//Initialize bulletinId with Algonquin College bulletin ID.
	UUID bulletinId = UUID.fromString("930cc92d-8a1f-4ba5-90b6-4373d90d6e22");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dashboard servlet initiated");

		// Below no longer necessary
//		// Check if sessionId is in an appropriate format.
//		Boolean sessionIdWrongFormat = false;
//
//		try {			
//			sessionId = UUID.fromString(request.getParameter("session"));
//			System.out.println("Session Id: "+ sessionId.toString());
//			
//		} catch (IllegalArgumentException | NullPointerException e) {
//			try {
//				sessionId = session.getSessionId();
//			} catch (IllegalArgumentException | NullPointerException f) {
//			e.printStackTrace();
//			sessionIdWrongFormat = true;
//		}
//		}
		session = request.getSession();
		user = (User) session.getAttribute("user");
	
		//send to profile page if profile button clicked
		if (request.getParameter("profile") != null) {
			
			// Below Updated to JSP approach that do not rely on passiong SessionId.
//			response.sendRedirect("profile?session=" + sessionId);
			request.setAttribute("user", user);
			response.sendRedirect("profile");
			
		} 
		
		
		else 
		
		// If sessionId is in in an inappropriate format, prompt user to login appropriately.
		if (user == null) {
			request.setAttribute("logInError", true);
			response.sendRedirect("login");
			
		} else {
//			// Check if user session created from login page.
//			Boolean sessionDoesNotExist = false;
//			
//
//			sessionDoesNotExist = appDB.verifySession(sessionId);
//			if (!sessionDoesNotExist) {
//				System.out.println("User session does not exist");
//				// Redirect to login.
//				response.sendRedirect("login");
//				
//				
//			} else {
			
			// Display Dashboard page if legitimate user session
			System.out.println("User session exists");
//			session = appDB.getSession(sessionId);
//			user = session.getUser();
//			UUID sessionIdToPass = session.getSessionId();
			
			Bulletin bulletin = appDB.getBulletin(bulletinId);
			List<Post> posts = appDB.getBulletinPosts(bulletinId);
			
			Map<String, String> authors = new HashMap<>();
			User author;
	    	for(Post post: posts) {
	    		author = appDB.getUser(post.getAuthorId());
	    		authors.put(post.getPostId().toString(), author.getUserName());
	    		System.out.println(">");
	    	}
			
	    	// Pass Dashboard page objects to request object.
			session.setAttribute("user", user);
			request.setAttribute("bulletin", bulletin);
			request.setAttribute("posts", posts);
			request.setAttribute("authors", authors);
			//for logout function
//			request.setAttribute("session", sessionIdToPass);				
			
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
				
				
//			}

		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if add a post button clicked
		if (request.getParameter("addPost") != null) {
			
			response.sendRedirect("addPost");
			} 
	}

}
