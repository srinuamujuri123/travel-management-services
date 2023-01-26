package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
import org.springframework.web.bind.annotation.RestController;

import com.tms.model.TMSResponse;
import com.tms.model.UserBookingDetails;
import com.tms.service.UserBookingService;

@RestController
@RequestMapping("/booking")
public class UserBookingController {

	@Autowired
	UserBookingService userBookingService;
<<<<<<< HEAD

	@PostMapping("save-user-booking")
	public TMSResponse saveUserBooking(@RequestBody UserBookingDetails userBookingDetails) {
		return userBookingService.saveUserBooking(userBookingDetails);
	}

	@GetMapping("get-user-booking-details-by-booking-id")
	public TMSResponse getUserBookingDetailsByBookingId(@RequestParam Integer userBookingId) {
		return userBookingService.getUserBookingDetailsByBookingId(userBookingId);

	}

	@RequestMapping("delete-Booking-Details-By-Booking-Id")
	public TMSResponse deleteBookingDetailsByBookingId(@RequestParam Integer userBookingId) {
		return userBookingService.deleteBookingDetailsByBookingId(userBookingId);
	}

=======
	
	@PostMapping("/save-booking")
	public TMSResponse saveUserBooking (@RequestBody UserBookingDetails userBookingDetails) {
		
		return userBookingService.saveUserBooking(userBookingDetails);
	}
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
}
