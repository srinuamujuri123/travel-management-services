package com.tms.service.impl;

import static com.tms.utils.TMSUtils.ZERO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants;
import com.tms.common.CommonConstants.Hotel;
import com.tms.dao.HotelDao;
import com.tms.dao.UserBookingDao;
import com.tms.model.HotelDetails;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
import com.tms.model.UserBookingDetails;
import com.tms.service.UserBookingService;
import com.tms.utils.DateUtils;
import com.tms.utils.TMSUtils;

@Service
public class UserBookingServiceImpl implements UserBookingService {

	@Autowired
	UserBookingDao userBookingDao;
	@Autowired
	HotelDao hotelDao;

	static Logger logger = LoggerFactory.getLogger(UserBookingServiceImpl.class);

	@Override
	public TMSResponse saveUserBooking(UserBookingDetails userBookingDetails, String bookingFromDate,
			String bookingToDate) {
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
			if (hotelNameObjFromDb == null) {
				response.setDetails(Hotel.RECORDNOTFOUND);
				return response;
			}
			int roomsAvailable = hotelNameObjFromDb.getRoomsAvailable();
			int remainingAvaibleRoomsToSave = roomsAvailable - userRequestedRooms;
			if (roomsAvailable > ZERO && roomsAvailable >= userRequestedRooms) {
				userBookingDetails.setActive(Boolean.TRUE);
				userBookingDetails.setCreatedOn(DateUtils.getCurrentDate());
				userBookingDetails.setUpdatedOn(DateUtils.getCurrentDate());
				Date fromDate = DateUtils.getDateFromStringDate(bookingFromDate);
				Date toDate = DateUtils.getDateFromStringDate(bookingToDate);
				userBookingDetails.setFromDate(fromDate);
				userBookingDetails.setToDate(toDate);
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
			String errorMessage = TMSUtils.getExceptionDetails(e);
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
			String errorMessage = TMSUtils.getExceptionDetails(e);
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

				if (DateUtils.getCurrentDate().before(userBookingDetailsDb.getFromDate())) {
					userBookingDetailsDb.setActive(Boolean.FALSE);
					userBookingDetailsDb.setUpdatedOn(DateUtils.getCurrentDate());
					UserBookingDetails updatedUserBookingDetails = userBookingDao.save(userBookingDetailsDb);
					HotelDetails userHotelDetails = hotelDao.getHotelDetailsByHotelNameAndCityName(userBookingDetailsDb.getHotelName(), userBookingDetailsDb.getCityName());
					int availableHotelRoooms = userHotelDetails.getRoomsAvailable() + userBookingDetailsDb.getNoOfRoom();
					userHotelDetails.setRoomsAvailable(availableHotelRoooms);
					hotelDao.save(userHotelDetails);
					response.setData(updatedUserBookingDetails);
					response.setDetails(Hotel.ROOMCANCELLED);
				} else {
					response.setDetails(Hotel.REACHEDCANCELLATIONTIME);
				}

			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, Unable to cancel rooms, please try after some time.");
			String errorMessage = TMSUtils.getExceptionDetails(e);
			response.setErrorMessage(errorMessage);
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
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

}
