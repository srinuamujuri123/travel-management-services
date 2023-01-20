package com.tms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.Hotel;
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
			HotelDetails hotelNameObjFromDb = hotelDao.findByHotelName(hotelDetails.getHotelName());
			boolean isHotelExist = (hotelNameObjFromDb != null) && (hotelNameObjFromDb.getCityName() != null)
					&& (hotelNameObjFromDb.getCityName().equalsIgnoreCase(hotelDetails.getCityName()));
			if (isHotelExist) {
				response.setDetails(Hotel.HOTELEXIST);
			} else {
				hotelDetails.setActive(true);
				response.setData(hotelDao.save(hotelDetails));
				response.setDetails(Hotel.SAVE);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(Hotel.ERROR);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getHotelDetails(Integer hotelId) {
		TMSResponse response = new TMSResponse();
		try {
			HotelDetails hotelDetailsById = hotelDao.findByHotelId(hotelId);
			if (hotelDetailsById != null) { // isPresent() and .get are optional method.
				response.setData(hotelDetailsById);
			} else {
				response.setData("Oops no data found for " + hotelId);
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
	public TMSResponse deleteHotelDetailsById(Integer hotelId, boolean status) {
		TMSResponse response = new TMSResponse();
		try {
			HotelDetails deleteHotelDetailsById = hotelDao.findByHotelId(hotelId); // Optional<HotelDetails>
			if (deleteHotelDetailsById != null) {
				if (status) {
					hotelDao.deleteByHotelId(hotelId);
				} else {
					deleteHotelDetailsById.setActive(status);
					response.setData(hotelDao.save(deleteHotelDetailsById));
				}
				response.setDetails(Hotel.DELETED);
			} else {
				response.setDetails(Hotel.RECORDNOTFOUND);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(User.UNABLETODELETE);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}
}
