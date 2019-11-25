package br.com.pitang.desafiopitang.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class LicensePlateException extends DataIntegrityViolationException {

	public LicensePlateException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
