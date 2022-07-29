package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserRoleDAO {
	public UserRole getUserRoleById(int ers_user_role_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ers_user_role_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				UserRole userRole = new UserRole(
						rs.getInt("ers_user_role_id"),
						rs.getString("user_role")
				);
				return userRole;
			}
			
		}
		catch(SQLException e) {
			System.out.println("GET USER ROLE BY ID FAILED");
			e.printStackTrace();
		}
		
		return null;
	}
}
