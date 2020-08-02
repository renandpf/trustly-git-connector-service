package br.com.pupposoft.trustly.connector.git.gateway.html.github.exceptions;

import org.springframework.http.HttpStatus;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.Getter;

@Getter
public class ErrorToGetAllPathsFilesGatewayException extends GitConnectorBaseException {
	private static final long serialVersionUID = -1279628480233484180L;
	
	private final String code = "tgc.error.github.errorToGetAllFilesPaths";
	private final String message = "Error to get all files paths (GitHub)";
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; 
}
