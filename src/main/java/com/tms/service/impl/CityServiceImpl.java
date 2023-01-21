package com.tms.service.impl;

import org.apache.tomcat.util.net.openssl.ciphers.Cipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.City;
import com.tms.common.CommonConstants.Hotel;
import com.tms.dao.CityDao;
import com.tms.model.CityDetails;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
import com.tms.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityDao cityDao;

	@Override
	public TMSResponse saveCityDetails(CityDetails cityDetails) {
		TMSResponse response = new TMSResponse();
		try {
			CityDetails cityNameObj = cityDao.findByCityName(cityDetails.getCityName());
			if (cityNameObj != null && cityDetails.getCityName() != null && cityNameObj.getCityName().equalsIgnoreCase(cityDetails.getCityName())) {
				// given city name should not available in database.
				response.setDetails(City.CITYEXIST);
			} else {
				cityDetails.setActive(true);
				response.setData(cityDao.save(cityDetails));
				response.setDetails(City.SAVE);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(City.ERROR);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}
	// 

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
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse deleteCityDetailsById(Integer cityId, boolean status) {
		TMSResponse response = new TMSResponse();
		try {
			CityDetails cityDetailsByIdobj = cityDao.findByCityId(cityId);
			if (cityDetailsByIdobj != null) {
				cityDao.deleteByCityId(cityId);
				if (status) {
					cityDetailsByIdobj.setActive(status);
					response.setData(cityDao.save(cityDetailsByIdobj));
				}
				response.setDetails(City.DELETED);
			} else {
				response.setDetails(City.CITYNOTEXIST);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(City.UNABLETODELETE);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}
}
