package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AuthDAO {
	public static Logger log = LogManager.getLogger();
	UserRoleDAO uDAO = new UserRoleDAO();
	
	public User login(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			// if anything is returned at all, we know a user exists with that password.
			// so we create a User object to send to frontend
			if(rs.next()) {
				User u = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
				);
				u.setUser_role(uDAO.getUserRoleById(rs.getInt("user_role_id")));
				log.info("USER LOGGED IN SUCCESSFULLY");
				return u;
			}
			
		}
		catch(SQLException e) {
			log.warn("USER FAILED TO LOGIN");
			System.out.println("LOGIN FAILED");
			e.printStackTrace();
		}
		
		return null;
	}
}
