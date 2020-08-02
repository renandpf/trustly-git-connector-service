package br.com.pupposoft.trustly.connector.git.gateway.html;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class GetFilesGatewayFactoryImplUnitTest {
	
	@InjectMocks
	private GetFilesGatewayFactory getFilesGatewayFactory = new GetFilesGatewayFactoryImpl();
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	//TODO: Implementar
}
