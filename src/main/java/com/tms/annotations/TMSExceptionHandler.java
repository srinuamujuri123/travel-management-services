package com.tms.annotations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tms.common.CommonConstants.User;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;

@ControllerAdvice
public class TMSExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public TMSResponse processUnmergeException(final MethodArgumentNotValidException ex) {
		TMSResponse response = new TMSResponse();
		List<String> list = ex.getBindingResult().getAllErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		response.setData(list);
		response.setDetails(User.SOME_ERROR);
		response.setStatus(Status.FAILED);
		return response;
	}
}
