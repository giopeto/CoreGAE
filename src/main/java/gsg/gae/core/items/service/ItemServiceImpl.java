package gsg.gae.core.items.service;


import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import gsg.gae.core.items.domain.Item;

import gsg.gae.core.common.objectify.service.OfyService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	@Qualifier("objectifyService")
	OfyService objectifyService;

	@Override
	public Item save(Item item) {
		if(item.getDate()==null) {
			item.setDate(new Date());
		}
		objectifyService.ofy().save().entity(item).now();
		return item;
	}

	@Override
	public List get() {
		return objectifyService.ofy().load().type(Item.class).order("-date").list();
	}

	@Override
	public Item getById(Long id) {
		return objectifyService.ofy().load().type(Item.class).id(id).now();
	}

	@Override
	public List getByGroupId(Long groupId) {
		return objectifyService.ofy().load().type(Item.class).filter("groupId", groupId).list();
	}


	@Override
	public void delete(Long id) {

		deleteImageToItem(id);
		objectifyService.ofy().delete().type(Item.class).id(id).now();
	}

	@Override
	public void deleteImageToItem(Long id) {
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

		Item item = getById(id);
		Map<String, String> fileIds = item.getFileIds();

		for (Map.Entry<String, String> entry : fileIds.entrySet()) {
			//System.out.println(entry.getKey() + "/" + entry.getValue());
			BlobKey blobKey = new BlobKey(entry.getKey());
			blobstoreService.delete(blobKey);
		}
	}


}
