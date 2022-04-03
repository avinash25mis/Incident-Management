package com.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericResponse<T> {
	String status;
	String message;
	T result;

	public GenericResponse(String status, String message, T result) {
				super();
				this.status = status;
				this.message = message;
				this.result = result;
	 }

			
			
	}
