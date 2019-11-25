package br.com.pitang.desafiopitang.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageError implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private int errorCode;
	
	
	public MessageError(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}


	@Override
	public String toString() {
		return "{ message: "+message+", errorCode: "+errorCode+"}";
	}
	
	

}
