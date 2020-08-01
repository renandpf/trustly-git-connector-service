package br.com.pupposoft.trustly.connector.git.domains;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileInfo {
	private String name;
	private String path;
	private Long lines;
	private BigDecimal size;
	
	public String getExtension() {
		final String extension = StringUtils.getFilenameExtension(this.name);
		if(extension != null) {
			return extension;
		}
		return "";
	}
}
