package com.hype.bookmarkmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hype.bookmarkmanager.controller.request.BookmarkRequest;
import com.hype.bookmarkmanager.model.Folder;
import com.hype.bookmarkmanager.service.IBookmarkService;

@RestController
@RequestMapping("/api")
public class BookmarkController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookmarkController.class);
	
	@Autowired
	private IBookmarkService bookmarkService;
			
			
	@RequestMapping(value = "/upload", method = RequestMethod.POST)		
	public ResponseEntity<String> uplaod(@Valid @RequestBody BookmarkRequest request){
		
		return bookmarkService.insertBookmark(request);
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)	
	public ResponseEntity<Resource> export(){
		
		byte[] document = bookmarkService.exportAsAFile();
		
		ByteArrayResource resource = new ByteArrayResource(document);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "Bookmark.html"));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		
		return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(document.length)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	@RequestMapping(value = "/sorted", method = RequestMethod.GET)
	public ResponseEntity<List<Folder>> getSortedBookmarks(){
		List<Folder> listFolder = bookmarkService.getSortedBookmarks();
		return ResponseEntity.status(HttpStatus.OK).body(listFolder);
	}
	
	@RequestMapping(value = "/distinct", method = RequestMethod.GET)
	public ResponseEntity<List<Folder>> getDistinctBookmarks(){
		List<Folder> listFolder = bookmarkService.getDistinctBookmarks();
		return ResponseEntity.status(HttpStatus.OK).body(listFolder);
	}
	
	
	

}
