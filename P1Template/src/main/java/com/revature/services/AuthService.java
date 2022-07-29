package com.revature.services;

import com.revature.daos.AuthDAO;
import com.revature.models.User;

public class AuthService {
AuthDAO aDAO = new AuthDAO();
	
	public User login(String username, String password) {
		
		if(aDAO.login(username, password) != null) {
			return aDAO.login(username, password); // if username and password get a object, send it up
		}
		return null;
	}
}
