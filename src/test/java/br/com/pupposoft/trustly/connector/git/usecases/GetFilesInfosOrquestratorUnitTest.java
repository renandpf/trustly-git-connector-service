package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;

public class GetFilesInfosOrquestratorUnitTest {
	
	@InjectMocks
	private GetFilesInfosOrquestrator getFilesInfosOrquestrator;
	
	@Mock
	private GetAllFilesPathUseCase getAllFilesPathUseCase;

	@Mock
	private GetFilesInfosGroupByExtension getFilesInfosGroupByExtension;

	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getWithSuccess() {
		final String projectUrlBase = "anyProjectUrlBase";
		
		final List<String> allFilesPaths = Arrays.asList("/path/any_file_01", "/path/any_file_02");
		doReturn(allFilesPaths).when(this.getAllFilesPathUseCase).get(projectUrlBase);
		
		final List<ExtensionFileInfos> extensionFileInfos = Arrays.asList(new ExtensionFileInfos());
		doReturn(extensionFileInfos).when(this.getFilesInfosGroupByExtension).get(allFilesPaths);
		
		List<ExtensionFileInfos> extensionFileInfosReturned = this.getFilesInfosOrquestrator.get(projectUrlBase);
		
		verify(getAllFilesPathUseCase).get(projectUrlBase);
		verify(getFilesInfosGroupByExtension).get(allFilesPaths);
		
		assertEquals(extensionFileInfos, extensionFileInfosReturned);
	}
	
}
