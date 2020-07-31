package br.com.pupposoft.trustly.connector.git.usecases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
		final List<ExtensionFileInfos> extensionFileInfosList = new ArrayList<>();
		
		final List<FileInfo> filesInfo = new ArrayList<>();
		filesPath.forEach(filePath -> filesInfo.add(this.getFileInfosGateway.getByPath(filePath)));
		
		final Set<String> extensions = new HashSet<>();
		filesInfo.forEach(fi -> extensions.add(fi.getExtension()));
		
		extensions.forEach(e -> {
			final List<FileInfo> filesFiltered = filesInfo.stream().filter(fi -> fi.getExtension().equals(e)).collect(Collectors.toList());
			final ExtensionFileInfos extensionFileInfos = new ExtensionFileInfos(e, filesFiltered);
			extensionFileInfosList.add(extensionFileInfos);
		});
		
		return extensionFileInfosList;
	}

}
