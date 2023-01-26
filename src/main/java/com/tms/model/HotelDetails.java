package com.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HotelDetails")
public class HotelDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer hotelId;
	String hotelName;
	String agentName;
	String hotelAddress;
	Integer hotelContact;
	Integer roomsAvailable;
	String cityName;
<<<<<<< HEAD
	Integer roomsAvailable;
=======

>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public Integer getHotelContact() {
		return hotelContact;
	}

	public void setHotelContact(Integer hotelContact) {
		this.hotelContact = hotelContact;
	}

	public Integer getRoomsAvailable() {
		return roomsAvailable;
	}

	public void setRoomsAvailable(Integer roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
<<<<<<< HEAD
	public Integer getRoomsAvailable() {
		return roomsAvailable;
	}
	public void setRoomsAvailable(Integer roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	
=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd

}
