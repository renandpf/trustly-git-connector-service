package br.com.pupposoft.trustly.connector.git.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileInfo {
	private String path;
	private Long lines;
	private Double size;
}
