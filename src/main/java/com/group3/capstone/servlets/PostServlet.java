package com.group3.capstone.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group3.capstone.beans.Post;
import com.group3.capstone.dao.ApplicationDaoProxy;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/addPost")
public class PostServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//dao object for CRUD ops
	ApplicationService addPostDB = new ApplicationDaoProxy();
//	//for getting user information attached to session
//	UserSession session = null;
//	String SID;
	HttpSession session;
	User user;
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("in do get");
		
//		String page = getHTMLString(request.getServletContext().getRealPath("/newPost.jsp"));
//		response.getWriter().write(page);
//		//get session id to be used by PostServlet doPost method
//		SID = request.getParameter("session");
		request.getRequestDispatcher("newPost.jsp").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get text user inputted to create new post
		String title = request.getParameter("discord_title");
		String description = request.getParameter("discord_desc");
		String url = request.getParameter("discord_url");
		//ensure all fields entered then make new post object
		request.setAttribute("error", false);
		if (title !=null && description !=null && url !=null) {
//			//get session
//			session = addPostDB.getSession(UUID.fromString(SID));
			//get user data from session
//			UUID authorId = session.getUser().getUserID();
			session = request.getSession();
			user = (User) session.getAttribute("user");
			UUID authorId = user.getUserID();
			//get algonquin college bulletin id 
			UUID bulletinId = UUID.fromString("930cc92d-8a1f-4ba5-90b6-4373d90d6e22");
			//get new post Id
			UUID postId = UUID.randomUUID();
			//set post date
			long currentTimeinMS=System.currentTimeMillis();  
			Date postDate = new Date(currentTimeinMS);
			//create new post with new data
			Post post = new Post(postId, title, description, url, postDate, bulletinId, authorId);
			//use dao object to write a new post 
			addPostDB.writePost(post);	
			//send user back to dashboard
//			response.sendRedirect("dashboard?session=" + SID);
			response.sendRedirect("dashboard");
		}else {
			//if not required fields entered
//			String page = getHTMLString(request.getServletContext().getRealPath("/newPost.jsp"));
//			page += "<h3 style=\"margin:auto; text-align:center;color:red\">Please fill out all required fields</h3>";
//			PrintWriter writer = response.getWriter();
//			response.getWriter().write(page);
			request.setAttribute("error", true);
			request.getRequestDispatcher("newPost.jsp").forward(request, response);
		}
	
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

}
