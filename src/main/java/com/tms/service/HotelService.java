package com.tms.service;

import com.tms.model.HotelDetails;
import com.tms.model.TMSResponse;

public interface HotelService {

	TMSResponse saveHotelDetails(HotelDetails hotelDetails);

	TMSResponse getHotelDetails(Integer id);

	TMSResponse DeleteHotelDetailsById(Integer hotelId, Boolean status);



}
