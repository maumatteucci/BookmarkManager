package com.hype.bookmarkmanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "folder")
public class Folder {
	
	public Folder() {
		
	}
	
	public Folder(String name, Date addDate, Date lastModified) {
		super();
		this.name = name;
		this.addDate = addDate;
		this.lastModified = lastModified;
		this.bookmarks = new ArrayList<>();
	}
	
	@Id
	@Column(name = "name")
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "addDate")
	private Date addDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastModified")
	private Date lastModified;
	@OneToMany(mappedBy = "folder")
	private List<Bookmark> bookmarks;
	
	
	public String getName() {
		return name;
	}
	public Date getAddDate() {
		return addDate;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
}
