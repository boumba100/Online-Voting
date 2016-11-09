package com.tej.voteEnligne.services;

import com.tej.voteEnligne.models.Act;

public interface ActDB {
	
	public void addAct(Act act);
	
	public void initDB();

}
