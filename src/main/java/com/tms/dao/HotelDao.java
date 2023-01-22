package com.tms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tms.model.HotelDetails;

@Transactional
public interface HotelDao extends JpaRepository<HotelDetails, Integer> {

	HotelDetails findByHotelName(String hotelName);

	HotelDetails findByHotelContact(Integer hotelContact);

	HotelDetails findByHotelId(Integer Id);
	
	HotelDetails deleteByHotelId(Integer hotelId);

	HotelDetails findByCityName(String cityName);

	HotelDetails findByHotelNameAndCityName(String hotelName, String cityName);

}
