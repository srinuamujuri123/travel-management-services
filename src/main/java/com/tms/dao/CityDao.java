package com.tms.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tms.model.CityDetails;
import com.tms.model.CityNamesView;

@Transactional
public interface CityDao extends JpaRepository<CityDetails, Integer> {

	CityDetails findByCityName(String cityName);

	CityDetails findByCityId(Integer cityId);

	void deleteByCityId(Integer cityId);

	CityDetails findByCityNameIgnoreCase(String cityName);

	List<CityDetails> findAllByIsActive(Boolean isActive, Pageable pageable);

	List<CityDetails> findAllByIsActiveAndCityNameContaining(Boolean isActive, String search, Pageable pageable);

	List<CityNamesView> findAllProjectedBy();

	List<CityNamesView> findAllByCityNameContains(String search);
	
	@Query("SELECT c.cityName FROM CityDetails c")
	List<String> getCityNameContains(String search);

}
