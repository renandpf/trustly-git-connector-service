package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetFilesInfosOrquestrator {

	@Autowired
	private GetAllFilesPathUseCase getAllFilesPathUseCase;

	@Autowired
	private GetFilesInfosGroupByExtensionUseCase getFilesInfosGroupByExtension;
	
	public List<ExtensionFileInfos> get(final String projectUrlBase) {
		log.trace("projectUrlBase: {}", projectUrlBase);
		final List<String> allFilesPath = this.getAllFilesPathUseCase.get(projectUrlBase);
		return this.getFilesInfosGroupByExtension.get(allFilesPath);
	}
}
