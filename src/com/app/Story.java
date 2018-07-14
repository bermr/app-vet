package com.app;

public class Story {
	private String text;
	private String fullDescription;
	private String highlights;
	
	public Story(String t, String f, String h) {
		this.text = t;
		this.fullDescription = f;
		this.highlights = h;
	}
	
	public String getHighlights() {
		return highlights;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
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
