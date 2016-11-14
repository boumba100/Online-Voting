package com.tej.voteEnligne.services;

import org.json.JSONObject;
import org.springframework.web.context.request.WebRequest;

public interface ControllerService {
	
	public String createVoteSession(String code, String actsString, String adminCode);
	
    public JSONObject getVoteSession(String code);
    
    public String startVoteSession(String code, String passcode);
    
    public JSONObject processControlRequest(WebRequest webRequest);
    
}
