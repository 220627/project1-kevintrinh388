package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAO {
	public User getUserById(int ers_users_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ers_users_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
				);
				return user;
			}
		}
		catch(SQLException e) {
			System.out.println("GET USER BY ID FAILED");
			e.printStackTrace();
		}
		
		return null;
	}
}
