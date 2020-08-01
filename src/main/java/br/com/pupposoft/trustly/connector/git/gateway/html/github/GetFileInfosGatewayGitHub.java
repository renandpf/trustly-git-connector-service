package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

@Component
public class GetFileInfosGatewayGitHub implements GetFileInfosGateway{
	private static final String FILE_NAME_END_TAG = "</strong>";
	private static final String FILE_NAME_START_TAG = "<strong class=\"final-path\">";
	private static final String FILE_NAME_START_NAME = ">";
	
	@Autowired
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Override
	public FileInfo getByPath(final String filePath) {
		final String pageContent = connectorGatewayFactory.load(filePath);
		final String fileName = this.getFileName(pageContent);
		
		
		

		final FileInfo fileInfo = new FileInfo(fileName, filePath, 0L, new BigDecimal("0"));
		return fileInfo;
	}

	private String getFileName(final String pageContent) {
		final int indexStartTagName = pageContent.indexOf(FILE_NAME_START_TAG);
		final int indexEndTagName = pageContent.indexOf(FILE_NAME_END_TAG, indexStartTagName);
		final String tagFileName = pageContent.substring(indexStartTagName, indexEndTagName);
		final int indexStartName = tagFileName.indexOf(FILE_NAME_START_NAME);
		final String fileName = tagFileName.substring(indexStartName+1).trim();
		
		return fileName;
	}

}
