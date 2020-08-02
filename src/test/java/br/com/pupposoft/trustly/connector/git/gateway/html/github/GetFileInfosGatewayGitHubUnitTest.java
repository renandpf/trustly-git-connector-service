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
import br.com.pupposoft.trustly.connector.git.domains.Measurement;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.html.exceptions.ErrorToGetGitFileInfoGatewayException;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

public class GetFileInfosGatewayGitHubUnitTest {
	
	@InjectMocks
	private GetFileInfosGateway getFileInfosGatewayGitHub = new GetFileInfosGatewayGitHub();
	
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
		final String filePath = "anyFilePath";
		this.mockLoadHtml("pages/github/pom-example-page.html", filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("pom.xml", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(87, fileInfo.getLines());
		assertEquals(new BigDecimal("2.11"), fileInfo.getFileSize().getSize());
		assertEquals(Measurement.KBYTES, fileInfo.getFileSize().getMeasurement());
		
		verify(this.connectorGatewayFactory).get(filePath);
		verify(this.httpConnectorGateway).load(filePath);
	}
	
	@Test
	public void getInfosGitIgnoreFile() {
		final String filePath = "anyFilePath";
		this.mockLoadHtml("pages/github/git-ignore-example-page.html", filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals(".gitignore", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(8, fileInfo.getLines());
		assertEquals(new BigDecimal("82"), fileInfo.getFileSize().getSize());
		assertEquals(Measurement.BYTES, fileInfo.getFileSize().getMeasurement());
		
		verify(this.connectorGatewayFactory).get(filePath);
		verify(this.httpConnectorGateway).load(filePath);
	}

	@Test
	public void getInfosReadmeFile() {
		final String filePath = "anyFilePath";
		this.mockLoadHtml("pages/github/readme-example-page.html", filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("README.md", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(1, fileInfo.getLines());
		assertEquals(new BigDecimal("7"), fileInfo.getFileSize().getSize());
		assertEquals(Measurement.BYTES, fileInfo.getFileSize().getMeasurement());
		
		verify(this.connectorGatewayFactory).get(filePath);
		verify(this.httpConnectorGateway).load(filePath);
	}

	@Test
	public void getInfosJavaFile() {
		final String filePath = "anyFilePath";
		this.mockLoadHtml("pages/github/java-example-page.html", filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("GitTrustlyConnectorService.java", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(14, fileInfo.getLines());
		assertEquals(new BigDecimal("448"), fileInfo.getFileSize().getSize());
		assertEquals(Measurement.BYTES, fileInfo.getFileSize().getMeasurement());
		
		verify(this.connectorGatewayFactory).get(filePath);
		verify(this.httpConnectorGateway).load(filePath);
	}
	
	@Test
	public void getInfosBlobFile() {
		final String filePath = "anyFilePath";
		this.mockLoadHtml("pages/github/blob-example-page.html", filePath);
		
		final FileInfo fileInfo = this.getFileInfosGatewayGitHub.getByPath(filePath);
		assertEquals("chromedriver.exe", fileInfo.getName());
		assertEquals(filePath, fileInfo.getPath());
		assertEquals(0L, fileInfo.getLines());
		assertEquals(new BigDecimal("7.41"), fileInfo.getFileSize().getSize());
		assertEquals(Measurement.MBYTES, fileInfo.getFileSize().getMeasurement());
		
		verify(this.connectorGatewayFactory).get(filePath);
		verify(this.httpConnectorGateway).load(filePath);
	}
	
	@Test(expected = ErrorToGetGitFileInfoGatewayException.class)
	public void getInfosWithError() {
		final String filePath = "anyFilePath";
		doThrow(new RuntimeException("Any error message")).when(this.httpConnectorGateway).load(filePath);
		
		try {
			this.getFileInfosGatewayGitHub.getByPath(filePath);
		} catch (final ErrorToGetGitFileInfoGatewayException e) {
			verify(this.connectorGatewayFactory).get(filePath);
			verify(this.httpConnectorGateway).load(filePath);
			throw e;
		}
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
