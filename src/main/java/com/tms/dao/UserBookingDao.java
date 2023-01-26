package com.tms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.model.UserBookingDetails;

public interface UserBookingDao extends JpaRepository<UserBookingDetails, Integer> {

	UserBookingDetails findByUserBookingId(Integer userBookingId);
	

}
