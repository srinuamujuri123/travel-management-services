package com.tms.service.impl;

import static com.tms.utils.TMSUtils.ZERO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.dao.HotelDao;
import com.tms.dao.UserBookingDao;
import com.tms.model.HotelDetails;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
import com.tms.model.UserBookingDetails;
import com.tms.service.UserBookingService;
@Service
public class UserBookingServiceImpl implements UserBookingService {

	@Autowired
	UserBookingDao userBookingDao;
	@Autowired
	HotelDao hotelDao;
	
	static Logger logger = LoggerFactory.getLogger(UserBookingServiceImpl.class);

	@Override
	public TMSResponse saveUserBooking(UserBookingDetails userBookingDetails) {
		TMSResponse response = new TMSResponse();

		try {
			String cityName = userBookingDetails.getCityName();
			String hotelName = userBookingDetails.getHotelName();
			Integer userRequestedRooms = userBookingDetails.getNoOfRoom() == null ? ZERO : userBookingDetails.getNoOfRoom();

			if (ZERO == userRequestedRooms) {
				response.setDetails("Oops! Looks you have selected 0 rooms");
				response.setStatus(Status.OK);
				return response;
			}

			HotelDetails hotelNameObjFromDb = hotelDao.findByHotelNameAndCityName(hotelName, cityName);
			
			int roomsAvailable = hotelNameObjFromDb.getRoomsAvailable();
			if (roomsAvailable > ZERO && roomsAvailable >= userRequestedRooms) {
				userBookingDetails.setActive(Boolean.TRUE);
				response.setData(userBookingDao.save(userBookingDetails));
				int remainingAvaibleRoomsToSave = roomsAvailable - userRequestedRooms;
				hotelNameObjFromDb.setRoomsAvailable(remainingAvaibleRoomsToSave);
				hotelDao.save(hotelNameObjFromDb);
				response.setDetails("Booking succesful and remaining rooms are" +  remainingAvaibleRoomsToSave);
			} else {
				if (roomsAvailable < userRequestedRooms) {
					response.setDetails("Oops, available rooms are " + roomsAvailable);
				}else {
					logger.warn("Something went");
				}
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, unable to save data");
			String errorMessage = e.getLocalizedMessage();
			response.setErrorMessage(errorMessage);
			logger.error(errorMessage);
			response.setStatus(Status.FAILED);
		}

		return response;
	}

}
