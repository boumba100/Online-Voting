package com.tej.voteEnligne.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteSession {
	private String passCode;
	private List<Act> acts;
	private int currentActIndex;
	private boolean start;
	private boolean active;
	
	public VoteSession(String sessionCode, List<Act> acts, String passCode) {
		this.acts = acts;
		this.currentActIndex = 0;
		this.passCode = passCode;
		this.active = false;
		
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public boolean getStart() {
		return this.start;
	}
	
	public void setActive() {
		this.active = true;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public List<Act> getActList() {
		return this.acts;
	}
	
	public boolean isPasscode(String passcode) {
		if (this.passCode.equals(passcode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean nextAct() {
		if(this.currentActIndex != acts.size()) {
			this.currentActIndex += 1;
			return true;
		} else {
			return false;
		}
	}
	
	public void appendActScore(int score) {
		this.acts.get(this.currentActIndex).appendScore(score);
	}
	
	public int getCurrentActIndex() {
		return this.currentActIndex;
	}
	
	public int getCurrentActScore() {
		return this.acts.get(currentActIndex).getScore();
	}
	
	public void addVoter() {
		this.acts.get(currentActIndex).addVoter();
	}
	
	public int getVoterCount() {
		return this.acts.get(currentActIndex).getVoterCount();
	}
	
}






