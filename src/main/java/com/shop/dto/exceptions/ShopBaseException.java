package com.shop.dto.exceptions;

public abstract class ShopBaseException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = -505724075803774810L;

	public ShopBaseException(String message) {
        super(message);
    }

    public abstract String getErrorCode();
}
