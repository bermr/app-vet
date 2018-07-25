package com.app;

public class Story {
	private String title;
	private String fullDescription;
	
	public Story(String t, String f) {
		this.title = t;
		this.fullDescription = f;
	}
	
	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getText() {
		return title;
	}

	public void setText(String text) {
		this.title = text;
	}
}
