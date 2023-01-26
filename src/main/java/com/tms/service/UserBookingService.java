package com.tms.service;

import com.tms.model.TMSResponse;
import com.tms.model.UserBookingDetails;

public interface UserBookingService {

	TMSResponse saveUserBooking(UserBookingDetails userBookingDetails);

	TMSResponse getUserBookingDetailsByBookingId(Integer userBookingId);

	TMSResponse deleteBookingDetailsByBookingId(Integer userBookingId);

}
