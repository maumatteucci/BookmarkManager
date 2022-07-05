package com.hype.bookmarkmanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hype.bookmarkmanager.controller.request.BookmarkRequest;
import com.hype.bookmarkmanager.model.Folder;

public interface IBookmarkService {
	
	public ResponseEntity<String> insertBookmark(BookmarkRequest request);
	public byte[] exportAsAFile();
	public List<Folder> getSortedBookmarks();
	public List<Folder> getDistinctBookmarks();

}
