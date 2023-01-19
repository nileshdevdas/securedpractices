package com.vinsys.securitylabs.FileUpload.Helper;

import java.io.IOException;



import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.vinsys.securitylabs.FileUpload.FileUploadException;
import com.vinsys.securitylabs.FileUpload.IFileUpload;
import com.vinsys.securitylabs.leastpriviledge.request.BaseResponse;

public class FilUploadHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(FilUploadHelper.class);
	public static ObjectMapper mapper = new ObjectMapper();
	public static BaseResponse baseResponse = new BaseResponse();
	public static Base64 base64 = new Base64();
	
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}
	
	public static String createJsonString(Object obj)
			throws JsonGenerationException, JsonMappingException, IOException {

		return mapper.writeValueAsString(obj);

	}
	public static String sendErrorResponse(String errorMessage, int errorCode)
			throws JsonGenerationException, JsonMappingException, IOException {

		baseResponse.setResponseMessage(errorMessage);
		baseResponse.setResponseCode(errorCode);
		return createJsonString(baseResponse);

	}
	
	public static String sendSuccessResponse(String successMessage, int successCode)
			throws JsonGenerationException, JsonMappingException, IOException {

		baseResponse.setResponseMessage(successMessage);
		baseResponse.setResponseCode(successCode);
		return createJsonString(baseResponse);

	}
	public static boolean isNullOrEmpty(Object object) {
		return (object == null);
	}
	
	public static boolean isNullOrEmpty(String value) {
		return (value == null) || (value.trim().equals("")) || (value.trim().equals("null"));
	}
	
	public static boolean uploadFile(MultipartFile multipartFile, String path) throws IOException, FileUploadException {

		boolean result = false;
		if (multipartFile != null) {
			byte[] bytes = multipartFile.getBytes();
			String str = path + "/"
					+ multipartFile.getOriginalFilename().replaceAll("\\s+", "_");
			System.out.println("Str:"+str+ "bytes:"+bytes);
			logger.info("File Saved at : " + path);
			result = true;
		}
		return result;
	}
	


}
