package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.FinanceManagerDAO;
import com.revature.models.Reimbursement;

import io.javalin.http.Handler;

public class FinanceManagerController {
	FinanceManagerDAO fmDAO = new FinanceManagerDAO();
	
	public Handler getReimbursementsByStatusIdHandler = (ctx) -> {
		int reimb_status_id = Integer.parseInt(ctx.pathParam("reimb_status_id"));
		ArrayList<Reimbursement> reimbursements = fmDAO.getReimbursementsByStatusId(reimb_status_id);
		Gson gson = new Gson();
		String jsonReimbursements = gson.toJson(reimbursements);
		
		ctx.result(jsonReimbursements);
		ctx.status(200);
	};
	public Handler updateReimbursementStatus = (ctx) -> {
		int reimb_id = Integer.parseInt(ctx.pathParam("reimb_id"));
		int status = Integer.parseInt(ctx.body());
		if(fmDAO.updateReimbursementStatus(reimb_id, status)) {
			ctx.status(202);
		}
		else {
			ctx.status(406);
		}
	};
}
