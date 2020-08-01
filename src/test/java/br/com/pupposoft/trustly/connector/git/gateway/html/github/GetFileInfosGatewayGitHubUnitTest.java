package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;

public class GetFileInfosGatewayGitHubUnitTest {
	
	@InjectMocks
	private GetFileInfosGateway getFileInfosGatewayGitHub = new GetFileInfosGatewayGitHub();
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	//TODO - Implementar!!!
	
//	@BeforeClass
//	public static void init() {
//		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
//	}
	
//	@Test
//	public void getWithSameExtension() {
//		final List<String> allFilesPaths = Arrays.asList("anyPath_1", "anyPath_2");
//		
//		final FileInfo fileInfoA = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.TXT);
//		doReturn(fileInfoA).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(0));
//		
//		final FileInfo fileInfoB = Fixture.from(FileInfo.class).gimme(FileInfoTemplate.TXT);
//		doReturn(fileInfoB).when(this.getFileInfosGateway).getByPath(allFilesPaths.get(1));
//		
//		final List<ExtensionFileInfos> extensionFileInfosReturned = this.getFilesInfosGroupByExtensionUseCase.get(allFilesPaths);
//		assertEquals(1, extensionFileInfosReturned.size());
//		
//		final ExtensionFileInfos txtExtensionInfos = getExtensionFileByExtensionName(extensionFileInfosReturned, "txt");
//		assertEquals("txt", txtExtensionInfos.getExtensionName());
//		assertEquals(2, txtExtensionInfos.getFiles().size());
//		assertEquals(fileInfoA.getName(), txtExtensionInfos.getFiles().get(0).getName());
//		assertEquals(fileInfoA.getLines(), txtExtensionInfos.getFiles().get(0).getLines());
//		assertEquals(fileInfoA.getPath(), txtExtensionInfos.getFiles().get(0).getPath());
//		assertEquals(fileInfoA.getSize(), txtExtensionInfos.getFiles().get(0).getSize());
//		
//		assertEquals(fileInfoB.getName(), txtExtensionInfos.getFiles().get(1).getName());
//		assertEquals(fileInfoB.getLines(), txtExtensionInfos.getFiles().get(1).getLines());
//		assertEquals(fileInfoB.getPath(), txtExtensionInfos.getFiles().get(1).getPath());
//		assertEquals(fileInfoB.getSize(), txtExtensionInfos.getFiles().get(1).getSize());
//		
//		final ArgumentCaptor<String> filesPathsCaptor = ArgumentCaptor.forClass(String.class);
//		verify(getFileInfosGateway, VerificationModeFactory.times(2)).getByPath(filesPathsCaptor.capture());
//
//		final List<String> filesPathSent = filesPathsCaptor.getAllValues();
//		assertEquals(2, filesPathSent.size());
//		assertEquals(allFilesPaths.get(0), filesPathSent.get(0));
//		assertEquals(allFilesPaths.get(1), filesPathSent.get(1));
//	}
}
