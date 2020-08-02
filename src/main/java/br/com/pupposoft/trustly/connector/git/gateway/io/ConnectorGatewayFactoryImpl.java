package br.com.pupposoft.trustly.connector.git.gateway.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.gateway.io.exceptions.ConnectorNotFoundGatewayException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConnectorGatewayFactoryImpl implements ConnectorGatewayFactory {

	@Autowired
	private ConnectorGateway httpConnectorGateway;
	
	//Put here other protocols implementations!
	
	@Override
	public ConnectorGateway get(final String filePath) {
		log.trace("filePath: {}", filePath);
		if(filePath.startsWith("http")) {
			return this.httpConnectorGateway;
		}
		
		log.warn("No protocol found");
		throw new ConnectorNotFoundGatewayException();

	}
}
