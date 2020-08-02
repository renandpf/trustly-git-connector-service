package br.com.pupposoft.trustly.connector.starter.exception.json;

import br.com.pupposoft.trustly.connector.starter.exception.GitConnectorBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ExceptionJson {

	private final String code;
	private final String message;
	
	public ExceptionJson(final GitConnectorBaseException baseException) {
		this.code = baseException.getCode();
		this.message = baseException.getMessage();
	}
}
