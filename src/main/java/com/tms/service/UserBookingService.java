package com.tms.service;

import com.tms.model.TMSResponse;
import com.tms.model.UserBookingDetails;

public interface UserBookingService {

	TMSResponse saveUserBooking(UserBookingDetails userBookingDetails, String bookingFromDate, String bookingToDate);

	TMSResponse getUserBookingDetailsByBookingId(Integer userBookingId);

	TMSResponse deleteBookingDetailsByBookingId(Integer userBookingId);

	TMSResponse getUserBookingDetails(Boolean isActive, String search, Integer start, Integer end);

}
