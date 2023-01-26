package com.tms.model;

<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserBookingDetails")
public class UserBookingDetails extends BaseEntity {
<<<<<<< HEAD

=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userBookingId;
	Integer userId;
	String cityName;
	String hotelName;
	Integer noOfRoom;
<<<<<<< HEAD
	Date fromDate;
	Date toDate;
=======

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd

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

<<<<<<< HEAD
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
	public Integer getNoOfRoom() {
		return noOfRoom;
	}

	public void setNoOfRoom(Integer noOfRoom) {
		this.noOfRoom = noOfRoom;
	}

<<<<<<< HEAD
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
}
