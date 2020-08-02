package br.com.pupposoft.trustly.connector.git.gateway.html.github.json;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AllFilePathsResponseJson {
	private List<String> paths;
}
