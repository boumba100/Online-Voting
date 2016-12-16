package com.tej.voteEnligne.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

public class VoterSession {
	private String voteSessionCode;
	private JSONArray actNameList;
	private int currentVoteIndex;
	private Map<Integer, Boolean> didVoteMap; //determines if voter has already voted for act
	
	public VoterSession(String voteSessionCode, JSONArray actNameList) {
		this.voteSessionCode = voteSessionCode;
		this.actNameList = actNameList;
		this.currentVoteIndex = 0;
		this.didVoteMap = new HashMap<Integer, Boolean>();
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
		this.didVoteMap.put(index, true);
		this.currentVoteIndex = index;
		this.didVoteMap.put(index, false);
	}
	
	public boolean canVote() {
		return !this.didVoteMap.get(this.currentVoteIndex);
	}
	
	public void lockCurrentVote() {
		this.didVoteMap.put(this.currentVoteIndex, true);
	}
	
	
	
	/*private List<String> listStringToList(String listString) {
		String t = listString.replace("[", " ").replace("]", "").replaceAll("\"", "").replaceAll("\\\\r", "");
		System.out.println(t);
		return null;
	}*/
}

















