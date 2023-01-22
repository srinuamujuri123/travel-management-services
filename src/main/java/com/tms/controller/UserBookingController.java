package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.model.TMSResponse;
import com.tms.model.UserBookingDetails;
import com.tms.service.UserBookingService;

@RestController
@RequestMapping("/booking")
public class UserBookingController {

	@Autowired
	UserBookingService userBookingService;
	
	@PostMapping("/save-booking")
	public TMSResponse saveUserBooking (@RequestBody UserBookingDetails userBookingDetails) {
		
		return userBookingService.saveUserBooking(userBookingDetails);
	}
}
