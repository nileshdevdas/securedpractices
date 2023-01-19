package com.vinsys.securitylabs.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SecurityHelper {
	
	public static boolean isNullOrEmpty(Object object) {
		return (object == null);
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
	 * Checks the Given array is NULL or EMPTY
	 * 
	 * @param array
	 * @return a boolean value
	 */
	public static boolean isNullOrEmpty(Object[] array) {
		return (array == null) || array.length == 0;
	}

	/*
	 * Checks the Given list is NULL or EMPTY
	 * 
	 * @param list
	 * @return a boolean value
	 */
	public static boolean isNullOrEmpty(List<?> list) {
		return (list == null) || list.isEmpty();
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
	
	
//	public static String sendSuccessResponse(String successMessage, int successCode)
//			throws JsonGenerationException, JsonMappingException, IOException {
//
//		baseResponse.setResponseMessage(successMessage);
//		baseResponse.setResponseCode(successCode);
//		return createJsonString(baseResponse);
//
//	}
//	
//	 public static String sendErrorResponse(String errorMessage, int errorCode)
//				throws JsonGenerationException, JsonMappingException, IOException {
//
//			baseResponse.setResponseMessage(errorMessage);
//			baseResponse.setResponseCode(errorCode);
//			return createJsonString(baseResponse);
//
//		}
	
	public static String encryptToSHA256(String inputStr) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(inputStr.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static String sendErrorResponse(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String sendSuccessResponse(String passwordchangesuccesfullymessage, int successcode) {
		// TODO Auto-generated method stub
		return null;
	}


}
