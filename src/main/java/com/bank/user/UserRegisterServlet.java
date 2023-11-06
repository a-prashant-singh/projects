package com.bank.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.UserDao;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		HttpSession session=request.getSession();
		session.setAttribute("emailid", emailid);

		// Validate input fields
		if (firstname.isEmpty() || lastname.isEmpty() || emailid.isEmpty() || password.isEmpty() || repassword.isEmpty()
				|| gender == null || dob.isEmpty() || address.isEmpty() || phone.isEmpty()) {
			out.print("All fields are required. Please fill in all the details.");
			RequestDispatcher errorDispatcher = request.getRequestDispatcher("register.html");
			errorDispatcher.include(request, response);
			return;
		}

		if (!isValidEmail(emailid)) {
			out.print("Invalid email format. Please enter a valid email address.");
			RequestDispatcher errorDispatcher = request.getRequestDispatcher("register.html");
			errorDispatcher.include(request, response);
			return;
		}

		// Validate password match
		if (!password.equals(repassword)) {
			out.print("Passwords do not match. Please enter matching passwords.");
			RequestDispatcher passwordMismatchDispatcher = request.getRequestDispatcher("register.html");
			passwordMismatchDispatcher.include(request, response);
			return;
		}

		int status;
		try {
			status = UserDao.register(firstname, lastname, emailid, password, gender, dob, address, phone);
		
		if (status > 0) {
			/*
			 * RequestDispatcher successDispatcher =
			 * request.getRequestDispatcher("userRegisterSuccess.jsp");
			 * successDispatcher.forward(request, response);
			 */
			response.sendRedirect("userRegisterSuccess.jsp");
		} else {
			out.print("Sorry, Registration failed. Please try again later.");
			RequestDispatcher errorDispatcher = request.getRequestDispatcher("index.html");
			errorDispatcher.include(request, response);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		return email.matches(emailRegex);
	}
}
