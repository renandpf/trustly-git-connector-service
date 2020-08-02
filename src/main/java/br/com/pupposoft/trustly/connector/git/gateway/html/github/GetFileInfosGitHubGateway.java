package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;

@Component
public class GetFileInfosGitHubGateway implements GetFileInfosGateway {

	@Autowired
	private GetFileInfosGitHubScrap getFileInfosGitHubScrap; 
	
	@Override
	public FileInfo getByPath(String filePath) {
		//TODO: Create unit test!!
		return this.getFileInfosGitHubScrap.getByPath(filePath);
	}

	@Override
	public List<String> getAllFilesPath(String urlAllFiles) {
		// TODO Implementar
		return null;
	}
}
