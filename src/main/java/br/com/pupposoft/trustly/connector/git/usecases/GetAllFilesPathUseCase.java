package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.trustly.connector.git.gateway.html.GetFilesInfosGatewayFactory;
import br.com.pupposoft.trustly.connector.git.usecases.exceptions.UnknownGitRepositoryBusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetAllFilesPathUseCase {

	@Autowired
	private GetFilesInfosGatewayFactory getFilesInfosGatewayFactory;
	
	public List<String> get(final String urlBase) {
		log.trace("urlBase: {}", urlBase);
		final String urlAllFiles = this.getAllPathFile(urlBase);
		
		final List<String> allFilesPaths = this.getFilesInfosGatewayFactory.get(urlAllFiles).getAllFilesPath(urlAllFiles);
		
		return allFilesPaths;
	}

	private String getAllPathFile(final String urlBase) {
		if(urlBase.contains("github.com")) {
			return urlBase + "/find/master";
		}
		
		log.warn("Unknown git repository");
		throw new UnknownGitRepositoryBusinessException();
	}

}
