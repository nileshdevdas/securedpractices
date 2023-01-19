package com.vinsys.securitylabs.FileUpload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vinsys.securitylabs.FileUpload.Helper.FilUploadHelper;
import com.github.sardine.Sardine;
import com.vinsys.securitylabs.FileUpload.FileUploadResponse;



@RestController
@RequestMapping(path = "/fileupload/problem")
public class FileUploadService {
	
	@Value("${base.webdav.url}")
	private String baseURL;
	
	@Value("${MaxFileUploadSize}")
	private long MaxSize;

	
	

	
	Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	
	@RequestMapping(path = "/addFile",method = {RequestMethod.POST,RequestMethod.PUT},produces = "application/json")
	@ResponseBody
	public String upload(@RequestParam(value="file") MultipartFile request,@RequestPart(value="filevo") FileUploadVo filevo) throws FileUploadException {
		try {
			if(FilUploadHelper.isNullOrEmpty(request)) {
				throw new FileUploadException(IFileUpload.FileUploadCode,
						IFileUpload.FileUploadErrorMessage);
			}
			
			if(!FilUploadHelper.isNullOrEmpty(request)) {
				
				String path = "";
				
				
				FileUploadResponse response = new FileUploadResponse();
				response = null;
				if(!FilUploadHelper.isNullOrEmpty(filevo.name)) {
					path = baseURL + "/webdav/uploadedFiles";
					FileUploadResponse filedetails =new FileUploadResponse();
					filedetails.setFile_name(filevo.getName());
					filedetails.setPath("/webdav/uploadedFiles"+ request.getOriginalFilename().replaceAll("\\s+", "_"));
					filedetails.setSubmitted(IFileUpload.SubmitDocument);
					System.out.println("Check path here ->> " + path);
					boolean val = FilUploadHelper.uploadFile(request, path);
					if(val)
						System.out.println("File successfully Uploaded");
					else
						System.out.println("file not uploded");
					
					List<Object> tempList = new ArrayList<>();
					tempList.add(response);
					
					FileUploadResponse fileresponse = new FileUploadResponse();
					fileresponse.setResponseCode(IFileUpload.successCode);
					fileresponse.setResponseMessage(IFileUpload.addedsuccessMessage);
					fileresponse.setListResponse(tempList);
					return FilUploadHelper.createJsonString(fileresponse);
				} else {
						throw new FileUploadException(IFileUpload.InsufficientDataCode,IFileUpload.InsufficientDataMessage);
				}
			}
		} catch(FileUploadException ex) {
			ex.printStackTrace();
			try {
				return FilUploadHelper.sendErrorResponse(ex.getResponseMessage(),ex.getResponseCode());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e1) {
			try {
				e1.printStackTrace();
				return FilUploadHelper.sendErrorResponse(IFileUpload.UnknowErrorMessage, IFileUpload.UnknowErrorCode);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		 return null;
}
}
