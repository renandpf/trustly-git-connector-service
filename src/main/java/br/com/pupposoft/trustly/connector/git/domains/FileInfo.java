package br.com.pupposoft.trustly.connector.git.domains;

import java.math.BigDecimal;

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
		//FIXME: usar: StringUtils.getFilenameExtension(path)
		if(this.name.indexOf(".") > 0) {
			return this.name.substring(this.name.lastIndexOf(".")+1, this.name.length());
		}
		
		return "";
	}
}
