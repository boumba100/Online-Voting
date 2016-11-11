package com.tej.voteEnligne.implement;


import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tej.voteEnligne.services.ControllerService;
import com.tej.voteEnligne.services.VoteService;

@Service
public class ControllerServiceImplement implements ControllerService{
	private static VoteService voteService; 
	
	public ControllerServiceImplement() {
		voteService = new VoteService();
	}
	
	@Override
	public String createVoteSession(String code, String actsString, String adminCode) {
		String creationResult = voteService.createVoteSession(code, actsString, adminCode);
		if(creationResult == null) {
			return "Creation complète";
		} else {
			return creationResult;
		}
	}
	
	@Override
	public JSONObject getVoteSession(String code) {
		return null;
	}
	
}
