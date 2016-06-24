package gsg.gae.core.items.controller;

import gsg.gae.core.items.domain.Item;
import gsg.gae.core.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public Item save(@RequestBody Item item) {
		return itemService.save(item);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public List get() {
		return itemService.get();
	}


	@RequestMapping(
			value="{id}",
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public Item getById(@PathVariable long id) {
		System.out.print("IN ctrl"); return itemService.getById(id);
	}


	@RequestMapping(
			value="getByGroupId/{groupId}",
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public List getByGroupId(@PathVariable long groupId) {
		System.out.println("In item Contrrrr");
		return itemService.getByGroupId(groupId);
	}


	@RequestMapping(
			value="{id}",
			method = RequestMethod.PUT,
			headers="Accept=application/json",
			produces="application/json"
	)
	public void update (@PathVariable long id, @RequestBody Item item) {
		itemService.save(item);
	}


	@RequestMapping(
			value="{id}",
			method = RequestMethod.DELETE
	)
	public void delete(@PathVariable long id) {
		itemService.delete(id);
	}
}
