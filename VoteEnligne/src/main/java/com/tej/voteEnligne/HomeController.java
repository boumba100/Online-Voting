package com.tej.voteEnligne;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tej.voteEnligne.implement.ControllerServiceImplement;
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

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(@RequestParam("chosenPage") String chosenPage, @RequestParam("code") String code) {
		return chosenPage;
	}

	@RequestMapping(value = "/pageVote", method = { RequestMethod.GET, RequestMethod.POST })
	public String pageVote() {
		return "pageVote";
	}

	@RequestMapping(value = "/getAct", method = RequestMethod.GET)
	@ResponseBody
	public String getAct() {
		String actResult = null;
		try {
			actResult = new JSONObject().put("test", "test").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return actResult;
	}

	@RequestMapping(value = "/creation", method = RequestMethod.GET)
	public String creation() {
		return "CreateVote";
	}

	@RequestMapping(value = "/creation", method = RequestMethod.POST)
	public String creation(@RequestParam("actsString") String actsString, @RequestParam("code") String code,
			@RequestParam("adminCode") String adminCode) {
		System.out.println(controllerService.createVoteSession(code, actsString, adminCode));
		return "CreateVote";
	}
	
	@RequestMapping(value = "controle", method = RequestMethod.GET)
	public String controle() {
		return "controle";
	}

}









































