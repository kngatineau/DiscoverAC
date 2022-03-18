package com.group3.capstone.servlets;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.dao.ApplicationDao;
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
    UserSession session = null;
    UUID sessionId = null;
	User user = null;
	String page = null;
	ApplicationService appDB = new ApplicationDaoProxy();
	//ApplicationDao appDB = new ApplicationDao();
	
	
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
//		//Temporary Face
		page = getHTMLString(request.getServletContext().getRealPath("/dashboard.html"));
//		response.getWriter().write(page);
		
		//User Entry Logic
		Writer writer = response.getWriter();
		
		// Check if sessionId is in an appropriate format.
		Boolean sessionIdWrongFormat = false;

		try {			
			sessionId = UUID.fromString(request.getParameter("session"));
			System.out.println("Session Id: "+ sessionId.toString());
			
		} catch (IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
			sessionIdWrongFormat = true;
		}
		
		if (request.getParameter("profile") != null) {
			
			response.sendRedirect("profile?session=" + sessionId);
		} else 
		
		// If sessionId is in in an inappropriate format, prompt user to login appropriately.
		if (sessionIdWrongFormat) {
			response.sendRedirect("login");
			
		} else {
			// Check if user session created from login page.
			Boolean sessionDoesNotExist = false;
			

			sessionDoesNotExist = appDB.verifySession(sessionId);
			if (!sessionDoesNotExist) {
				System.out.println("User session does not exist");
				// Redirect to login.
				response.sendRedirect("login");
				
			} else {
				// Display Dashboard page if legitimate user session
				System.out.println("User sesion exists");
				session = appDB.getSession(sessionId);
				user = session.getUser();
	
				
				page = getHTMLString(request.getServletContext().getRealPath("/dashboard.html"));
				page = MessageFormat.format(page, user.getFirstName(), user.getLastName());
				
				try {
					// Write bulletin posts to page if posts exist.
					page += populatePosts(appDB.getBulletinPosts(bulletinId));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				writer.write(page);
				writer.write(" <form method=\"get\">\r\n"
						+ "    <button type=\"submit\" name=\"profile\">My Profile</button>\r\n"
						+ " </form>");
				
			}

		}
		
	}
	
    public String populatePosts(List<Post> posts) throws SQLException{
    
    	String htmlResults = "<h4 style='text-align:center; font-size: 25px;'>"
    			+ appDB.getBulletin(bulletinId).getBulletinName() +" Bulletin Board:</h4>"
    			+ "<table style='border-collapse: collapse;'>"
        		+ "        <thead>\n"
        		+ "            <tr>\n"
        		+ "                <th>Title</th>\n"
        		+ "                <th>Description</th>\n"
        		+ "                <th>URL</th>\n"
        		+ "                <th>Post Date</th>\n"
        		+ "                <th>Author</th>\n"
        		+ "            </tr>\n"
        		+ "        </thead>\n";
    	User user = null;
    	
    	
    	for(Post post: posts) {
    		user = appDB.getUser(post.getAuthorId());
    		htmlResults += "<tr style='height:100px; background-color:#ccedc5; "
    				+ "border-bottom: 10px solid white;'>\n"
             		+ "			<td style='padding-right:20px;''>"+ post.getTitle()+"</td>\n"
            		+ "			<td style='width:300px; padding-right:20px;'>"
             		+ post.getDescription()+"</td>\n"
            		+ "         <td style='padding-right:20px;'>"
            		+ "  			<a href=\""+ post.getUrl()+"\">"+post.getUrl()+"</a>"
             		+ "			</td>\n"
             		+ "         <td style='padding-right:20px;'>"+ post.getPostDate().toString()+"</td>\n"
                    + "         <td style='padding-right:20px;'>"+ user.getUserName()+"</td>\n"
            		+ "</tr>";
    	}
    	
        // Finally, add the closing html:
        htmlResults += "        </tbody></table>";
    	
    	return htmlResults;
    	
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
