package br.com.pupposoft.trustly.connector.git.test.fixture;

import java.math.BigDecimal;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FileInfoTemplate {

	public static final String TXT = "TXT";

	protected static void load() {

		Fixture.of(FileInfo.class).addTemplate(TXT, new Rule() {
			{
				add("name", random("file_01.txt", "file_02.txt"));
				add("path", random("/dir/file_01.txt", "/dir/file_02.txt"));
				add("lines", random(15L));
				add("size", new BigDecimal("55.5"));
			}
		});
	}
}
