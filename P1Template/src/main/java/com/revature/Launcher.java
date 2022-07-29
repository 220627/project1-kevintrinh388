package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.FinanceManagerController;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("WELCOME TO THE EMPLOYEE REIMBURSEMENT SYSTEM");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("CONNECTION SUCCESSFUL :)");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		//Typical Javalin syntax to create a Javalin object
		Javalin app = Javalin.create(
				// the config lambda lets us specify certain configs for our Javalin app
				config -> {
					config.enableCorsForAllOrigins();  // this lets us process HTTP requests from any origin
				}
		).start(3000); // we need .start() to start our Java server on port 3000
		
		// instantiate controllers
		AuthController ac = new AuthController();
		EmployeeController ec = new EmployeeController();
		FinanceManagerController fmc = new FinanceManagerController();
		
		// End-point handlers below
		app.post("/login", ac.loginHandler); // will handle both normal employees and managers
		
		app.get("/reimbursements/:reimb_author", ec.getUserReimbursementsHandler);
		app.post("/reimbursements", ec.insertReimbursementHandler);
		
		app.get("/reimbursements/manager/:reimb_status_id", fmc.getReimbursementsByStatusIdHandler);
		app.put("/reimbursements/status/manager/:reimb_id", fmc.updateReimbursementStatus);
	}
	
}
