package br.com.pupposoft.trustly.connector.git.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
}
