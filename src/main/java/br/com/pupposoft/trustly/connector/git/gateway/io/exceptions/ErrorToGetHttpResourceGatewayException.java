package br.com.pupposoft.trustly.connector.git.gateway.io.exceptions;

import org.springframework.http.HttpStatus;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.Getter;

@Getter
public class ErrorToGetHttpResourceGatewayException  extends GitConnectorBaseException {
	private static final long serialVersionUID = -7656182977049228685L;
	
	private final String code = "tgc.error.errorToGetHttpResource";
	private final String message = "Error to get file";
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; 
}
