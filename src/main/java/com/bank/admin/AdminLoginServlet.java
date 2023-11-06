package com.bank.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailid = request.getParameter("emailid");
        String password = request.getParameter("password");
        
        
        
        if (emailid.equals("admin@gmail.com") & password.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("emailid", emailid);
            session.setAttribute("isLoggedIn", true);
            System.out.println(session.getAttribute("isLoggedIn").getClass());
            
            response.sendRedirect("adminDashboard.jsp");
        } else {
            response.sendRedirect("login.html?error=invalid");
        }
    }
    
   



}
