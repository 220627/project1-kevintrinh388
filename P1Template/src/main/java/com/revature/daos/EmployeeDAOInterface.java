package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface EmployeeDAOInterface {
	// get all user's reimbursements
	public ArrayList<Reimbursement> getAllUserReimbursements(int reimb_author);
	
	// insert a new reimbursement
	public boolean insertReimbursement(Reimbursement reimbursement);
}
