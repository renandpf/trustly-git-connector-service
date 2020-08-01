package br.com.pupposoft.trustly.connector.git.gateway.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.gateway.io.exceptions.ConnectorNotFoundGatewayException;

@Component
public class ConnectorGatewayFactoryImpl implements ConnectorGatewayFactory {

	@Autowired
	private ConnectorGateway httpConnectorGateway;
	
	@Override
	public ConnectorGateway get(final String filePath) {
		
		if(filePath.startsWith("http")) {
			return this.httpConnectorGateway;
		}
		
		throw new ConnectorNotFoundGatewayException();

	}
}
