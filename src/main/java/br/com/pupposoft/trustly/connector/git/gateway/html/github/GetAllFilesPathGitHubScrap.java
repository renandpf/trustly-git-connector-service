package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

@Component
public class GetAllFilesPathGitHubScrap {
	private static final String DATA_URL_ID_CLEAN = "\"";
	private static final String DATA_URL_ID = "/";
	private static final String DATA_URL_ID_END = "mark-selector=\".js-tree-browser-result-path\"";
	private static final String DATA_URL_ID_START = "max-matches";

	@Autowired
	private ConnectorGatewayFactory connectorGatewayFactory;
	
	@Autowired
	private GetAllFilePathsRestGitHub getAllFilePathsRestGitHub;

	public List<String> get(final String urlAllFiles) {
		final String pageContent = this.connectorGatewayFactory.get(urlAllFiles).load(urlAllFiles);
		
		final String dataId = this.getDataUrlId(pageContent);
		final String baseRepositoryPath = this.getBaseRepositoryPath(urlAllFiles);
		final String filesPathsDataSourceUrl = this.makeDataSourceUrl(dataId, baseRepositoryPath);

		final List<String> paths = this.getAllFilePathsRestGitHub.getPaths(filesPathsDataSourceUrl);
		final List<String> filesLocations = paths.stream().map(path -> baseRepositoryPath + "/blob/master/" + path).collect(Collectors.toList());
		
		return filesLocations;
	}

	private String makeDataSourceUrl(final String dataId, final String baseRepositoryPath) {
		final String filesPathsDataSourceUrl = baseRepositoryPath + "/tree-list/" + dataId;
		return filesPathsDataSourceUrl;
	}

	private String getBaseRepositoryPath(final String urlAllFiles) {
		final int endIndex = urlAllFiles.indexOf("/find/master");
		final String baseRepositoryPath = urlAllFiles.substring(0, endIndex);
		return baseRepositoryPath.trim();
	}

	private String getDataUrlId(final String pageContent) {
		
		final int startindexOfId = pageContent.indexOf(DATA_URL_ID_START);
		final int endIndexOfId = pageContent.indexOf(DATA_URL_ID_END);
		final String dataUrl = pageContent.substring(startindexOfId, endIndexOfId);
		final String dataId = dataUrl.substring(dataUrl.lastIndexOf(DATA_URL_ID)+1).replace(DATA_URL_ID_CLEAN, "");
		return dataId.trim();
	}

}
