package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.trustly.connector.git.gateway.html.GetFilesPathsGateway;
import br.com.pupposoft.trustly.connector.git.usecases.exceptions.UnknownRepositoryBusinessException;

@Service
public class GetAllFilesPathUseCase {

	@Autowired
	private GetFilesPathsGateway getFilesPathsGateway;
	
	public List<String> get(final String urlBase) {
		final String urlAllFiles = this.getAllPathFile(urlBase);
		
		final List<String> allFilesPaths = this.getFilesPathsGateway.get(urlAllFiles);
		
		return allFilesPaths;
	}

	private String getAllPathFile(final String urlBase) {
		if(urlBase.contains("github.com")) {
			return urlBase + "/find/master";
		}
		
		throw new UnknownRepositoryBusinessException();
	}

}
