package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GetAllFilesPathUseCase {

	public List<String> get(final String urlBase) {
		// TODO Implementar
		
		final List<String> allPathMock = Arrays.asList(
				"https://github.com/renandpf/trustly-git-connector-service/blob/master/.gitignore",
				"https://github.com/renandpf/trustly-git-connector-service/blob/master/README.md",
				"https://github.com/renandpf/trustly-git-connector-service/blob/master/pom.xml");
		
		
		return allPathMock;
	}

}
