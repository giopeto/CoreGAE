package gsg.gae.core.ps.controller;

import com.googlecode.objectify.Ref;
import gsg.gae.core.auth.service.AuthService;
import gsg.gae.core.items.domain.Item;
import gsg.gae.core.ps.domain.PS;
import gsg.gae.core.ps.service.PSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ps")
public class PSController {

	private final Logger logger = LoggerFactory.getLogger(PSController.class);

	@Autowired
	private PSService psService;

	@RequestMapping(
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public PS save(@RequestBody PS ps) {


		logger.info("PSController create Empty PS");
		return psService.createEmptyPS(ps.getUserId());


		/*if(ps.getStatus() == null) {

		} else {
			logger.info("\n\n\n\n\n\n\n\n\n\nPSController save obj: " + ps.items + ", to str: " + ps.toString());
			*//*for (Ref item : ps.items) {
			System.out.println(item);
			}
			logger.info("PSController save obj: " + ps.items + ", to str: " + ps.toString());
			return null;*//*
			//return psService.save(ps);
			return null;
		}*/

	}


	/*@RequestMapping(
			value="{userId}",
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public PS createEmptyPS(@PathVariable Long userId) {

		logger.info("PSController create Empty PS obj: " + userId);

		return psService.createEmptyPS(userId);
	}*/



	@RequestMapping(
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public List get() {
		return psService.get();
	}

	@RequestMapping(
			value="{id}",
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public PS getById(@PathVariable long id) {

		return psService.getById(id);
	}

	@RequestMapping(
			value="{id}",
			method = RequestMethod.PUT,
			headers="Accept=application/json",
			produces="application/json"
	)
	public PS update (@PathVariable long id, @RequestBody PS ps) {
		//logger.info("\nPSController update obj: " + ps.newItem.getId() + ", to str: " + ps.toString());

	/*	Item thisItem = new Item();
		thisItem.setId(ps.newItem.getId());
		//ps.setNewItem(null);



		Ref refItem = Ref.create(thisItem);
		ps.items.add(refItem);
		logger.info("ps :" + ps.toString());

		logger.info("thisItem :" + thisItem);*/

		psService.save(ps);

		return ps;
		/*Ref refItem = Ref.create(thisItem);
		ps.setNewItem(null);
		ps.items.add(refItem);
		//ps.setNewItem(null);
		return psService.save(ps);*/
	}





}
