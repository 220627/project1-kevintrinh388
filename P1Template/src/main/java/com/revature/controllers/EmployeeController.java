package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.EmployeeDAO;
import com.revature.models.Reimbursement;

import io.javalin.http.Handler;

public class EmployeeController {
	EmployeeDAO eDAO = new EmployeeDAO();
	public Handler getUserReimbursementsHandler = (ctx) -> {
		int reimb_author = Integer.parseInt(ctx.pathParam("reimb_author"));
		ArrayList<Reimbursement> reimbursements = eDAO.getAllUserReimbursements(reimb_author);
		Gson gson = new Gson();
		String jsonReimbursements = gson.toJson(reimbursements);
		
		ctx.result(jsonReimbursements);
		ctx.status(200);
	};
	
	public Handler insertReimbursementHandler = (ctx) -> {
		String body = ctx.body();
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		if(eDAO.insertReimbursement(reimbursement)) {
			ctx.status(202);
		}
		else {
			ctx.status(406);
		}
	};
}
