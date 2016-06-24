package gsg.gae.core.groups.service;

import gsg.gae.core.common.objectify.service.OfyService;
import gsg.gae.core.groups.domain.Group;
import gsg.gae.core.ps.domain.PS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    @Qualifier("objectifyService")
    OfyService objectifyService;

    public Group save(Group g) {
        objectifyService.ofy().save().entity(g).now();
        return g;
    }

    public List<Group> get() {
        return objectifyService.ofy().load().type(Group.class).order("name").list();
    }


    public Group getById(Long id) {
        return objectifyService.ofy().load().type(Group.class).id(id).now();
    }

    public void delete(Long id) {
        objectifyService.ofy().delete().type(Group.class).id(id).now();

    }
}
