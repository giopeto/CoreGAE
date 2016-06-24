package gsg.gae.core.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import gsg.gae.core.auth.service.AuthService;
import gsg.gae.core.users.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthService.class);


	@Autowired
	private AuthService authService;

	ObjectMapper mapper = new ObjectMapper();
	ObjectReader reader = mapper.reader(Map.class);

	@RequestMapping(
			value={"facebook"},
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public User getFacebookUser(@RequestBody String jsonAuthData) throws IOException {
		Map<String, String> map = reader.readValue(jsonAuthData);
		return authService.getFacebookUser(map.get("code"), map.get("clientId"), map.get("redirectUri"));
	}

	@RequestMapping(
			value={"google"},
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public User get(@RequestBody String jsonAuthData) throws IOException {
		Map<String, String> map = reader.readValue(jsonAuthData);
		return authService.getGoogleUser(map.get("code"), map.get("clientId"), map.get("redirectUri"));
	}
}
