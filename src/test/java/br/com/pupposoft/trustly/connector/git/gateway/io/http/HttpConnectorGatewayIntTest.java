package br.com.pupposoft.trustly.connector.git.gateway.io.http;

import org.junit.Ignore;
import org.junit.Test;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.exceptions.ErrorToGetHttpResourceGatewayException;

public class HttpConnectorGatewayIntTest {
	
	private ConnectorGateway httpConnector = new HttpConnectorGateway();
	
	@Ignore //Ignored only to fast build. 
	@Test
	public void load() {
		final String httpUrl = "https://github.com/renandpf/trustly-git-connector-service/blob/master/README.md";
		final String responseBody = httpConnector.load(httpUrl);
		System.out.println(responseBody);
	}
	
	@Ignore //Ignored only to fast build.
	@Test(expected = ErrorToGetHttpResourceGatewayException.class)
	public void loadWithsError() {
		final String httpUrl = "https://github.com/renandpf/trustly-git-connector-service/blob/master/FILE_NOT_FOUND";
		httpConnector.load(httpUrl);
	}
}
