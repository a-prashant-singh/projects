package com.bank.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.GetCon;
import com.bank.dao.UserDao;

@WebServlet("/AdminWithdrawBalanceServlet")
public class AdminWithdrawBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminWithdrawBalanceServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long accountNo = Long.parseLong(request.getParameter("accountno"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        boolean userExists = UserDao.isUserExistsByAccountno(accountNo);

        if (userExists) {
            try (Connection con = GetCon.getCon();
                 PreparedStatement selectStmt = con.prepareStatement("SELECT amount FROM NEWACCOUNT WHERE accountno=?");
                 PreparedStatement updateStmt = con.prepareStatement("UPDATE NEWACCOUNT SET amount=? WHERE accountno=?")) {

                selectStmt.setLong(1, accountNo);
                ResultSet rs = selectStmt.executeQuery();

                BigDecimal userBalance = BigDecimal.ZERO;

                if (rs.next()) {
                    userBalance = rs.getBigDecimal("amount");

                    if (userBalance.compareTo(amount) >= 0) {
                        userBalance = userBalance.subtract(amount);
                        updateStmt.setBigDecimal(1, userBalance);
                        updateStmt.setLong(2, accountNo);
                        int rowsUpdated = updateStmt.executeUpdate();

                        if (rowsUpdated > 0) {
                            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
                        }
                    } else {
                        // Handle insufficient balance
                        request.getRequestDispatcher("adminWithdraw.jsp").forward(request, response);

                        // You can redirect to an error page or provide an error message.
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately (e.g., log it or display an error message).
            }
        } else {
            // Handle the case where the user does not exist.
            request.getRequestDispatcher("adminWithdraw.jsp").forward(request, response);

            // You can redirect to an error page or provide an error message.
        }
    }
}
