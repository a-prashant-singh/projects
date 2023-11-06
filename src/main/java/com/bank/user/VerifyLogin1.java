package com.bank.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.GetCon;

public class VerifyLogin1 {

	public static boolean checkLogin(String accountno, String username, String password) {
		boolean status = false;
		Connection con = GetCon.getCon();
		try {
			PreparedStatement ps = con
					.prepareStatement("Select * from NEWACCOUNT where accountno=? and username = ? and password =?");
			ps.setString(1, accountno);
			ps.setString(2, username);
			ps.setString(3, password);

			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean checkAdminLogin(String username, String password) {
		boolean status = false;

		if (username.equals("admin") && password.equals("admin")) {

			status = true;
		}

		return status;
	}

	public static boolean checkUserLogin(String emailid, String password) {
		boolean status = false;
		Connection con = GetCon.getCon();
		try {
			PreparedStatement ps = con
					.prepareStatement("Select * from NEWACCOUNT where emailid=? and password =?");
			ps.setString(1, emailid);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean checkUserPassword(String accountno, String password) {
		boolean status = false;
		Connection con = GetCon.getCon();
		try {
			PreparedStatement ps = con
					.prepareStatement("Select * from NEWACCOUNT where accountno=? and password =?");
			ps.setString(1, accountno);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
