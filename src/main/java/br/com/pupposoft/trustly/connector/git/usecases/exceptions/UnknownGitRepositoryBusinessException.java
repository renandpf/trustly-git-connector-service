package br.com.pupposoft.trustly.connector.git.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.Getter;

@Getter
public class UnknownGitRepositoryBusinessException extends GitConnectorBaseException {
	private static final long serialVersionUID = 2603758441768111735L;
	
	private final String code = "tgc.error.unknownGitRepository";
	private final String message = "This git repository not implemented yet";
	private final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY; 
}
