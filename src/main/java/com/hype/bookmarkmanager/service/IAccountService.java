package com.hype.bookmarkmanager.service;

import com.hype.bookmarkmanager.model.Account;

public interface IAccountService {
	
	public Account findByUsername(String username);
	
}
