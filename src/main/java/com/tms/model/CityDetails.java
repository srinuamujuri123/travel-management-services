package com.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CityDetails")
<<<<<<< HEAD
public class CityDetails extends BaseEntity {
=======
public class CityDetails extends BaseEntity{
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cityId;
	String cityName;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CityDetails [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
	@Override
	public String toString() {
		return "CityDetails [cityId=" + cityId + ", cityName=" + cityName + "]";
	}


}
