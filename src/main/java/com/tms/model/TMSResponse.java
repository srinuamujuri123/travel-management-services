package com.tms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
@JsonInclude(Include.NON_NULL)
public class TMSResponse {

	Object data;
	Integer count;
	Status status;
	String details;
	String errorMessage;

	public enum Status {
		OK, FAILED
	}
}
