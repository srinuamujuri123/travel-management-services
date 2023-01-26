package com.tms.service;

import com.tms.model.TMSResponse;
import com.tms.model.UserBookingDetails;

public interface UserBookingService {

	TMSResponse saveUserBooking(UserBookingDetails userBookingDetails);

<<<<<<< HEAD
	TMSResponse getUserBookingDetailsByBookingId(Integer userBookingId);

	TMSResponse deleteBookingDetailsByBookingId(Integer userBookingId);
=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd

}
