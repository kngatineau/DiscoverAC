package com.group3.capstone.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.capstone.beans.AdminRole;
import com.group3.capstone.beans.SignedUserRole;
import com.group3.capstone.beans.User;
import com.group3.capstone.dao.ApplicationDao;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	User user = null;
	UUID userId = null;
	String page = null;
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

		//Temporary Face
		String page = getHTMLString(request.getServletContext().getRealPath("/dashboard.html"));
		response.getWriter().write(page);
		
		//User Entry Logic
/*		
		// Check if userID is in an appropriate format.
		Boolean userIdCheck = true;
		try {			
			userId = UUID.fromString(request.getParameter("user"));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			userIdCheck = false;
		}
		
		// If UserID is in in an inappropriate format, prompt user to login appropriately.
		if (!userIdCheck) {
			// Notify inappropriate login.
			page = getHTMLString(request.getServletContext().getRealPath("/wronguser.html"));
//			PreparedStatement statement = prepareStatement(page);
//
//			for (int count=5; count>1; count--) {
//				statement.setString(1, x);
//			}
			// Send user back home after a few seconds.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("");
		} else {
			// Check if user allowed to access bulletin.
			Boolean userExistsCheck = false;
			
			ApplicationDao UserDB = new ApplicationDao();
			userExistsCheck = UserDB.verifyUser(userId);
			if (!userExistsCheck) {
				
			} else {
				// Display Dashboard page if legimate user
				page = getHTMLString(request.getServletContext().getRealPath("/dashboard.html"));
				
			}
		}
		
	}
		
//		
//		
//		// Initial page
//
//		
//		ApplicationDao AdminRole = new ApplicationDao();
//		
//
//		boolean permissionGranted = AdminRole.verifyUser(userName, password);
//		
//		if (permissionGranted) {
//			System.out.println("User authentication approved");
//			
//			// Redirect to new servlet instead of rewriting dashboard on the same page.
//			response.sendRedirect("dashboard");
////			String page = getHTMLString(request.getServletContext().getRealPath("/dashboard.html"));
////			response.getWriter().write(page);
//			
//		}
//		else {
//			System.out.println("Access Denied");
//			String page = getHTMLString(request.getServletContext().getRealPath("/index.html"));
//			//ideally would like alert to user to try again 
//			PrintWriter writer = response.getWriter();
//			writer.write(page);
//		}
//		
//		
//		
//		response.getWriter().write(page);
 */
		
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

}
