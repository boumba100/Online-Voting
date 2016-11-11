package com.tej.voteEnligne.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tej.voteEnligne.models.Act;
import com.tej.voteEnligne.models.VoteSession;

@Service
public class VoteService {
	private static Map<String, VoteSession> codeVoteMap;

	public VoteService() {
		this.codeVoteMap = new HashMap<String, VoteSession>();
	}

	public String createVoteSession(String code, String actsString, String adminCode) {
		if (code == null || this.codeVoteMap.containsKey(code)) {
			return "Ce code exist deja";
		} else if (actsString == null || actsString.length() == 0) {
			return "Il n'y a pas assez de participants";
		} else if(!actsStringValid(actsString)) {
			return "Erreur";
		}
		else {
			codeVoteMap.put(code, new VoteSession(code, actsStringToActList(actsString), adminCode));
			return null;
		}
	}
	
	private List<Act> actsStringToActList(String actsString) {
		List<Act> actList = new ArrayList<Act>();
		String[] actStringArray = actsString.split("\n");
		for(String actString: actStringArray) {
			actList.add(new Act(actString.split(",")));
		}
		return actList;
	}
	
	private boolean actsStringValid(String actsString) {
		if(actsString == null || actsString.length() == 0) {
			return false;
		} else {
			return true;
		}
	}

}
