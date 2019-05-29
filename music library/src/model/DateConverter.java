package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
	/**
	 * This method converts java.util.Date date into a LocalDate from
	 * java.time.LocalDate
	 * 
	 * @param dateToConvert The date we want to convert
	 * @return
	 */
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * This method converts java.util.Date date into a LocalDateTime from
	 * java.time.LocalDateTime
	 * 
	 * @param dateToConvert The date we want to convert
	 * @return
	 */
	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * This method converts java.time.LocalDate date into a java.util.Date date
	 * 
	 * @param dateToConvert The date we want to convert
	 * @return
	 */
	public static Date convertToDateViaInstant(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * This method converts java.time.LocalDateTime date into a java.util.Date date
	 * 
	 * @param dateToConvert The date we want to convert
	 * @return
	 */
	public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}
