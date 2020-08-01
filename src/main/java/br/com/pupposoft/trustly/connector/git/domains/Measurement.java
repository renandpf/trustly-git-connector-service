package br.com.pupposoft.trustly.connector.git.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Measurement {
	BYTES("MB"),
	KBYTES("Bytes"),
	MBYTES("KB");

	private String measurement;
}
