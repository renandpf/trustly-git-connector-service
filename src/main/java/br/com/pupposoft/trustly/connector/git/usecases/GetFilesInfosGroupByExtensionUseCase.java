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
import br.com.pupposoft.trustly.connector.git.gateway.html.GetFilesInfosGatewayFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetFilesInfosGroupByExtensionUseCase {

	@Autowired
	private GetFilesInfosGatewayFactory getFilesInfosGatewayFactory;
	
	public List<ExtensionFileInfos> get(final List<String> filesPath) {
		log.trace("filesPath: ", filesPath);
		final List<FileInfo> filesInfo = this.getFilesInfos(filesPath);
		return this.groupByExtension(filesInfo);
	}

	private List<ExtensionFileInfos> groupByExtension(final List<FileInfo> filesInfo) {
		
		final Set<String> extensions = getAllExtensionFromFiles(filesInfo);
		final List<ExtensionFileInfos> extensionFileInfosList = new ArrayList<>();
		extensions.forEach(e -> {
			final List<FileInfo> filesFiltered = filesInfo.stream().filter(fi -> fi.getExtension().equals(e)).collect(Collectors.toList());
			final ExtensionFileInfos extensionFileInfos = new ExtensionFileInfos(e, filesFiltered);
			extensionFileInfosList.add(extensionFileInfos);
		});
		
		return extensionFileInfosList;
	}

	private Set<String> getAllExtensionFromFiles(final List<FileInfo> filesInfo) {
		final Set<String> extensions = new HashSet<>();
		filesInfo.forEach(fi -> extensions.add(fi.getExtension()));
		return extensions;
	}

	private List<FileInfo> getFilesInfos(final List<String> filesPath) {
		final List<FileInfo> filesInfo = new ArrayList<>();
		filesPath.forEach(filePath -> filesInfo.add(this.getFilesInfosGatewayFactory.get(filePath).getByPath(filePath)));
		return filesInfo;
	}

}
