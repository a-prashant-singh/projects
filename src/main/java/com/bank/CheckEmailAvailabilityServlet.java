package com.bank;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.UserDao;

@WebServlet("/CheckEmailAvailabilityServlet")
public class CheckEmailAvailabilityServlet extends HttpServlet {
    // ... other code ...

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        boolean userExists;
       
           
            	userExists = UserDao.isUserExists(email);

            if (userExists) {
                response.getWriter().write("Email already exists");
            } else {
                response.getWriter().write("Email is available");
            }
     
            
        
    }
}
