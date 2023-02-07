package com.tms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.model.UserBookingDetails;

public interface UserBookingDao extends JpaRepository<UserBookingDetails, Integer> {

	UserBookingDetails findAllByUserBookingId(UserBookingDetails userBookingDetails);

	UserBookingDetails findByUserBookingId(Integer userBookingId);

	List<UserBookingDetails> findAllByIsActive(Boolean isActive, org.springframework.data.domain.Pageable pageable);

	List<UserBookingDetails> findAllByIsActiveAndHotelNameContaining(Boolean isActive, String search, Pageable pageable);

	List<UserBookingDetails> findAllByIsActiveAndToDate(Boolean true1, Date currentDate);


}
