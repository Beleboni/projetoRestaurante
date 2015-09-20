package br.com.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDate toLocalDate(String date, String mask) {
		return date != null ? LocalDate.parse(date,
				DateTimeFormatter.ofPattern(mask)) : null;
	}

	public static LocalDate toLocalDate(String date) {
		return date != null ? LocalDate.parse(date,
				DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
	}
	
	public static String toString(LocalDate date){
		return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Ativo";
	}

}
