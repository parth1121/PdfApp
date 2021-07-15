package com.codeplanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeplanet.model.User;
import com.codeplanet.service.HomeService;

@RestController
public class ApiController {
@Autowired
HomeService hs;
	@PostMapping(value="/apiget")
	public User api1(@RequestBody User u1) {
		System.out.println(u1.getEmailId());;
		User u=hs.getUser();
		return u;
	}
	@PostMapping(value="tests")
	public User tests(@RequestBody User u)
	{
		System.out.println(u.getEmailId());;
		User u1=new User();
		return u1;
	}
}
