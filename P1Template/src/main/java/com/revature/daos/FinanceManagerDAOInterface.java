package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface FinanceManagerDAOInterface {
	public ArrayList<Reimbursement> getReimbursementsByStatusId(int reimb_status_id);
	
	public boolean updateReimbursementStatus(int reimb_id, int status);
}
