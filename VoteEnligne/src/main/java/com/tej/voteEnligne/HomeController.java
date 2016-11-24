package com.tej.voteEnligne;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.tej.voteEnligne.implement.ControllerServiceImplement;
import com.tej.voteEnligne.models.VoterSession;
import com.tej.voteEnligne.services.ControllerService;

/**
 * Handles requests for the application home page.
 */
@Controller
@ComponentScan("com.tej.services")
public class HomeController {

	@Autowired
	private static ControllerService controllerService = new ControllerServiceImplement();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	/*@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(@RequestParam("chosenPage") String chosenPage, @RequestParam("code") String code) {
		return chosenPage;
	}*/
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String home(HttpSession session, @RequestParam("sessionCode") String sessionCode) {
		JSONObject result = controllerService.enterVoteSession(sessionCode);
		VoterSession voterSession = null;
		try {
			if(result.getBoolean("success") == true) {
				voterSession = new VoterSession(result.getString("sessionCode"), (JSONArray) result.get("actNames")); 
				session.setAttribute("voterSession", voterSession);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	@RequestMapping(value = "/voteSession", method = RequestMethod.POST)
	@ResponseBody
	public String voteSession(HttpSession session, WebRequest webRequest) { 
		VoterSession voterSession = (VoterSession) session.getAttribute("voterSession");
		return controllerService.processVoteSessionRequest(webRequest, voterSession).toString();
	}

	@RequestMapping(value = "/pageVote", method = RequestMethod.GET)
	public String pageVote() {
		return "pageVote";
	}
	


	@RequestMapping(value = "/creation", method = RequestMethod.GET)
	public String creation() {
		return "CreateVote";
	}

	@RequestMapping(value = "/creation", method = RequestMethod.POST)
	public String creation(@RequestParam("actsString") String actsString, @RequestParam("code") String code,
			@RequestParam("adminCode") String adminCode) {
		controllerService.createVoteSession(code, actsString, adminCode);
		return "CreateVote";
	}
	
	@RequestMapping(value = "controle", method = RequestMethod.GET)
	public String controle() {
		return "controle";
	}
	
	@RequestMapping(value = "controle", method = RequestMethod.POST)
	@ResponseBody
	public String controle(WebRequest webRequest) {
		return controllerService.processControlRequest(webRequest).toString();
	}
	
	
	
}









































