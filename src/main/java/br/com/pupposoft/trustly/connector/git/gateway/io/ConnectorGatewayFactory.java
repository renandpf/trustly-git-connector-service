package br.com.pupposoft.trustly.connector.git.gateway.io;

public interface ConnectorGatewayFactory {
	ConnectorGateway get(final String filePath);
}
