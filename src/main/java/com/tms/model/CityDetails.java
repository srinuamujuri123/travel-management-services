package com.tms.model;

import com.tms.annotations.NullOrNotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CityDetails")
public class CityDetails extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NullOrNotBlank(message = "Oops!!! City Id Empty")
	Integer cityId;
	@NullOrNotBlank(message = "Oops!!! City Name Empty")
	String cityName;

}
