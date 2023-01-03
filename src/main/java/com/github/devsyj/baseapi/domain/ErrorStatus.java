package com.github.devsyj.baseapi.domain;

import org.springframework.http.HttpStatus;

/**
 * ErrorCode 를 정의할 enum class 의 interface
 */
public interface ErrorStatus {
	default HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	Integer getErrorCode();
	
	String getErrorMessage();
}
