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
public class editServlet extends HttpServlet {
	Key key=null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		// get access to the user service to get our user
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		key =(Key) req.getSession().getAttribute("update");

		try{
		if (u != null) {
			req.setAttribute("loggedin", 1);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Appointment app = pm.getObjectById(Appointment.class, key);
			req.setAttribute("name", app.getName());
			req.setAttribute("date", app.getDate());
			req.setAttribute("time", app.getTime());
			req.getSession().setAttribute("update",null);


		} else {
			req.setAttribute("loggedin", null);
		}
		}catch(Exception e){resp.sendRedirect("/");}

		String login_url = us.createLoginURL("/");
		req.setAttribute("user", u);
		req.setAttribute("login_url", login_url);

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/edit.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		// get access to the user service to get our user
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();

		String n = req.getParameter("name").trim();
		String d = req.getParameter("date").trim();
		String t = req.getParameter("time").trim();
		if (n.equals("") || d.equals("") || t.equals("")) {
			displayAlert("Invalid Input !", "'/editServlet'", out);
			return;
		}
		String uid = u.getUserId();
		PersistenceManager pm = null;
		Key user_key = KeyFactory.createKey("User", uid);
		com.arjun.app.User user;
		try {
			// Check if the KEY exists...
			pm = PMF.get().getPersistenceManager();
			Appointment ap = pm.getObjectById(Appointment.class, key);
			ap.setName(n);
			ap.setDate(d);
			ap.setTime(t);
			pm.close();
			displayAlert("Appointment updated !", "'/'", out);

		} catch (Exception e) {
			displayAlert("Update Failed !", "'/'", out);
		} finally {
			pm.close();
		}
	}
	private void displayAlert(String msg, String path, PrintWriter out) {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + msg + "');");
		out.println("location=" + path + ";");
		out.println("</script>");
	}

}
