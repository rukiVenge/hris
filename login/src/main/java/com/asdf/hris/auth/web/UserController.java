package com.asdf.hris.auth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asdf.hris.auth.model.Login;
import com.asdf.hris.auth.model.User;
import com.asdf.hris.auth.service.SecurityService;
import com.asdf.hris.auth.service.SecurityServiceImpl;
import com.asdf.hris.auth.service.UserService;
import com.asdf.hris.auth.validator.UserValidator;

/**
 * @author markkelvinpineda
 *
 */

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserValidator userValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
		
		userValidator.validate(userForm, bindingResult);
		
		logger.debug("dto is " + userForm.toString());
		
		if(bindingResult.hasErrors()){
			return "registration";
		}
		
		userService.save(userForm);
		
		securityService.autologin(userForm.getUsername(), userForm.getPassword());
		
		return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Login login) {

        return new ResponseEntity("Successfully login", new HttpHeaders(), HttpStatus.OK);

    }
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model, String error, String logout){
//		if(error != null)
//			model.addAttribute("error", "Your username and password is invalid");
//		
//		if(logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
//		
//		return "login";
//	}
	
	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcome(Model model){
		return "welcome";
	}
	

}
