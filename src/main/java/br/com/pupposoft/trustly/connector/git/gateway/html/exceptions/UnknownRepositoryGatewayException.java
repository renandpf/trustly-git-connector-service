package br.com.pupposoft.trustly.connector.git.gateway.html.exceptions;

import org.springframework.http.HttpStatus;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.Getter;

@Getter
public class UnknownRepositoryGatewayException extends GitConnectorBaseException {
	private static final long serialVersionUID = -4006441732084054627L;
	
	private final String code = "tgc.error.unknownRepository";
	private final String message = "This repository not implemented yet.";
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; 
}
