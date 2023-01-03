package com.github.devsyj.baseapi.controller;

import com.github.devsyj.baseapi.domain.ErrorResponse;
import com.github.devsyj.baseapi.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {
	/**
	 * stacktrace 에 관한 내용은 남지 않으므로 서비스단에서의 logging 필수
	 *
	 * @param baseException
	 * @return
	 */
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> handleBaseException(BaseException baseException) {
		ErrorResponse responseData = new ErrorResponse(baseException, baseException.getCause().toString());
		HttpStatus httpStatus = baseException.getErrorStatus().getHttpStatus();
		return new ResponseEntity<>(responseData, httpStatus);
	}
}