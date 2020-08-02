package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.domains.FileSize;
import br.com.pupposoft.trustly.connector.git.domains.Measurement;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;
import br.com.pupposoft.trustly.connector.git.gateway.html.exceptions.ErrorToGetGitFileInfoGatewayException;
import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

@Component
public class GetFileInfosGatewayGitHub implements GetFileInfosGateway{
	private static final String FILE_INFO_DIVIDER_CLASS = "<span class=\"file-info-divider\"></span>";
	
	private static final String FILE_SIZE_MBYTES_CLEAN = "MB";
	private static final String FILE_SIZE_BYTES_CLEAN = "Bytes";
	private static final String FILE_SIZE_KB_CLEAN = "KB";
	private static final String FILE_SIZE_SPAN_CLEAN = "</span>";
	private static final String FILE_SIZE_START = ">";
	private static final String FILE_SIZE_END_TAG = "</div>";
	private static final String FILE_SIZE_START_TAG = FILE_INFO_DIVIDER_CLASS;
	
	private static final String FILE_INFOS_START_TAG = "<div class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">";
	
	private static final String LINE_NUMBER_START = FILE_SIZE_START;
	private static final String LINE_NUMBER_END_TAG = "line";
	
	private static final String FILE_NAME_END_TAG = "</strong>";
	private static final String FILE_NAME_START_TAG = "<strong class=\"final-path\">";
	private static final String FILE_NAME_START = FILE_SIZE_START;
	
	@Autowired
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Override
	public FileInfo getByPath(final String filePath) {
		try {
			final String pageContent = connectorGatewayFactory.get(filePath).load(filePath);
			final String fileName = this.getFileName(pageContent);
			final Long lineNumber = this.getLineNumber(pageContent);
			final FileSize fileSize = this.getFileSize(pageContent);
			
			return new FileInfo(fileName, filePath, lineNumber, fileSize);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ErrorToGetGitFileInfoGatewayException();
		}
	}

	private Long getLineNumber(final String pageContent) {
		if(pageContent.contains(FILE_INFO_DIVIDER_CLASS)) {
			return Long.valueOf(this.getValueInsideTag(pageContent, FILE_INFOS_START_TAG, LINE_NUMBER_END_TAG, LINE_NUMBER_START));
		}
		
		return 0L;
	}

	private String getFileName(final String pageContent) {
		return this.getValueInsideTag(pageContent, FILE_NAME_START_TAG, FILE_NAME_END_TAG, FILE_NAME_START);
	}
	
	private FileSize getFileSize(final String pageContent) {
		final String fileSize = getFileSizeFromPageRule(pageContent);
		final Measurement measurement = getMesurement(fileSize);
		final String fileSizeClean = cleanFileSizeNumber(fileSize);
		
		return new FileSize(new BigDecimal(fileSizeClean), measurement);
	}

	private String getFileSizeFromPageRule(final String pageContent) {
		String fileSize;
		if(pageContent.contains(FILE_INFO_DIVIDER_CLASS)) {
			fileSize = this.getValueInsideTag(pageContent, FILE_SIZE_START_TAG, FILE_SIZE_END_TAG, FILE_SIZE_START);
		}else {
			fileSize = this.getValueInsideTag(pageContent, FILE_INFOS_START_TAG, FILE_SIZE_END_TAG, FILE_SIZE_START);
		}
		return fileSize;
	}

	private String cleanFileSizeNumber(String fileSize) {
		final String fileSizeClean = fileSize
				.replace(FILE_SIZE_SPAN_CLEAN, "")
				.replace(FILE_SIZE_KB_CLEAN, "")
				.replace(FILE_SIZE_BYTES_CLEAN, "")
				.replace(FILE_SIZE_MBYTES_CLEAN, "")
				.trim();
		return fileSizeClean;
	}

	private Measurement getMesurement(String fileSize) {
		Measurement measurement = Measurement.BYTES;
		if(fileSize.contains(FILE_SIZE_KB_CLEAN)) {
			measurement = Measurement.KBYTES;
		}else if(fileSize.contains(FILE_SIZE_MBYTES_CLEAN)){
			measurement = Measurement.MBYTES;
		}
		return measurement;
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
