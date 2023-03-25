package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/city")
public class CityController {
	@Autowired
	CityService cityService;

	@CacheEvict(value="city", allEntries=true)
	@PostMapping("/save-city-details")
	public TMSResponse saveCityDetails(@RequestBody @Valid CityDetails cityDetails) {
		return cityService.saveCityDetails(cityDetails);
	}

	@GetMapping("/get-city-details-by-id")
	public TMSResponse getCityDetailsById(@RequestParam Integer cityId) {
		return cityService.getCityDetailsById(cityId);
	}

	@Cacheable(value = "city")
	@GetMapping("/get-city-details")
	public TMSResponse getCityDetails(@RequestParam Boolean isActive, @RequestParam(required = false) String search,
			@RequestParam Integer start, @RequestParam Integer end) {
		return cityService.getCityDetails(isActive, search, start, end);
	}
	
	@GetMapping("/get-cities")
	public TMSResponse getCities(@RequestParam(required = false) String search) {
		return cityService.getCities(search);
	}

	@DeleteMapping("/delete-city-details-by-id")
	public TMSResponse deleteCityDetailsById(@RequestParam Integer cityId,
			@RequestParam(required = false) Boolean isActive) {
		return cityService.deleteCityDetailsById(cityId, isActive);
	}

}
