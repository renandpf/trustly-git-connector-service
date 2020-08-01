package br.com.pupposoft.trustly.connector.git.gateway.io.http;

import org.junit.Ignore;
import org.junit.Test;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGateway;

public class HttpConnectorGatewayIntTest {
	
	private ConnectorGateway httpConnector = new HttpConnectorGateway();
	
	@Ignore
	@Test
	public void load() {
		final String httpUrl = "https://github.com/renandpf/trustly-git-connector-service/blob/master/README.md";
		final String responseBody = httpConnector.load(httpUrl);
		System.out.println(responseBody);
	}
}
