package cn.nianzuochen.springcloud.controller;

import cn.nianzuochen.microservice.dao.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("consumer")
public class DeptController_Consumer {
//    private static final String REST_URL_PREFIX = "http://localhost:8001";

    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD";

    @Autowired
    private RestTemplate template;

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept) {
        // template.postForObject(url, requestObject, ResponseBean.class)
        return template.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @GetMapping("/dept/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return template.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @GetMapping("/dept/list")
    public List<Dept> list( ) {
        return template.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }
}
