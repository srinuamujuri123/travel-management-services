package com.tms.utils;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.hibernate.exception.GenericJDBCException;

public class TMSUtils {

	public final static int ZERO = BigDecimal.ZERO.intValue();

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
}
