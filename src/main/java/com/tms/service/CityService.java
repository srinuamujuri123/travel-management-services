package com.tms.service;

import com.tms.model.CityDetails;
import com.tms.model.TMSResponse;

public interface CityService {

	TMSResponse saveCityDetails(CityDetails cityDetails);

	TMSResponse getCityDetailsById(Integer cityId);

	TMSResponse deleteCityDetailsById(Integer cityId, Boolean status);

	TMSResponse getCityDetails(Boolean isActive);

}
