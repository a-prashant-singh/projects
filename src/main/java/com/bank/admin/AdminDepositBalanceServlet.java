package com.bank.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.GetCon;
import com.bank.dao.UserDao;

@WebServlet("/AdminDepositBalanceServlet")
public class AdminDepositBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long accountNo = Long.parseLong(request.getParameter("accountno"));
		BigDecimal depositAmount = new BigDecimal(request.getParameter("amount"));

		
		  if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
		  response.sendRedirect("adminDeposit.jsp"); return;
		  }
		 

		Connection con = null;
		con = GetCon.getCon();
		try {
			
			if (UserDao.isUserExistsByAccountno(accountNo)) {
				BigDecimal userBalance = UserDao.getUserBalance(con, accountNo);
				BigDecimal newBalance = userBalance.add(depositAmount);

				boolean success = UserDao.updateBalance(con, accountNo, newBalance);

				if (success) {
					request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);

				} else {
					request.getRequestDispatcher("adminDeposit1.jsp").forward(request, response);
				}
			}
			else
			{
				System.out.println("user not found");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} 
			
		}
	}


