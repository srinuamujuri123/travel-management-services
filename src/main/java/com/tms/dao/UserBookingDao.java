package com.tms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.model.UserBookingDetails;

<<<<<<< HEAD
public interface UserBookingDao extends JpaRepository<UserBookingDetails, Integer> {

	UserBookingDetails findByUserBookingId(Integer userBookingId);
	
=======
public interface UserBookingDao extends JpaRepository<UserBookingDetails, Integer>{

	UserBookingDetails findAllByUserBookingId(UserBookingDetails userBookingDetails);
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd

}
