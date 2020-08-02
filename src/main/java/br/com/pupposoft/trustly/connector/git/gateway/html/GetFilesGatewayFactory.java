package br.com.pupposoft.trustly.connector.git.gateway.html;

public interface GetFilesGatewayFactory {

	public GetFileInfosGateway getFileInfos(final String url);
	public GetFileInfosGateway getFilePaths(final String url);
	
}
