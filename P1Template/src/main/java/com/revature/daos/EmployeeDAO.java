package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class EmployeeDAO implements EmployeeDAOInterface{
	public static Logger log = LogManager.getLogger();
	UserDAO uDAO = new UserDAO();
	ReimbursementStatusDAO rsDAO = new ReimbursementStatusDAO();
	ReimbursementTypeDAO rtDAO = new ReimbursementTypeDAO();
	
	@Override
	public ArrayList<Reimbursement> getAllUserReimbursements(int reimb_author) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_author);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimbursement> reimbursements = new ArrayList<>();
			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
				);
				reimbursement.setAuthor(uDAO.getUserById(rs.getInt("reimb_author")));
				reimbursement.setReimb_status(rsDAO.getReimbursementStatusById(rs.getInt("reimb_status_id")));
				reimbursement.setReimb_type(rtDAO.getReimbursementTypeById(rs.getInt("reimb_type_id")));
				reimbursements.add(reimbursement);
			}
			log.info("GET ALL USER REIMBURSEMENTS SUCCESSFUL");
			return reimbursements;
		}
		catch(SQLException e) {
			log.warn("GET ALL USER REIMBURSEMENTS FAILED");
			System.out.println("GET ALL USER REIMBURSEMENTS FAILED");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertReimbursement(Reimbursement reimbursement) {
		try(Connection conn = ConnectionUtil.getConnection()){
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_status_id, reimb_type_id)\r\n"
					+ "VALUES (?, ?, ?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbursement.getReimb_amount());
			ps.setTimestamp(2, timestamp);
			ps.setInt(3, reimbursement.getReimb_author());
			ps.setInt(4, reimbursement.getReimb_status_id());
			ps.setInt(5, reimbursement.getReimb_type_id());
			ps.executeUpdate();
			
			log.info("INSERT REIMBURSEMENT SUCCESSFUL");
			return true;
		}
		catch(SQLException e) {
			log.warn("INSERT REIMBURSEMENT FAILED");
			System.out.println("INSERT REIMBURSEMENT FAILED");
			e.printStackTrace();
		}
		return false;
	}

}
