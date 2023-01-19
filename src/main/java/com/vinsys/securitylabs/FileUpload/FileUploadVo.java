package com.vinsys.securitylabs.FileUpload;

import org.springframework.stereotype.Component;

public class FileUploadVo {

	
	
	public String name;
	public String type;
	public long size;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
