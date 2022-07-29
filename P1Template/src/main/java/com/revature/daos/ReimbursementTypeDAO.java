package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementTypeDAO {
	public ReimbursementType getReimbursementTypeById(int reimb_type_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_type_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementType reimbursementType = new ReimbursementType(
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_type")
				);
				return reimbursementType;
			}
		}
		catch(SQLException e) {
			System.out.println("GET REIMBURSEMENT TYPE BY ID FAILED");
			e.printStackTrace();
		}
		return null;
	}
}
