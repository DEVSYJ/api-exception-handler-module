package com.github.devsyj.baseapi.controller;

import com.github.devsyj.baseapi.domain.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 에러 처리<br>
 * business 단에서 발생하지 않은 에러는 여기서 처리한다.
 *
 * @see <a href="https://buulight.tistory.com/17">참고 블로그</a>
 * <br>
 * [인용] mapping 된 endpoint 가 없어서 혹은 다른 이유로 컨트롤러 단을 타지 못하고 발생되는 에러는
 * application.yml이나 application.properties에 설정된 path 로 요청이 가는데
 * (default값 : '/error')
 * Spring Boot의 org.springframework.boot.autoconfigure.web.servlet.error 패키지 의 BasicErrorController가 해당 path 를 mapping 하고 있다.
 */
@RestController
public class ExceptionController implements ErrorController {
	
	@RequestMapping(value = "${server.error.path:${error.path:/error}}")
	public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		
		ErrorResponse responseData = new ErrorResponse(status.value(), status.getReasonPhrase());
		responseData.setErrorCause(getCause(request));
		
		return new ResponseEntity<>(responseData, status);
	}
	
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
		if(statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		try {
			return HttpStatus.valueOf(statusCode);
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	private String getCause(HttpServletRequest request) {
		Exception cause = (Exception) request.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION");
		return cause != null ? cause.toString() : null;
	}
	
	@Override
	public String getErrorPath() {
		return null;
	}
}
