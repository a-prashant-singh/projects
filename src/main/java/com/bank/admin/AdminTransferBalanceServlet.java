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

@WebServlet("/AdminTransferBalanceServlet")
public class AdminTransferBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminTransferBalanceServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long accountNo = Long.parseLong(request.getParameter("accountno"));
        long targetAccountNo = Long.parseLong(request.getParameter("taccountno"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        boolean senderExists = UserDao.isUserExistsByAccountno(accountNo);
        boolean receiverExists = UserDao.isUserExistsByAccountno(targetAccountNo);

        if (senderExists && receiverExists) {
            try (Connection con = GetCon.getCon();
                 PreparedStatement selectSenderStmt = con.prepareStatement("SELECT amount FROM NEWACCOUNT WHERE accountno=?");
                 PreparedStatement selectReceiverStmt = con.prepareStatement("SELECT amount FROM NEWACCOUNT WHERE accountno=?");
                 PreparedStatement updateSenderStmt = con.prepareStatement("UPDATE NEWACCOUNT SET amount=? WHERE accountno=?");
                 PreparedStatement updateReceiverStmt = con.prepareStatement("UPDATE NEWACCOUNT SET amount=? WHERE accountno=?")) {

                con.setAutoCommit(false);

                selectSenderStmt.setLong(1, accountNo);
                ResultSet senderResultSet = selectSenderStmt.executeQuery();

                selectReceiverStmt.setLong(1, targetAccountNo);
                ResultSet receiverResultSet = selectReceiverStmt.executeQuery();

                BigDecimal senderBalance = BigDecimal.ZERO;
                BigDecimal receiverBalance = BigDecimal.ZERO;

                if (senderResultSet.next() && receiverResultSet.next()) {
                    senderBalance = senderResultSet.getBigDecimal("amount");
                    receiverBalance = receiverResultSet.getBigDecimal("amount");

                    if (senderBalance.compareTo(amount) >= 0) {
                        senderBalance = senderBalance.subtract(amount);
                        receiverBalance = receiverBalance.add(amount);

                        updateSenderStmt.setBigDecimal(1, senderBalance);
                        updateSenderStmt.setLong(2, accountNo);
                        updateReceiverStmt.setBigDecimal(1, receiverBalance);
                        updateReceiverStmt.setLong(2, targetAccountNo);

                        int rowsUpdatedSender = updateSenderStmt.executeUpdate();
                        int rowsUpdatedReceiver = updateReceiverStmt.executeUpdate();

                        if (rowsUpdatedSender > 0 && rowsUpdatedReceiver > 0) {
                            con.commit();
                            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
                        } else {
                            con.rollback();
                            request.getRequestDispatcher("adminTransferBalance.jsp").forward(request, response);
                        }
                    } else {
                        con.rollback();
                        // Handle insufficient balance
                        request.getRequestDispatcher("adminTransferBalance.jsp").forward(request, response);

                        // You can redirect to an error page or provide an error message.
                    }
                } else {
                    con.rollback();
                    // Handle the case where the user does not exist
                    request.getRequestDispatcher("adminTransferBalance.jsp").forward(request, response);

                    // You can redirect to an error page or provide an error message.
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately (e.g., log it or display an error message).
            }
        } else {
            // Handle the case where either the sender or receiver does not exist
            request.getRequestDispatcher("adminTransferBalance.jsp").forward(request, response);

            // You can redirect to an error page or provide an error message.
        }
    }
}
