package com.tej.voteEnligne.services;

import org.json.JSONObject;

public interface ControllerService {
	
	public String createVoteSession(String code, String actsString, String adminCode);
	
    public JSONObject getVoteSession(String code);
	

}
