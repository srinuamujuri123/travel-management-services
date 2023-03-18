package com.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data	//lombok used
@Entity
@Table(name = "CityDetails")
public class CityDetails extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cityId;
	String cityName;

//	  public Integer getCityId() { return cityId; }
//	  
//	  public void setCityId(Integer cityId) { this.cityId = cityId; }
//	  
//	  public String getCityName() { return cityName; }
//	  
//	  public void setCityName(String cityName) { this.cityName = cityName; }
//	  
//	  @Override public String toString() { return "CityDetails [cityId=" + cityId +
//	  ", cityName=" + cityName + "]"; }

}
