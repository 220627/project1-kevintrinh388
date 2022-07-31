package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbursementStatusDAO {
	public static Logger log = LogManager.getLogger();
	public ReimbursementStatus getReimbursementStatusById(int reimb_status_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_status_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementStatus reimbursementStatus = new ReimbursementStatus(
						rs.getInt("reimb_status_id"),
						rs.getString("reimb_status")
				);
				log.info("GET REIMBURSEMENT STATUS BY ID SUCCESSFUL");
				return reimbursementStatus;
			}
			
		}
		catch(SQLException e) {
			log.warn("GET REIMBURSEMENT STATUS BY ID FAILED");
			System.out.println("GET REIMBURSEMENT STATUS BY ID FAILED");
			e.printStackTrace();
		}
		return null;
	}
}
