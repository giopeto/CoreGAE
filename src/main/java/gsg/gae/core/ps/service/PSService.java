package gsg.gae.core.ps.service;

import gsg.gae.core.ps.domain.PS;

import java.util.List;


public interface PSService {

	public PS save(PS ps);
	public List<PS> get();
	public PS getById(Long id);
	public List<PS> getByUserId(Long id);
	public void delete(Long id);

	public PS createEmptyPS(Long userId);

}
