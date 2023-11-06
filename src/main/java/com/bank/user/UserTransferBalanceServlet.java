package com.bank.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.GetCon;
import com.bank.bean.User;
import com.bank.dao.UserDao;

@WebServlet("/UserTransferBalanceServlet")
public class UserTransferBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String emailid = (String) session.getAttribute("emailid");
			
		String targetAccountNoStr = String.valueOf(session.getAttribute("taccountno"));
		long targetAccountNo = Long.parseLong(targetAccountNoStr);

		String amountStr = (String) session.getAttribute("amount");
		BigDecimal amount = new BigDecimal(amountStr);

		Connection con = null;
		PreparedStatement psUpdate = null;
		PreparedStatement psSelectByEmailId = null;
		PreparedStatement psSelectByAccountNo = null;

		boolean userExists = false;
		BigDecimal senderBalance = new BigDecimal(0);
		BigDecimal receiverBalance = new BigDecimal(0);

		userExists = UserDao.isUserExists(emailid);

		if (userExists) {
			User user = null;
			user = UserDao.getUserByEmailId(emailid);

			if (user != null) {
				try {
					con = GetCon.getCon();
					String selectQueryByEmailId = "SELECT amount FROM NEWACCOUNT WHERE emailid=?";
					psSelectByEmailId = con.prepareStatement(selectQueryByEmailId);
					psSelectByEmailId.setString(1, emailid);

					ResultSet resultSet = psSelectByEmailId.executeQuery();
					while (resultSet.next()) {
						senderBalance = resultSet.getBigDecimal("amount");
					}
					String selectQueryByAccountNo = "SELECT amount FROM NEWACCOUNT WHERE accountno=?";
					psSelectByAccountNo = con.prepareStatement(selectQueryByAccountNo);
					psSelectByAccountNo.setLong(1, targetAccountNo);
					resultSet = psSelectByAccountNo.executeQuery();
					while (resultSet.next()) {
						receiverBalance = resultSet.getBigDecimal("amount");
					}
					
					System.out.println(senderBalance+" "+receiverBalance+" "+amount);
					if (senderBalance.compareTo(amount) ==	1) {
						UserDao.updateBalancesAndCommit(con, psUpdate, user.getAccountno(), targetAccountNo, senderBalance,
								receiverBalance, amount);
						/*
						 * RequestDispatcher dispatcher =
						 * request.getRequestDispatcher("userDashboard.jsp");
						 * dispatcher.forward(request, response);
						 */
						response.sendRedirect("userDashboard.jsp");
					} else {
						System.out.println("Balance Insufficient");
						RequestDispatcher dispatcher = request.getRequestDispatcher("userTransferBalance1.jsp");
						dispatcher.forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace(); // Handle or log the exception
				} finally {
					
					UserDao.closeResources(con, psUpdate,psSelectByEmailId,psSelectByAccountNo);
				}
			}
		}
	}


}
