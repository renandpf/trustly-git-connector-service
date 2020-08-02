package br.com.pupposoft.trustly.connector.git.gateway.html;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class GetFilesInfosGatewayFactoryImplUnitTest {
	
	@InjectMocks
	private GetFilesInfosGatewayFactory getFilesGatewayFactory = new GetFilesInfosGatewayFactoryImpl();
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getFileInfos() {
		final String url = "https://github.com/<anyValue>";
		
		final GetFileInfosGateway getFileInfosGateway = this.getFilesGatewayFactory.get(url);
		//TODO: Implementar
	}
	
}
