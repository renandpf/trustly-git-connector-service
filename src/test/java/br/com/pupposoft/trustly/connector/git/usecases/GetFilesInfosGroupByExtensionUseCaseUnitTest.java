package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.util.ClassUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;
import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.test.fixture.DomainsTemplateLoader;
import br.com.pupposoft.trustly.connector.git.test.fixture.FileInfoTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class GetFilesInfosGroupByExtensionUseCaseUnitTest {
	
	@InjectMocks
	private GetFilesInfosGroupByExtensionUseCase getFilesInfosGroupByExtensionUseCase;
	
	@Mock
	private GetFileInfosGateway getFileInfosGateway;

	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
	}
	
	@Test
	public void getWithSuccess() {
		final List<String> allFilesPaths = Arrays.asList("anyPath_1", "anyPath_2");
		
		final FileInfo fileInfoA = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.TXT);
		doReturn(fileInfoA).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(0));
		
		final FileInfo fileInfoB = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.TXT);
		doReturn(fileInfoB).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(1));
		
		final List<ExtensionFileInfos> extensionFileInfosReturned = this.getFilesInfosGroupByExtensionUseCase.get(allFilesPaths);
		assertEquals(1, extensionFileInfosReturned.size());
		assertEquals("txt", extensionFileInfosReturned.get(0).getExtensionName());
		assertEquals(2, extensionFileInfosReturned.get(0).getFiles().size());
		assertEquals(fileInfoA.getName(), extensionFileInfosReturned.get(0).getFiles().get(0).getName());
		assertEquals(fileInfoB.getName(), extensionFileInfosReturned.get(0).getFiles().get(1).getName());
		//TODO: Adiciona assert de quantidade de linhas e tamanho
		
		final ArgumentCaptor<String> filesPathsCaptor = ArgumentCaptor.forClass(String.class);
		verify(getFileInfosGateway, VerificationModeFactory.times(2)).getByPath(filesPathsCaptor.capture());

		final List<String> filesPathSent = filesPathsCaptor.getAllValues();
		assertEquals(2, filesPathSent.size());
		assertEquals(allFilesPaths.get(0), filesPathSent.get(0));
		assertEquals(allFilesPaths.get(1), filesPathSent.get(1));
	}
	
	//TODO: Adicionar casos de teste: extensões diferentes, sem extensão
}
