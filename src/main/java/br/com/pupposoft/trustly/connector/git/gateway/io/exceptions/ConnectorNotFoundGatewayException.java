package br.com.pupposoft.trustly.connector.git.gateway.io.exceptions;

import org.springframework.http.HttpStatus;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.Getter;

@Getter
public class ConnectorNotFoundGatewayException extends GitConnectorBaseException {
	private static final long serialVersionUID = 8156292652824000438L;
	
	private final String code = "tgc.error.connectorNotFound";
	private final String message = "This protocol not implemented yet.";
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; 
}
