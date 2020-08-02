package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGatewayFactory;

@Component
public class GetAllFilesPathGitHubScrap {

	@Autowired
	private ConnectorGatewayFactory connectorGatewayFactory;

	public List<String> get(final String urlAllFiles) {
		final String pageContent = this.connectorGatewayFactory.get(urlAllFiles).load(urlAllFiles);
		
		//Buscar pelo ID do 'data-url' usando: 'mark-selector=".js-tree-browser-result-path"'.
		//Em seguida, deve chamar a URL (equivalente): '/renandpf/backend-challenge/tree-list/84112756b6aef53ea80711a6d907417f6213a75c'
		//Será retornado um JSON conforme salvo no arquivo 'all-files-data-url.json'
		
		//TODO: implementar
		return null;
	}
	
	//TODO: Maybe this method is better stay inside of any util class
	private String getValueInsideTag(final String source, final String startTag, final String endTag, final String startValue) {
		final int indexStartTag = source.indexOf(startTag);
		final int indexEndTag = source.indexOf(endTag, indexStartTag);
		final String tag = source.substring(indexStartTag, indexEndTag);
		final int indexStartValue = tag.indexOf(startValue);
		return tag.substring(indexStartValue+1).trim();
	}

}
