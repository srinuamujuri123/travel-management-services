package com.tms.service;

import com.tms.client.model.TMSResponse;
import com.tms.model.CityDetails;

public interface CityService {

	TMSResponse saveCityDetails(CityDetails cityDetails);

	TMSResponse getCityDetailsById(Integer cityId);

	TMSResponse getCityDetails(Boolean isActive, String search, Integer start, Integer end);

	TMSResponse deleteCityDetailsById(Integer cityId, Boolean status);

	TMSResponse getCities(String search);

}
