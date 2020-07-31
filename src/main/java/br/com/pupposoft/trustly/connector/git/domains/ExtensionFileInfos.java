package br.com.pupposoft.trustly.connector.git.domains;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionFileInfos {
	private String extensionName;
	private List<FileInfo> files;
}
