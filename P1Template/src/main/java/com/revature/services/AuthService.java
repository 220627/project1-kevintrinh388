package com.revature.services;

import com.revature.daos.AuthDAO;
import com.revature.models.User;

public class AuthService {
AuthDAO aDAO = new AuthDAO();
	
	public User login(String username, String password) {
		User user = aDAO.login(username, password);
		if(user != null) {
			return user; // if username and password get a object, send it up
		}
		return null;
	}
}
