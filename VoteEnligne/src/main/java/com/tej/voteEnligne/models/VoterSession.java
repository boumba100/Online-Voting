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
		this.didVoteMap = new HashMap<Integer, Boolean>();
		this.initCurrentIndex();
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
		this.didVoteMap.put(index, false);
		this.currentVoteIndex = index;
	}
	
	public void initCurrentIndex() {
		this.didVoteMap.put(0, false);
		this.currentVoteIndex = 0;
	}
	
	public boolean canVote() {
		return !this.didVoteMap.get(this.currentVoteIndex);
	}
	
	public void lockCurrentVote() {
		this.didVoteMap.put(this.currentVoteIndex, true); //true means voter has already voted for this act
	}
	

}

















