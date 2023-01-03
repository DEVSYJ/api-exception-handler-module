package com.github.devsyj.baseapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서비스 별 추가로 내려가야 할 데이터가 있는 경우 상속해 사용.<br>
 * 단순 성공 응답만 내려가는 경우도 있어 구현체로 만들었다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
	
	/**
	 * (required)<br>
	 * 0이면 성공, 그 외 미리 정의 된 errorCode.<br>
	 * 데이타가 없더라도 오류가 아니면 성공으로 처리.
	 * <br><br>
	 */
	protected Integer errorCode = 0;
	/**
	 * 미리 정의 된 errorMessage
	 */
	protected String errorMessage = "Success.";
}
