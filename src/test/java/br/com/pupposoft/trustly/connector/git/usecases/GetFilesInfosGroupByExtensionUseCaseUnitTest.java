package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;

public class GetFilesInfosGroupByExtensionUseCaseUnitTest {
	
	@InjectMocks
	private GetFilesInfosGroupByExtensionUseCase getFilesInfosGroupByExtensionUseCase;
	
	@Mock
	private GetFileInfosGateway getFileInfosGateway;

	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getWithSuccess() {
		final List<String> allFilesPaths = Arrays.asList("/path/any_file_01.txt", "/path/any_file_02.txt");
		
		final List<ExtensionFileInfos> extensionFileInfosReturned = this.getFilesInfosGroupByExtensionUseCase.get(allFilesPaths);
		//TODO: assert
		
		final ArgumentCaptor<String> filesPathsCaptor = ArgumentCaptor.forClass(String.class);
		verify(getFileInfosGateway, VerificationModeFactory.times(2)).getByPath(filesPathsCaptor.capture());

		final List<String> filesPathSent = filesPathsCaptor.getAllValues();
		assertEquals(2, filesPathSent.size());
		assertEquals(allFilesPaths.get(0), filesPathSent.get(0));
		assertEquals(allFilesPaths.get(1), filesPathSent.get(1));
	}
	
}
