package com.group3.capstone.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.capstone.dao.ApplicationDaoProxy;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	ApplicationService dao = new ApplicationDaoProxy();
//	UserSession session;
//	User user;
//	UUID sessionId = null;
//	String SID;
	
	public LogoutServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session no longer managed by DB, therefore, below unnecessary
//		//sets sessionId to equal the session parameter if not null)
//		//gets sessionId to delete from db
//		if (request.getParameter("session") != null) {
//			sessionId = UUID.fromString(request.getParameter("session"));
//			SID = sessionId.toString();
//			System.out.println(SID);
//		}
//		
//		//boolean to check if delete successfull
//		boolean deleteSuccess = dao.deleteSession(sessionId);
//		
//		if (deleteSuccess) {
		
			System.out.println("user session ended");
		
			// Redirect to log in page servlet.
			response.sendRedirect("index.jsp");
//			
//		} else {
//			
//			System.out.println("Session unable to close");
//			String page = getHTMLString(request.getServletContext().getRealPath("/logOutFooter.jsp"));
//			//Notify user, ideally would like alert to user to try again 
//			page += "<h3 style=\"margin:auto; text-align:center;color:red\">Log out failed. \nPlease try again!</h3>";
//			PrintWriter writer = response.getWriter();
//			writer.write(page);
//			}
//	}
	
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
			
	}
}
	
