package com.tms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.model.UserBookingDetails;

public interface UserBookingDao extends JpaRepository<UserBookingDetails, Integer> {

	UserBookingDetails findAllByUserBookingId(UserBookingDetails userBookingDetails);

	UserBookingDetails findByUserBookingId(Integer userBookingId);

	List<UserBookingDetails> findAllByIsActive(Boolean isActive);

	List<UserBookingDetails> findAllByIsActiveAndHotelNameContaining(Boolean isActive, String search);

}
