package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;
import br.com.pupposoft.trustly.connector.git.domains.FileInfo;
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFileInfosGateway;

@Service
public class GetFilesInfosGroupByExtensionUseCase {

	@Autowired
	private GetFileInfosGateway getFileInfosGateway;
	
	public List<ExtensionFileInfos> get(final List<String> filesPath) {
		final List<FileInfo> filesInfo = new ArrayList<>();
		filesPath.forEach(filePath -> filesInfo.add(this.getFileInfosGateway.getByPath(filePath)));
		
		//TODO: Implementar
		return null;
	}

}
