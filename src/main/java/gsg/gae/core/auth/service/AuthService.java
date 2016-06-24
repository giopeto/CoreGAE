package gsg.gae.core.auth.service;


import gsg.gae.core.users.domain.User;

import java.io.IOException;

public interface AuthService {

	User getGoogleUser(String code, String clientId, String redirectUri) throws IOException;
	User getFacebookUser(String code, String clientId, String redirectUri) throws IOException;

}
