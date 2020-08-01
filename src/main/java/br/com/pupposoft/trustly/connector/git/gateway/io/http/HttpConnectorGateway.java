package br.com.pupposoft.trustly.connector.git.gateway.io.http;

import br.com.pupposoft.trustly.connector.git.gateway.io.ConnectorGateway;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpConnectorGateway implements ConnectorGateway {

	@Override
	public String load(final String filePath) {

		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			final Request request = new Request.Builder()
					.url(filePath)
					.method("GET", null)
					.build();
			final Response response = client.newCall(request).execute();
			
			if(response.isSuccessful()) {
				return new String(response.body().bytes());
			}
			
			// TODO: Lancar exceção especifica!!
			throw new RuntimeException("Error to get http resource!");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: Lancar exceção especifica!!
			throw new RuntimeException(e);
		}
	}

}
