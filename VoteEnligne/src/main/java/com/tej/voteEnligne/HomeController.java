package com.tej.voteEnligne;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(@RequestParam("chosenPage") String chosenPage) {
		return chosenPage;
	}
	
	@RequestMapping(value = "/pageVote", method = {RequestMethod.GET, RequestMethod.POST})
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actResult;
	}
	
}


















