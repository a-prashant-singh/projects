package com.bank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.GetCon;
import com.bank.bean.User;

public class UserDao {
	static int status = 0;

	public static int register(String firstname, String lastname, String emailid, String password, String gender,
			String dob, String address, String phone) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = GetCon.getCon();
			String sqlQuery = "INSERT INTO newaccount(accountno, firstname, lastname, emailid, password, gender, dob, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sqlQuery);

			long accountNo = System.currentTimeMillis();
			ps.setLong(1, accountNo);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setString(4, emailid);
			ps.setString(5, password);
			ps.setString(6, gender);
			ps.setString(7, dob);
			ps.setString(8, address);
			ps.setString(9, phone);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
// Registration successful
				return 1;
			} else {
// Registration failed
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log or handle the exception appropriately
			return 0; // Registration failed due to exception
		} finally {
			if (ps != null) {
				ps.close();
			}

		}
	}

	public static List<User> getAllUser() throws SQLException {
		List<User> userList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = GetCon.getCon();
		try {
			
			String sqlQuery = "SELECT * FROM newaccount";
			ps = con.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setAccountno(rs.getLong("accountno"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmailid(rs.getString("emailid"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getString("dob"));
				user.setBalance(rs.getBigDecimal("amount"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log or handle the exception appropriately
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		}

		return userList;
	}

	public static User getLastUser() throws SQLException {
		User user = new User();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = GetCon.getCon();
			String sqlQuery = "SELECT * FROM newaccount WHERE accountno=(SELECT MAX(accountno) FROM newaccount)";
			ps = con.prepareStatement(sqlQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setAccountno(rs.getLong("accountno"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmailid(rs.getString("emailid"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getString("dob"));
				user.setBalance(rs.getBigDecimal("amount"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		}

		return user;
	}

	public static User getUserByAccountno(long accountno) {
		User user = new User();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = GetCon.getCon();
			String sqlQuery = "SELECT amount FROM NEWACCOUNT WHERE accountno=?";
			ps = con.prepareStatement(sqlQuery);
			ps.setLong(1, accountno);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setBalance(rs.getBigDecimal("amount"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return user;
	}

	public static int updateUser(User user) throws SQLException {
		int i = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = GetCon.getCon();
			String sqlQuery = "UPDATE newaccount SET  firstname=?, lastname=?, emailid=?, dob=?,  address=?, phone=? WHERE id=?";
			ps = con.prepareStatement(sqlQuery);

			// Set the values for the placeholders in the prepared statement
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmailid());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getPhone()); // Fixed index here
			ps.setInt(7, user.getId());

			i = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (ps != null) {
				ps.close();
			}

		}
		return i;
	}

	public static int deleteUser(User user) throws SQLException {
		int i = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = GetCon.getCon();
			String sqlQuery = "DELETE FROM newaccount WHERE id=?";
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, user.getId());

			i = ps.executeUpdate();

		} catch (SQLException e) {
			// Log the exception instead of printing the stack trace
			// Logging library like SLF4J or java.util.logging can be used
			// logger.error("Error deleting user with id: " + user.getId(), e);
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}

		}
		return i;
	}

	public static boolean isUserExists(String emailid) {
		boolean userExists = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		con = GetCon.getCon();
		String sqlQuery = "SELECT COUNT(*) FROM newaccount WHERE emailid = ?";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, emailid);

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				userExists = count > 0;
			}
		} catch (SQLException e) {
			// Log the exception instead of printing the stack trace
			// Logging library like SLF4J or java.util.logging can be used
			// logger.error("Error checking if user exists with email: " + emailid, e);
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return userExists;
	}

	public static boolean isUserExistsByAccountno(long accountno) {
		boolean userExists = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		con = GetCon.getCon();
		String sqlQuery = "SELECT COUNT(*) FROM newaccount WHERE accountno = ?";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setLong(1, accountno);

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				userExists = count > 0;
			}
		} catch (SQLException e) {
			// Log the exception instead of printing the stack trace
			// Logging library like SLF4J or java.util.logging can be used
			// logger.error("Error checking if user exists with email: " + emailid, e);
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return userExists;
	}

	public static User getUserByEmailId(String emailid) {
		User user = new User();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = GetCon.getCon();
		String sqlQuery = "SELECT * FROM NEWACCOUNT WHERE emailid=?";

		try {

			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, emailid);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setAccountno(rs.getLong("accountno"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmailid(rs.getString("emailid"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getString("dob"));
				user.setBalance(rs.getBigDecimal("amount"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// Properly log the exception instead of printing the stack trace
			// Logging library like SLF4J or java.util.logging can be used
			// logger.error("Error fetching user by email: " + emailid, e);
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return user;
	}
	
	public static User getUserById(int id) {
		User user = new User();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = GetCon.getCon();
		String sqlQuery = "SELECT * FROM NEWACCOUNT WHERE id=?";

		try {

			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setAccountno(rs.getLong("accountno"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmailid(rs.getString("emailid"));
				user.setPassword(rs.getString("password"));
				user.setDob(rs.getString("dob"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// Properly log the exception instead of printing the stack trace
			// Logging library like SLF4J or java.util.logging can be used
			// logger.error("Error fetching user by email: " + emailid, e);
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return user;
	}

	public static boolean userAuthenticate(String emailid, String password) throws SQLException {
		boolean validUser = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = GetCon.getCon();
			String sqlQuery = "SELECT COUNT(*) FROM newaccount WHERE emailid = ? AND password = ?";

			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, emailid);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				validUser = count > 0;
			}
		} catch (SQLException e) {
			// Properly log the exception instead of printing the stack trace
			// Logging library like SLF4J or java.util.logging can be used
			// logger.error("Error authenticating user with email: " + emailid, e);
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		}

		return validUser;
	}

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		return email.matches(emailRegex);
	}

	public static void updateBalancesAndCommit(Connection con, PreparedStatement psUpdate, long senderAccountNo,
			long receiverAccountNo, BigDecimal senderBalance, BigDecimal receiverBalance, BigDecimal amount) throws SQLException {
		String updateQuery = "UPDATE NEWACCOUNT SET amount=? WHERE accountno=?";
		psUpdate = con.prepareStatement(updateQuery);

		con.setAutoCommit(false); // Disable auto-commit for transaction

		BigDecimal updatedSenderAmount =senderBalance.subtract(amount);
		BigDecimal updatedReceiverAmount = receiverBalance.add(amount);

		psUpdate.setLong(2, senderAccountNo);
		psUpdate.setBigDecimal(1, updatedSenderAmount);
		int i = psUpdate.executeUpdate();
		psUpdate.setLong(2, receiverAccountNo);
		psUpdate.setBigDecimal(1, updatedReceiverAmount);
		int j = psUpdate.executeUpdate();
		if (i > 0 && j > 0) {
			con.commit(); // Commit the transaction
			con.setAutoCommit(true); // Enable auto-commit after successful transaction
		} else {
			con.rollback(); // Rollback the transaction
			con.setAutoCommit(true); // Enable auto-commit after failed transaction
		}
	}

	public static void closeResources(Connection con, PreparedStatement... preparedStatements) {
		for (PreparedStatement ps : preparedStatements) {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(); // Handle or log the exception
				}
			}
		}

	}
	
    public static BigDecimal getUserBalance(Connection con, long accountNo) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT amount FROM NEWACCOUNT WHERE accountno = ?")) {
            ps.setString(1, Long.toString(accountNo));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("amount");
                }
            }
        }
        return BigDecimal.ZERO;
    }

    public static boolean updateBalance(Connection con, long accountNo, BigDecimal newBalance) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("UPDATE NEWACCOUNT SET amount = ? WHERE accountno = ?")) {
            ps.setBigDecimal(1, newBalance);
            ps.setString(2, Long.toString(accountNo));
            return ps.executeUpdate() > 0;
        }
    }

}
