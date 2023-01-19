package com.vinsys.securitylabs.FileUpload;

import java.util.Date;

public interface IFileUpload {
	
	public static final int FileUploadCode = 128;
	public static final String FileUploadErrorMessage = "File is not selected";
	public static final int FileUploadSizeLimitCode = 127;
	public static final String FileUploadSizeLimitErrorMessage = "Maximum file upload size is 2 MB ";
	public static final String SubmitDocument = "YES";
	public final static int successCode = 0;
	public final static String addedsuccessMessage = "Successfully added";
	public final static int InsufficientDataCode = 108;
	public final static String InsufficientDataMessage = "Insufficient Data";
	public final static String UnknowErrorMessage = "Oops!!! Something Went Wrong";
	public final static int UnknowErrorCode = 101;
	public final static int total_number_records=1;
	public final static String date="11-18-2022";

}
