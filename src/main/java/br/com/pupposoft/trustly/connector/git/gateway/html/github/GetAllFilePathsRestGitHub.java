package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.trustly.connector.git.gateway.html.github.exceptions.ErrorToGetAllPathsFilesGatewayException;
import br.com.pupposoft.trustly.connector.git.gateway.html.github.json.AllFilePathsResponseJson;

@Component
class GetAllFilePathsRestGitHub {

	public List<String> getPaths(final String filesPathsDataSourceUrl) {

		try {
			//TODO: 'WebClient' is thrown any exceptions in console. But the request works fine.
			
			@SuppressWarnings("rawtypes")
			WebClient.RequestHeadersSpec requestSpec = WebClient
					.create()
					.method(HttpMethod.GET)
					.uri(filesPathsDataSourceUrl)
					.header("Accept", " application/json");
			
			
			final AllFilePathsResponseJson response = requestSpec
					.retrieve()
					.bodyToMono(AllFilePathsResponseJson.class)
					.block();
			
			return response.getPaths();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorToGetAllPathsFilesGatewayException();
		}
	}

}
