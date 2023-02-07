package com.tms.scheduler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tms.dao.UserBookingDao;
import com.tms.model.UserBookingDetails;
import com.tms.utils.DateUtils;

@Service
public class TMSScheduler {

	@Autowired
	UserBookingDao userBookingDao;
	
<<<<<<< HEAD
	//@Scheduled(cron = "*/10 * * * * *")
=======
	//@Scheduled(cron = "*/10 * * * * *")	//if you active this then the Scheduler will run every 10 seconds.
>>>>>>> fb7ebcb9627a1f36ccc2aa2d490c3aa5128e2613
	public void saveUserDetailsAsInactive() throws ParseException {
		Date yesterdayDate = DateUtils.addDays(DateUtils.getCurrentDate(), -1);
		List<UserBookingDetails> userBookingDBList = userBookingDao.findAllByIsActiveAndToDate(Boolean.TRUE, yesterdayDate);
		if (CollectionUtils.isNotEmpty(userBookingDBList)) {
			userBookingDBList.forEach(userBookingDetail -> userBookingDetail.setActive(Boolean.FALSE));
			userBookingDao.saveAll(userBookingDBList);
		}
	}
}
