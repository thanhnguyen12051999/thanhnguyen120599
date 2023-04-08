package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class LoginModel {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection conn;
		
	public LoginModel(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public boolean loginModel(User user){
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "SELECT * FROM tbluser WHERE username = ? AND password = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst=conn.prepareStatement(sqlCommand);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
		
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnect(conn);
			DBConnection.closePreparedStatement(pst);
		}
		
		return result;
	}
	
	
}
