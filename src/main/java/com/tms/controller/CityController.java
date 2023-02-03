package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tms.model.CityDetails;
import com.tms.model.TMSResponse;
import com.tms.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	@Autowired
	CityService cityService;

	@PostMapping("/save-city-details")
	public TMSResponse saveCityDetails(@RequestBody CityDetails cityDetails) {
		return cityService.saveCityDetails(cityDetails);
	}

	@GetMapping("/get-city-details-by-id")
	public TMSResponse getCityDetailsById(@RequestBody CityDetails cityDetails, @RequestParam Integer cityId) {
		return cityService.getCityDetailsById(cityId);
	}
	
	@GetMapping("/get-city-details")
	public TMSResponse getCityDetails(@RequestParam Boolean isActive, @RequestParam(required = false) String search,@RequestParam Integer start, @RequestParam Integer end) {
		return cityService.getCityDetails(isActive, search, start, end);
	}

	@DeleteMapping("/delete-city-details-by-id")
	public TMSResponse deleteCityDetailsById(@RequestParam Integer cityId,
			@RequestParam(required = false) Boolean isActive) {
		return cityService.deleteCityDetailsById(cityId, isActive);
	}

}
