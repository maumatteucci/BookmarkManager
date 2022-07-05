package com.hype.bookmarkmanager.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bookmark")
public class Bookmark {
	
	public Bookmark() {
		
	}
	
	public Bookmark(String name, Date addDate, String href, Date lastModified, String iconUri) {
		super();
		this.name = name;
		this.addDate = addDate;
		this.href = href;
		this.lastModified = lastModified;
		this.iconUri = iconUri;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "addDate")
	private Date addDate;
	@Column(name = "href")
	private String href;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastModified")
	private Date lastModified;
	@Column(name = "iconUri")
	private String iconUri;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "folderName", referencedColumnName = "name") })
	private Folder folder;
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Date getAddDate() {
		return addDate;
	}
	public String getHref() {
		return href;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public String getIconUri() {
		return iconUri;
	}
	public Folder getFolder() {
		return folder;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	
	
	

}
