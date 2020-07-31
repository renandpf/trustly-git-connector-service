package br.com.pupposoft.trustly.connector.git.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class FileInfoUnitTest {

	@Test
	public void getExtension() {
		final FileInfo fileInfo = new FileInfo("test.txt", "path/test.txt", 15L, new BigDecimal("55.5"));
		final String extension = fileInfo.getExtension();
		assertEquals("txt", extension);
	}
	
	@Test
	public void getExtensionFromFileWithouExtension() {
		final FileInfo fileInfo = new FileInfo("test", "path/test", 15L, new BigDecimal("55.5"));
		final String extension = fileInfo.getExtension();
		assertEquals("", extension);
	}
}
