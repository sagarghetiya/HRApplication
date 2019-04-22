package com.dm.hrapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showEntryPage(ModelMap model) {
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
		/*
		 * if (!result.hasErrors() && principal != null) { // do not redirect for
		 * absolute URLs (i.e. https://evil.com) // do not redirect if we are not
		 * authenticated return "dashboard"; } return "login";
		 */
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegisterPage(ModelMap model) {
		return "registration";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashboardPage(ModelMap model) {
		return "dashboard";
	}

	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String showContactUsPage(ModelMap model) {
		return "contactus";
	}
	
	@RequestMapping(value = "/myworkflow", method = RequestMethod.GET)
	public String showMyWorkflowPage(ModelMap model) {
		return "myworkflow";
	}
	
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public String showInboxPage(ModelMap model) {
		return "inbox";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTestPage(ModelMap model) {
		return "test";
	}
}