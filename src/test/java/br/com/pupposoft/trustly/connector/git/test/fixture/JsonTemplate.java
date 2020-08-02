package br.com.pupposoft.trustly.connector.git.test.fixture;

import br.com.pupposoft.trustly.connector.git.gateway.controller.json.ExtensionFileInfosRequestJson;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class JsonTemplate {

	public static final String GITHUB_PROJECT = "GITHUB_PROJECT";

	protected static void load() {

		Fixture.of(ExtensionFileInfosRequestJson.class).addTemplate(GITHUB_PROJECT, new Rule() {
			{
				add("repositoryBaseUrl", "https://github.com/renandpf/teste-git");
			}
		});
	}
}
