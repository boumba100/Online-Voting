package com.tej.voteEnligne.models;

import java.util.List;

import org.json.JSONArray;

public class VoterSession {
	private String voteSessionCode;
	private JSONArray actNameList;
	
	public VoterSession(String voteSessionCode, JSONArray actNameList) {
		this.voteSessionCode = voteSessionCode;
		this.actNameList = actNameList;
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
	
	/*private List<String> listStringToList(String listString) {
		String t = listString.replace("[", " ").replace("]", "").replaceAll("\"", "").replaceAll("\\\\r", "");
		System.out.println(t);
		return null;
	}*/
}

















