package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;

public class GetFilesInfosOrquestratorUnitTest {
	
	@InjectMocks
	private GetFilesInfosOrquestrator getFilesInfosOrquestrator;
	
	@Mock
	private GetAllFilesPathUseCase getAllFilesPathUseCase;

	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getWithSuccess() {
		final String projectUrlBase = "anyProjectUrlBase";
		final List<FileInfo> fileInfos = this.getFilesInfosOrquestrator.get(projectUrlBase);
		verify(getAllFilesPathUseCase).get(projectUrlBase);
		//TODO: asserts
	}
	
}
