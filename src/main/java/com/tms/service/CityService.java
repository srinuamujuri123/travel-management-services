package com.tms.service;

import com.tms.model.CityDetails;
import com.tms.model.TMSResponse;

public interface CityService {

	TMSResponse saveCityDetails(CityDetails cityDetails);

	TMSResponse getCityDetailsById(Integer cityId);

	TMSResponse getCityDetails(Boolean isActive, String search);

	TMSResponse deleteCityDetailsById(Integer cityId, Boolean status);

}
