package br.com.pupposoft.trustly.connector.git.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import br.com.pupposoft.trustly.connector.git.test.fixture.DomainsTemplateLoader;
import br.com.pupposoft.trustly.connector.git.test.fixture.FileInfoTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class ExtensionFileInfosUnitTest {

	
	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
	}

	@Test
	public void getAmountLines() {
		final FileInfo fileTxtA = Fixture.from(FileInfo.class)
				.gimme(FileInfoTemplate.TXT, new Rule() {
			{
				add("lines", 10L);
			}});
		final FileInfo fileTxtB = Fixture.from(FileInfo.class)
				.gimme(FileInfoTemplate.TXT, new Rule() {
			{
				add("lines", 15L);
			}});
		
		
		List<FileInfo> filesInfos = Arrays.asList(fileTxtA, fileTxtB);
		final ExtensionFileInfos extensionFileInfos = new ExtensionFileInfos("txt", filesInfos);
		
		assertEquals(25L, extensionFileInfos.getAmountLines());
	}
	
	@Test
	public void getAmountBytes() {
		final FileSize fileSizeA = Fixture.from(FileSize.class)
				.gimme(FileInfoTemplate.ANY_SIZE, new Rule() {
					{
						add("size", new BigDecimal("7.41"));
						add("measurement", Measurement.MBYTES);
					}});
		final FileInfo fileTxtA = Fixture.from(FileInfo.class)
				.gimme(FileInfoTemplate.TXT, new Rule() {
			{
				add("fileSize", fileSizeA);
			}});

		final FileSize fileSizeB = Fixture.from(FileSize.class)
				.gimme(FileInfoTemplate.ANY_SIZE, new Rule() {
					{
						add("size", new BigDecimal("2.42"));
						add("measurement", Measurement.KBYTES);
					}});
		final FileInfo fileTxtB = Fixture.from(FileInfo.class)
				.gimme(FileInfoTemplate.TXT, new Rule() {
			{
				add("fileSize", fileSizeB);
			}});

		final FileSize fileSizeC = Fixture.from(FileSize.class)
				.gimme(FileInfoTemplate.ANY_SIZE, new Rule() {
					{
						add("size", new BigDecimal("82"));
						add("measurement", Measurement.BYTES);
					}});
		final FileInfo fileTxtC = Fixture.from(FileInfo.class)
				.gimme(FileInfoTemplate.TXT, new Rule() {
			{
				add("fileSize", fileSizeC);
			}});
		
		//7769948.16B + 2478.08B + 82B = 7772508.24B
		List<FileInfo> filesInfos = Arrays.asList(fileTxtA, fileTxtB, fileTxtC);
		final ExtensionFileInfos extensionFileInfos = new ExtensionFileInfos("txt", filesInfos);
		
		assertEquals(new BigDecimal("7772508.24"), extensionFileInfos.getAmountSizeInBytes());
	}
	
}
