package br.com.pupposoft.trustly.connector.git.test.fixture;

import java.math.BigDecimal;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.domains.FileSize;
import br.com.pupposoft.trustly.connector.git.domains.Measurement;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FileInfoTemplate {

	private static final String ANY_SIZE = "ANY_SIZE";
	public static final String TXT = "TXT";
	public static final String JAVA = "JAVA";
	public static final String TS = "TS";
	public static final String NO_EXTENSION = "NO_EXTENSION";

	protected static void load() {

		Fixture.of(FileSize.class).addTemplate(ANY_SIZE, new Rule() {
			{
				add("size", new BigDecimal("55.5"));
				add("measurement", Measurement.MBYTES);
			}
		});
		
		Fixture.of(FileInfo.class).addTemplate(TXT, new Rule() {
			{
				add("name", random("file_01.txt", "file_02.txt"));
				add("path", random("/dir/file_01.txt", "/dir/file_02.txt"));
				add("lines", random(15L));
				add("fileSize", one(FileSize.class, ANY_SIZE));
			}
		});
		
		Fixture.of(FileInfo.class).addTemplate(JAVA).inherits(TXT, new Rule() {
			{
				add("name", random("file_01.java", "file_02.java"));
				add("path", random("/dir/file_01.java", "/dir/file_02.java"));
			}
		});
		
		Fixture.of(FileInfo.class).addTemplate(TS).inherits(TXT, new Rule() {
			{
				add("name", random("file_01.ts", "file_02.ts"));
				add("path", random("/dir/file_01.ts", "/dir/file_02.ts"));
			}
		});
		
		Fixture.of(FileInfo.class).addTemplate(NO_EXTENSION).inherits(TXT, new Rule() {
			{
				add("name", random("file_01", "file_02"));
				add("path", random("/dir/file_01", "/dir/file_02"));
			}
		});
	}
}
