package com.hype.bookmarkmanager.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hype.bookmarkmanager.controller.request.BookmarkRequest;
import com.hype.bookmarkmanager.model.Bookmark;
import com.hype.bookmarkmanager.model.Folder;
import com.hype.bookmarkmanager.repository.BookmarkRepository;
import com.hype.bookmarkmanager.repository.FolderRepository;
import com.hype.bookmarkmanager.service.IBookmarkService;


@Service
public class BookmarkService implements IBookmarkService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookmarkService.class);
	
	@Autowired
	BookmarkRepository bookmarkRepository;
	
	@Autowired
	FolderRepository folderRepository;
	
	@Override
	public ResponseEntity<String> insertBookmark(BookmarkRequest request) {
		Folder folder = folderRepository.findByName(request.getFolderName());
		Bookmark bookmark = new Bookmark(request.getName(), new Date(),request.getHref() , new Date(), request.getIconUri());
		if(folder == null) {
			folder = new Folder(request.getFolderName(), new Date(), new Date());
			try {
				folder = folderRepository.save(folder);
			} catch (Throwable t) {
				logger.error(t.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getMessage());
			}
		}
		bookmark.setFolder(folder);
		try {
			bookmarkRepository.save(bookmark);
		} catch (Throwable t) {
			logger.error(t.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Bookmark saved successfully");
		
	}
	@Override
	public byte[] exportAsAFile() {
		List<Folder> bookmarksFolder = folderRepository.findAll();
		StringBuilder document = new StringBuilder();
		document.append("<!DOCTYPE NETSCAPE-Bookmark-file-1>\n");
		document.append("<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">\n");
		document.append("<TITLE>Bookmarks</TITLE>\n");
		document.append("<H1>Bookmarks</H1>\n");
		document.append("<DL><p>\n");
		bookmarksFolder.stream().forEach(folder -> {
			StringBuilder line = new StringBuilder();
			line.append("\t \t <DT><H3 ADD_DATE=" + folder.getAddDate() +" LAST_MODIFIED="+ folder.getLastModified()+">"+folder.getName()+"</H3>\n");
			line.append("\t \t <DL><p>\n");
			folder.getBookmarks().stream().forEach(bookmark -> {
				line.append("\t \t \t \t  <DT><A HREF="+bookmark.getHref()+" ADD_DATE="+bookmark.getAddDate()+" LAST_MODIFIED="+ bookmark.getLastModified()+" ICON_URI="+ bookmark.getIconUri()+">"+bookmark.getName()+"</A>\n");
			});
			line.append("\t \t </DL><p>\n");
			document.append(line);
		});
		document.append("</DL><p>\n");
		return document.toString().getBytes();
	}
	@Override
	public List<Folder> getSortedBookmarks(){
		List<Folder> bookmarksFolder = folderRepository.findAll();
		bookmarksFolder.stream().forEach(folder -> {
			List<Bookmark> sortedBookmarks = folder.getBookmarks().stream()
				.sorted(Comparator.comparing(Bookmark::getName))
				.collect(Collectors.toList());
			folder.setBookmarks(sortedBookmarks);
		});
		return bookmarksFolder;
	}
	@Override
	public List<Folder> getDistinctBookmarks(){
		List<Folder> bookmarksFolder = folderRepository.findAll();
		bookmarksFolder.stream().forEach(folder -> {
			List<Bookmark> distinctBookmarks = folder.getBookmarks().stream()
					.filter(distinctByName(Bookmark::getName))
					.collect(Collectors.toList());
			folder.setBookmarks(distinctBookmarks);
		});
		return bookmarksFolder;
	
	}
	
	private static <T> Predicate<T> distinctByName(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
}
