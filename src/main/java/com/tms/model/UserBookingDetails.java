package com.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserBookingDetails")
public class UserBookingDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userBookingId;
	Integer userId;
	String cityName;
	String hotelName;
	Integer noOfRoom;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getUserBookingId() {
		return userBookingId;
	}

	public void setUserBookingId(Integer userBookingId) {
		this.userBookingId = userBookingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getNoOfRoom() {
		return noOfRoom;
	}

	public void setNoOfRoom(Integer noOfRoom) {
		this.noOfRoom = noOfRoom;
	}

}
