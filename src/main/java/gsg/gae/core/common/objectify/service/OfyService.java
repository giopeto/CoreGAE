package gsg.gae.core.common.objectify.service;

import gsg.gae.core.groups.domain.Group;
import gsg.gae.core.items.domain.Item;
import gsg.gae.core.ps.domain.PS;
import gsg.gae.core.users.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;

@Service
@Qualifier("objectifyService")
public class OfyService {
	static {
		ObjectifyService.register(Item.class);
		ObjectifyService.register(Group.class);
		ObjectifyService.register(User.class);
		ObjectifyService.register(PS.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}