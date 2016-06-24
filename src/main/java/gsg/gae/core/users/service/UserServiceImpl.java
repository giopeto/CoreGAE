package gsg.gae.core.users.service;

import gsg.gae.core.auth.service.AuthService;
import gsg.gae.core.common.objectify.service.OfyService;
import gsg.gae.core.users.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	@Qualifier("objectifyService")
	OfyService objectifyService;

	@Override
	public User set(User user) {

		User checkUser = getByVendorId(user.getVendorId());

		if (checkUser !=null && checkUser.getId() > 0) {
			user.setId(checkUser.getId());
		}

		objectifyService.ofy().save().entity(user).now();

		return user;
	}

	@Override
	public List get() {
		return objectifyService.ofy().load().type(User.class).list();
	}

	@Override
	public User getById(Long id) {
		return null;
	}

	@Override
	public User getByVendorId(String vendorId) {
		User user = null;
		List<User> listUser = objectifyService.ofy().load().type(User.class).filter("vendorId", vendorId).list();

		if (!listUser.isEmpty()) {
			user = listUser.get(0);
		}

		return user;
	}
}
