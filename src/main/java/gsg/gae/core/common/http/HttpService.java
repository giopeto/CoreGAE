package gsg.gae.core.common.http;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
@Qualifier("httpService")
public class HttpService {

	public String getHttpResponse(String thisUrl, String httpMethod) throws IOException {

		URL url = new URL(thisUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.setDoOutput(true);
		connection.setRequestMethod(httpMethod);
		connection.setInstanceFollowRedirects(false);
		System.out.println("******************" + connection.toString());
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ connection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(connection.getInputStream()) , "UTF-8"));

		String responseString = "";
		String output;

		while ((output = br.readLine()) != null) {
			responseString += output.trim();

		}
		//Charset.forName("UTF-8").encode(responseString);

		return responseString;
	}

}
