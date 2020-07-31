package br.com.pupposoft.trustly.connector.git.gateway.html;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;

public interface GetFileInfosGateway {

	FileInfo getByPath(final String filePath);

}
