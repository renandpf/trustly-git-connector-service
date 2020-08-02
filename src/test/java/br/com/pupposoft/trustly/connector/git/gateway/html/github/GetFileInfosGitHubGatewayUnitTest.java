package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;

public class GetFileInfosGitHubGatewayUnitTest {
	
	@InjectMocks
	private GetFileInfosGateway getFileInfosGitHubGateway = new GetFileInfosGitHubGateway();
	
	@Mock
	private GetFileInfosGitHubScrap getFileInfosGitHubScrap;
	
	@Mock
	private GetAllFilesPathGitHubScrap getAllFilesPathGitHubScrap;
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getByPath() {
		final String filePath = "anyFilePath";
		this.getFileInfosGitHubGateway.getByPath(filePath);
		verify(this.getFileInfosGitHubScrap).getByPath(filePath);
	}

	@Test
	public void getAllFilesPath() {
		final String urlAllFiles = "anyUrlAllFiles";
		this.getFileInfosGitHubGateway.getAllFilesPath(urlAllFiles);
		verify(this.getAllFilesPathGitHubScrap).get(urlAllFiles);
	}

}
