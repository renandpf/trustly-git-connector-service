package br.com.pupposoft.trustly.connector.git.gateway.controller.json;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ExtensionFileInfosJson {
	private String extensionName;
	private Long totalLineNumbers;
	private Double totalBytes;
	
	public ExtensionFileInfosJson(final ExtensionFileInfos extensionFileInfos) {
		this.extensionName = extensionFileInfos.getExtensionName();
		this.totalLineNumbers = extensionFileInfos.getAmountLines();
		this.totalBytes = extensionFileInfos.getAmountSizeInBytes().doubleValue();
	}
}
