package com.adcash.product.category.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adcash.product.category.entity.exception.NotFoundException;

@ControllerAdvice
public class CustomExceptionResolver {

	@ExceptionHandler(value = NotFoundException.class)
    public void handleNotFoundException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
