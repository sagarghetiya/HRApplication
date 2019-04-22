package com.dm.hrapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.dm.hrapplication.model.LoginWrapper;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;
import com.dm.hrapplication.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<Void> doLogin(@Valid @RequestBody LoginWrapper loginWrapper, UriComponentsBuilder builder) {
		System.out.println("inside login method");
		boolean flag = userService.doLogin(loginWrapper.getUsername(), loginWrapper.getPassword());
		if (flag == true) {
			System.out.println("Returning 200");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			System.out.println("Returning 201");
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/getuserdetails/{username}")
	public ResponseEntity<List<User>> getUserDetails(@PathVariable(value = "username") String username) {
		List<User> userDetailList = userService.getUserDetails(username);
		return new ResponseEntity<List<User>>(userDetailList, HttpStatus.OK);
	}
	
	@PostMapping("/addusergroup")
	public ResponseEntity<Void> addUserGroup(@Valid @RequestBody UserGroup userGroup, UriComponentsBuilder builder) {
		userService.addUserGroup(userGroup);
		return new ResponseEntity<Void>(HttpStatus.OK); 
	}
	
	@GetMapping("/getallusergroup")
	public ResponseEntity<List<UserGroup>> getAllUserGroups(){
		return new ResponseEntity<List<UserGroup>>(userService.getAllGroups(),HttpStatus.OK);
	}
	
}
