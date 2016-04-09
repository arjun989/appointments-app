package com.arjun.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class RootServlet extends HttpServlet {
	private String output = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// we need to get access to the google user service
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		String login_url = us.createLoginURL("/");
		String logout_url = us.createLogoutURL("/");
		String addAppointment = "/addServlet";
        //u=null;
		// attach a few things to the request such that we can access them in
		// the jsp
		req.setAttribute("user", u);
		req.setAttribute("login_url", login_url);
		req.setAttribute("logout_url", logout_url);
		req.setAttribute("addAppointment", addAppointment);
		String uid;
		try{
			if (u != null) {
				uid = u.getUserId();
				//req.getSession().setAttribute("user_id", uid);
				//displaying current appointments
				PersistenceManager pm = null;
				Key user_key = KeyFactory.createKey("User", uid);
				com.arjun.app.User user;
				// print out what was stored for each user
				pm = PMF.get().getPersistenceManager();
				user = pm.getObjectById(com.arjun.app.User.class, user_key);
				//resp.getWriter().println("Appointments for user: " + user.getEmail() + " :");
				ArrayList<String> lst=new ArrayList<>();
				for (Appointment ctemp : user.getAppointments()) {
					lst.add("Name: " + ctemp.getName() + "  Date : " + ctemp.getDate() + "  Time : " + ctemp.getTime()+"     ~~~    ");
					//resp.getWriter().println("Name: " + ctemp.getName() + " Date : " + ctemp.getDate() + " Time : " + ctemp.getTime());
				}
				req.setAttribute("appointments", lst);
				
			} 
		}catch(Exception e){
			req.setAttribute("appointments", null);
		}
		//else {
//			req.getSession().setAttribute("user_id", "");
//		}
		
		

		// get a request dispatcher and launch a jsp that will render our page
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/root.jsp");
		rd.forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

}
