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

import com.group3.capstone.beans.AdminRole;
import com.group3.capstone.beans.SignedUserRole;
import com.group3.capstone.beans.User;
import com.group3.capstone.dao.ApplicationDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/")
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
		//String page = getHTMLString(request.getServletContext().getRealPath("/register.html"));
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

		ApplicationDao AdminRole = new ApplicationDao();
		
//		AdminRole.createUser(user);
		
		boolean permissionGranted = AdminRole.verifyUser(userName, password);
		
		if (permissionGranted) {
			System.out.println("User authentication approved");
			user = AdminRole.getUser(userName);
			
			// Redirect to new servlet instead of rewriting dashboard on the same page.
			response.sendRedirect("dashboard?user="+user.getUserID().toString());
//			String page = getHTMLString(request.getServletContext().getRealPath("/dashboard.html"));
//			response.getWriter().write(page);
			
		}
		else {
			System.out.println("Access Denied");
			String page = getHTMLString(request.getServletContext().getRealPath("/index.html"));
			
			//Notify user, ideally would like alert to user to try again 
			page += "<h3 style=\"margin:auto; text-align:center;color:red\">Wrong username or password. \nPlease try again!</h3>";
			PrintWriter writer = response.getWriter();
			writer.write(page);
		}
		
		//doGet(request, response);
	}

}
