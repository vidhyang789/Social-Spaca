package com.example.blog_app.blog_app_exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	String resourcename;
	String fieldname;
	long fieldvalue;
	public ResourceNotFoundException(String resourcename, String fieldname, int fieldvalue) {
		super(String.format("%s not found with %s : %d", resourcename,fieldname,fieldvalue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	

}
