package br.com.pupposoft.trustly.connector.git.gateway.html.github;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.pupposoft.trustly.connector.git.gateway.html.github.exceptions.ErrorToGetAllPathsFilesGatewayException;

public class GetAllFilePathsRestGitHubIntTest {

	@Ignore //Integrated test
	@Test
	public void sucess() {
		final GetAllFilePathsRestGitHub getAllFilePathsRestGitHub = new GetAllFilePathsRestGitHub();
		final List<String> filesPaths = getAllFilePathsRestGitHub.getPaths("https://github.com/renandpf/trustly-git-connector-service/tree-list/8f193439efd30176df9911fb9540f41ee9c17e92");
		assertEquals(54, filesPaths.size());
	}
	
	@Ignore //Integrated test
	@Test(expected = ErrorToGetAllPathsFilesGatewayException.class)
	public void error() {
		final GetAllFilePathsRestGitHub getAllFilePathsRestGitHub = new GetAllFilePathsRestGitHub();
		try {
			
			getAllFilePathsRestGitHub.getPaths("https://github.com/renandpf/trustly-git-connector-service/tree-list/ERROR");
		} catch (ErrorToGetAllPathsFilesGatewayException e) {
			throw e;
		}
		
	}
	
}
