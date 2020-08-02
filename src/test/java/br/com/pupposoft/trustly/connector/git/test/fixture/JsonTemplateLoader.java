package br.com.pupposoft.trustly.connector.git.test.fixture;

import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class JsonTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		JsonTemplate.load();		
	}

}
