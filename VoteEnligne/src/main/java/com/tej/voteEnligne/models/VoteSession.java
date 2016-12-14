package com.tej.voteEnligne.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteSession {
	private String sessionCode;
	private String passCode;
	private List<Act> acts;
	private int currentActIndex;
	private int voterCount;
	private List<Integer> voterActList;
	private boolean start;
	private boolean active;
	
	public VoteSession(String sessionCode, List<Act> acts, String passCode) {
		this.sessionCode = sessionCode;
		this.acts = acts;
		this.currentActIndex = 0;
		this.passCode = passCode;
		this.active = false;
		this.voterCount = 0;
		this.voterActList = new ArrayList<Integer>();
		this.voterActList.add(0);
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
			this.voterActList.add(0);
			return true;
		} else {
			return false;
		}
	}
	
	public void appendActScore(int score) {
		this.acts.get(this.currentActIndex).appendScore(score);
		this.voterActList.set(currentActIndex, this.voterActList.get(currentActIndex) + 1);
	}
	
	public int getCurrentActIndex() {
		return this.currentActIndex;
	}
	
	public int getCurrentActScore() {
		return this.acts.get(currentActIndex - 1).getScore();
	}
	
	public void addVoter() {
		this.voterCount += 1;
	}
	
	public int getVoterCount() {
		return this.voterActList.get(currentActIndex - 1);
	}
	
}






