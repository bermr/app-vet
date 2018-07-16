package com.app;

public class Story {
	private String text;
	private String fullDescription;
	
	public Story(String t, String f, String h) {
		this.text = t;
		this.fullDescription = f;
	}
	
	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
