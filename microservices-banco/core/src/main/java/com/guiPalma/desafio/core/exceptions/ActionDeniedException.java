package com.guiPalma.desafio.core.exceptions;

public class ActionDeniedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ActionDeniedException(String msg) {
		super(msg);
	}

	public ActionDeniedException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
