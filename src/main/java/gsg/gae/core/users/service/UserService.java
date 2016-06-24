package gsg.gae.core.users.service;

import gsg.gae.core.users.domain.User;

import java.util.List;

/**
 * Created by George on 16.5.2016 г..
 */
public interface UserService {

	public User set (User user);
	public List get();
	public User getById(Long id);
	public User getByVendorId(String vendorId);


}
