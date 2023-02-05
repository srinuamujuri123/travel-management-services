package com.tms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.Hotel;
import com.tms.common.CommonConstants.User;
import com.tms.dao.HotelDao;
import com.tms.model.HotelDetails;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
import com.tms.service.HotelService;
import com.tms.utils.DateUtils;
import com.tms.utils.TMSUtils;

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
				hotelDetails.setCreatedOn(DateUtils.getTodayDate());
				hotelDetails.setUpdatedOn(DateUtils.getTodayDate());
				hotelDetails.setActive(true);
				response.setData(hotelDao.save(hotelDetails));
				response.setDetails(Hotel.SAVE);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {

			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setDetails(Hotel.ERROR);
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
			response.setDetails(Hotel.UNABLETOFETCHDATA);
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getHotelDetails(Boolean isActive, String search, Integer start, Integer end) {
		TMSResponse response = new TMSResponse();
		List<HotelDetails> hotelDetailsList = new ArrayList<HotelDetails>();
		try {
			Pageable pageable = PageRequest.of(start, end);
			if (StringUtils.isNotEmpty(search)) {
				// if(search != null || !search.isEmpty()) {
				hotelDetailsList = hotelDao.findAllByIsActiveAndHotelNameContaining(isActive, search, pageable);
			} else {
				hotelDetailsList = hotelDao.findAllByIsActive(isActive, pageable);
			}
			if (CollectionUtils.isNotEmpty(hotelDetailsList)) {
				// if(hotelDetailsList != null || hotelDetailsList.size() >0) {
				response.setData(hotelDetailsList);
				response.setCount(hotelDetailsList.size());
			} else {
				response.setDetails(Hotel.LISTNOTFOUND);
			}
			response.setStatus(Status.OK);
			return response;

		} catch (Exception e) {
			response.setDetails(Hotel.UNABLETOFETCHDATA);
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
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
					deleteHotelDetailsById.setUpdatedOn(DateUtils.getTodayDate());
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
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}
}
