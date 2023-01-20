package com.tms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.User;
import com.tms.dao.HotelDao;
import com.tms.model.HotelDetails;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
import com.tms.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelDao hotelDao;

	@Override
	public TMSResponse saveHotelDetails(HotelDetails hotelDetails) {
		TMSResponse response = new TMSResponse();
		try {
			HotelDetails hotelNameObj = hotelDao.findByHotelName(hotelDetails.getHotelName());
			HotelDetails hotelContactObj = hotelDao.findByHotelContact(hotelDetails.getHotelContact());
			// HotelDetails hotelCityNameObj=
			// hotelDao.findByCityName(hotelDetails.getCityName());
			if (hotelNameObj == null && hotelContactObj == null) {
				hotelDetails.setActive(true);
				response.setData(hotelDao.save(hotelDetails));
				response.setDetails(User.SAVE);
			} else if (hotelNameObj != null) {
				response.setDetails("hotel name existing");
			} else if (hotelContactObj != null) {
				response.setDetails("hotel contact existing");
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, Unable to save data.");
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getHotelDetails(Integer Id) {
		TMSResponse response = new TMSResponse();
		try {
			Optional<HotelDetails> hotelDetailsById = hotelDao.findByHotelId(Id);
			if (hotelDetailsById.isPresent()) {
				response.setData(hotelDetailsById.get());
			} else {
				response.setData("Oops no data found for " + Id);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, Unable to fetch Data, please try after some time.");
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse DeleteHotelDetailsById(Integer Id, Boolean status) {
		TMSResponse response = new TMSResponse();
		try {
			HotelDetails deleteHotelDetailsById = hotelDao.findById(Id); // Optional<HotelDetails>
			if (deleteHotelDetailsById != null) {
				if (status) {
					hotelDao.deleteByHotelId(Id);

				} else {
					deleteHotelDetailsById.setActive(status);
					response.setData(hotelDao.save(deleteHotelDetailsById));
				}
				response.setDetails(User.DELETED);
			} else {
				response.setDetails(User.RECORDNOTFOUND);
			}
			response.setStatus(Status.OK);
		}

		catch (Exception e) {
			response.setDetails(User.UNABLETODELETE);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}

		return response;
	}

}
