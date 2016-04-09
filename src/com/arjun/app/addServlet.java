package com.arjun.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class addServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// we will be outputting html to the client
		resp.setContentType("text/html");

		// get the value of the attribute input from the session and forward it
		// to the // JSP
		String uid = (String) req.getSession().getAttribute("user_id");
		if (uid != null && uid.length() > 0) {
			req.setAttribute("loggedin", 1);
		} else {
			req.setAttribute("loggedin", null);
		}

		// get a request dispatcher and launch a jsp that will render our page
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		String login_url = us.createLoginURL("/");
		req.setAttribute("user", u);
		req.setAttribute("login_url", login_url);

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/add.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	private void displayAlert(String msg, PrintWriter out) {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + msg + "');");
		out.println("location='/';");
		out.println("</script>");
	}

}
