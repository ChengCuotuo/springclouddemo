package cn.nianzuochen.springcloue.controller;

import cn.nianzuochen.microservice.dao.Dept;
import cn.nianzuochen.springcloue.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;

    @Autowired
    DiscoveryClient client;

    @GetMapping("/find/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.findById(id);
    }

    @GetMapping("list")
    public List<Dept> list() {
        return deptService.findAllDept();
    }

    @PostMapping("add")
    public boolean add(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

//    @Autowired
//    DiscoveryClient client;
    @GetMapping("/discovery")
    public Object discovery() {
        List<String> list = client.getServices();
        System.out.println("*****" + list);
        List<ServiceInstance> servList = client.getInstances("SERVICE-8001");
        for (ServiceInstance element : servList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() +
                    "\t" + element.getUri());
        }

        return client;
    }
}
