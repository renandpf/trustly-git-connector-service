package br.com.pupposoft.trustly.connector.git.gateway.io;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ConnectorGatewayFactoryImplUnitTest {
	
	@InjectMocks
	private ConnectorGatewayFactory connectorGatewayFactory = new ConnectorGatewayFactoryImpl();
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getHttpConnector() {
		//TODO: implementar
	}
}
