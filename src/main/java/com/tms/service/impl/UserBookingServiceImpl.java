package com.tms.service.impl;

import static com.tms.utils.TMSUtils.ZERO;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.Hotel;
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
			Integer userRequestedRooms = userBookingDetails.getNoOfRoom() == null ? ZERO
					: userBookingDetails.getNoOfRoom();

			if (ZERO == userRequestedRooms) {
				response.setDetails("Oops! Looks you have selected 0 rooms");
				response.setStatus(Status.OK);
				return response;
			}

			HotelDetails hotelNameObjFromDb = hotelDao.findByHotelNameAndCityName(hotelName, cityName);
			int roomsAvailable = hotelNameObjFromDb.getRoomsAvailable();
			int remainingAvaibleRoomsToSave = roomsAvailable - userRequestedRooms;
			if (roomsAvailable > ZERO && roomsAvailable >= userRequestedRooms) {
				userBookingDetails.setActive(Boolean.TRUE);
				response.setData(userBookingDao.save(userBookingDetails));
				hotelNameObjFromDb.setRoomsAvailable(remainingAvaibleRoomsToSave);
				hotelDao.save(hotelNameObjFromDb);
				response.setDetails("Booking succesful, you have booked " + userRequestedRooms + " rooms.");
			} else {
				if (roomsAvailable < userRequestedRooms) {
					response.setDetails("Oops, available rooms are " + roomsAvailable);
				} else {
					logger.warn("Oops, selected more rooms than available");
					response.setDetails("Booking succesful and remaining rooms are" + remainingAvaibleRoomsToSave);
				}
				response.setStatus(Status.OK);
			}
		} catch (Exception e) {
			response.setDetails("Oops, unable to save data");
			String errorMessage = e.getLocalizedMessage();
			response.setErrorMessage(errorMessage);
			logger.error(errorMessage);
			response.setStatus(Status.FAILED);
		}

		return response;
	}

	@Override
	public TMSResponse getUserBookingDetailsByBookingId(Integer userBookingId) {
		TMSResponse response = new TMSResponse();
		UserBookingDetails userBookingDetailsDb = userBookingDao.findByUserBookingId(userBookingId);

		try {
			boolean bookingDetailsStatus = (userBookingDetailsDb == null)
					|| (userBookingDetailsDb.getUserBookingId() != userBookingId);
			if (bookingDetailsStatus) {
				response.setDetails("Oops no room booking found with Booking Id " + userBookingId);
				response.setStatus(Status.OK);
			} else {
				response.setData(userBookingDetailsDb);
				response.setStatus(Status.OK);
			}
		} catch (Exception e) {
			response.setDetails("Oops unable to fetch data, try after some time.");
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public TMSResponse deleteBookingDetailsByBookingId(Integer userBookingId) {
		TMSResponse response = new TMSResponse();
		try {
			UserBookingDetails userBookingDetailsDb = userBookingDao.findByUserBookingId(userBookingId);
			boolean bookingDetailsNotFound = (userBookingDetailsDb == null);
			if (bookingDetailsNotFound) {
				response.setDetails("Oops no room booking found with Booking Id " + userBookingId);
			} else {
				userBookingDetailsDb.setActive(Boolean.FALSE);
				UserBookingDetails updatedUserBookingDetails = userBookingDao.save(userBookingDetailsDb);

				response.setData(updatedUserBookingDetails);
				response.setDetails("Rooms Cancelled successfully");
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, Unable to cancel rooms, please try after some time.");
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getUserBookingDetails(Boolean isActive, String search) {
		TMSResponse response = new TMSResponse();
		List<UserBookingDetails> userBookingDetailsList = new ArrayList<UserBookingDetails>();
		try {
			if (StringUtils.isNotEmpty(search)) {
				userBookingDetailsList = userBookingDao.findAllByIsActiveAndHotelNameContaining(isActive, search);
			} else {
				userBookingDetailsList = userBookingDao.findAllByIsActive(isActive);
			}
			if (CollectionUtils.isNotEmpty(userBookingDetailsList)) {
				response.setData(userBookingDetailsList);
				response.setCount(userBookingDetailsList.size());
			} else {
				response.setDetails(Hotel.LISTNOTFOUND);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(Hotel.UNABLETOFETCHDATA);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

}
