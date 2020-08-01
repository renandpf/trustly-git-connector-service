package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

public class GetFileInfosGatewayGitHubUnitTest {
	
	@InjectMocks
	private GetFileInfosGateway getFileInfosGatewayGitHub = new GetFileInfosGatewayGitHub();
	
	@Mock
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Before
	public void InitMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getInfosPomFile() {
		final String filePath = "anyFilePath";
		
		final String pageContent = this.loadFileFromResource("pages/github/pom-example-page.html");
		doReturn(pageContent).when(this.connectorGatewayFactory).load(filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("pom.xml", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(87, fileInfo.getLines());
		assertEquals(new BigDecimal("2.11"), fileInfo.getSize());

		
		verify(this.connectorGatewayFactory).load(filePath);
	}
	
	@Test
	public void getInfosGitIgnoreFile() {
		final String filePath = "anyFilePath";
		
		final String pageContent = this.loadFileFromResource("pages/github/git-ignore-example-page.html");
		doReturn(pageContent).when(this.connectorGatewayFactory).load(filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals(".gitignore", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(8, fileInfo.getLines());
		assertEquals(new BigDecimal("82"), fileInfo.getSize());
		
		verify(this.connectorGatewayFactory).load(filePath);
	}

	@Test
	public void getInfosReadmeFile() {
		final String filePath = "anyFilePath";
		
		final String pageContent = this.loadFileFromResource("pages/github/readme-example-page.html");
		doReturn(pageContent).when(this.connectorGatewayFactory).load(filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("README.md", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(1, fileInfo.getLines());
		assertEquals(new BigDecimal("7"), fileInfo.getSize());
		
		verify(this.connectorGatewayFactory).load(filePath);
	}

	@Test
	public void getInfosJavaFile() {
		final String filePath = "anyFilePath";
		
		final String pageContent = this.loadFileFromResource("pages/github/java-example-page.html");
		doReturn(pageContent).when(this.connectorGatewayFactory).load(filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("GitTrustlyConnectorService.java", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(14, fileInfo.getLines());
		assertEquals(new BigDecimal("448"), fileInfo.getSize());
		
		verify(this.connectorGatewayFactory).load(filePath);
	}
	
	private String loadFileFromResource(final String path) {

        final ClassLoader classLoader = getClass().getClassLoader();

        try (final InputStream inputStream = classLoader.getResourceAsStream(path)) {

            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error to read resorce file");
        }
    }
}
