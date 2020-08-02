package br.com.pupposoft.trustly.connector.git.gateway.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.gateway.html.exceptions.UnknownRepositoryGatewayException;

@Component
public class GetFilesInfosGatewayFactoryImpl implements GetFilesInfosGatewayFactory {

	@Autowired
	private GetFileInfosGateway getFileInfosGitHubGateway;
	
	//Put here other repositories implementations vendors!
	
	@Override
	public GetFileInfosGateway get(final String url) {
		if(url.contains("github.com")) {
			return this.getFileInfosGitHubGateway;
		}
		
		throw new UnknownRepositoryGatewayException();
	}

}
