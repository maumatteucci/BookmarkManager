package com.hype.bookmarkmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hype.bookmarkmanager.model.Account;
import com.hype.bookmarkmanager.repository.AccountRepository;
import com.hype.bookmarkmanager.service.IAccountService;


@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private AccountRepository apiAccountRepository;
	

	@Override
	public Account findByUsername(String username){
		return apiAccountRepository.findByUsername(username);
	}

	
}
