package gsg.gae.core.auth.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import gsg.gae.core.common.http.HttpService;

import gsg.gae.core.users.domain.User;
import gsg.gae.core.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthServiceImpl implements AuthService {

	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("httpService")
	HttpService httpService;

	public User getFacebookUser(String code, String clientId, String redirectUri) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		String ACCESS_TOKEN = null;
		String EXCHANGE_CODE_FOR_TOKEN_URL = "https://graph.facebook.com/v2.3/oauth/access_token";
		String EXCHANGE_TOKEN_FOR_USER_URL  = "https://graph.facebook.com/me";

		String urlParameters = "?code="
				+ code
				+ "&client_id="
				+ clientId
				+ "&client_secret=d64c39571ff4a5d277840d2ff9748130"
				+ "&redirect_uri="
				+ redirectUri;


		String oathTokenResponseString = httpService.getHttpResponse(EXCHANGE_CODE_FOR_TOKEN_URL + urlParameters, "POST");
		ObjectReader reader = objectMapper.reader(Map.class);

		Map<String, String> oathTokenResponse = reader.readValue(oathTokenResponseString);

		if(oathTokenResponse.get("access_token") != null) {
			ACCESS_TOKEN = oathTokenResponse.get("access_token").toString().replaceAll("\"", "");
		}

		urlParameters = null;
		urlParameters = "?access_token="
				+ ACCESS_TOKEN
				+ "&fields="
				+ "id,email,name,first_name,last_name,picture,gender,languages,locale";



		String oathUserResponseString = httpService.getHttpResponse(EXCHANGE_TOKEN_FOR_USER_URL + urlParameters, "GET");

		JsonNode rootNode = objectMapper.readTree(oathUserResponseString);
		JsonNode idNode = rootNode.path("id");
		JsonNode firstNameNode = rootNode.path("first_name");
		JsonNode lastNameNode = rootNode.path("last_name");
		JsonNode pictureNode = rootNode.path("picture");

		JsonNode pictureDataNode = pictureNode.path("data");

		JsonNode pictureUrlNode = pictureDataNode.path("url");



		Map<String, String> oathUserResponse = new HashMap<String, String>();

		oathUserResponse.put("vendor", "facebook");
		//oathUserResponse.put("vendorCode", code);
		oathUserResponse.put("vendorId", idNode.textValue());
		oathUserResponse.put("given_name", firstNameNode.textValue());
		oathUserResponse.put("family_name", lastNameNode.textValue());
		oathUserResponse.put("email", rootNode.path("email").textValue());
		oathUserResponse.put("picture", pictureUrlNode.textValue());
		oathUserResponse.put("gender", rootNode.path("gender").textValue());
		oathUserResponse.put("locale", rootNode.path("locale").textValue());

		oathUserResponse.remove("id");



		User user  = objectMapper.convertValue(oathUserResponse, User.class);




		return userService.set(user);
	}


	@Override
	public User getGoogleUser(String code, String clientId, String redirectUri) throws IOException {

		logger.info("METHOD:getFacebookUser");
		logger.info("Code: " + code);
		logger.info("clientId: " + clientId);
		logger.info("redirectUri: " + redirectUri);
		ObjectMapper objectMapper = new ObjectMapper();

		String ACCESS_TOKEN = null;
		String EXCHANGE_CODE_FOR_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
		String EXCHANGE_TOKEN_FOR_USER_URL  = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";

		String urlParameters = "?code="
				+ code
				+ "&client_id="
				+ clientId
				+ "&client_secret=xDgxKDTKthKqt-ly5VrPodvJ&grant_type=authorization_code&redirect_uri="
				+ redirectUri;

		String oathTokenResponseString = httpService.getHttpResponse(EXCHANGE_CODE_FOR_TOKEN_URL + urlParameters, "POST");

		ObjectReader reader = objectMapper.reader(Map.class);

		Map<String, String> oathTokenResponse = reader.readValue(oathTokenResponseString);

		if(oathTokenResponse.get("access_token") != null) {
			ACCESS_TOKEN = oathTokenResponse.get("access_token").toString().replaceAll("\"", "");
		}
		ACCESS_TOKEN = oathTokenResponse.get("access_token").toString().replaceAll("\"", "");

		logger.info("oathTokenResponse: " + oathTokenResponse);

		logger.info("FIRST ACCESS_TOKEN: " + ACCESS_TOKEN);

		String oathUserResponseString = httpService.getHttpResponse(EXCHANGE_TOKEN_FOR_USER_URL + ACCESS_TOKEN, "GET");


		logger.info("oathUserResponseString: " + oathUserResponseString);

		JsonNode rootNode = objectMapper.readTree(oathUserResponseString);
		JsonNode idNode = rootNode.path("id");


		//
		JsonNode firstNameNode = rootNode.path("given_name");
		JsonNode lastNameNode = rootNode.path("family_name");
		JsonNode emailNode = rootNode.path("email");


		JsonNode pictureUrlNode = rootNode.path("picture");




		Map<String, String> oathUserResponse = new HashMap<String, String>();

		oathUserResponse.put("vendor", "google");
		//oathUserResponse.put("vendorCode", code);
		oathUserResponse.put("vendorId", idNode.textValue());

		oathUserResponse.put("given_name", firstNameNode.textValue());
		oathUserResponse.put("family_name", lastNameNode.textValue());
		oathUserResponse.put("email", emailNode.textValue());

		oathUserResponse.put("gender", rootNode.path("gender").textValue());
		oathUserResponse.put("locale", rootNode.path("locale").textValue());



		oathUserResponse.put("picture", pictureUrlNode.textValue());


		oathUserResponse.remove("id");



		//11:04:07,371  INFO AuthService:167 - oathUserResponseString: {"id": "111802934228760900008","email": "georg3georgiev@gmail.com","verified_email": true,"name": "Георги Георгиев","given_name": "Георги","family_name": "Георгиев","link": "https://plus.google.com/111802934228760900008","picture": "https://lh4.googleusercontent.com/-TcOP0UmM8uQ/AAAAAAAAAAI/AAAAAAAAAx4/WekMEQkHFqw/photo.jpg","gender": "male","locale": "bg"}


		User user  = objectMapper.convertValue(oathUserResponse, User.class);
		logger.info("bEFORE SAVE user is" + user.toString());

		userService.set(user);

		return user;

	}
}
