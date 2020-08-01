package br.com.pupposoft.trustly.connector.git.gateway.io;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.pupposoft.trustly.connector.git.gateway.io.exceptions.ConnectorNotFoundGatewayException;

import static org.mockito.Mockito.*;

public class ConnectorGatewayFactoryImplUnitTest {
	
	@InjectMocks
	private ConnectorGatewayFactory connectorGatewayFactory = new ConnectorGatewayFactoryImpl();
	
	@Mock
	private ConnectorGateway httpConnectorGateway;

	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getHttpConnector() {
		final String httpUrl = "http://anyPath";
		
		final ConnectorGateway httpConnector = this.connectorGatewayFactory.get(httpUrl);
		httpConnector.load(httpUrl);
		
		verify(this.httpConnectorGateway).load(httpUrl);
	}
	
	@Test
	public void getHttpsConnector() {
		final String httpUrl = "https://anyPath";
		
		final ConnectorGateway httpConnector = this.connectorGatewayFactory.get(httpUrl);
		httpConnector.load(httpUrl);
		
		verify(this.httpConnectorGateway).load(httpUrl);
	}
	
	@Test(expected = ConnectorNotFoundGatewayException.class)
	public void getFtpConnector() {
		final String httpUrl = "ftp://anyPath";
		
		try {
			this.connectorGatewayFactory.get(httpUrl);
			
		} catch (ConnectorNotFoundGatewayException e) {
			verify(this.httpConnectorGateway, VerificationModeFactory.times(0)).load(httpUrl);
			throw e;
		}
	}
}
