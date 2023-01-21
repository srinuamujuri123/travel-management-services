package com.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CityDetails")
public class CityDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cityId;
	String cityName;
	boolean isActive;

	@Override
	public String toString() {
		return "CityDetails [cityId=" + cityId + ", cityName=" + cityName + ", isActive=" + isActive + "]";
	}

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
