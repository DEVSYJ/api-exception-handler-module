package com.github.devsyj.baseapi.exception;

import com.github.devsyj.baseapi.domain.ErrorStatus;
import lombok.Getter;

/**
 * 가급적 서비스마다 특정된 이름의 exception class로 상속해 사용 권장.<br>
 * ex. PurchaseException extends BaseException
 */
@Getter
public class BaseException extends RuntimeException {
	
	private final ErrorStatus errorStatus;
	
	public BaseException(ErrorStatus errorStatus) {
		super(errorStatus.getErrorMessage());
		this.errorStatus = errorStatus;
	}
	
	public BaseException(ErrorStatus errorStatus, Throwable cause) {
		super(errorStatus.getErrorMessage(), cause);
		this.errorStatus = errorStatus;
	}
}
