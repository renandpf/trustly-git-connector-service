package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

public class GetAllFilesPathGitHubScrapUnitTest {
	
	@InjectMocks
	private GetAllFilesPathGitHubScrap getAllFilesPathGitHubScrap = new GetAllFilesPathGitHubScrap();
	
	@Mock
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Mock
	private ConnectorGateway httpConnectorGateway;
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
		doReturn(this.httpConnectorGateway).when(this.connectorGatewayFactory).get(anyString());
	}
	
	@Test
	public void getInfosPomFile() {
		final String urlAllFiles = "anyAllFilesPaths";
		this.mockLoadHtml("pages/github/all-files-path-example.html", urlAllFiles);
		
		final List<String> allFilesPath = this.getAllFilesPathGitHubScrap.get(urlAllFiles);
		
		verify(this.connectorGatewayFactory).get(urlAllFiles);
		verify(this.httpConnectorGateway).load(urlAllFiles);
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
