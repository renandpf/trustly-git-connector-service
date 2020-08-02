package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFilesInfosGatewayFactory;
import br.com.pupposoft.trustly.connector.git.usecases.exceptions.UnknownGitRepositoryBusinessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllFilesPathUseCaseUnitTest {
	
	@InjectMocks
	private GetAllFilesPathUseCase getAllFilesPathUseCase;
	
	@Mock
	private GetFilesInfosGatewayFactory getFilesInfosGatewayFactory;

	@Mock
	private GetFileInfosGateway getFileInfosGateway;
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
		doReturn(getFileInfosGateway).when(this.getFilesInfosGatewayFactory).get(anyString());
	}
	
	@Test
	public void getWithSuccess() {
		final String urlBase = "https://github.com/renandpf/trustly-git-connector-service";
		final String urlAllFilesExpected = "https://github.com/renandpf/trustly-git-connector-service/find/master";
		
		final List<String> allFilesPathsExistent = 
				Arrays.asList(
						"https://github.com/renandpf/trustly-git-connector-service/blob/master/.gitignore", 
						"https://github.com/renandpf/trustly-git-connector-service/blob/master/README.md");
		doReturn(allFilesPathsExistent).when(this.getFileInfosGateway).getAllFilesPath(eq(urlAllFilesExpected));
		
		final List<String> allFilesPaths = this.getAllFilesPathUseCase.get(urlBase);
		assertEquals(2, allFilesPaths.size());
		assertEquals(allFilesPathsExistent.get(0), allFilesPaths.get(0));
		assertEquals(allFilesPathsExistent.get(1), allFilesPaths.get(1));
		
		verify(this.getFileInfosGateway).getAllFilesPath(urlAllFilesExpected);
		verify(this.getFilesInfosGatewayFactory).get(urlAllFilesExpected);
	}
	
	@Test(expected = UnknownGitRepositoryBusinessException.class)
	public void getWithUnknownRepositoryException() {
		final String urlBase = "https://gitlab.com/renandpf-cursos/biometria-digital/poc-biometric-client-api";
		
		try {
			this.getAllFilesPathUseCase.get(urlBase);
			
		} catch (UnknownGitRepositoryBusinessException e) {
			verify(this.getFilesInfosGatewayFactory, VerificationModeFactory.noInteractions()).get(anyString());
			throw e;
		}
	}
}
