package gsg.gae.core.items.service;

import gsg.gae.core.items.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by George on 15.5.2016 г..
 */
@Service
public interface ItemService {

	public Item save(Item item);
	public List get();
	public Item getById(Long id);
	public List getByGroupId(Long groupId);
	public void delete(Long id);
	public void deleteImageToItem(Long id);






	/*
	public void deleteImageToItem(Long id);
	public void update(Item reqItem);

	public List<Item> getItemsByGroupId(Long id);*/

}
