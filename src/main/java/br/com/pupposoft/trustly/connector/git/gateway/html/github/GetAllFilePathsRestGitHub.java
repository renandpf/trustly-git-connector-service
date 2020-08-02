package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.trustly.connector.git.gateway.html.github.exceptions.ErrorToGetAllPathsFilesGatewayException;
import br.com.pupposoft.trustly.connector.git.gateway.html.github.json.AllFilePathsResponseJson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class GetAllFilePathsRestGitHub {

	public List<String> getPaths(final String filesPathsDataSourceUrl) {

		try {
			log.trace("filesPathsDataSourceUrl: {}", filesPathsDataSourceUrl);
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
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToGetAllPathsFilesGatewayException();
		}
	}

}
