package com.tej.voteEnligne.models;

import org.json.JSONObject;

public class Act {
	private final String LABEL_FIELD = "label";
	private final String NAMES_FIELD = "names";
	private final String NAME_FIELD = "score";
	private String label;
	private String[] names;
	private int score;
	
	public Act(String label, String[] names, int score) { 
		this.label = label;
		this.names = names;
		this.score = score;
	}
	
	public String getJsonString() {
		JSONObject actJson = new JSONObject();
		return null;
	}
}
