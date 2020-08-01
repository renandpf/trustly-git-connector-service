package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;

@Component
public class GetFileInfosGatewayGitHub implements GetFileInfosGateway{

	@Override
	public FileInfo getByPath(final String filePath) {
		//TODO: Implementar

		return null;
	}

}
