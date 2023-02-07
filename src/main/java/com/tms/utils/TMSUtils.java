package com.tms.utils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Random;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tms.model.HotelDetails;
@Service
public class TMSUtils {
	
	@Autowired
	RestClient restClient;

	public final static int ZERO = BigDecimal.ZERO.intValue();
	public final static String BOOKINGID_PREFIX = "TMS";
	public final static String HOTEL_VIP = "http://localhost:8081/"; 
	public final static String GET_HOTELDETAILS_BY_CITYNAME_HOTELNAME = "hotel/get-hotel-details-by-hotelname-and-cityname";

	public static String getSQLException(Exception e) {
		if (e.getCause() != null && e.getCause() instanceof GenericJDBCException) {
			GenericJDBCException ge = (GenericJDBCException) e.getCause();
			if (ge.getCause() != null && ge.getCause() instanceof SQLException) {
				SQLException se = (SQLException) ge.getCause();
				return se.getLocalizedMessage();
			}
		}
		return null;
	}

	public static String getExceptionDetails(Exception e) {
		String errorMessage = TMSUtils.getSQLException(e);
		return (errorMessage == null) ? e.getLocalizedMessage() : errorMessage;
	}
	
	public static String generateRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 4) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public HotelDetails getHotelDetailsByHotelNameAndCityName(String cityName, String hotelName) {
		ResponseEntity<HotelDetails> hotelDetails = restClient.getForObject(HOTEL_VIP
				+ GET_HOTELDETAILS_BY_CITYNAME_HOTELNAME + "?hotelName=" + hotelName + "&cityName=" + cityName,
				HotelDetails.class);
		return hotelDetails.getBody();
	}
}
