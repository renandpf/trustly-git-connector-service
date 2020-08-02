package br.com.pupposoft.trustly.connector.git.gateway.html;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.pupposoft.trustly.connector.git.gateway.html.exceptions.UnknownRepositoryGatewayException;

public class GetFilesInfosGatewayFactoryImplUnitTest {
	
	@InjectMocks
	private GetFilesInfosGatewayFactory getFilesGatewayFactory = new GetFilesInfosGatewayFactoryImpl();
	
	@Mock
	private GetFileInfosGateway getFileInfosGateway;
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getFileInfos() {
		final String url = "https://github.com/<anyValue>";

		final GetFileInfosGateway getFileInfosGateway = this.getFilesGatewayFactory.get(url);
		
		assertEquals(this.getFileInfosGateway, getFileInfosGateway);
	}
	
	@Test(expected = UnknownRepositoryGatewayException.class)
	public void getFileInfosWithUnknownRepository() {
		final String url = "https://gitlab.com/<anyValue>";
		this.getFilesGatewayFactory.get(url);
	}
	
}
