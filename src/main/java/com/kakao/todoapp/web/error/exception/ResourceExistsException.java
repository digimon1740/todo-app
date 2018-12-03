package com.kakao.todoapp.web.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceExistsException extends RuntimeException {

	public ResourceExistsException() {
		super();
	}

	public ResourceExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceExistsException(String message) {
		super(message);
	}

	public ResourceExistsException(Throwable cause) {
		super(cause);
	}

}
