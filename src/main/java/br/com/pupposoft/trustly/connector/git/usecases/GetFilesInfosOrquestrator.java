package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.trustly.connector.git.domains.FileInfo;

@Service
public class GetFilesInfosOrquestrator {

	@Autowired
	private GetAllFilesPathUseCase getAllFilesPathUseCase;
	
	public List<FileInfo> get(final String projectUrlBase) {
		//TODO: Implementar
		final List<String> allFilesPath = this.getAllFilesPathUseCase.get(projectUrlBase);
		return null;
	}
}
