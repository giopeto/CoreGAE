package gsg.gae.core.groups.service;

import gsg.gae.core.groups.domain.Group;

import java.util.List;
/**
 * Created by george on 5/11/16.
 */
public interface GroupService {
    public Group save(Group g);
    public List get();
    public Group getById(Long id);
    public void delete(Long id);

}
