package com.bank.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.UserDao;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailid = request.getParameter("emailid");
        String password = request.getParameter("password");
        
        boolean userAuthenticate;
		try {
			userAuthenticate = UserDao.userAuthenticate(emailid, password);
		
        
        if (userAuthenticate) {
            HttpSession session = request.getSession();
            session.setAttribute("emailid", emailid);
            session.setAttribute("isLoggedIn", true);
            
            response.sendRedirect("userDashboard.jsp");
        } else {
            response.sendRedirect("login.html?error=invalid");
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   



}
