package com.tej.voteEnligne.models;

import java.util.List;

import org.json.JSONArray;

public class VoterSession {
	private String voteSessionCode;
	private JSONArray actNameList;
	private int currentVoteIndex;
	
	public VoterSession(String voteSessionCode, JSONArray actNameList) {
		this.voteSessionCode = voteSessionCode;
		this.actNameList = actNameList;
		this.currentVoteIndex = 0;
	}
	
	public String getVoteSessionCode() {
		return this.voteSessionCode;
	}
	
	public void setVoteSessionCode(String VoteSessionCode) {
		this.voteSessionCode = VoteSessionCode;
	}
	
	public JSONArray getActNameList() {
		return this.actNameList;
	}
	
	public void setActNameList(JSONArray actNameList) {
		this.actNameList = actNameList;
	} 
	
	public int getCurrentVoteindex() {
		return this.currentVoteIndex;
	}
	
	public void setCurrentIndex(int index) {
		this.currentVoteIndex = index;
	}
	
	/*private List<String> listStringToList(String listString) {
		String t = listString.replace("[", " ").replace("]", "").replaceAll("\"", "").replaceAll("\\\\r", "");
		System.out.println(t);
		return null;
	}*/
}

















