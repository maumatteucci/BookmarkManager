package com.hype.bookmarkmanager.controller.request;

import javax.validation.constraints.NotNull;

public class BookmarkRequest {
	@NotNull(message = "Please provide a name")
	private String name;
	@NotNull(message = "Please provide  href")
	private String href;
	private String iconUri;
	@NotNull(message = "Please provide  a folder name")
	private String folderName;
	
	public String getName() {
		return name;
	}

	public String getHref() {
		return href;
	}
	
	public String getIconUri() {
		return iconUri;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	
	

}
