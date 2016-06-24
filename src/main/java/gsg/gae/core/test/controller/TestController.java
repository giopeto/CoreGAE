package gsg.gae.core.test.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 15.5.2016 г..
 */
@RestController
public class TestController {

    @RequestMapping(
            value="/test",
            method = RequestMethod.POST,
            headers="Accept=application/json",
            produces="application/json"
    )
    public Map<String, String> test (@RequestBody Map<String, String> userParams) {
        Map<String, String> map = new HashMap<String, String>();

        System.out.println(userParams);

        map.put("1", "2");
        map.put("From server", (String)userParams.get("userName"));
        return map;
    }

}
