package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {
	AuthService as = new AuthService();
	
	public Handler loginHandler = (ctx) -> {
		String body = ctx.body();
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		User user = as.login(lDTO.getUsername(), lDTO.getPassword());
		
		if(user != null) {
			String userJSON = gson.toJson(user);
			ctx.result(userJSON);
			ctx.status(202);
		}
		else {
			ctx.status(401);
		}
	};
}
