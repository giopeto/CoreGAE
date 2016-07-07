package gsg.gae.core.groups.service;

import gsg.gae.core.groups.domain.Group;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by george on 5/11/16.
 */
@Service
public interface GroupService {
    public Group save(Group g);
    public List get();
    public Group getById(Long id);
    public void delete(Long id);

}
