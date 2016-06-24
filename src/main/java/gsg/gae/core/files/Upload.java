package gsg.gae.core.files;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import gsg.gae.core.items.domain.Item;
import gsg.gae.core.items.service.ItemServiceImpl;



public class Upload extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	private ItemServiceImpl itemService = new ItemServiceImpl();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("IN Upload");

		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");

		String fileId = blobKeys.get(0).getKeyString();
		//BlobKey fileId = blobKeys.get(0);

		long itemId =  Long.valueOf(req.getParameter("itemId")).longValue();
		System.out.println("Item id 2: " + itemId);
		Item item = itemService.getById(itemId);

		System.out.println("item.toStr(): " + item.getFileIds());

		Map<String, String>  fields = item.getFileIds();

		//System.out.println("fields(): " + fields.toString());

		/*int fileNumber = 0;
		if (fields != null) {
			fileNumber = fields.size();
		}*/
		Long fileNumber = (long) fields.size();
		fileNumber++;
		fields.put(fileNumber.toString(), fileId);



		ofy().save().entity(item).now();

		res.sendRedirect("/");
	}
}