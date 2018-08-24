package com.gek.jee.to;

public class DocumentTO {
	private String name;
	private String id;
	private boolean downloadable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDownloadable() {
		return downloadable;
	}
	public void setDownloadable(boolean downloadable) {
		this.downloadable = downloadable;
	}
}
