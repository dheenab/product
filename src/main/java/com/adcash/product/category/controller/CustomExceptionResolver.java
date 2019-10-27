package com.adcash.product.category.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adcash.product.category.entity.ResponseStatus;
import com.adcash.product.category.entity.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class CustomExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@ExceptionHandler(value = NotFoundException.class)
    public void handleNotFoundException(HttpServletResponse response, NotFoundException notFoundException) throws IOException {
		logger.info("Exception occurred", notFoundException);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Failure");
		responseStatus.setData(notFoundException.getMessage());
		ObjectMapper objectMapper= new ObjectMapper();
		response.getWriter().write(objectMapper.writeValueAsString(responseStatus));
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
	
	@ExceptionHandler(value = Exception.class )
    public void handleException(HttpServletResponse response,Exception exception) throws IOException {
		logger.info("Exception occurred", exception);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Failure");
		responseStatus.setData(exception.getMessage());
		ObjectMapper objectMapper= new ObjectMapper();
		response.getWriter().write(objectMapper.writeValueAsString(responseStatus));
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());

    }
	
}
