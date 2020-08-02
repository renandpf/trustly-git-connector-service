package br.com.pupposoft.trustly.connector.git.gateway.html.exceptions;

import org.springframework.http.HttpStatus;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.Getter;

@Getter
public class ErrorToGetGitFileInfoGatewayException extends GitConnectorBaseException {
	private static final long serialVersionUID = -1794220725844184435L;
	
	private final String code = "tgc.error.errorToGetFileInfo";
	private final String message = "Error to get file info";
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; 
}
