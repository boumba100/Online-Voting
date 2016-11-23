package com.tej.voteEnligne.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tej.voteEnligne.implement.SqliteActDBImplementation;
import com.tej.voteEnligne.models.Act;
import com.tej.voteEnligne.models.VoteSession;

@Service
public class VoteService {
	private static ActDB actDB;
	private static Map<String, VoteSession> codeVoteMap;

	public VoteService() {
		codeVoteMap = new HashMap<String, VoteSession>();
		actDB = new SqliteActDBImplementation();
	}

	public String createVoteSession(String code, String actsString, String adminCode) {
		if (code == null || actDB.sessionExist(code)) {
			return "Ce code exist deja";
		} else if (actsString == null || actsString.length() == 0) {
			return "Il n'y a pas assez de participants";
		} else if (!actsStringValid(actsString)) {
			return "Erreur";
		} else {
			this.actDB.insertVoteSession(adminCode, adminCode, actsString);
			return null;
		}
	}

	public String initVoteSession(String code, String passcode) {
		String actsString = actDB.getVoteActsString(code, passcode);
		if (actsString == null) {
			return "mauvais code ou code de passe!";
		} else if (codeVoteMap.containsKey(code)) {
			return "session deja commence!";
		} else {
			codeVoteMap.put(code, new VoteSession(code, actsStringToActList(actsString), passcode));
			return "succes";
		}

	}
	
	public List<String> getSessionActNames(String code) {
		VoteSession voteSession = codeVoteMap.get(code);
		return actListToNameList(voteSession.getActList());
	}
	
	public JSONObject nextAct(String code, String passcode) {
		JSONObject result = new JSONObject();
		VoteSession voteSession = codeVoteMap.get(code);
		if(voteSession != null) {
			if(voteSession.isPasscode(passcode)) {
				try {
					voteSession.nextAct();
					result.put("success", true);
					result.put("currentActIndex", voteSession.getCurrentActIndex());
					result.put("score", voteSession.getCurrentActScore());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				try {
					result.put("success", false);
					result.put("message", "mauvais code");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				result.put("success", false);
				result.put("message", "session n'exist pas");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Deprecated
	public boolean isSessionStart(String sessionCode) {
		if(codeVoteMap.containsKey(sessionCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canEnterSession(String sessionCode) {
		if(codeVoteMap.containsKey(sessionCode) && !codeVoteMap.get(sessionCode).isActive()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean needForUpdate(String sessionCode, int clientIndex) {
		VoteSession voteSession = codeVoteMap.get(sessionCode);
		if(!(voteSession.getCurrentActIndex() == clientIndex)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getVoteSessionIndex(String sessionCode) {
		return codeVoteMap.get(sessionCode).getCurrentActIndex();
	}
	
	public void appendScore(String sessionCode, int score) {
		codeVoteMap.get(sessionCode).appendActScore(score);
	}
	
	private List<Act> actsStringToActList(String actsString) {
		List<Act> actList = new ArrayList<Act>();
		String[] actStringArray = actsString.split("\n");
		for (String actString : actStringArray) {
			actList.add(new Act(actString.split(",")));
		}
		return actList;
	}
	
	private List<String> actListToNameList(List<Act> actList) {
		List<String> actNameList = new ArrayList<String>();
		for (Act act: actList) {
			actNameList.add(act.getLabel());
		}
		return actNameList;
		
	}

	private boolean actsStringValid(String actsString) {
		if (actsString == null || actsString.length() == 0) {
			return false;
		} else {
			return true;
		}
	}

}








