package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

public class GetAllFilesPathGitHubScrapUnitTest {
	
	@InjectMocks
	private GetAllFilesPathGitHubScrap getAllFilesPathGitHubScrap = new GetAllFilesPathGitHubScrap();
	
	@Mock
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Mock
	private GetAllFilePathsRestGitHub getAllFilePathsRestGitHub;
	
	@Mock
	private ConnectorGateway httpConnectorGateway;
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
		doReturn(this.httpConnectorGateway).when(this.connectorGatewayFactory).get(anyString());
	}
	
	@Test
	public void getAllPathsFile() {
		final String urlAllFiles = "https://github.com/renandpf/trustly-git-connector-service/find/master";
		final String filesPathsDataSourceUrlExpected = "https://github.com/renandpf/trustly-git-connector-service/tree-list/8f193439efd30176df9911fb9540f41ee9c17e92";
		this.mockLoadHtml("pages/github/all-files-path-example.html", urlAllFiles);
		
		List<String> filesPaths = Arrays.asList(
				".gitignore",
				"README.md",
				"src/main/java/br/com/pupposoft/trustly/connector/GitTrustlyConnectorService.java",
				"src/test/java/.gitkeep");
		doReturn(filesPaths).when(this.getAllFilePathsRestGitHub).getPaths(filesPathsDataSourceUrlExpected);
		final List<String> allFilesPath = this.getAllFilesPathGitHubScrap.get(urlAllFiles);
		assertEquals("https://github.com/renandpf/trustly-git-connector-service/blob/master/.gitignore", allFilesPath.get(0));
		assertEquals("https://github.com/renandpf/trustly-git-connector-service/blob/master/README.md", allFilesPath.get(1));
		assertEquals("https://github.com/renandpf/trustly-git-connector-service/blob/master/src/main/java/br/com/pupposoft/trustly/connector/GitTrustlyConnectorService.java", allFilesPath.get(2));
		assertEquals("https://github.com/renandpf/trustly-git-connector-service/blob/master/src/test/java/.gitkeep", allFilesPath.get(3));
		
		verify(this.connectorGatewayFactory).get(urlAllFiles);
		verify(this.httpConnectorGateway).load(urlAllFiles);
		
		final ArgumentCaptor<String> filesPathsDataSourceUrlCaptor = ArgumentCaptor.forClass(String.class);
		verify(this.getAllFilePathsRestGitHub).getPaths(filesPathsDataSourceUrlCaptor.capture());
		assertEquals(filesPathsDataSourceUrlExpected, filesPathsDataSourceUrlCaptor.getValue());
	}
	
	
	private void mockLoadHtml(final String pathExampleFile, final String filePath) {
		final String pageContent = this.loadFileFromResource(pathExampleFile);
		doReturn(pageContent).when(this.httpConnectorGateway).load(filePath);
	}
	
	private String loadFileFromResource(final String path) {

        final ClassLoader classLoader = getClass().getClassLoader();

        try (final InputStream inputStream = classLoader.getResourceAsStream(path)) {

            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error to read resource file");
        }
    }
}
