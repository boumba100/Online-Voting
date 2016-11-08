package com.tej.voteEnligne.implement;

import org.springframework.stereotype.Service;

import com.tej.voteEnligne.services.ActDB;
import com.tej.voteEnligne.services.ControllerService;

@Service
public class ControllerServiceImplement implements ControllerService{
	private ActDB actDB;
	
	public ControllerServiceImplement() {
		this.actDB = new SqliteActDBImplementation();
	}
	@Override
	public String getCurrentAct(String actName) {
		return null;
	}
}
