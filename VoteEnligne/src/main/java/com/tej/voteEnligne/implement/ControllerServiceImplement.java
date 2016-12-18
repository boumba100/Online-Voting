package com.tej.voteEnligne.implement;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.tej.voteEnligne.models.VoterSession;
import com.tej.voteEnligne.services.ActDB;
import com.tej.voteEnligne.services.ControllerService;
import com.tej.voteEnligne.services.VoteService;

@Service
public class ControllerServiceImplement implements ControllerService {
	private static VoteService voteService;

	public ControllerServiceImplement() {
		voteService = new VoteService();
	}

	@Override
	public String createVoteSession(String code, String actsString, String adminCode) {
		String creationResult = voteService.createVoteSession(code, actsString, adminCode);
		if (creationResult == null) {
			return "Creation complète";
		} else {
			return creationResult;
		}
	}

	@Override
	public String startVoteSession(String code, String passcode) {
		return voteService.initVoteSession(passcode, code);
	}

	@Override
	public JSONObject processControlRequest(WebRequest webRequest) {
		String requestType = webRequest.getParameter("type");
		JSONObject requestResult = null;

		if (requestType.equals("command")) {
			if (webRequest.getParameter("command").equals("nextAct")) {
				requestResult = voteService.nextAct(webRequest.getParameter("code"),
						webRequest.getParameter("passcode"));
			} else if(webRequest.getParameter("command").equals("stopSession")){
				requestResult = voteService.stopSession(webRequest.getParameter("code"),
						webRequest.getParameter("passcode"));
			}
		} else if (requestType.equals("startSession")) {
			String sessionStartResult = startVoteSession(webRequest.getParameter("code"),
					webRequest.getParameter("passcode"));
			if (sessionStartResult.equals("succes")) {
				try {
					requestResult = new JSONObject();
					requestResult.put("actNames", voteService.getSessionActNames(webRequest.getParameter("code")));
					requestResult.put("succes", true);
					requestResult.put("message", sessionStartResult);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				requestResult = new JSONObject();
				try {
					requestResult.put("type", "startSession");
					requestResult.put("succes", false);
					requestResult.put("message", sessionStartResult);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return requestResult;
	}

	@Override
	public JSONObject enterVoteSession(String sessionCode) {
		JSONObject result = new JSONObject();
		if (voteService.canEnterSession(sessionCode)) {
			try {
				result.put("success", true);
				result.put("sessionCode", sessionCode);
				result.put("actNames", voteService.getSessionActNames(sessionCode));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			try {
				result.put("success", false);
				result.put("message", "session pas commence ou session n'est active");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public JSONObject processVoteSessionRequest(WebRequest webRequest, VoterSession voterSession) {
		JSONObject result = new JSONObject();
		String request = webRequest.getParameter("request");
		if (voterSession != null) {
			int clientIndex = voterSession.getCurrentVoteindex();
			String sessionCode = voterSession.getVoteSessionCode();
			if (request.equals("getActNames")) {
				try {
					result.put("success", true);
					result.put("sessionCode", sessionCode);
					result.put("actNames", voteService.getSessionActNames(sessionCode));

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else if (request.equals("update")) {
				int needForUpdate = voteService.needForUpdate(sessionCode, clientIndex);
				if (needForUpdate == 0) { //0 means session does not exist; 1 means update; 2 means no update
					try {
						result.put("update", false);
						result.put("type", 0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else if(needForUpdate == 1) {
					try {
						int currentIndex = voteService.getVoteSessionIndex(sessionCode);
						result.put("update", true);
						result.put("currentIndex", currentIndex);
						voterSession.setCurrentIndex(currentIndex);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						result.put("update", false);
						result.put("type", 2);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else if (request.equals("forceUpdate")) {
				try {
					int currentIndex = voteService.getVoteSessionIndex(sessionCode);
					result.put("currentIndex", currentIndex);
					result.put("update", true);
					result.put("didVote", !voterSession.canVote());
					if (voterSession.getCurrentVoteindex() != currentIndex) {
						voterSession.setCurrentIndex(currentIndex);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			else if (request.equals("submit")) {
				int score = Integer.parseInt(webRequest.getParameter("score"));
				if (score >= 1 && score <= 10 && voterSession.canVote()) {
					voteService.appendScore(sessionCode, score);
					voterSession.lockCurrentVote();
					try {
						result.put("success", true);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					try {
						result.put("success", false);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				result.put("success", false);
				result.put("message", "sessionExpired");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
