package br.com.pupposoft.trustly.connector.git.gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.trustly.connector.git.domains.ExtensionFileInfos;
import br.com.pupposoft.trustly.connector.git.gateway.controller.json.ExtensionFileInfosJson;
import br.com.pupposoft.trustly.connector.git.gateway.controller.json.ExtensionFileInfosRequestJson;
import br.com.pupposoft.trustly.connector.git.usecases.GetFilesInfosOrquestrator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("trustly/git/extension-file-infos")
public class FileInfosController {

	@Autowired
	private GetFilesInfosOrquestrator getFilesInfosOrquestrator;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public List<ExtensionFileInfosJson> getExtensionFileInfos(
			final @RequestBody(required = true) ExtensionFileInfosRequestJson extensionFileInfosRequestJson){
		final List<ExtensionFileInfos> extensionFileInfos = this.getFilesInfosOrquestrator.get(extensionFileInfosRequestJson.getRepositoryBaseUrl());
		final List<ExtensionFileInfosJson> extensionFileInfosJson = 
				extensionFileInfos
				.stream()
				.map(ExtensionFileInfosJson::new)
				.collect(Collectors.toList());
		
		return extensionFileInfosJson;
	}
}
