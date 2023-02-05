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
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	HotelService hotelService;

	@PostMapping("/save-hotel-details")
	public TMSResponse saveHotelDetails(@RequestBody HotelDetails hotelDetails) {
		return hotelService.saveHotelDetails(hotelDetails);
	}
	
	@GetMapping("/get-hotel-details")
	public TMSResponse getHotelDetails(@RequestParam Boolean isActive, @RequestParam String search) {
		return hotelService.getHotelDetails(isActive, search);
	}

	@GetMapping("/get-hotel-details-by-id")
	public TMSResponse getHotelDetailsById(@RequestParam Integer hotelId) {
		return hotelService.getHotelDetails(hotelId);
	}

	@DeleteMapping("delete-hotel-details-by-id")
	public TMSResponse deleteHotelDetailsById(@RequestParam Integer hotelId, @RequestParam(required = false) boolean status) {
		return hotelService.deleteHotelDetailsById(hotelId, status);
	}

}
