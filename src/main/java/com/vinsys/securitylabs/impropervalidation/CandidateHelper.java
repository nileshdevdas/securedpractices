package com.vinsys.securitylabs.impropervalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CandidateHelper {
	public static boolean regexMatcher(String inputStr, String regex) {
		boolean result = false;
		if (!isNullOrEmpty(inputStr) && !isNullOrEmpty(regex)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(inputStr);
			result = matcher.matches();
		}
		return result;

	}
	
	
	/**
	 * Checks the Given value is NULL or EMPTY
	 * 
	 * @param value
	 * @return a boolean value
	 */
	public static boolean isNullOrEmpty(String value) {
		return (value == null) || (value.trim().equals("")) || (value.trim().equals("null"));
	}
	
	
	/**
	 * Checks the Given long is equals to 0
	 * 
	 * @param long
	 * @return a boolean value
	 */
	public static boolean isLongZero(long longNumber) {
		return longNumber == 0;
	}
}
