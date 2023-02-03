package com.tms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.City;
import com.tms.dao.CityDao;
import com.tms.model.CityDetails;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
import com.tms.service.CityService;
import com.tms.utils.DateUtils;
import com.tms.utils.TMSUtils;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityDao cityDao;

	@Override
	public TMSResponse saveCityDetails(CityDetails cityDetails) {
		TMSResponse response = new TMSResponse();
		if (cityDetails.getCityName() == null) {
			response.setDetails("Invalid request parameters" + cityDetails.toString());
			return response;
		}
		try {
			CityDetails cityNameDBObj = cityDao.findByCityNameIgnoreCase(cityDetails.getCityName());
			if (cityNameDBObj != null) {
				response.setDetails(City.CITYEXIST);
			} else {
				cityDetails.setCreatedOn(DateUtils.getTodayDate());
				cityDetails.setUpdatedOn(DateUtils.getTodayDate());
				cityDetails.setActive(true);
				response.setData(cityDao.save(cityDetails));
				response.setDetails(City.SAVE);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(City.ERROR);
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getCityDetailsById(Integer cityId) {
		TMSResponse response = new TMSResponse();
		try {
			CityDetails cityDetailsByIdObj = cityDao.findByCityId(cityId);
			if (cityDetailsByIdObj != null) {
				response.setData(cityDetailsByIdObj);
			} else {
				response.setData("Oops, city not found with the Id " + cityId);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, Unable to fetch Data, try after some time.");
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse deleteCityDetailsById(Integer cityId, Boolean isActive) {
		TMSResponse response = new TMSResponse();
		try {
			CityDetails cityDetailsByIdobj = cityDao.findByCityId(cityId);
			if (cityDetailsByIdobj != null) {
				isActive = isActive == null ? false : isActive; // if isActive is null then make it as false else take
																// it as isActive
				if (isActive) {
					cityDao.deleteByCityId(cityId);
				} else {
					cityDetailsByIdobj.setActive(isActive);
					cityDetailsByIdobj.setUpdatedOn(DateUtils.getTodayDate());
					response.setData(cityDao.save(cityDetailsByIdobj));
				}

				response.setDetails(City.DELETED);
			} else {
				response.setDetails(City.CITYNOTEXIST);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(City.UNABLETODELETE);
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getCityDetails(Boolean isActive, String search, Integer start, Integer end) {
		TMSResponse response = new TMSResponse();
		List<CityDetails> cityDetailsList = new ArrayList();
		try {
			Pageable pageable = PageRequest.of(start, end);
			if (StringUtils.isNotEmpty(search)) {
				cityDetailsList = cityDao.findAllByIsActiveAndCityNameContaining(isActive, search, pageable);
			} else {
				cityDetailsList = cityDao.findAllByIsActive(isActive, pageable);
			}

			if (CollectionUtils.isNotEmpty(cityDetailsList)) {
				response.setData(cityDetailsList);
				response.setCount(cityDetailsList.size());
				response.setDetails(City.LISTFOUND);
			} else {
				response.setDetails(City.LISTNOTFOUND);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(City.CITYNOTEXIST);
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}
}
