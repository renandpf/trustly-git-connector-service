package br.com.pupposoft.trustly.connector.git.gateway.controller;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ClassUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.pupposoft.trustly.connector.git.gateway.controller.json.ExtensionFileInfosRequestJson;
import br.com.pupposoft.trustly.connector.git.test.JsonUtils;
import br.com.pupposoft.trustly.connector.git.test.fixture.JsonTemplate;
import br.com.pupposoft.trustly.connector.git.test.fixture.JsonTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileInfosControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(JsonTemplateLoader.class));
	}

	@Ignore //Only to build faster
	@Test
	public void extensionFileInfos() throws Exception {
		
		final ExtensionFileInfosRequestJson request = Fixture.from(ExtensionFileInfosRequestJson.class).gimme(JsonTemplate.GITHUB_PROJECT);
		
		mockMvc.perform(post("/trustly/git/extension-file-infos")
				.contentType("application/json")
				.content(JsonUtils.generateJsonStringFromObject(request)))
		.andExpect(status().isOk());
	}

}
