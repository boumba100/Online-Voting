package com.tej.voteEnligne.implement;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

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
	public JSONObject getVoteSession(String code) {
		return null;
	}

	@Override
	public String startVoteSession(String code, String passcode) {
		return voteService.initVoteSession(passcode, code);
	}

	@Override
	public JSONObject processControlRequest(WebRequest webRequest) {
		String requestType = webRequest.getParameter("type");
		JSONObject requestResult = null;
		System.out.println(webRequest.getParameter("type"));
		
		if (requestType.equals("command")) {
			if (webRequest.getParameter("command").equals("nextAct")) {
				requestResult = voteService.nextAct(webRequest.getParameter("code"),
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return requestResult;
	}

}















