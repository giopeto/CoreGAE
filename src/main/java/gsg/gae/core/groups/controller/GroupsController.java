package gsg.gae.core.groups.controller;

import gsg.gae.core.groups.domain.Group;
import gsg.gae.core.groups.service.GroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private GroupService groupsService;

    @RequestMapping(
            method = RequestMethod.POST,
            headers="Accept=application/json",
            produces="application/json"
    )
    public Group save(@RequestBody Group group) {
        return groupsService.save(group);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            headers="Accept=application/json",
            produces="application/json"
    )
    public List get() {
        return groupsService.get();
    }


    @RequestMapping(
            value="{id}",
            method = RequestMethod.GET,
            headers="Accept=application/json",
            produces="application/json"
    )
    public Group getById(@PathVariable long id) {
        return groupsService.getById(id);
    }


    @RequestMapping(
            value="{id}",
            method = RequestMethod.PUT,
            headers="Accept=application/json",
            produces="application/json"
    )
    public void update (@PathVariable long id, @RequestBody Group group) {
        groupsService.save(group);
    }


    @RequestMapping(
            value="{id}",
            method = RequestMethod.DELETE
    )
    public void delete(@PathVariable long id) {
        groupsService.delete(id);
    }






}
