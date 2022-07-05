package com.hype.bookmarkmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hype.bookmarkmanager.model.Account;



public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUsername(String username);
	
}
