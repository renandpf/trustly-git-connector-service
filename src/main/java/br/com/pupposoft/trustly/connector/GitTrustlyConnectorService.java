package br.com.pupposoft.trustly.connector;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitTrustlyConnectorService {
    public static void main(String[] args) throws Exception {
    	Locale.setDefault(new Locale( "pt", "BR" ));
        SpringApplication.run(GitTrustlyConnectorService.class, args);
    }
}
