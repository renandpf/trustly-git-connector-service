package br.com.pupposoft.trustly.connector.git.gateway.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.gateway.html.exceptions.UnknownRepositoryGatewayException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GetFilesInfosGatewayFactoryImpl implements GetFilesInfosGatewayFactory {

	@Autowired
	private GetFileInfosGateway getFileInfosGitHubGateway;
	
	//Put here other repositories implementations vendors!
	
	@Override
	public GetFileInfosGateway get(final String url) {
		log.trace("url: {};", url);
		if(url.contains("github.com")) {
			return this.getFileInfosGitHubGateway;
		}
		
		log.warn("No implementation found to requested repository.");
		throw new UnknownRepositoryGatewayException();
	}

}
