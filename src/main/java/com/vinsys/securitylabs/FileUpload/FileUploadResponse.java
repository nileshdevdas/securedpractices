package com.vinsys.securitylabs.FileUpload;

import java.util.List;

import com.vinsys.securitylabs.leastpriviledge.request.BaseResponse;

public class FileUploadResponse extends BaseResponse {
	
	private  List<Object> listResponse;
	private String path;
	private String file_name;
	private String updateDate;
	private String submitted;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public List<Object> getListResponse() {
		return listResponse;
	}
	public void setListResponse(List<Object> listResponse) {
		this.listResponse = listResponse;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	

}
