package com.hype.bookmarkmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hype.bookmarkmanager.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{

}
