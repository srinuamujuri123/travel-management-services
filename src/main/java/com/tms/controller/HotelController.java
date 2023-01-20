package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tms.model.HotelDetails;
import com.tms.model.TMSResponse;
import com.tms.service.HotelService;

@RestController
@RequestMapping("/user")
public class HotelController {
	@Autowired
	HotelService hotelService;

	@GetMapping
	public String healthCheck() {
		return "working fine";
	}

	@PostMapping("/save-Hotel-Details")
	public TMSResponse saveHotelDetails(@RequestBody HotelDetails hotelDetails) {
		return hotelService.saveHotelDetails(hotelDetails);

	}
	
	@GetMapping("/get-Hotel-Details-By-Id")
	public TMSResponse getHotelDetails(@RequestParam Integer hotelId) {
		return hotelService.getHotelDetails(hotelId);
	}
	
	@DeleteMapping("Delete=Hotel=Details=ById")
	public TMSResponse DeleteHotelDetailsById(@RequestParam Integer hotelId, @RequestParam Boolean status) {
		return hotelService.DeleteHotelDetailsById(hotelId, status);
	}

}
