package br.com.pupposoft.trustly.connector.git.domains;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileSize {
	private BigDecimal size;
	private Measurement measurement;
}
