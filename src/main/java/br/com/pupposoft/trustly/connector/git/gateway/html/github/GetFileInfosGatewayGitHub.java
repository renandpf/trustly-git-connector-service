package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

@Component
public class GetFileInfosGatewayGitHub implements GetFileInfosGateway{
	private static final String FILE_SIZE_KB_CLEAN = "KB";
	private static final String FILE_SIZE_SPAN_CLEAN = "</span>";
	private static final String FILE_SIZE_START = ">";
	private static final String FILE_SIZE_END_TAG = "</div>";
	private static final String FILE_SIZE_START_TAG = "<span class=\"file-info-divider\"></span>";
	
	private static final String LINE_NUMBER_START = FILE_SIZE_START;
	private static final String LINE_NUMBER_END_TAG = "line";
	private static final String LINE_NUMBER_START_TAG = "<div class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">";
	
	private static final String FILE_NAME_END_TAG = "</strong>";
	private static final String FILE_NAME_START_TAG = "<strong class=\"final-path\">";
	private static final String FILE_NAME_START = FILE_SIZE_START;
	
	@Autowired
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Override
	public FileInfo getByPath(final String filePath) {
		final String pageContent = connectorGatewayFactory.load(filePath);
		final String fileName = this.getFileName(pageContent);
		final String lineNumber = this.getLineNumber(pageContent);
		final String fileSize = this.getFileSize(pageContent);
		
		return new FileInfo(fileName, filePath, Long.valueOf(lineNumber), new BigDecimal(fileSize));
	}

	private String getLineNumber(final String pageContent) {
		return this.getValueInsideTag(pageContent, LINE_NUMBER_START_TAG, LINE_NUMBER_END_TAG, LINE_NUMBER_START);
	}

	private String getFileName(final String pageContent) {
		return this.getValueInsideTag(pageContent, FILE_NAME_START_TAG, FILE_NAME_END_TAG, FILE_NAME_START);
	}
	
	private String getFileSize(final String pageContent) {
		final String fileSize = this.getValueInsideTag(pageContent, FILE_SIZE_START_TAG, FILE_SIZE_END_TAG, FILE_SIZE_START);
		return StringUtils.delete(StringUtils.delete(fileSize, FILE_SIZE_SPAN_CLEAN), FILE_SIZE_KB_CLEAN).trim();
	}
	
	//TODO: Maybe this method is better stay inside of any util class
	private String getValueInsideTag(final String source, final String startTag, final String endTag, final String startValue) {
		final int indexStartTag = source.indexOf(startTag);
		final int indexEndTag = source.indexOf(endTag, indexStartTag);
		final String tag = source.substring(indexStartTag, indexEndTag);
		final int indexStartValue = tag.indexOf(startValue);
		return tag.substring(indexStartValue+1).trim();
	}

}
