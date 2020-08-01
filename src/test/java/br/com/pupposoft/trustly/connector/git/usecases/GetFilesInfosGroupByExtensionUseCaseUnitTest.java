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
	public void getWithSameExtension() {
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
		assertEquals(fileInfoA.getLines(), extensionFileInfosReturned.get(0).getFiles().get(0).getLines());
		assertEquals(fileInfoA.getPath(), extensionFileInfosReturned.get(0).getFiles().get(0).getPath());
		assertEquals(fileInfoA.getSize(), extensionFileInfosReturned.get(0).getFiles().get(0).getSize());
		
		assertEquals(fileInfoB.getName(), extensionFileInfosReturned.get(0).getFiles().get(1).getName());
		assertEquals(fileInfoB.getLines(), extensionFileInfosReturned.get(0).getFiles().get(1).getLines());
		assertEquals(fileInfoB.getPath(), extensionFileInfosReturned.get(0).getFiles().get(1).getPath());
		assertEquals(fileInfoB.getSize(), extensionFileInfosReturned.get(0).getFiles().get(1).getSize());
		
		final ArgumentCaptor<String> filesPathsCaptor = ArgumentCaptor.forClass(String.class);
		verify(getFileInfosGateway, VerificationModeFactory.times(2)).getByPath(filesPathsCaptor.capture());

		final List<String> filesPathSent = filesPathsCaptor.getAllValues();
		assertEquals(2, filesPathSent.size());
		assertEquals(allFilesPaths.get(0), filesPathSent.get(0));
		assertEquals(allFilesPaths.get(1), filesPathSent.get(1));
	}
	
	@Test
	public void getWithDiffExtension() {
		final List<String> allFilesPaths = Arrays.asList("anyPath_txt", "anyPath_java", "anyOtherPath_java", "anyPath_ts");
		
		final FileInfo txtFile = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.TXT);
		doReturn(txtFile).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(0));
		
		final FileInfo javaFile = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.JAVA);
		doReturn(javaFile).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(1));
		
		final FileInfo java2File = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.JAVA);
		doReturn(java2File).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(2));
		
		final FileInfo tsFile = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.TS);
		doReturn(tsFile).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(3));
		
		final List<ExtensionFileInfos> extensionFileInfosReturned = this.getFilesInfosGroupByExtensionUseCase.get(allFilesPaths);
		assertEquals(3, extensionFileInfosReturned.size());

		final ExtensionFileInfos txtExtensionInfos = extensionFileInfosReturned.get(0);
		assertEquals("txt", txtExtensionInfos.getExtensionName());
		assertEquals(1, txtExtensionInfos.getFiles().size());
		assertEquals(txtFile.getName(), txtExtensionInfos.getFiles().get(0).getName());
		assertEquals(txtFile.getLines(), txtExtensionInfos.getFiles().get(0).getLines());
		assertEquals(txtFile.getPath(), txtExtensionInfos.getFiles().get(0).getPath());
		assertEquals(txtFile.getSize(), txtExtensionInfos.getFiles().get(0).getSize());
		
		final ExtensionFileInfos javaExtensionInfos = extensionFileInfosReturned.get(1);
		assertEquals("java", javaExtensionInfos.getExtensionName());
		assertEquals(2, javaExtensionInfos.getFiles().size());
		assertEquals(javaFile.getName(), javaExtensionInfos.getFiles().get(0).getName());
		assertEquals(javaFile.getLines(), javaExtensionInfos.getFiles().get(0).getLines());
		assertEquals(javaFile.getPath(), javaExtensionInfos.getFiles().get(0).getPath());
		assertEquals(javaFile.getSize(), javaExtensionInfos.getFiles().get(0).getSize());
		assertEquals(java2File.getName(), javaExtensionInfos.getFiles().get(1).getName());
		assertEquals(java2File.getLines(), javaExtensionInfos.getFiles().get(1).getLines());
		assertEquals(java2File.getPath(), javaExtensionInfos.getFiles().get(1).getPath());
		assertEquals(java2File.getSize(), javaExtensionInfos.getFiles().get(1).getSize());
		
		final ExtensionFileInfos tsExtensionInfos = extensionFileInfosReturned.get(2);
		assertEquals("ts", tsExtensionInfos.getExtensionName());
		assertEquals(1, tsExtensionInfos.getFiles().size());
		assertEquals(tsFile.getName(), tsExtensionInfos.getFiles().get(0).getName());
		assertEquals(tsFile.getLines(), tsExtensionInfos.getFiles().get(0).getLines());
		assertEquals(tsFile.getPath(), tsExtensionInfos.getFiles().get(0).getPath());
		assertEquals(tsFile.getSize(), tsExtensionInfos.getFiles().get(0).getSize());

	}
	
	//TODO: Adicionar casos de teste: extensões diferentes, sem extensão
}
