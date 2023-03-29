package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tms.client.model.TMSResponse;
import com.tms.model.UserDetails;
import com.tms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/health")
	public String healthcheck() {
		return "working fine";
	}

	@PostMapping("/save-user-details")
	public TMSResponse saveuserdetails(@RequestBody UserDetails userDetails) {
		return userService.saveUserDetails(userDetails);
	}

	@GetMapping("/get-user-details")
	public TMSResponse getuserdetails(@RequestParam Boolean isActive , @RequestParam String search,@RequestParam Integer start, @RequestParam Integer end) {

		return userService.getuserdetails(isActive, search, start, end);
	}

	@GetMapping("/get-user-details-by-id")
	public TMSResponse getUserDetailsById(@RequestParam Integer userId) {
		return userService.getUserDetailsById(userId);
	}

	@DeleteMapping("/delete-user-details-by-id")
	public TMSResponse deleteUserDetailsById(@RequestParam Integer userId, @RequestParam Boolean status) {
		return userService.deleteUserDetailsById(userId, status);
	}
}
