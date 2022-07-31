package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementTypeDAO {
	public static Logger log = LogManager.getLogger();
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
				log.info("GET REIMBURSEMENT TYPE BY ID SUCCESSFUL");
				return reimbursementType;
			}
		}
		catch(SQLException e) {
			log.warn("GET REIMBURSEMENT TYPE BY ID FAILED");
			System.out.println("GET REIMBURSEMENT TYPE BY ID FAILED");
			e.printStackTrace();
		}
		return null;
	}
}
