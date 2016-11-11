package com.tej.voteEnligne.models;

import java.util.List;

public class VoteSession {
	private String sessionCode;
	private String adminCode;
	private List<Act> acts;
	private int currentActIndex;
	private boolean start;
	
	public VoteSession(String sessionCode, List<Act> acts, String adminCode) {
		this.sessionCode = sessionCode;
		this.acts = acts;
		this.currentActIndex = 0;
		this.adminCode = adminCode;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public boolean getStart() {
		return this.start;
	}

}
