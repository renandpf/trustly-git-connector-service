package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

@Component
public class GetFileInfosGatewayGitHub implements GetFileInfosGateway{
	private static final String LINE_NUMBER_START = ">";
	private static final String LINE_NUMBER_END_TAG = "line";
	private static final String LINE_NUMBER_START_TAG = "<div class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">";
	private static final String FILE_NAME_END_TAG = "</strong>";
	private static final String FILE_NAME_START_TAG = "<strong class=\"final-path\">";
	private static final String FILE_NAME_START_NAME = LINE_NUMBER_START;
	
	@Autowired
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Override
	public FileInfo getByPath(final String filePath) {
		final String pageContent = connectorGatewayFactory.load(filePath);
		final String fileName = this.getFileName(pageContent);
		final String lineNumber = this.getLineNumber(pageContent);
		
		
		return new FileInfo(fileName, filePath, Long.valueOf(lineNumber), new BigDecimal("0"));
	}

	private String getLineNumber(final String pageContent) {
		final int indexStartTag = pageContent.indexOf(LINE_NUMBER_START_TAG);
		final int indexEndTag = pageContent.indexOf(LINE_NUMBER_END_TAG, indexStartTag);
		final String tag = pageContent.substring(indexStartTag, indexEndTag);
		final int indexStart = tag.indexOf(LINE_NUMBER_START);
		final String lineNumber = tag.substring(indexStart+1).trim();
		return lineNumber;
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
