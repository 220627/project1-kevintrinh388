package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class FinanceManagerDAO implements FinanceManagerDAOInterface{
	UserDAO uDAO = new UserDAO();
	ReimbursementStatusDAO rsDAO = new ReimbursementStatusDAO();
	ReimbursementTypeDAO rtDAO = new ReimbursementTypeDAO();

	public ArrayList<Reimbursement> getAllReimbursements(){
		try(Connection conn = ConnectionUtil.getConnection()){
			ArrayList<Reimbursement> reimbursements = new ArrayList<>();
			String sql = "SELECT * FROM ers_reimbursement;";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
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
			
			return reimbursements;
		}
		catch(SQLException e) {
			System.out.println("GET ALL REIMBURSEMENTS FAILED");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<Reimbursement> getReimbursementsByStatusId(int reimb_status_id) {
		// managers can get reimbursements by status id
		try(Connection conn = ConnectionUtil.getConnection()){
			ArrayList<Reimbursement> reimbursements = new ArrayList<>();
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_status_id);
			ResultSet rs = ps.executeQuery();
			
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
			
			return reimbursements;
		}
		catch(SQLException e) {
			System.out.println("GET REIMBURSEMENTS BY STATUS ID FAILED");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateReimbursementStatus(int reimb_id, int status) {
		// managers can update status by reimb_id
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE ers_reimbursement SET reimb_status_id = ? WHERE reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, reimb_id);
			ps.executeUpdate();
			
			return true;
		}
		catch(SQLException e) {
			System.out.println("UPDATE REIMBURSEMENT STATUS FAILED");
			e.printStackTrace();
		}
		
		return false;
	}

}
