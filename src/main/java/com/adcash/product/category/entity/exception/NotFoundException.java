package com.adcash.product.category.entity.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6768814689618351314L;

	public NotFoundException(String entity) {
        super(entity + " not found");
    }
}
