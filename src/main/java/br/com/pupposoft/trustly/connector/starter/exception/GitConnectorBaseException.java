package br.com.pupposoft.trustly.connector.starter.exception;

import org.springframework.http.HttpStatus;

public abstract class GitConnectorBaseException extends RuntimeException {
	private static final long serialVersionUID = 2115411860896771334L;

	public abstract String getCode();
	public abstract HttpStatus getHttpStatus();
	public abstract String getMessage();
}
