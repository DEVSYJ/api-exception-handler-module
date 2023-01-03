package com.github.devsyj.baseapi.domain;

import com.github.devsyj.baseapi.exception.BaseException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ErrorResponse extends BaseResponse {
	/**
	 * 에러 원인. 확실하지 않은 경우 Throw 된 exception 의 message 를 그대로 던지기.<br>
	 * 주로 third party 에서 확인한 errorMessage 전달.
	 */
	@Getter
	@Setter
	private String errorCause;
	
	public ErrorResponse(BaseException baseException) {
		super(baseException.getErrorStatus().getErrorCode(), baseException.getErrorStatus().getErrorMessage());
	}
	
	public ErrorResponse(BaseException baseException, String errorCause) {
		super(baseException.getErrorStatus().getErrorCode(), baseException.getErrorStatus().getErrorMessage());
		this.errorCause = errorCause;
	}
	
	public ErrorResponse(Integer errorCode, Exception exception) {
		super(errorCode, exception.getMessage());
		this.errorCause = ExceptionUtils.getStackTrace(exception);
	}
	
	/**
	 * 404 처리용. 가급적 사용하지 말고 다른 생성자를 사용할 것.
	 */
	public ErrorResponse(Integer errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}