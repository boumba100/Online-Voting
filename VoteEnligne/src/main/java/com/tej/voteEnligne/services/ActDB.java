package com.tej.voteEnligne.services;

import com.tej.voteEnligne.models.Act;

public interface ActDB {
	
	public void initDB();
	
	public void insertVoteSession(String code, String passcode, String actsString);
	
	public String getVoteActsString(String code, String passcode);
	
	public boolean sessionExist(String code);

}
