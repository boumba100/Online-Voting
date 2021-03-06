package com.tej.voteEnligne.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Act {
	private final String ACT_NAME_FIELD = "actName";
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
	
	public Act(String[] actArray) { 
		this.label = actArray[0];
		this.names = extractNamesArray(actArray);
		this.score = 0;
	}
	
	public String getInfoJson() throws JSONException { // returns json object wiht name and 
		JSONObject actInfo = new JSONObject();
		actInfo.put(this.ACT_NAME_FIELD, this.label).put(this.NAMES_FIELD, this.names);
		return actInfo.toString();
	}
	
	public String getLabel () {
		return this.label;
	}
	
	public int getScore() {
		return this.score;
	}
	
	private String[] extractNamesArray(String[] actArray) {
		String[] namesArray = new String[actArray.length - 1];
		for(int i = 1; i < actArray.length - 1; i ++) {
			namesArray[i - 1] = actArray[i];
		}
		return namesArray;
	}
}
