package com.hype.bookmarkmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hype.bookmarkmanager.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, String>{
	Folder findByName(String name);

}
